package com.war.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.common.GameConfig;
import com.war.constant.BuildingConstant;
import com.war.constant.CacheConstant;
import com.war.constant.CityBuildingStateConstant;
import com.war.constant.ConstraintDependTypeConstant;
import com.war.constant.QueueTypeConstant;
import com.war.constant.TechnologyConstant;
import com.war.constant.TreasureCategoryConstant;
import com.war.constant.TreasureTypeConstant;
import com.war.dao.IBuildingDAO;
import com.war.dao.ICityBuildingDAO;
import com.war.dao.IGuildDAO;
import com.war.dao.IGuildExtDAO;
import com.war.dao.IPlayerDAO;
import com.war.domain.Building;
import com.war.domain.City;
import com.war.domain.CityBuilding;
import com.war.domain.CityResource;
import com.war.domain.CityTechnology;
import com.war.domain.ConstraintDepend;
import com.war.domain.Guild;
import com.war.domain.PreBuilding;
import com.war.domain.ProcessQueue;
import com.war.exception.GameException;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IConstraintDependService;
import com.war.service.IProcessQueueService;
import com.war.service.ITechnologyService;
import com.war.service.ITreasureQueueService;
import com.war.util.ConstraintDependUtil;
import com.war.util.ResourceCalculateUtil;

public class BuildingService implements IBuildingService {

	private IBuildingDAO buildingDAO;

	private ICityBuildingDAO cityBuildingDAO;

	private IPlayerDAO playerDAO;
	
	private ICityService cityService;
	
	private IGuildDAO guildDAO;
	
	private IGuildExtDAO guildExtDAO;
	
	private IConstraintDependService constraintDependService;

	private IProcessQueueService processQueueService;
	
	private ITechnologyService technologyService;
	
	private ITreasureQueueService treasureQueueService;
	
	private static Logger logger = Logger.getLogger(BuildingService.class);
	
	private final Lock buildBuildingLock = new ReentrantLock();
	
	private final Lock processBuildUpgradeFinishedLock = new ReentrantLock();
	
	private final Lock backupLock = new ReentrantLock();
	

	public List<Building> initBuildingList() {
		List<Building> buildingList = buildingDAO.getBuildingList();

		for (int i = 0; i < buildingList.size(); i++) {
			Building building = buildingList.get(i);
			buildingList.get(i).setNextConstraintDepend(constraintDependService.getConstraintDependByTypeAndTargetIDAndLevel(ConstraintDependTypeConstant.BUILDING, building.getBuildingID(), 1));
		}

		return buildingList;
	}
	
	public Map<Integer,Building> initBuildingsMap(){
		
		List<Building> buildingList = buildingDAO.getBuildingList();
		
		Map<Integer,Building> buildingsMap = new HashMap<Integer,Building>();
		
		for(int i=0;i<buildingList.size();i++){
			buildingsMap.put(buildingList.get(i).getBuildingID(), buildingList.get(i));
		}
		
		return buildingsMap;
	}
	
	public Map<Integer, Map<Integer, Building>> initBuildingsMapWithConstraintDepend() {

		List<Building> buildingList = buildingDAO.getBuildingList();

		Map<Integer, Map<Integer, Building>> buildingsMap = new HashMap<Integer, Map<Integer, Building>>();

		for(int i=0;i<buildingList.size();i++){
			
			Building building = buildingList.get(i);
			
			Map<Integer, Building> buildingMap = new HashMap<Integer, Building>();
			
			List<ConstraintDepend> constraintDependList = constraintDependService.getConstraintDependListByTypeAndTargetID(ConstraintDependTypeConstant.BUILDING, buildingList.get(i).getBuildingID());

			// 0级建筑约束依赖
			Building level0Building = new Building();
			try {
				BeanUtils.copyProperties(level0Building, building);
			} catch (IllegalAccessException e) {
				logger.error("异常：", e);
			} catch (InvocationTargetException e) {
				logger.error("异常：", e);
			}
			level0Building.setNextConstraintDepend(constraintDependList.get(0));
			buildingMap.put(0, level0Building);
			
			for(int j=0;j<constraintDependList.size();j++){
				
				Building levelBuilding = new Building();
				
				try {
					BeanUtils.copyProperties(levelBuilding, building);
				} catch (IllegalAccessException e) {
					logger.error("异常：", e);
				} catch (InvocationTargetException e) {
					logger.error("异常：", e);
				}
				
				levelBuilding.setConstraintDepend(constraintDependList.get(j));
				if (j<constraintDependList.size()-1) {
					levelBuilding.setNextConstraintDepend(constraintDependList.get(j+1));
				}
				buildingMap.put(constraintDependList.get(j).getLevel(), levelBuilding);
			}
			
			buildingsMap.put(building.getBuildingID(), buildingMap);
		}

		return buildingsMap;
	}
	
	@SuppressWarnings("unchecked")
	public Building getBuildingByBuildingIDAndLevel(Integer buildingID, Integer level) {
		Map buildingsMap = (Map) CacheService.getFromCache(CacheConstant.BUILDINGS_WITH_CONSTRANT_MAP);
		return (Building) ((Map) buildingsMap.get(buildingID)).get(level);
	}
	
	public Integer buildBuilding(Integer cityID,Integer buildingID,Integer position){
		
		try {
			buildBuildingLock.lock();
			
			CityBuilding cityBuilding = cityBuildingDAO.getCityBuildingByPosition(cityID, position);
			
			Building building = this.getBuildingByID(buildingID);
			
			// 升级
			if (cityBuilding!=null) {
				if (cityBuilding.getState()!=CityBuildingStateConstant.NORMAL) {
					throw new GameException("该建筑正在升级或拆除。");
				}
				
				// 最高级的建筑处理
				if (building.getMaxLevel().intValue()==cityBuilding.getLevel().intValue()) {
					throw new GameException(building.getName()+"已达到最高级别。");
				}
			} else {
				// 如果是惟一建筑，判断城内是否已经存在该建筑
				if (building.getIsOnlyone()==1) {
					if (cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(cityID, buildingID)!=null) {
						throw new GameException("城市只能建造一个"+building.getName()+"。");
					}
				}
				
				// 建造
				cityBuilding = new CityBuilding();
				cityBuilding.setCityID(cityID);
				cityBuilding.setBuildingID(buildingID);
				cityBuilding.setLevel(0);
				cityBuilding.setState(CityBuildingStateConstant.BUILDING);
				cityBuilding.setPosition(position);
			}
			
			building = this.getBuildingByBuildingIDAndLevel(cityBuilding.getBuildingID(), cityBuilding.getLevel()+1);
			
			City city = cityService.getCityByID(cityID);
			
			// 建造约束依赖
			ConstraintDepend buildConstraintDepend = building.getConstraintDepend();
			// 检查是否满足约束条件
			this.checkAllConstraintDepend(city, buildConstraintDepend);
			
			CityResource cityResource = cityService.getCityResourceByCityID(cityID);
			
			// 计算建造后的剩余资源
			Map<String,Object> cityResourceParams = ConstraintDependUtil.getDecreaseResourceParams(cityResource, buildConstraintDepend);
			Map<String, Object> cityParams = new HashMap<String, Object>();
			cityParams.put("cityID", cityResourceParams.get("cityID"));
			long populationFree = (city.getPopulationFree() - buildConstraintDepend.getCostPopulation());
			cityParams.put("populationFree", populationFree);
			long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum() + cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(), populationFree, city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
			cityResourceParams.put("moneyOutput", moneyOutput);

			// 扣减相关资源
			cityService.updateCity(cityParams);
			cityService.updateCityResource(cityResourceParams);

			if (cityBuilding.getLevel()==0) {
				// 新建
				cityBuilding.setCityBuildingID(cityBuildingDAO.createCityBuilding(cityBuilding));
			} else {
				// 升级
				CityBuilding cityBuildingUpdate = cityBuildingDAO.getCityBuildingByID(cityBuilding.getCityBuildingID());
				cityBuildingUpdate.setState(CityBuildingStateConstant.BUILDING);
				cityBuildingDAO.updateCityBuilding(cityBuildingUpdate);
			}
			
			// 进程队列
			ProcessQueue processQueue = new ProcessQueue();
			processQueue.setCityID(cityID);
			processQueue.setTargetID(cityBuilding.getCityBuildingID());
			processQueue.setType(QueueTypeConstant.QUEUE_BUILD_UPGRADE);
			Date finishTime = new Date();
			finishTime.setTime(System.currentTimeMillis() + buildConstraintDepend.getCostTime() * 1000);
			processQueue.setStartTime(DateService.getCurrentUtilDate());
			processQueue.setFinishTime(finishTime);
			processQueueService.addProcessQueue(processQueue);
			
			return cityBuilding.getCityBuildingID();
		} finally {
			buildBuildingLock.unlock();
		}
		
	}

	public void buildingFinished(ProcessQueue processQueue){
		switch(processQueue.getType()){
			case QueueTypeConstant.QUEUE_BUILD_UPGRADE: 	
				processBuildUpgradeFinished(processQueue);
				break;
			case QueueTypeConstant.QUEUE_BACKOUT_BUILDING:
				backoutBuilding(processQueue);
				break;
		}
	}
	
	private void processBuildUpgradeFinished(ProcessQueue processQueue){
		
		try {
			//保证同步
			processBuildUpgradeFinishedLock.lock();
			if (processQueueService.getProcessQueueByID(processQueue.getProcessQueueID())==null) {
				return;
			}
			//删除进程队列
			processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());
			
			//获得城市信息
			City city = cityService.getCityByID(processQueue.getCityID());
			CityResource cityResource = cityService.getCityResourceByCityID(processQueue.getCityID());
			
			//获得城市建筑信息
			CityBuilding cityBuilding = cityBuildingDAO.getCityBuildingByID(processQueue.getTargetID());
			Building building = getBuildingByBuildingIDAndLevel(cityBuilding.getBuildingID(),cityBuilding.getLevel()+1);
			//将建造占用的人口归还
			long populationFree = city.getPopulationFree() + building.getConstraintDepend().getCostPopulation();
			
			int buildingID = cityBuilding.getBuildingID();
			int level = cityBuilding.getLevel()+1;
			
			Map<String,Object> cityParams = new HashMap<String, Object>();
			java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
			cityParams.put("cityID", city.getCityID());
			cityResourceParams.put("cityID", city.getCityID());
			cityParams.put("populationFree", populationFree);
			long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),populationFree , city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
			cityResourceParams.put("moneyOutput", moneyOutput);

			//根据建筑编号分别处理建筑完成事件
			switch(buildingID)
			{
				//民房(计算公式：10*n)
				case BuildingConstant.HOURSE:
					long populationMax;
					//没有增加人口上限的效果
					if(treasureQueueService.getTreasureQueueByType(city.getCityID(),TreasureCategoryConstant.NORMAL, TreasureTypeConstant.POPULATION_MAX_ADD) == null){
						populationMax = city.getPopulationMax() + 10 * level;
					}else{
						populationMax =  (long) ((Math.ceil(city.getPopulationMax() * 100 / 110.0) + 10 * level) * 110 / 100);
					}
					cityParams.put("populationMax", populationMax);
					break;
				//仓库(计算公式：7200*n)
				case BuildingConstant.STORAGE:
					int treasureAdd = 0;
					//如果有仓储增加的效果，在计算仓储时加上加成值
					if( treasureQueueService.getTreasureQueueByType(city.getCityID(),TreasureCategoryConstant.NORMAL, TreasureTypeConstant.STORAGE_ADD) != null){
						treasureAdd = 25; // 25%的加成
					}
					
					CityTechnology cityTech = technologyService.getCityTechnologyByCityIDAndTechnologyID(city.getCityID(), TechnologyConstant.OTHER_STORAGE_ADD);
					
					int techAdd = 0;
					if(cityTech != null){
						techAdd = GameConfig.STORAGE_TECH_ADD * cityTech.getLevel();
					}
					
					//获得仓库的基础容量
					long storage = ResourceCalculateUtil.calculateStorageCapacity(level, new int[]{treasureAdd, techAdd});
					cityResourceParams.put("resourceNumMax", storage);
					break;
				//农场(每升一级增加2%食物产量)	
				case BuildingConstant.FARM:
					//计算食物产量
					int foodBuildingAdd = GameConfig.FOOD_OUTPUT_LEVEL_ADD * level;
					long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(),foodBuildingAdd,cityResource.getFoodTechAdd(),cityResource.getFoodOfficerAdd(),cityResource.getFoodGuildAdd(),cityResource.getFoodFieldAdd(),cityResource.getFoodTreasureAdd());
					cityResourceParams.put("foodOutput", foodOutput);
					cityResourceParams.put("foodBuildingAdd", foodBuildingAdd);
					break;
				//伐木场(每升一级增加2%木材产量)	
				case BuildingConstant.LUMBER_MILL:
					//计算木材产量
					int woodBuildingAdd = GameConfig.WOOD_OUTPUT_LEVEL_ADD * level;
					long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(),woodBuildingAdd,cityResource.getWoodTechAdd(),cityResource.getWoodOfficerAdd(),cityResource.getWoodGuildAdd(),cityResource.getWoodFieldAdd(),cityResource.getWoodTreasureAdd());
					cityResourceParams.put("woodOutput", woodOutput);
					cityResourceParams.put("woodBuildingAdd", woodBuildingAdd);
					break;
				//炼钢厂(每升一级增加2%钢铁产量)
				case BuildingConstant.STEEL_PLANT:
					//计算钢铁产量
					int steelBuildingAdd = GameConfig.STEEL_OUTPUT_LEVEL_ADD * level;
					long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(),steelBuildingAdd,cityResource.getSteelTechAdd(),cityResource.getSteelOfficerAdd(),cityResource.getSteelGuildAdd(),cityResource.getSteelFieldAdd(),cityResource.getSteelTreasureAdd());
					cityResourceParams.put("steelOutput", steelOutput);
					cityResourceParams.put("steelBuildingAdd", steelBuildingAdd);
					break;
				//油井(每升一级增加2%石油产量)	
				case BuildingConstant.OIL_WELL:
					//计算石油产量
					int oilBuildingAdd = GameConfig.OIL_OUTPUT_LEVEL_ADD * level;
					long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(),oilBuildingAdd,cityResource.getOilTechAdd(),cityResource.getOilOfficerAdd(),cityResource.getOilGuildAdd(),cityResource.getOilFieldAdd(),cityResource.getOilTreasureAdd());
					cityResourceParams.put("oilOutput", oilOutput);
					cityResourceParams.put("oilBuildingAdd", oilBuildingAdd);
					break;
				//市场
				case BuildingConstant.MARKET:
					//空闲商人数量增加1
					cityParams.put("businessmanFree", city.getBusinessmanFree()+1);
					break;
				//指挥中心
				case BuildingConstant.COMMOND_CENTER:
					
					break;
				// 大使馆
				case BuildingConstant.EMBASSY:
					//如果当前玩家为军团创始人则增加军团人数上限
					Integer guildID = playerDAO.getPlayerByID(city.getPlayerID()).getGuildID();
					if(guildID==null)
						break;
					Guild guild = guildDAO.getGuildByID(guildID);
					if(guild.getChairmanID().intValue() == city.getPlayerID().intValue()){
						guild.setPopulationMax(guild.getPopulationMax()+3);
						guildDAO.updateGuild(guild);
					}
					break;
				default:
					break;
			}
			
			//更新城市建筑点数
			cityParams.put("constructionPoint", building.getConstraintDepend().getCostTime()/100 + city.getConstructionPoint());
			
			//更新城市信息
			cityService.updateCity(cityParams);
			cityService.updateCityResource(cityResourceParams);
			
			//更新玩家声望
			playerDAO.addPlayerRenown(city.getPlayerID(), building.getConstraintDepend().getCostTime()/100);
			
			//更新城市建筑信息
			cityBuilding.setLevel(cityBuilding.getLevel() + 1);
			cityBuilding.setState(CityBuildingStateConstant.NORMAL);
			cityBuildingDAO.updateCityBuilding(cityBuilding);
			
		} catch(Exception e) {
			logger.error("异常：", e);
		} finally {
			processBuildUpgradeFinishedLock.unlock();
		}
	}
	
	private void backoutBuilding(ProcessQueue processQueue){
		
		try {
			//保证同步
			backupLock.lock();
			if(processQueueService.getProcessQueueByID(processQueue.getProcessQueueID()) == null){
				return;
			}
			//删除进程队列
			processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());
		
			CityBuilding cb = this.getCityBuildingByID(processQueue.getTargetID());
			
			// 获得建造建筑编号及等级所对应的建筑
			Building building = this.getBuildingByBuildingIDAndLevel(cb.getBuildingID(), cb.getLevel());
			
			//获得城市信息
			City city = cityService.getCityByID(processQueue.getCityID());
			CityResource cityResource = cityService.getCityResourceByCityID(processQueue.getCityID());
	
			// 得到升级到当前级别需要的条件
			ConstraintDepend buildConstraintDepend = building.getConstraintDepend();
			
			//计算返还后的资源
			Map<String, Object> cityParams = new HashMap<String, Object>();
			Map<String,Object> cityResourceParams = ConstraintDependUtil.getIncreaseHalfResourceParams(cityResource, buildConstraintDepend);
			
			int level = cb.getLevel();
	
			//根据建筑编号分别处理建筑拆除完成事件
			switch(building.getBuildingID())
			{
				//民房(计算公式：10*n)
				case BuildingConstant.HOURSE:
					long populationMax ;
					//没有增加人口上限的效果
					if(treasureQueueService.getTreasureQueueByType(city.getCityID(),TreasureCategoryConstant.NORMAL, TreasureTypeConstant.POPULATION_MAX_ADD) == null){
						populationMax = city.getPopulationMax() - 10 * level;
					}else{
						populationMax =  (long) ((Math.ceil(city.getPopulationMax() * 100 / 110.0) - 10 * level) * 110 / 100);
					}
					cityParams.put("populationMax", populationMax);
					break;
				//仓库(计算公式：7200*n)
				case BuildingConstant.STORAGE:
					int treasureAdd = 0;
					//如果有仓储增加的效果，在计算仓储时加上加成值
					if( treasureQueueService.getTreasureQueueByType(city.getCityID(),TreasureCategoryConstant.NORMAL, TreasureTypeConstant.STORAGE_ADD) != null){
						treasureAdd = 25;// 25%的加成
					}
					
					int techAdd = 0;
					CityTechnology cityTech = technologyService.getCityTechnologyByCityIDAndTechnologyID(city.getCityID(), TechnologyConstant.OTHER_STORAGE_ADD);
					
					if(cityTech != null){
						techAdd = GameConfig.STORAGE_TECH_ADD * cityTech.getLevel();
					}
					
					//获得仓库的基础容量
					long storage = ResourceCalculateUtil.calculateStorageCapacity(level, new int[]{treasureAdd, techAdd});
					cityResourceParams.put("resourceNumMax", storage);
					break;
				//伐木场(每升一级增加2%木材产量)	
				case BuildingConstant.LUMBER_MILL:
					//计算木材产量
					int woodBuildingAdd = GameConfig.WOOD_OUTPUT_LEVEL_ADD * (level-1);
					if(level-1==0){
						cityResourceParams.put("woodWorkerNum", 0);
						cityParams.put("populationFree", city.getPopulationFree()+cityResource.getWoodWorkerNum());
						cityResource.setWoodWorkerNum(0);
					}
					long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(),woodBuildingAdd,cityResource.getWoodTechAdd(),cityResource.getWoodOfficerAdd(),cityResource.getWoodGuildAdd(),cityResource.getWoodFieldAdd(),cityResource.getWoodTreasureAdd());
					cityResourceParams.put("woodOutput", woodOutput);
					cityResourceParams.put("woodBuildingAdd", woodBuildingAdd);
					break;
				//炼钢厂(每升一级增加2%钢铁产量)
				case BuildingConstant.STEEL_PLANT:
					//计算钢铁产量
					int steelBuildingAdd = GameConfig.STEEL_OUTPUT_LEVEL_ADD * (level-1);
					if(level-1==0){
						cityResourceParams.put("steelWorkerNum", 0);
						cityParams.put("populationFree", city.getPopulationFree()+cityResource.getSteelWorkerNum());
						cityResource.setSteelWorkerNum(0);
					}
					long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(),steelBuildingAdd,cityResource.getSteelTechAdd(),cityResource.getSteelOfficerAdd(),cityResource.getSteelGuildAdd(),cityResource.getSteelFieldAdd(),cityResource.getSteelTreasureAdd());
					cityResourceParams.put("steelOutput", steelOutput);
					cityResourceParams.put("steelBuildingAdd", steelBuildingAdd);
					break;
				//油井(每升一级增加2%石油产量)	
				case BuildingConstant.OIL_WELL:
					//计算石油产量
					int oilBuildingAdd = GameConfig.OIL_OUTPUT_LEVEL_ADD * (level-1);
					if(level-1==0){
						cityResourceParams.put("oilWorkerNum", 0);
						cityParams.put("populationFree", city.getPopulationFree()+cityResource.getOilWorkerNum());
						cityResource.setOilWorkerNum(0);
					}
					long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(),oilBuildingAdd,cityResource.getOilTechAdd(),cityResource.getOilOfficerAdd(),cityResource.getOilGuildAdd(),cityResource.getOilFieldAdd(),cityResource.getOilTreasureAdd());
					cityResourceParams.put("oilOutput", oilOutput);
					cityResourceParams.put("oilBuildingAdd", oilBuildingAdd);
					break;
				//农场(每升一级增加2%食物产量)	
				case BuildingConstant.FARM:
					//计算食物产量
					int foodBuildingAdd = GameConfig.FOOD_OUTPUT_LEVEL_ADD * (level-1);
					if(level-1==0){
						cityResourceParams.put("foodWorkerNum", 0);
						cityParams.put("populationFree", city.getPopulationFree()+cityResource.getFoodWorkerNum());
						cityResource.setFoodWorkerNum(0);
					}
					long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(),foodBuildingAdd,cityResource.getFoodTechAdd(),cityResource.getFoodOfficerAdd(),cityResource.getFoodGuildAdd(),cityResource.getFoodFieldAdd(),cityResource.getFoodTreasureAdd());
					cityResourceParams.put("foodOutput", foodOutput);
					cityResourceParams.put("foodBuildingAdd", foodBuildingAdd);
					break;
				//市场
				case BuildingConstant.MARKET:
					//空闲商人数量减1
					cityParams.put("businessmanFree", Math.max(city.getBusinessmanFree()-1,0));
					break;
				//指挥中心
				case BuildingConstant.COMMOND_CENTER:
					
					break;
				// 大使馆
				case BuildingConstant.EMBASSY:
					//如果当前玩家为军团创始人则减少军团人数上限
					Integer guildID = playerDAO.getPlayerByID(city.getPlayerID()).getGuildID();
					if(guildID==null)
						break;
					Guild guild = guildDAO.getGuildByID(guildID);
					if(guild.getChairmanID().intValue() == city.getPlayerID().intValue()){
						guild.setPopulationMax(guild.getPopulationMax()-3);
						guildDAO.updateGuild(guild);
					}
					break;
				default:
					break;
			}
			
			// 扣减相关资源
			if (cityParams.size() > 0) {
				cityParams.put("cityID", cityResourceParams.get("cityID"));
				cityService.updateCity(cityParams);
			}
			cityService.updateCityResource(cityResourceParams);
			
			level -= 1;
			
			//如果还没有到0级.更新建筑信息
			if(level>0){
				cb.setLevel(level);
				cb.setState(CityBuildingStateConstant.NORMAL);//设置状态为正常
				
				this.cityBuildingDAO.updateCityBuilding(cb);
			}else{
				//删除建筑
				this.cityBuildingDAO.deleteCityBuildingByID(cb.getCityBuildingID());
			}
			
		} catch (Exception e) {
			logger.error("异常：", e);
		} finally {
			backupLock.unlock();
		}
	}

	public List<Building> getCityAvailableBuildingList(Integer cityID) {

		List<Building> buildingList = this.getBuildingList();

		List<CityBuilding> cityBuildingList = cityBuildingDAO
				.getCityBuildingListByCityID(cityID);

		List<Building> availableBuildingList = new ArrayList<Building>();

		for (int i = 0; i < buildingList.size(); i++) {

			Building building = buildingList.get(i);

			if (building.getIsOnlyone() == 2) {
				// 当前建筑为非唯一建筑，则添加至有效建筑列表中
				availableBuildingList.add(building);
			} else {
				int illegal = 0;
				for (int j = 0; j < cityBuildingList.size(); j++) {
					if (building.getBuildingID().intValue() == cityBuildingList
							.get(j).getBuildingID().intValue()) {
						// 当前城市已建设或正在建设buildingList中第i个建筑
						illegal = 1;
						break;
					}

				}
				// 如果当前城市已建建筑和建筑队列均没有当前建筑，则添加至可用建造列表中
				if (illegal == 0) {
					availableBuildingList.add(building);
				}
			}
		}

		return availableBuildingList;
	}
	
	public Building getLevel0BuildingByID(Integer buildingID) {
		return this.getBuildingByBuildingIDAndLevel(buildingID, 0); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Building> getBuildingList() {
		return (List<Building>)CacheService.getFromCache(CacheConstant.BUILDING_LIST);
	}


	public CityBuilding getCityBuildingByID(Integer cityBuildingID) {
		CityBuilding cb = cityBuildingDAO.getCityBuildingByID(cityBuildingID);
		if(cb != null){
			setCityBuildingData(cb);
			return cb;
		}
		return null;
	}
	
	public List<CityBuilding> getCityBuildingListByCityID(Integer cityID) {
		
		List<CityBuilding> cityBuildingList = cityBuildingDAO.getCityBuildingListByCityID(cityID);
		
		CityBuilding cb;
		for (int i=0; i<cityBuildingList.size(); i++) {
			cb = cityBuildingList.get(i);
			setCityBuildingData(cb);
		}
		return cityBuildingList;
	}

	private void setCityBuildingData(CityBuilding cb){
		
		cb.setBuilding(this.getBuildingByBuildingIDAndLevel(cb.getBuildingID(), cb.getLevel()));
		
		switch (cb.getState()) {
			//升级中
			case CityBuildingStateConstant.BUILDING:
				cb.setProcessQueue(this.processQueueService.getProcessQueue(cb.getCityID(), cb.getCityBuildingID(),QueueTypeConstant.QUEUE_BUILD_UPGRADE));
				break;
			//拆除中
			case CityBuildingStateConstant.DESTROYING:
				cb.setProcessQueue(this.processQueueService.getProcessQueue(cb.getCityID(), cb.getCityBuildingID(),QueueTypeConstant.QUEUE_BACKOUT_BUILDING));
				break;
		}
	}

	public void cancelProcess(Integer processQueueID) {
		
		ProcessQueue pq = this.processQueueService.getProcessQueueByID(processQueueID);
		if (pq==null) {
			return;
		}
			
		switch (pq.getType()) {
			//如果时取消建筑升级或建造进程
			case QueueTypeConstant.QUEUE_BUILD_UPGRADE:
				CityBuilding cb = this.getCityBuildingByID(pq.getTargetID());
				// 获得建造建筑编号及等级所对应的建筑
				Building building = this.getBuildingByBuildingIDAndLevel(cb.getBuildingID(), cb.getLevel()+1);
				//获得城市信息
				City city = cityService.getCityByID(pq.getCityID());
				CityResource cityResource = cityService.getCityResourceByCityID(pq.getCityID());
				ConstraintDepend buildConstraintDepend = building.getConstraintDepend();
				//计算返还后的资源
				Map<String, Object> cityParams = new HashMap<String, Object>();
				Map<String,Object> cityResourceParams = ConstraintDependUtil.getIncreaseHalfResourceParams(cityResource, buildConstraintDepend);
				cityParams.put("cityID", cityResourceParams.get("cityID"));
				//将建造占用的人口归还，重新计算空闲人口(保证人口不能超过人口上限)
				long populationFree = city.getPopulationFree() + buildConstraintDepend.getCostPopulation();
				cityParams.put("populationFree", populationFree);
				long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),populationFree ,
						city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
				cityResourceParams.put("moneyOutput", moneyOutput);
				cityService.updateCity(cityParams);
				cityService.updateCityResource(cityResourceParams);
				break;
			//如果时取消拆除进程
			case QueueTypeConstant.QUEUE_BACKOUT_BUILDING:
				break;
		}
		
		this.processQueueService.deleteProcessQueueByID(processQueueID);
		CityBuilding cb = this.cityBuildingDAO.getCityBuildingByID(pq.getTargetID());
		//如果取消建造过程，就把该建筑删除
		if(cb.getLevel() == 0){
			this.cityBuildingDAO.deleteCityBuildingByID(cb.getCityBuildingID());
		}else{
			cb.setState(CityBuildingStateConstant.NORMAL);
			this.cityBuildingDAO.updateCityBuilding(cb);
		}
			
	}

	public void backoutBuilding(Integer cityBuildingID) {
		
		CityBuilding cb = this.getCityBuildingByID(cityBuildingID);
		
		if (cb.getState()!=CityBuildingStateConstant.NORMAL) {
			throw new GameException("该建筑正在升级或拆除。");
		}
		
		// 进程队列
		ProcessQueue processQueue = new ProcessQueue();
		processQueue.setCityID(cb.getCityID());
		processQueue.setTargetID(cityBuildingID);
		processQueue.setType(QueueTypeConstant.QUEUE_BACKOUT_BUILDING);
		// 完成时间
		Date finishTime = new Date();
		finishTime.setTime(System.currentTimeMillis() + (cb.getBuilding().getConstraintDepend().getCostTime()/2) * 1000);
		processQueue.setStartTime(DateService.getCurrentUtilDate());
		processQueue.setFinishTime(finishTime);
		processQueueService.addProcessQueue(processQueue);
		// 拆除标记
		cb.setState(CityBuildingStateConstant.DESTROYING);
		this.cityBuildingDAO.updateCityBuilding(cb);
	}

	public void clientProcessFinished(Integer processQueueID) {
		ProcessQueue pq = processQueueService.getProcessQueueByID(processQueueID);
		//已经被处理
		if(pq == null)
			return;
		
		//进程结束时间和当前时间的间隔毫秒数
		long clips = pq.getFinishTime().getTime()-(new Date()).getTime();
		
		//如果过了完成时间就对该进程进行处理
		if(clips<=0){
			this.buildingFinished(pq);
		}
	}
	
	private int getBuildingMaxLevel(List<CityBuilding> cityBuildingList,int buildingID){
		CityBuilding cityBuilding;

		int level = -1;
		
		for(int j=0;j<cityBuildingList.size();j++){
			cityBuilding = cityBuildingList.get(j);
			//前提建造和城市建筑一致
			if(cityBuilding.getBuildingID().intValue() == buildingID){
				level = Math.max(level, cityBuilding.getLevel());
			}
		}
		
		return level;
	}
	
	public void checkPreBuildingAndTech(Integer cityID,ConstraintDepend constraintDepend){
		// 前提建筑
		List<PreBuilding> preBuildingList = constraintDepend.getPreBuildingList();
		
		// 有前提建筑约束
		if(preBuildingList != null && !preBuildingList.isEmpty()){
			// 城内建筑
			List<CityBuilding> cityBuildingList = cityBuildingDAO.getCityBuildingListByCityID(cityID);
			
			PreBuilding preBuilding;
			int maxLevel ;
			
			for(int i=0;i<preBuildingList.size();i++){
				preBuilding = preBuildingList.get(i);
				
				maxLevel = getBuildingMaxLevel(cityBuildingList,preBuilding.getBuildingID());
				if(maxLevel < preBuilding.getLevel()){
					//用户未达到约束条件所需要建筑及等级，返回
					throw new GameException("未满足前提建筑条件。");
				}
			}
		}
	}
	
	public void checkResources(City city,ConstraintDepend constraintDepend){
		checkResources(city,constraintDepend,1);
	}
	
	public void checkResources(City city,ConstraintDepend constraintDepend,int num){
		
		CityResource cityResource = cityService.getCityResourceByCityID(city.getCityID());
		//资源检查
		if(city.getPopulationFree() < constraintDepend.getCostPopulation() * num){
			throw new GameException("城市空闲人口不足");
		}
		if (cityResource.getWoodNum() < constraintDepend.getCostWood() * num ){
			throw new GameException("城市木材不足");
		}
		if (cityResource.getSteelNum() < constraintDepend.getCostSteel() * num) {
			throw new GameException("城市钢铁不足");
		}
		if (cityResource.getOilNum() < constraintDepend.getCostOil() * num) {
			throw new GameException("城市石油不足");
		}
		if (cityResource.getFoodNum() < constraintDepend.getCostFood() * num) {
			throw new GameException("城市食物不足");
		}
		if (cityResource.getMoneyNum() < constraintDepend.getCostMoney() * num) {
			throw new GameException("城市金钱不足");
		}
	}
	
	public void checkAllConstraintDepend(City city,ConstraintDepend constraintDepend){
		checkAllConstraintDepend(city,constraintDepend,1);
	}
	
	public void checkAllConstraintDepend(City city,ConstraintDepend constraintDepend,int num){
		checkPreBuildingAndTech(city.getCityID(),constraintDepend);
		checkResources(city,constraintDepend,num);
	}
	
	public CityBuilding getCityBuilding(Integer cityID,Integer buildingID){
		return cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(cityID, buildingID);
	}
	
	@SuppressWarnings("unchecked")
	public Building getBuildingByID(Integer buildingID) {
		return ((Map<Integer, Building>)CacheService.getFromCache(CacheConstant.BUILDINGS_MAP)).get(buildingID);
	}
	
	
	public IBuildingDAO getBuildingDAO() {
		return buildingDAO;
	}

	public void setBuildingDAO(IBuildingDAO buildingDAO) {
		this.buildingDAO = buildingDAO;
	}

	public ICityBuildingDAO getCityBuildingDAO() {
		return cityBuildingDAO;
	}

	public void setCityBuildingDAO(ICityBuildingDAO cityBuildingDAO) {
		this.cityBuildingDAO = cityBuildingDAO;
	}

	public IProcessQueueService getProcessQueueService() {
		return processQueueService;
	}

	public void setProcessQueueService(IProcessQueueService processQueueService) {
		this.processQueueService = processQueueService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}
	
	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IGuildDAO getGuildDAO() {
		return guildDAO;
	}

	public void setGuildDAO(IGuildDAO guildDAO) {
		this.guildDAO = guildDAO;
	}
	
	public IGuildExtDAO getGuildExtDAO() {
		return guildExtDAO;
	}

	public void setGuildExtDAO(IGuildExtDAO guildExtDAO) {
		this.guildExtDAO = guildExtDAO;
	}

	public IConstraintDependService getConstraintDependService() {
		return constraintDependService;
	}

	public void setConstraintDependService(
			IConstraintDependService constraintDependService) {
		this.constraintDependService = constraintDependService;
	}
	
	public ITechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(ITechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	public ITreasureQueueService getTreasureQueueService() {
		return treasureQueueService;
	}

	public void setTreasureQueueService(ITreasureQueueService treasureQueueService) {
		this.treasureQueueService = treasureQueueService;
	}
}