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

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.common.GameConfig;
import com.war.constant.BuildingConstant;
import com.war.constant.CacheConstant;
import com.war.constant.CityTechnologyStateConstant;
import com.war.constant.QueueTypeConstant;
import com.war.constant.TechnologyConstant;
import com.war.constant.TreasureCategoryConstant;
import com.war.constant.TreasureTypeConstant;
import com.war.dao.ICityBuildingDAO;
import com.war.dao.ICityTechnologyDAO;
import com.war.dao.IPlayerDAO;
import com.war.dao.ITechnologyDAO;
import com.war.domain.City;
import com.war.domain.CityBuilding;
import com.war.domain.CityResource;
import com.war.domain.CityTechnology;
import com.war.domain.ConstraintDepend;
import com.war.domain.ProcessQueue;
import com.war.domain.Technology;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.IConstraintDependService;
import com.war.service.IProcessQueueService;
import com.war.service.ITechnologyService;
import com.war.service.ITreasureQueueService;
import com.war.util.ResourceCalculateUtil;

public class TechnologyService implements ITechnologyService {

	private ITechnologyDAO technologyDAO;
	
	private ICityBuildingDAO cityBuildingDAO;
	
	private ICityTechnologyDAO cityTechnologyDAO;
	
	private IPlayerDAO playerDAO;
	
	private ICityService cityService;
	
	private IConstraintDependService constraintDependService;
	
	private IProcessQueueService processQueueService;
	
	private ITreasureQueueService treasureQueueService;
	
	private DataSourceTransactionManager transactionManager;
	
	private static Logger logger = Logger.getLogger(TechnologyService.class);
	
	private final Lock researchTechnologyLock = new ReentrantLock();
	
	private final Lock researchFinishedLock = new ReentrantLock();
	
	public Map<Integer, Technology> initTechnologiesMap() {
		
		List<Technology> technologyList = technologyDAO.getTechnologyList();
		
		Map<Integer,Technology> technologyMap = new HashMap<Integer,Technology>();
		
		for(int i=0;i<technologyList.size();i++){
			technologyMap.put(technologyList.get(i).getTechnologyID(), technologyList.get(i));
		}
		
		return technologyMap;
	}
	
	public Map<Integer,List<Technology>> initTechnologiesListByType(){
		
		Map<Integer,List<Technology>> technologyMap = new HashMap<Integer,List<Technology>>();
		
		int type = 1;
		
		List<Technology> technologyList;
		
		while((technologyList=technologyDAO.getTechnologyListByType(type)).size()!=0){
			technologyMap.put(type, technologyList);
			type++;
		}
		
		return technologyMap;
	}
	
	public Map<Integer,Map<Integer,Technology>> initTechnologiesMapWithConstraintDepend(){
		
		List<Technology> technologoList = technologyDAO.getTechnologyList();
		
		Map<Integer,Map<Integer,Technology>> technologiesMap = new HashMap<Integer,Map<Integer,Technology>>();
		
		for(int i=0;i<technologoList.size();i++){
			
			Technology technologoy = technologoList.get(i);
			
			Map<Integer,Technology> technologyMap = new HashMap<Integer,Technology>();
			
			List<ConstraintDepend> constraintDependList = constraintDependService.getConstraintDependListByTypeAndTargetID(2, technologoList.get(i).getTechnologyID());
			
			for(int j=0;j<constraintDependList.size();j++){
				
				Technology levelTechnology = new Technology();
				try {
					BeanUtils.copyProperties(levelTechnology, technologoy);
				} catch (IllegalAccessException e) {
					logger.error("异常：", e);
				} catch (InvocationTargetException e) {
					logger.error("异常：", e);
				}
				
				levelTechnology.setConstraintDepend(constraintDependList.get(j));
				technologyMap.put(constraintDependList.get(j).getLevel(), levelTechnology);
				
			}
			
			technologiesMap.put(technologoy.getTechnologyID(), technologyMap);
		}
		
		return technologiesMap;
	}
	
	@SuppressWarnings("unchecked")
	public Technology getTechnologyByID(Integer technologyID){
		return (Technology)((Map)CacheService.getFromCache(CacheConstant.TECHNOLOGIES_MAP)).get(technologyID);
	}
	
	@SuppressWarnings("unchecked")
	public Technology getTechnologyByIDAndLevel(Integer technologyID,Integer level){
		
		Map technologiesMap = (Map)CacheService.getFromCache(CacheConstant.TECHNOLOGIES_WITH_CONSTRANT_MAP);
		
		Technology technology = (Technology)((Map)technologiesMap.get(technologyID)).get(level);
		
		return technology;
	}
	
	@SuppressWarnings("unchecked")
	public List<Technology> getTechnologyListByType(Integer type){
		return ((Map<Integer,List<Technology>>)CacheService.getFromCache(CacheConstant.TECHNOLOGIES_LIST_BY_TYPE)).get(type);
	}
	
	public void researchTechnology(Integer cityID, Integer technologyID) {
		
		try {
			researchTechnologyLock.lock();
			
			// 判断是否已存在科技研究
			if (processQueueService.getProcessQueue(cityID, QueueTypeConstant.QUEUE_TECH_UPGRADE)!=null) {
				throw new GameException("已有科技正在研究。");
			}
			
			CityTechnology cityTechnology = cityTechnologyDAO.getCityTechnologyByCityIDAndTechnologyID(cityID, technologyID);
			
			Integer cityTechnologyID = 0;
			
			Technology technology = null;
			if(cityTechnology == null){
				// 城市科技表中不存在对应科技信息
				technology = this.getTechnologyByIDAndLevel(technologyID, 1);
			}else{
				//将城市科技编号赋值
				cityTechnologyID = cityTechnology.getCityTechnologyID();
				technology = this.getTechnologyByIDAndLevel(technologyID, cityTechnology.getLevel()+1);
			}
			
			if (cityTechnology!=null && cityTechnology.getLevel()>=technology.getMaxLevel()) {
				throw new GameException(technology.getName() + "已达到最高级别。");
			}
			
			// 判断是否达到前提条件
			if(technology.getConstraintDepend().getLevel()>cityBuildingDAO.getCityBuildingLevelByCityIDAndBuildingID(cityID, BuildingConstant.TECHNOLOGY_CENTER)){
				throw new GameException("科技中心等级不足。");
			}
			
			// 扣减相关资源
			cityService.minusCityResources(cityID, 0L, 0L, 0L, 0L, technology.getConstraintDepend().getCostMoney());
			
			// 添加/修改城市科技信息
			if (cityTechnology == null) {
				// 当前城市未研究此科技
				CityTechnology newCityTechnology = new CityTechnology();
				newCityTechnology.setCityID(cityID);
				newCityTechnology.setTechnologyID(technologyID);
				newCityTechnology.setLevel(0);
				// 设置状态为研究中
				newCityTechnology.setState(CityTechnologyStateConstant.UPGRADE);
				// 将城市科技并将编号赋值
				cityTechnologyID = cityTechnologyDAO.createCityTechnology(newCityTechnology);
				
			} else {
				// 更新城市科技状态为研究中
				cityTechnologyDAO.updateCityTechnologyState(cityTechnology.getCityTechnologyID(), CityTechnologyStateConstant.UPGRADE);
			}
			
			ProcessQueue processQueue = new ProcessQueue();
			processQueue.setCityID(cityID);
			processQueue.setTargetID(cityTechnologyID);
			processQueue.setType(QueueTypeConstant.QUEUE_TECH_UPGRADE);
			
			// 计算完成时间
			processQueue.setStartTime(DateService.getCurrentUtilDate());
			Date finishTime = new Date();
			finishTime.setTime(System.currentTimeMillis() + technology.getConstraintDepend().getCostTime() * 1000);
			processQueue.setFinishTime(finishTime);
			processQueueService.addProcessQueue(processQueue);
			
		} finally {
			researchTechnologyLock.unlock();
		}
		
	}

	public void cancelResearchTechnology(Integer cityID) {
		
		CityTechnology cityTechnology = cityTechnologyDAO.getCityTechnologyByCityIDAndState(cityID, CityTechnologyStateConstant.UPGRADE);
		
		if(cityTechnology == null){
			throw new GameException("当前没有正在研究的科技。");
		}
		
		if(cityTechnology.getLevel()==0){
			// 如果是0级则删除当前城市科技
			cityTechnologyDAO.deleteCityTechnologyByID(cityTechnology.getCityTechnologyID());
		}else{
			// 不为0级则更新状态为正常
			// 更新城市科技状态为正常
			cityTechnologyDAO.updateCityTechnologyState(cityTechnology.getCityTechnologyID(), CityTechnologyStateConstant.NORMAL);
		}
		
		//获得进程队列并删除
		ProcessQueue processQueue = processQueueService.getProcessQueue(cityID, cityTechnology.getCityTechnologyID(), QueueTypeConstant.QUEUE_TECH_UPGRADE);
		processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());
	}

	public void researchFinished(ProcessQueue processQueue) {
		
		try {
			//保证后面的操作是同步的
			researchFinishedLock.lock();
			
			if(processQueueService.getProcessQueueByID(processQueue.getProcessQueueID()) == null){
				return;
			}
			
			//删除进程队列
			processQueueService.deleteProcessQueueByID(processQueue.getProcessQueueID());

			CityTechnology cityTechnology = cityTechnologyDAO.getCityTechnologyByID(processQueue.getTargetID());
			
			cityTechnology.setLevel(cityTechnology.getLevel()+1);
			//设置城市科技为正常
			cityTechnology.setState(CityTechnologyStateConstant.NORMAL);
			//更新科技
			cityTechnologyDAO.updateCityTechnology(cityTechnology);
			
			Technology technology = this.getTechnologyByIDAndLevel(cityTechnology.getTechnologyID(), cityTechnology.getLevel());
			
			City city = cityService.getCityByID(cityTechnology.getCityID());
			CityResource cityResource = cityService.getCityResourceByCityID(cityTechnology.getCityID());
			int level = cityTechnology.getLevel();
			
			Map<String,Object> cityParams = new HashMap<String,Object>();
			Map<String, Object> cityResourceParams = new HashMap<String, Object>();
			Map<String,Object> cityExtParams = new HashMap<String,Object>();
			
			cityParams.put("cityID", city.getCityID());
			
			switch(cityTechnology.getTechnologyID()){
				//增加食物产量(农耕机械)
				case TechnologyConstant.FOOD_OUTPUT_ADD:
					
					int foodTechAdd = GameConfig.FOOD_TECH_ADD*level;
					
					cityResourceParams.put("foodOutput", ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), foodTechAdd, cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd()));
					cityResourceParams.put("foodTechAdd", foodTechAdd);
					
					break;
				//增加木材产量(工业砍伐)
				case TechnologyConstant.WOOD_OUTPUT_ADD:
					
					int woodTechAdd = GameConfig.WOOD_TECH_ADD*level;
					
					cityResourceParams.put("woodOutput", ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), woodTechAdd, cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd()));
					cityResourceParams.put("woodTechAdd", woodTechAdd);
					
					break;
				//增加钢铁产量(高炉炼钢)
				case TechnologyConstant.STEEL_OUTPUT_ADD:
					
					int steelTechAdd = GameConfig.WOOD_TECH_ADD*level;
					
					cityResourceParams.put("steelOutput", ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), steelTechAdd, cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd()));
					cityResourceParams.put("steelTechAdd", steelTechAdd);
					
					break;
				//增加石油产量(深层开采)
				case TechnologyConstant.OIL_OUTPUT_ADD:
					
					int oilTechAdd = GameConfig.WOOD_TECH_ADD*level;
					
					cityResourceParams.put("oilOutput", ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), oilTechAdd, cityResource.getOilOfficerAdd(), cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd()));
					cityResourceParams.put("oilTechAdd", oilTechAdd);
					
					break;
				case TechnologyConstant.ARMY_SPEED_ADD:
					cityExtParams.put("techArmySpeed", level/TechnologyConstant.TECH_ARMY_SPEED_ADD_MULTIPLE);
					break;
				case TechnologyConstant.ARMY_ATT_ADD:
					cityExtParams.put("techArmyAttack", level/TechnologyConstant.TECH_ARMY_ATTACK_ADD_MULTIPLE);
					break;
				case TechnologyConstant.ARMY_DEF_ADD:
					cityExtParams.put("techArmyDefense", level/TechnologyConstant.TECH_ARMY_DEFENSE_ADD_MULTIPLE);
					break;
				case TechnologyConstant.ARMY_RANGE_ADD:
					cityExtParams.put("techArmyRange", level/TechnologyConstant.TECH_ARMY_RANGE_ADD_MULTIPLE);
					break;
				case TechnologyConstant.TRUCK_SPEED_ADD:
					cityExtParams.put("techTruckSpeed", level/TechnologyConstant.TECH_TRUCK_SPEED_ADD_MULTIPLE);
					break;
				case TechnologyConstant.TRUCK_ATT_ADD:
					cityExtParams.put("techTruckAttack", level/TechnologyConstant.TECH_TRUCK_ATTACK_ADD_MULTIPLE);
					break;
				case TechnologyConstant.TRUCK_DEF_ADD:
					cityExtParams.put("techTruckDefense", level/TechnologyConstant.TECH_TRUCK_DEFENSE_ADD_MULTIPLE);
					break;
				case TechnologyConstant.TRUCK_RANGE_ADD:
					cityExtParams.put("techTruckRange", level/TechnologyConstant.TECH_TRUCK_RANGE_ADD_MULTIPLE);
					break;
				case TechnologyConstant.AIRPLANE_SPEED_ADD:
					cityExtParams.put("techAirplaneSpeed", level/TechnologyConstant.TECH_AIRPLANE_SPEED_ADD_MULTIPLE);
					break;
				case TechnologyConstant.AIRPLANE_ATT_ADD:
					cityExtParams.put("techAirplaneAttack", level/TechnologyConstant.TECH_AIRPLANE_ATTACK_ADD_MULTIPLE);
					break;
				case TechnologyConstant.AIRPLANE_DEF_ADD:
					cityExtParams.put("techAirplaneDefense", level/TechnologyConstant.TECH_AIRPLANE_DEFENSE_ADD_MULTIPLE);
					break;
				case TechnologyConstant.AIRPLANE_RANGE_ADD:
					cityExtParams.put("techAirplaneRange", level/TechnologyConstant.TECH_AIRPLANE_RANGE_ADD_MULTIPLE);
					break;
				//提升仓库最大容量(仓储管理)
				case TechnologyConstant.OTHER_STORAGE_ADD:
					int treasureAdd = 0;
					//如果有仓储增加的效果，在计算仓储时加上加成值
					if( treasureQueueService.getTreasureQueueByType(city.getCityID(),TreasureCategoryConstant.NORMAL, TreasureTypeConstant.STORAGE_ADD) != null){
						treasureAdd = 25; // 25%的加成
					}
					// 当前等级科技加成
					int techAdd = GameConfig.STORAGE_TECH_ADD * level;
					
					int buildingLevel = 0;
					CityBuilding cityBuilding = cityBuildingDAO.getCityBuildingByCityIDAndBuildingID(city.getCityID(), BuildingConstant.STORAGE);
					if (cityBuilding!=null) {
						buildingLevel = cityBuilding.getLevel();
					}
					long storage = ResourceCalculateUtil.calculateStorageCapacity(buildingLevel, new int[]{treasureAdd, techAdd});
					cityResourceParams.put("resourceNumMax", storage);
					break;
				case TechnologyConstant.OTHER_CARRY_ADD:
					cityExtParams.put("techCarryAdd", level*TechnologyConstant.TECH_ADD_CARRY);
					break;
				case TechnologyConstant.OTHER_SPY_ADD:
					break;
				case TechnologyConstant.OTHER_DEFENSE_ADD:
					cityExtParams.put("techDefenseAttackAdd", level/TechnologyConstant.TECH_ADD_DEFENSE_ATTACK_MULTIPLE);
					break;
				case TechnologyConstant.MILITARY_WOUNDED_TRANSFORM_RATE_ADD:
					cityExtParams.put("techWoundedArmyRate", level);
					break;
				case TechnologyConstant.RESOURCES_PROTECT:
					cityExtParams.put("techProtectResourcePercent", level);
					break;
			}
			
			// 更新城市科技点数
			cityParams.put("technologyPoint", technology.getConstraintDepend().getCostTime()/20 + city.getTechnologyPoint());
			
			// 更新城市信息
			cityService.updateCity(cityParams);
			// 如果城市资源有更新，则更新更新资源
			if (cityResourceParams.size()>0) {
				cityResourceParams.put("cityID", city.getCityID());
				cityService.updateCityResource(cityResourceParams);
			}

			// 如果cityExtParams中有值，则更新城市扩展信息
			if(cityExtParams.size()>0){
				// 更新城市扩展信息
				cityExtParams.put("cityID", cityTechnology.getCityID());
				cityService.updateCityExt(cityExtParams);
			}
			
			// 更新玩家声望
			playerDAO.addPlayerRenown(city.getPlayerID(), technology.getConstraintDepend().getCostTime()/20);
			
		} catch (Exception e) {
			logger.error("异常：", e);
		} finally {
			researchFinishedLock.unlock();
		}
	}
	
	public Map<String,Object> getResearchingTechnology(Integer cityID){
		
		CityTechnology researchingTechnology = cityTechnologyDAO.getCityTechnologyByCityIDAndState(cityID, 2);
		
		if(researchingTechnology==null){
			return null;
		}else{
			researchingTechnology.setTechnology(getTechnologyByID(researchingTechnology.getTechnologyID()));
			ProcessQueue processQueue = processQueueService.getProcessQueue(cityID, QueueTypeConstant.QUEUE_TECH_UPGRADE);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("researchingTechnology", researchingTechnology);
			map.put("processQueue", processQueue);
			
			return map;
		}
		
	}
	
	public Map<Integer,CityTechnology> getCityTechnologyMap(Integer cityID){
		List<CityTechnology> cityTechnologyList = cityTechnologyDAO.getCityTechnologyListByCityID(cityID);
		
		Map<Integer,CityTechnology> cityTechnologyMap = new HashMap<Integer,CityTechnology>();
		for(int i=0;i<cityTechnologyList.size();i++){
			cityTechnologyMap.put(cityTechnologyList.get(i).getTechnologyID(), cityTechnologyList.get(i));
		}
		
		return cityTechnologyMap;
	}
	
	public List<CityTechnology> getCityTechnologyListByType(Integer cityID,Integer type) {
		
		List<Technology> technologyList = this.getTechnologyListByType(type);
		
		List<CityTechnology> cityTechnologyList = cityTechnologyDAO.getCityTechnologyListByCityID(cityID);
		
		List<CityTechnology> initedCityTechnologyList = new ArrayList<CityTechnology>();
		
		//是否研究当前科技
		int haveResearchTechnology = 0;
		
		for(int i=0;i<technologyList.size();i++){
			
			haveResearchTechnology = 0;
			//当前科技
			Technology currentTechnology = technologyList.get(i);
			//当前城市科技 下面代码初始化对象
			CityTechnology currentCityTechnology = null;
			
			for(int j=0;j<cityTechnologyList.size();j++){
				if(currentTechnology.getTechnologyID().intValue()==cityTechnologyList.get(j).getTechnologyID().intValue()){
					//已找到当前科技所对应城市科技
					currentCityTechnology = cityTechnologyList.get(j);
					currentCityTechnology.setTechnology(this.getTechnologyByIDAndLevel(currentCityTechnology.getTechnologyID(), Math.min(currentTechnology.getMaxLevel(),currentCityTechnology.getLevel()+1)));//保证level不大于科技的最高等级
					haveResearchTechnology = 1;
					break;
				}
			}
			
			if(haveResearchTechnology==0){
				//当前城市未研究此科技，初始化城市科技信息
				currentCityTechnology = new CityTechnology();
				currentCityTechnology.setLevel(0);
				currentCityTechnology.setTechnology(this.getTechnologyByIDAndLevel(currentTechnology.getTechnologyID(), 1));
			}
			initedCityTechnologyList.add(currentCityTechnology);
			
		}
		
		return initedCityTechnologyList;
	}

	public void clientProcessFinished(Integer processQueueID){
		ProcessQueue pq = processQueueService.getProcessQueueByID(processQueueID);
		//已经被处理
		if(pq == null)
			return;
		
		//进程结束时间和当前时间的间隔毫秒数
		long clips = pq.getFinishTime().getTime()-(new Date()).getTime();
		
		//如果过了完成时间就对该进程进行处理
		if(clips<=0){
			this.researchFinished(pq);
		}
	}
	
	public Integer getCityTechnologyLevel(Integer cityID,Integer technologyID){
		return cityTechnologyDAO.getCityTechnologyLevelByCityIDAndTechnologyID(cityID, technologyID);
	}
	
	public CityTechnology getCityTechnologyByCityIDAndTechnologyID(Integer cityID,Integer technologyID){
		return cityTechnologyDAO.getCityTechnologyByCityIDAndTechnologyID(cityID, technologyID);
	}
	
	public Integer createTechnology(Technology technology) {
		return technologyDAO.createTechnology(technology);
	}

	public void deleteTechnologyByID(Integer technologyID) {
		technologyDAO.deleteTechnologyByID(technologyID);
	}
	
	public List<Technology> getTechnologyList() {
		return technologyDAO.getTechnologyList();
	}

	public Integer getCityTechnologyNumWithLevel(Integer cityID, Integer level) {
		return cityTechnologyDAO.getCityTechnologyNumByCityIDAndLevel(cityID, level);
	}
	

	public ITechnologyDAO getTechnologyDAO() {
		return technologyDAO;
	}

	public void setTechnologyDAO(ITechnologyDAO technologyDAO) {
		this.technologyDAO = technologyDAO;
	}

	public ICityBuildingDAO getCityBuildingDAO() {
		return cityBuildingDAO;
	}

	public void setCityBuildingDAO(ICityBuildingDAO cityBuildingDAO) {
		this.cityBuildingDAO = cityBuildingDAO;
	}

	public ICityTechnologyDAO getCityTechnologyDAO() {
		return cityTechnologyDAO;
	}

	public void setCityTechnologyDAO(ICityTechnologyDAO cityTechnologyDAO) {
		this.cityTechnologyDAO = cityTechnologyDAO;
	}

	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IConstraintDependService getConstraintDependService() {
		return constraintDependService;
	}

	public void setConstraintDependService(
			IConstraintDependService constraintDependService) {
		this.constraintDependService = constraintDependService;
	}

	public IProcessQueueService getProcessQueueService() {
		return processQueueService;
	}

	public void setProcessQueueService(IProcessQueueService processQueueService) {
		this.processQueueService = processQueueService;
	}

	public ITreasureQueueService getTreasureQueueService() {
		return treasureQueueService;
	}

	public void setTreasureQueueService(ITreasureQueueService treasureQueueService) {
		this.treasureQueueService = treasureQueueService;
	}

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
