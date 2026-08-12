package com.war.script.impl;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.war.common.GameConfig;
import com.war.common.RandomService;
import com.war.constant.ChatConstant;
import com.war.constant.CityBuildingStateConstant;
import com.war.constant.CityConstant;
import com.war.constant.CityHeroStateConstant;
import com.war.constant.HeroConstant;
import com.war.constant.HonorConstant;
import com.war.constant.OperationLogConstant;
import com.war.constant.PlayerStateConstant;
import com.war.constant.QueueTypeConstant;
import com.war.constant.ReportTypeConstant;
import com.war.constant.TreasureCategoryConstant;
import com.war.constant.TreasureConstant;
import com.war.constant.TreasureTypeConstant;
import com.war.domain.City;
import com.war.domain.CityHero;
import com.war.domain.CityHeroExt;
import com.war.domain.CityHeroLevelupLog;
import com.war.domain.CityMilitary;
import com.war.domain.CityOrdnance;
import com.war.domain.CityResource;
import com.war.domain.Guild;
import com.war.domain.GuildPlayer;
import com.war.domain.Player;
import com.war.domain.PlayerEquipment;
import com.war.domain.PlayerTask;
import com.war.domain.PlayerTreasure;
import com.war.domain.TreasureHistory;
import com.war.domain.TreasureQueue;
import com.war.exception.GameException;
import com.war.script.IGameScriptContext;
import com.war.service.IArmyService;
import com.war.service.IBattleService;
import com.war.service.IChatService;
import com.war.service.ICityService;
import com.war.service.IDeclareWarService;
import com.war.service.IEquipmentService;
import com.war.service.IGameScriptService;
import com.war.service.IGuildService;
import com.war.service.IHeroService;
import com.war.service.IMapService;
import com.war.service.IMilitaryService;
import com.war.service.IOperationLogService;
import com.war.service.IOrdnanceService;
import com.war.service.IPlayerService;
import com.war.service.IProcessQueueService;
import com.war.service.IProductionQueueService;
import com.war.service.IReportService;
import com.war.service.ITaskService;
import com.war.service.ITechnologyService;
import com.war.service.ITradeQueueService;
import com.war.service.ITreasureQueueService;
import com.war.service.ITreasureService;
import com.war.service.building.ICityDefenseService;
import com.war.service.building.IMarketService;
import com.war.util.CfgFileUtil;
import com.war.util.ResourceCalculateUtil;

/**
 * IGameScriptContext的groovy实现
 *
 * @author ghleed
 * @version 1.0
 */
public class GroovyGameScriptContext implements IGameScriptContext {
	
	private IGameScriptService gameScriptService;
	private IOrdnanceService ordnanceService;
	private ITreasureService treasureService;
	private ICityService cityService; 
	private ICityDefenseService cityDefenseService;
	private IProcessQueueService processQueueService;
	private IProductionQueueService productionQueueService;
	private ITreasureQueueService treasureQueueService;
	private ITradeQueueService tradeQueueService;
	private IHeroService heroService;
	private IMilitaryService militaryService;
	private IGuildService guildService;
	private IReportService reportService;
	private IDeclareWarService declareWarService;
	private IPlayerService playerService;
	private ITaskService taskService;
	private IEquipmentService equipmentService;
	private IChatService chatService;
	private IMarketService marketService;
	private IMapService mapService;
	private IBattleService battleService;
	private IOperationLogService operationLogService;
	private ITechnologyService technologyService;
	private IArmyService armyService;
	
	private final Lock useRebornMedicamentLock = new ReentrantLock();
	
	private final Lock useAdvanceRebornMedicamentLock = new ReentrantLock();
	
	private final Lock updateHonorLock = new ReentrantLock();
	
	
	/** 城市信息*/
	private City city;
	/** 城市资源 */
	private CityResource cityResource;
	/** 玩家信息*/
	private Player player;
	/** 操作类型(指示脚本做什么样的操作)*/
	private int processType;
	/** 给脚本传递的其他参数*/
	private Object params;
	
	/** 声望任务的配置信息*/
	private static Properties honorTaskCfg;
	
	private Properties getHonrTaskCfg() {
		if(honorTaskCfg == null){
			URI url;
			try {
				url = new URI(Thread.currentThread().getContextClassLoader().getResource("") + "script/task/honorTask.cfg");
				honorTaskCfg = CfgFileUtil.getDataProperties(CfgFileUtil.getDataByLine(new File(url)));
			} catch (URISyntaxException e) {
				throw new GameException(e.getMessage());
			}
			
		}
		
		return honorTaskCfg;
	}
	
	/**
	 * 初始化脚本上下文
	 * @param p 玩家信息
	 * @param processType 操作类型  检查任务完成情况返回2,领取任务奖励操作返回1.
	 */
	public void  initGroovyGameScriptContext(Player p,int processType,Object params){
		this.player = p;
		this.city = p.getCity();
		this.cityResource = cityService.getCityResourceByCityID(city.getCityID());
		this.processType = processType;
		this.params = params;
	}
	
	
	/** 得到编码后的字符串，用于解决groovy脚本中返回汉字的问题 */
	public String getEncodingText(String txt) {
//		try {
//			return txt;
////			return new String(txt.getBytes("GBK"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		return txt;
	}
	
	
	/**
	 * 操作类型(指示脚本做什么样的操作)
	 * @return 检查任务完成情况返回2,领取任务奖励操作返回1.
	 */
	public int getProcessType(){
		return this.processType;
	}
	
	/**
	 * 得到给脚本传递的特殊参数
	 * @return
	 */
	public Object getParams(){
		return this.params;
	}

	/**
	 * 获得建筑的最大等级
	 * @param buildingID 建筑编号
	 * @return 返回该建筑的最大等级，如果没有该建筑或者建筑正在建造中返回0
	 */
	public int getBuildingMaxLevel(int buildingID) {
		return gameScriptService.getBuildingMaxLevel(city.getCityID(), buildingID);
	}

	/**
	 * 获得大于等于指定等级建筑的数量
	 * @param buildingID 建筑编号
	 * @param level 建筑等级
	 * @return 返回建筑的数量，如果没有该建筑返回0
	 */
	public int getBuildingNum(int buildingID, int level) {
		return gameScriptService.getBuildingNum(city.getCityID(), buildingID, level);
	}
	
	/**
	 * 获得指定等级建筑的所有数量(等级大于0的建筑数量)
	 * @param buildingID 建筑编号
	 * @return 返回建筑的数量，如果没有该建筑返回0
	 */
	public int getBuildingNum(int buildingID){
		return gameScriptService.getBuildingNum(city.getCityID(), buildingID);
	}
	
	/**
	 * 获得城市人口上限
	 * @return
	 */
	public long getCityMaxPopulation() {
		return cityService.getCityPopulation(city.getCityID()).get("populationMax");
	}

	/**
	 * 获得城市某兵的数量
	 * @param armyID 兵种编号
	 * @return 返回城市里拥有的该兵数量，如果没有该兵返回0
	 */
	public int getCityArmyNum(int armyID) {
		return gameScriptService.getCityArmyNum(city.getCityID(), armyID);
	}

	/**
	 * 获得城防
	 * @param type 城防类型(DefenseConstant类中定义)
	 * @return 返回相应城防的数量
	 */
	public int getCityDefenseNum(int defenseID) {
		return gameScriptService.getCityDefenseNum(city.getCityID(), defenseID);
	}

	/**
	 * 获得城市拥有的指挥官数量
	 * @return 返回指挥官的数量，如果没有任何指挥官返回0
	 */
	public int getCityHeroNum() {
		return gameScriptService.getCityHeroNum(city.getCityID());
	}

	/**
	 * 获得城市已编制的军队数量
	 * @return 返回已编制军队数量，如果没有编制任何军队返回0
	 */
	public int getCityMilitaryNum() {
		return gameScriptService.getCityMilitaryNum(city.getCityID());
	}

	/**
	 * 获得科技等级
	 * @param technologyID 科技编号
	 * @return 返回科技的等级，如果科技没有升级返回0
	 */
	public int getTechnologyLevel(int technologyID) {
		return gameScriptService.getTechnologyLevel(city.getCityID(), technologyID);
	}

	/**
	 * 是否有指定等级的建筑
	 * @param buildingID 建筑编号
	 * @param level 等级
	 * @return 如果有满足条件的建筑返回true，否则返回false
	 */
	public boolean hasBuilding(int buildingID, int level) {
		return this.getBuildingNum(buildingID, level)>0;
	}
	
	/**
	 * 获得农场工人数量
	 * @return 返回农场当前工人数量
	 */
	public int getFoodWorkerNum() {
		return cityResource.getFoodWorkerNum();
	}

	/**
	 * 获得石油工人数量
	 * @return 返回炼油厂当前工人数量
	 */
	public int getOilWorkerNum() {
		return cityResource.getOilWorkerNum();
	}

	/**
	 * 获得钢铁工人数量
	 * @return 返回钢铁厂当前工人数量
	 */
	public int getSteelWorkerNum() {
		return cityResource.getSteelWorkerNum();
	}

	/**
	 * 获得木材工人数量
	 * @return 返回木材厂当前工人数量
	 */
	public int getWoodWorkerNum() {
		return cityResource.getWoodWorkerNum();
	}
	
	/**
	 * 获得玩家招募的市民数量
	 * @return 返回已招募的市民数量,如果玩家没有招募市民返回0
	 */
	public int getEnlistCitizen() {
		return (int)(city.getPopulationTotal() - CityConstant.INIT_CITIZEN_NUM);
	}
	
	/**
	 * 获得城市的军械数量
	 * @param ordnanceID 军械编号
	 * @return 返回军械的数量，如果没有军械返回0
	 */
	public int getCityOrdnanceNum(int ordnanceID) {
		return gameScriptService.getCityOrdnanceNum(city.getCityID(), ordnanceID);
	}

	/**
	 * 获得城市新兵的数量
	 * @return 返回新兵的数量,如果没有新兵返回0
	 */
	public int getRecruitNum() {
		return city.getRecruitNum();
	}
	
	public int getProductionQueueNum() {
		return processQueueService.getCityIDProcessQueueList(city.getCityID()).size();
	}

	/**
	 * 获得玩家指定宝物的数量
	 * @param treasureID 宝物编号
	 */
	public int getPlayerTreasureNum(int treasureID){
		PlayerTreasure pt = treasureService.getPlayerTreasureByID(player.getPlayerID(), treasureID);
		
		return (pt == null ? 0 : pt.getNum());
	}
	
	/**
	 * 获得玩家的声望值
	 * @return
	 */
	public long getRenown(){
		return player.getRenown();
	}
	
	/**
	 * 获得玩家军衔编号
	 * @return
	 */
	public Integer getHonorID(){
		return player.getHonorID();
	}
	
	/**
	 * 获得城市的金钱数量
	 * @return
	 */
	public long getMoneyNum(){
		return cityResource.getMoneyNum();
	}
	
	/**
	 * 如果城市存在执政官返回true，否则返回false
	 * @return
	 */
	public boolean existsCityOfficer(){
		return heroService.existsCityOfficer(city.getCityID());
	}

	/**
	 * 如果城市留守部队返回true，否则返回false
	 * @return
	 */
	public boolean existsStayMilitary(){
		return militaryService.existsStayMilitary(city.getCityID());
	}
	
	/**
	 * 是否已经创建或者加入了工会
	 * @return
	 */
	public boolean hasCreateOrJoinGuild(){
		return guildService.hasCreateOrJoinGuild(player.getPlayerID());
	}
	
	/**
	 * 是否已经攻击了中立军队
	 * @return
	 */
	public boolean hasAttackMonster(){
		//如果有军事类报告就算符合条件
		if(reportService.getReportCount(player.getPlayerID(), ReportTypeConstant.MILITARY_ACTION) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 更新玩家的军衔,同时更新与军衔相关的任务信息
	 * @param honorID
	 */
	public void updateHonor(Integer honorID){
		try {
			updateHonorLock.lock();
			
			// 配置文件中暂无高于中尉（honorID = 10）的任务触发信息
			if (honorID >= HonorConstant.FIRST_LIEUTENANT) {
				playerService.updateHonor(player.getPlayerID(), honorID);
				return ;
			}
			
			if (player.getHonorID() >= honorID)
				return;
			
			//前一个军衔对应的领取工资的任务:当前军衔对应的领取工资任务编号
			String honorValue = getHonrTaskCfg().getProperty(honorID.toString());
			
			if(honorValue == null) {
				throw new GameException("军衔信息不存在：" + honorID);
			}
			
			String[] tmp = honorValue.split(":");
			
			if(tmp.length < 1) {
				throw new GameException("配置信息格式有误。");
			}
			
			//前一军衔对应的任务编号
			String[] preTaskIDs = tmp[0].split(",");
			//当前军衔对应的任务编号
			String[] curTaskIDs = tmp[1].split(",");
			
			for (int i = 0; i < preTaskIDs.length; i++) {
				taskService.deletePlayerTask(player.getPlayerID(), Integer.parseInt(preTaskIDs[i].trim()));
			}
			
			//更新任务
			PlayerTask pt = null;
			for (int i = 0; i < curTaskIDs.length; i++) {
				int taskID = new Integer(curTaskIDs[i].trim());
				pt = new PlayerTask();
				pt.setPlayerID(player.getPlayerID());
				pt.setTaskID(taskID);
				pt.setTaskType(taskService.getTaskByID(taskID).getType());
				pt.setState(0);
				pt.setFlag(0);
				
				taskService.createPlayerTask(pt);
			}
			
			playerService.updateHonor(player.getPlayerID(), honorID);
			
		} finally {
			updateHonorLock.unlock();
		}
	}
	
	/**
	 * 减少用户某个宝物的数量
	 * 处理包括两种情况：
	 * 1.如果用户还没有该宝物，或者减少的num比宝物的之前的数量要大则抛出运行时异常
	 * 2.否则就在之前的数量-num
	 * @param treasureID 宝物编号
	 * @param num 数量
	 */
	public void decreasePlayerTreasure(Integer treasureID,int num){
		treasureService.decreasePlayerTreasure(player.getPlayerID(), treasureID, num);
	}
	
	/**
	 * 奖励市民
	 * @param num 奖励的数量
	 */
	public void rewardCitizen(int num) {
		long populationTotal = city.getPopulationTotal()+num;
		long populationFree =  city.getPopulationFree()+num;
		int workerNum = cityResource.getFoodWorkerNum() + cityResource.getWoodWorkerNum() + cityResource.getSteelWorkerNum() + cityResource.getOilWorkerNum();
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(workerNum, populationFree, city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		// 重新计算食物消耗
		long foodConsume = cityResource.getFoodConsume() + num * GameConfig.CITIZEN_CONSUME_FOOD;
		
		// 更新市民数量以及金钱产量 
		Map<String, Object> cityParams = new HashMap<String, Object>();
		Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree",populationFree);
		cityParams.put("populationTotal", populationTotal);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("moneyOutput", moneyOutput);
		cityResourceParams.put("foodConsume", foodConsume);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
	}

	/**
	 * 奖励军械
	 * @param ordanceID 军械编号
	 * @param num 奖励的数量
	 */
	public void rewardOrdnance(int ordnanceID, int num) {
		CityOrdnance cd = ordnanceService.getCityOrdnance(city.getCityID(), ordnanceID);
		
		if(cd == null){
			cd = new CityOrdnance();
			cd.setCityID(city.getCityID());
			cd.setOrdnanceID(ordnanceID);
			cd.setNum(num);
			
			ordnanceService.createCityOrdnance(cd);
		}else{
			cd.setNum(cd.getNum()+num);
			
			ordnanceService.updateCityOrdnance(cd);
		}
	}
	
	/**
	 * 奖励装备
	 * @param equipmentID 装备编号
	 * @param num 奖励的装备数量
	 */
	public void rewardEquipment(int equipmentID,int num ){
		PlayerEquipment pe = new PlayerEquipment();
		pe.setEquipmentID(equipmentID);
		pe.setPlayerID(player.getPlayerID());
		
		for(int i=0; i<num; i++){
			equipmentService.addPlayerEquipment(pe);
		}
	}
	
	/**
	 * 奖励声望
	 * @param renown 奖励的声望值 
	 */
	public void rewardRenown(long renown){
		playerService.updateRenown(player.getPlayerID(), player.getRenown()+renown);
	}
	
	/**
	 * 减少声望
	 * @param renown
	 */
	public void reduceRenown(long renown) {
		if (player.getRenown() < renown) {
			throw new GameException("声望不足。");
		}
		
		playerService.updateRenown(player.getPlayerID(), player.getRenown() - renown);
	}
	
	/**
	 * 减少道具
	 * @param treasureID
	 * @param num
	 */
	public void reduceTreasure(Integer treasureID, Integer num) {
		treasureService.decreasePlayerTreasure(player.getPlayerID(), treasureID, num);
	}
	
	public void moveCity(Integer mapArea) {
		cityService.moveCity(city.getCityID(), mapArea);
	}
	
	public void moveCityToTargetPosition(int posX, int posY) {
		cityService.moveCityToTargetPosition(city.getCityID(), posX, posY);
	}

	public void reduceBuildCostTime(int queueID, int reduceTime) {
		processQueueService.reduceBuildCostTime(queueID, reduceTime);
	}
	
	public void reduceTechResearchCostTime(int queueID, int reduceTime) {
		processQueueService.reduceTechResearchCostTime(queueID, reduceTime);
	}
	
	public void reduceOrdnanceProductCostTime(int queueID, int reduceSecond) {
		productionQueueService.reduceOrdnanceProductCostTime(city.getCityID(), reduceSecond);
	}
	
	public void reduceTradeCostTime(int queueID){
		tradeQueueService.reduceTradeCostTime(queueID);
	}
	
	public void reduceMoneyNum(long money){
		if(cityResource.getMoneyNum() < money){
			throw new GameException("金钱不足");
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("moneyNum", cityResource.getMoneyNum() - money);
		
		cityService.updateCityResource(params);
	}

	public void setFoodTreasureAdd(int num) {
		Integer foodTreasureAdd = cityResource.getFoodTreasureAdd();
		
		//目前只支持一种数值的加成
		foodTreasureAdd = num;
		
		//重新计算产出
		long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), foodTreasureAdd);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("foodTreasureAdd", foodTreasureAdd);
		params.put("foodOutput", foodOutput);
		
		cityService.updateCityResource(params);
	}

	public void setOilTreasureAdd(int num) {
		Integer oilTreasureAdd = cityResource.getOilTreasureAdd();
		
		//目前只支持一种数值的加成
		oilTreasureAdd = num;
		
		//重新计算产出
		long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), oilTreasureAdd);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("oilTreasureAdd", oilTreasureAdd);
		params.put("oilOutput", oilOutput);
		
		cityService.updateCityResource(params);
	}

	public void setSteelTreasureAdd(int num) {
		Integer steelTreasureAdd = cityResource.getSteelTreasureAdd();
		
		//目前只支持一种数值的加成
		steelTreasureAdd = num;
		
		//重新计算产出
		long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), steelTreasureAdd);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("steelTreasureAdd", steelTreasureAdd);
		params.put("steelOutput", steelOutput);
		
		cityService.updateCityResource(params);
	}

	public void setWoodTreasureAdd(int num) {
		Integer woodTreasureAdd = cityResource.getWoodTreasureAdd();
		
		//目前只支持一种数值的加成
		woodTreasureAdd = num;
		
		//重新计算产出
		long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), woodTreasureAdd);
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("woodTreasureAdd", woodTreasureAdd);
		params.put("woodOutput", woodOutput);
		
		cityService.updateCityResource(params);
		
	}
	
	public void setMoneyTreasureAdd(int num) {
		Integer moneyTreasureAdd = cityResource.getMoneyTreasureAdd();
		
		//目前只支持一种数值的加成
		moneyTreasureAdd = num;
		
		//重新计算产出
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getFoodWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("moneyTreasureAdd", moneyTreasureAdd);
		params.put("moneyOutput", moneyOutput);
		
		cityService.updateCityResource(params);
	}
	
	/**
	 * 增加10%人口上限
	 */
	public void addCityPopulationMax(int days) {
		if (updateTreasureQueue(city.getCityID(), TreasureCategoryConstant.NORMAL, TreasureTypeConstant.POPULATION_MAX_ADD, days * 24)) {
			long populationMax = city.getPopulationMax() * 110 / 100;
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("cityID", city.getCityID());
			params.put("populationMax", populationMax);

			cityService.updateCity(params);
		}
	}
	
	/**
	 * 给日期加上指定的小时数
	 * @param date 日期
	 * @param hours 加上的小时数
	 * @return
	 */
	private Date addHour(Date date,int hours){
		date.setTime(date.getTime() + hours * 60 * 60 * 1000);
		return date;
	}
	
	/**
	 * 更新宝物效果队列
	 * 
	 * @param category 宝物分类
	 * @param type 宝物类型
	 * @param hours 效果持续的小时数
	 * @return 创建了新的宝物队列返回true，更新宝物队列返回false
	 */
	private boolean updateTreasureQueue(int targetID, int category, int type, int hours) {
		TreasureQueue treasureQueue = this.treasureQueueService.getTreasureQueueByType(targetID, category, type);
		//如果不存在该类型的宝物效果就创建新的队列,否则更新宝物效果持续时间
		if(treasureQueue == null){
			treasureQueue = new TreasureQueue();
			treasureQueue.setTargetID(targetID);
			treasureQueue.setCategory(category);
			treasureQueue.setType(type);
			treasureQueue.setFinishTime(addHour(new Date(),hours));
			this.treasureQueueService.createTreasureQueue(treasureQueue);
			
			return true;
			
		}else{
			treasureQueue.setFinishTime(addHour(treasureQueue.getFinishTime(),hours));
			this.treasureQueueService.updateTreasureQueue(treasureQueue);
			return false;
		}
	}

	public void setFoodTreasureAdd(int num, int days) {
		//宝物分类
		int category = TreasureCategoryConstant.RESOURCE_PRODUCTION;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
		case 25:
			type = TreasureTypeConstant.TREASURE_FOOD_ADD;
			break;
		default:
			throw new GameException("该效果未实现");
		}
		
		if (updateTreasureQueue(city.getCityID(), category,type,days * 24))
			this.setFoodTreasureAdd(num);
		
	}

	public void setOilTreasureAdd(int num, int days) {
		//宝物分类
		int category = TreasureCategoryConstant.RESOURCE_PRODUCTION;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
		case 25:
			type = TreasureTypeConstant.TREASURE_OIL_ADD;
			break;
		default:
			throw new GameException("该效果未实现");
		}
		
		if (updateTreasureQueue(city.getCityID(), category,type,days * 24))
			this.setOilTreasureAdd(num);
		
	}

	public void setSteelTreasureAdd(int num, int days) {
		// 宝物分类
		int category = TreasureCategoryConstant.RESOURCE_PRODUCTION;
		// 宝物类型 
		int type;
		
		// 将加成效果映射到对应的宝物类型
		switch(num){
			case 25:
				type = TreasureTypeConstant.TREASURE_STEEL_ADD;
				break;
			default:
				throw new GameException("该效果未实现");
		}
		
		if (updateTreasureQueue(city.getCityID(), category,type,days * 24))
			this.setSteelTreasureAdd(num);
		
	}

	public void setWoodTreasureAdd(int num, int days) {
		//宝物分类
		int category = TreasureCategoryConstant.RESOURCE_PRODUCTION;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
		case 25:
			type = TreasureTypeConstant.TREASURE_WOOD_ADD;
			break;
		default:
			throw new GameException("该效果未实现");
		}
		
		if (updateTreasureQueue(city.getCityID(), category,type,days * 24))
			this.setWoodTreasureAdd(num);
		
	}
	
	public void setMoneyTreasureAdd(int num, int days) {
		
		//宝物分类
		int category = TreasureCategoryConstant.RESOURCE_PRODUCTION;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
		case 25:
			type = TreasureTypeConstant.TREASURE_MONEY_ADD;
			break;
		default:
			throw new GameException("该效果未实现");
		}
		
		if (updateTreasureQueue(city.getCityID(), category,type,days * 24))
			this.setMoneyTreasureAdd(num);
		
	}
	
	public void setStorageTreasureAdd(int num,int days){
		//宝物分类
		int category = TreasureCategoryConstant.NORMAL;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
		case 25:
			type = TreasureTypeConstant.STORAGE_ADD;
			break;
		default:
			throw new GameException("该效果未实现");
		}
		
		if (updateTreasureQueue(city.getCityID(), category, type, days * 24)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cityID", city.getCityID());
			params.put("resourceNumMax", (long)(cityResource.getResourceNumMax() * (100 + num) / 100));

			//更新仓储上限
			cityService.updateCityResource(params);
		}
	}

	public void strategyCamouflage(int days) {
		
		//宝物分类
		int category = TreasureCategoryConstant.MILITARY;
		//宝物类型 
		int type = TreasureTypeConstant.STRATEGY_CAMOUFLAGE;
		//检查是否是否已经使用过战略欺骗
		if(this.treasureQueueService.getTreasureQueueByType(city.getCityID(),category, TreasureTypeConstant.STRATEGY_CHEAT) != null){
			throw new GameException("战略欺骗和战略伪装不能同时使用。");
		}
		
		this.updateTreasureQueue(city.getCityID(), category,type,days * 24);
		
	}

	public void strategyCheat(int days) {
		//宝物分类
		int category = TreasureCategoryConstant.MILITARY;
		//宝物类型 
		int type = TreasureTypeConstant.STRATEGY_CHEAT;
		//检查是否是否已经使用过战略伪装
		if(this.treasureQueueService.getTreasureQueueByType(city.getCityID(),category, TreasureTypeConstant.STRATEGY_CAMOUFLAGE) != null){
			throw new GameException("战略欺骗和战略伪装不能同时使用。");
		}
		
		this.updateTreasureQueue(city.getCityID(), category,type,days * 24);
	}
	
	public void avoidWar(int hours) {
		//如果已经宣战则无法进行该操作
		if(declareWarService.getDeclareWarCountByPlayerID(player.getPlayerID()) > 0){
			throw new GameException("在您对其他玩家宣战或其他玩家对您宣战期间，无法使用该宝物。");
		}
		
		//宝物分类
		int category = TreasureCategoryConstant.NORMAL;
		//宝物类型 
		int type = TreasureTypeConstant.AVOID_WAR;
		
		if (this.updateTreasureQueue(player.getPlayerID(), category, type, hours)) {
			player.setState(PlayerStateConstant.FREEWAR);
			playerService.updatePlayer(player);
		}
	}

	public void suppressRiot() {
		
		//宝物分类
		int category = TreasureCategoryConstant.NORMAL;
		//宝物类型 
		int type = TreasureTypeConstant.SUPPRESS_RIOT;
		TreasureQueue treasureQueue = treasureQueueService.getTreasureQueueByType(city.getCityID(),category, type);
		
		if(treasureQueue != null){
			throw new GameException("使用巡逻队24小时后才能再次使用。");
		}
		
		//最高治安值
		int security = 100 - city.getTax();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("security", security);

		this.cityService.updateCity(params);
		
		//冷却7天
		this.updateTreasureQueue(city.getCityID(), category, type, 24);
		
	}

	public void setMilitaryAttackAdd(int num, int days) {
		//宝物分类
		int category = TreasureCategoryConstant.MILITARY;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
			case 20:
				type = TreasureTypeConstant.MILITARY_ATTACK_ADD;
				break;
			default:
				throw new GameException("该效果未实现");
		}
		
		this.updateTreasureQueue(city.getCityID(), category,type,days * 24);
	}

	public void setMilitaryDefenseAdd(int num, int days) {
		//宝物分类
		int category = TreasureCategoryConstant.MILITARY;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
			case 20:
				type = TreasureTypeConstant.MILITARY_DEFENSE_ADD;
				break;
			default:
				throw new GameException("该效果未实现");
		}
		
		this.updateTreasureQueue(city.getCityID(), category,type,days * 24);
	}

	public void setCommanderExpAdd(int cityHeroID, int num, int days) {
		
		//宝物分类
		int category = TreasureCategoryConstant.COMMANDER;
		//宝物类型 
		int type;
		
		//将加成效果映射到对应的宝物类型
		switch(num){
			case 20:
				type = TreasureTypeConstant.COMMANDER_EXP_ADD;
				break;
			default:
				throw new GameException("该效果未实现");
		}
		
		if (this.updateTreasureQueue(cityHeroID, category,type,days * 24)) {
			// 更新指挥官扩展信息
			CityHeroExt cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHeroID);
			cityHeroExt.setExpTreasureAdd(num);
			heroService.updateCityHeroExt(cityHeroExt);
		}
	}
	
	
	
	public void addCityHeroStamina(int cityHeroID, int addStamina) {
		heroService.addCityHeroStamina(cityHeroID, addStamina);
	}
	
	public void addCityHeroMaxSkillNum(int cityHeroID,int num) {
		heroService.addCityHeroMaxSkillNum(cityHeroID, num);
	}
	
	public void finishProduction(int queueID) {
		productionQueueService.finishProduction(queueID);
	}

	public void refreshCityCandidacyHeroList(){
		heroService.refreshCityCandidacyHeroList(city.getCityID());
	}
	
	/**
	 * 奖励资源
	 * @param woodNum 奖励的木材数量
	 * @param steelNum 奖励的钢铁数量
	 * @param oilNum 奖励的石油数量
	 * @param foodNum 奖励的食物数量
	 * @param moneyNum 奖励的金钱数量
	 */
	public void rewardResource(int woodNum, int steelNum, int oilNum,int foodNum, int moneyNum) {
		cityService.addCityResources(city.getCityID(), (long)woodNum,  (long)steelNum,  (long)oilNum,  (long)foodNum,  (long)moneyNum);
	}
	
	/**
	 * 捐献资源
	 * @param woodNum 捐献的木材数量
	 * @param steelNum 捐献的钢铁数量
	 * @param oilNum 捐献的石油数量
	 * @param foodNum 捐献的食物数量
	 * @param moneyNum 捐献的金钱数量
	 */
	public void minusResource(int woodNum, int steelNum, int oilNum,int foodNum, int moneyNum) {
		cityService.minusCityResources(city.getCityID(), (long)woodNum, (long)steelNum, (long)oilNum, (long)foodNum, (long)moneyNum);
	}

	/**
	 * 奖励宝物
	 * @param treasureID 宝物编号
	 * @param num 奖励的宝物数量
	 */
	public void rewardTreasure(int treasureID, int num) {
		treasureService.increasePlayerTreasure(player.getPlayerID(), treasureID, num);
	}
	
	/**
	 * 扣除宝物
	 * @param treasureID
	 * @param num
	 */
	public void minusTreasure(int treasureID, int num) {
		treasureService.decreasePlayerTreasure(player.getPlayerID(), treasureID, num);
	}

	/**
	 * 奖励城防
	 * @param type 城防类型(DefenseConstant类中定义)
	 * @param num 奖励的城防数量
	 */
	public void rewardCityDefense(int defenseID,int num){
		cityDefenseService.addCityDefense(city.getCityID(), defenseID, num);
	}
	
	/**
	 * 是否已使用了宝物
	 * @param treasureID
	 * @return
	 */
	public boolean hasUsedTreasure(int treasureID) {
		List<TreasureHistory> treasureHistoryList = treasureService.getTreasureHistoryList(player.getPlayerID(), treasureID, TreasureConstant.HISTORY_USE_TYPE);
		if (treasureHistoryList != null && !treasureHistoryList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否已在世界频道进行聊天
	 * @param playerName
	 * @return
	 */
	public boolean hasChatedInWorldScope() {
		return chatService.getChatHistoryNum(player.getName(), ChatConstant.WORLD_CHAT_CHANNEL) == 0 ? false : true;
	}
	
	/**
	 * 是否已在军团频道进行聊天
	 * @return
	 */
	public boolean hasChatedInGuildScope() {
		return chatService.getChatHistoryNum(player.getName(), ChatConstant.GUILD_CHAT_CHANNEL) == 0 ? false : true;
	}
	
	/**
	 * 获得城市正在售卖的资源交易数目
	 * @return
	 */
	public int getCityResourceSalesNum() {
		return marketService.getCityResourceSalesList(city.getCityID()).size();
	}
	
	/**
	 * 获得正在购买的资源交易数
	 * @return
	 */
	public int getImportResourceTradeNum() {
		return tradeQueueService.getImportResourceTradeQueueNum(city.getCityID());
	}
	
	/**
	 * 获得身上有装备的指挥官数目
	 * @return
	 */
	public int getEquipedCityHeroNum() {
		return heroService.getEquipedCityHeroNum(city.getCityID());
	}
	
	
	public boolean hasAppointedOfficer() {
		return heroService.existsCityOfficer(city.getCityID());
	}
	
	/**
	 * 获得玩家地图收藏数目
	 * @return
	 */
	public int getMapFavouriteNum() {
		return mapService.getMapFavouriteNumOfPlayer(player.getPlayerID());
	}
	
	/**
	 * 获得玩家参与进攻的战斗日志数目
	 * @return
	 */
	public int getPlayerAttackBattleLogNum() {
		return battleService.getPlayerAttackBattleLogNum(player.getPlayerID());
	}
	
	/**
	 * 获得玩家好友数目
	 * @return
	 */
	public int getFriendNum() {
		return playerService.getFriendNum(player.getPlayerID());
	}
	
	/**
	 * 玩家是否已加入军团
	 * @return
	 */
	public boolean hasJoinedGuild() {
		return player.getGuildID() == null ? false : true;
	}
	
	/**
	 * 建筑是否完成升级
	 * @param buildingID
	 * @param level	要达到的等级
	 * @return
	 */
	public boolean buildingUpgradeHasFinished(Integer buildingID, Integer level) {
		Map<String, Integer> map = gameScriptService.getBuildingMaxLevelAndState(buildingID, city.getCityID());
		if (map.get("level") >= level && map.get("state") == CityBuildingStateConstant.NORMAL) {
			return true;
		}
		
		return false;
	}

	/**
	 * 获得城市正在建造的建筑数量
	 * @return
	 */
	public int getCityBuildBuildingNum() {
		return processQueueService.getProcessQueueNumByCityIDAndType(city.getCityID(), QueueTypeConstant.QUEUE_BUILD_UPGRADE);
	}
	
	/**
	 * 获得城市税收额
	 * @return
	 */
	public int getCityTex() {
		return city.getTax();
	}
	
	/**
	 * 用户是否执行过这个动作
	 * @return
	 */
	public boolean hasPerformTheOperation(String operation) {
		return operationLogService.hasPerformedOperation(player.getPlayerID(), operation);
	}
	
	/**
	 * 获得玩家的军团贡献值
	 * @return
	 */
	public long getGuildPlayerContribution() {
		return guildService.getGuildPlayerContribution(player.getGuildID(), player.getPlayerID());
	}

	/**
	 * 是否领取过军团补贴
	 * @return
	 */
	public boolean hasReceivedSubsidy() {
		return guildService.hasReceivedSubsidy(player.getPlayerID());
	}
	
	/**
	 * 城市中所有科技是否都达到了指定等级
	 * @param level
	 * @return
	 */
	public boolean hasAllTechnologReachedTheLevel(Integer level) {
 		return technologyService.getCityTechnologyNumWithLevel(city.getCityID(), level) < technologyService.getTechnologyList().size() ? false : true ;
	}
	
	/**
	 * 奖励城市军队
	 * @param armyID
	 * @param num
	 */
	public void rewardCityArmy(Integer armyID, Integer num) {
		armyService.rewardCityArmy(city.getCityID(), armyID, num);
	}
	
	/**
	 * 是否完成指定目标的侦查任务 
	 * @param operation
	 * @param level
	 * @param date 界定时间: 获得的记录都在此时间之后
	 * @return
	 */
	public boolean hasCompleteSpyForSpecifyLevel(String operation, Integer level, Date date) {
		return operationLogService.getOperationLogNumForSpy(player.getPlayerID(), operation, level, date) > 0 ? true : false ;
	}
	
	/**
	 * 是否完成指定目标的掠夺任务 
	 * @param level
	 * @param date
	 * @return 界定时间：获得的记录都在此时间之后
	 */
	public boolean hasCompleteAttackForSpecifyLevel(Integer level, Date date) {
		return battleService.getBattleLogNumForAttackTask(player.getPlayerID(), level, date) > 0 ? true : false ;
	}
	
	/**
	 * 交换宝物
	 * @param originalTreasureID 被用来交换的宝物编号
	 * @param fromNum 被用来交换的宝物的数量
	 * @param targetTreasureNum 所要交换的宝物编号
	 * @param toNum 所要交换的宝物的数量
	 */
	public void exchangeTreasure(Integer originalTreasureID, Integer fromNum, Integer targetTreasureID, Integer toNum) {
		treasureService.decreasePlayerTreasure(player.getPlayerID(), originalTreasureID, fromNum);
		treasureService.increasePlayerTreasure(player.getPlayerID(), targetTreasureID, toNum);
	}
	
	/**
	 * 交换部队
	 * @param originalAmryID 被用来交换的部队编号
	 * @param fromNum 被用来交换的部队的数量
	 * @param targetTreasureID 所要交换的部队编号
	 * @param toNum 所要交换的部队的数量
	 */
	public void exchangeArmy(Integer originalAmryID, Integer fromNum, Integer targetTreasureID, Integer toNum) {
		armyService.reduceCityArmyForTask(city.getCityID(), originalAmryID, fromNum);
		armyService.rewardCityArmy(city.getCityID(), targetTreasureID, toNum);
	}
	
	/**
	 * 获得城市资源数目（木，铁，油，食，钱）
	 * 其中的key 为: woodNum, steelNum, oilNum, foodNum, moneyNum
	 * @return
	 */
	public Map<String, Long> getCityResource() {
		return cityService.getCityResourcesNum(city.getCityID());
	}
	
	/**
	 * 奖励礼金
	 * @param num
	 */
	public void rewardGiftCertificate(Integer num) {
		playerService.updateGiftCertificate(player.getPlayerID(), player.getGiftCertificate() + num);
	}
	
	/**
	 * 获得已编制的城市军队数量
	 * @return
	 */
	public Integer getHasArmyCityMilitaryNum() {
		int count = 0;
		
		List<CityMilitary> cityMilitaryList = militaryService.getCityMilitaryList(city.getCityID());
		for (CityMilitary cityMilitary : cityMilitaryList) {
			
			if (	cityMilitary.getArmy1() != null || 
					cityMilitary.getArmy2() != null || 
					cityMilitary.getArmy3() != null ||
					cityMilitary.getArmy4() != null || 
					cityMilitary.getArmy5() != null || 
					cityMilitary.getArmy6() != null || 
					cityMilitary.getArmy7() != null || 
					cityMilitary.getArmy8() != null ) {
				
				count++;
			}
		}
		
		return count;
	}
	
	public void setCityHeroReinAdd(int cityHeroID, int addReinRate, int day) {
		
		// 宝物分类
		int category = TreasureCategoryConstant.COMMANDER;
		
		// 宝物类型
		int type;
		
		switch (addReinRate) {
			case 10:
				type = TreasureTypeConstant.COMMANDER_REIN_ADD;
				break;
			default:
				throw new GameException("该效果未实现");
		}
		
		CityHero cityHero = heroService.getCityHero(cityHeroID);
		
		if (this.updateTreasureQueue(cityHeroID, category, type, day * 24)) {
			int rein = heroService.getCityHeroBasicRein(cityHero.getQuality(), cityHero.getLevel());
			heroService.updateReinByCityHeroID(cityHeroID, (rein * (100 + addReinRate) / 100) + cityHero.getRein());
		}
	}
	
	public void addCityHeroLeadership(int cityHeroID, int addLeadershipPoint) { 

		CityHero cityHero = heroService.getCityHero(cityHeroID);
		heroService.updateCityHeroLeadership(cityHeroID, Math.min(cityHero.getLeadership() + addLeadershipPoint, 100));
	}
	
	public void addCityHeroExp(int cityHeroID, Long addExpPoint) {
		
		heroService.addHeroExp(cityHeroID, addExpPoint);
	}
	
	public void cityHeroPerfectLevelUp(int cityHeroID) {
		heroService.heroPerfectLevelUp(cityHeroID);
	}
	
	public String nationalTreasureErnie() {
		String[] treasures = 
		{
				"914:2:一星铜质战功章",
				"915:2:一星白银战功章",
				"916:2:一星黄金战功章",
				"917:1:二星铜质战功章",
				"918:1:二星白银战功章",
				"919:1:二星黄金战功章",
				"920:1:三星铜质战功章",
				"921:1:三星白银战功章",
				"922:1:三星黄金战功章",
				"116:5:大喇叭",
				"211:2:建筑工",
				"141:1:小型仓库",
				"231:1:初级流水线",
				"221:1:计算机"
		};
		
		int index = (int)( Math.random() * treasures.length );
		
		return treasures[index];
	}
	
	public String[] getBattleHonorBoxTreasure(String[] treasures) {
		
		if (treasures.length != 3) {
			throw new GameException("数据不合法。");
		}
		
		String[] result = new String[3];
		
		int random = (int)(Math.random() * 100);
		int index = (int)(Math.random() * 3);
		boolean openRate = RandomService.isInTheLimits(80, 100); 
		
		if (random >= 0 && random <= 69){
			result[0] = treasures[index];
			
			if (openRate) {
				result[0] += ":2";
			} else {
				result[0] += ":3";
			}
			
		} else if (random >= 70 && random <= 89) {
			
			for (int i = 0; i < treasures.length; i++) {
				
				result[i] = treasures[i];
				
				if (openRate) {
					result[i] += ":1";
				} else {
					result[i] += ":2";
				}
			}
			
			result[index] = null;
			
		} else if (random >= 90 && random <=99) {
			for (int i = 0; i < treasures.length; i++) {
				result[i] = treasures[i];
				result[i] += ":1";
			}
		}
		
		return result;
	}
	
	public String getAdvanceHonorBoxTreasure() {
		
		String[] treasures = 
		{
			"926:1:军团名誉章",
			"927:1:黄金军团名誉章",
			"928:1:钻石军团名誉章",
			"929:1:国家名誉章",
			"930:1:国家荣誉章"
		};
		
		int random = (int)( Math.random() * 100 );
		
		if (random >= 0 && random <= 29 )
			return treasures[0];
		else if (random >= 30 && random <= 59)
			return treasures[1];
		else if (random >= 60 && random <= 89)
			return treasures[2];
		else if (random >= 90 && random <= 94)
			return treasures[3];
		else
			return treasures[4];
	}
	
	public void useRebornMedicament(Integer cityHeroID) {
		try {
			useRebornMedicamentLock.lock();
			
			CityHero cityHero = heroService.getCityHero(cityHeroID);
			int level = cityHero.getLevel();
			
			if (level == 1) {
				throw new GameException("该指挥官的当前等级为1级，无法重复使用再生药水。");
			}
			
			// 需要的再生药水数量
			int needMedicamentNum = (int)Math.ceil(level/10.0);
			PlayerTreasure playerTreasure = treasureService.getPlayerTreasureByID(player.getPlayerID(), TreasureConstant.REBORN_MEDICAMENT);
			if ( playerTreasure == null || playerTreasure.getNum() < needMedicamentNum ) {
				throw new GameException("再生药水数量不足。");
			}
			
			// 减少玩家再生药水
			// 减少needMedicamentNum - 1个是因为TreasureService.userTreasure()方法还会例行减掉玩家一个再生药水
			treasureService.decreasePlayerTreasure(player.getPlayerID(), TreasureConstant.REBORN_MEDICAMENT, needMedicamentNum - 1);
			
			CityHeroExt cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHeroID);
			CityHeroLevelupLog cityHeroLevelupLog = heroService.getCityHeroLevelupLog(cityHeroID, 1);
			
			Random random = new Random();
			int unsetPoint = 0;
			int command = 0, mind = 0, defense = 0, executivepower = 0;
			long exp = cityHero.getExp();
			
			if ( cityHeroLevelupLog == null ) {
				
				// 重新计算指挥官点数
				double numA = 1.0 * (cityHero.getCommand() + cityHero.getMind() + cityHero.getExecutivepower() + cityHero.getDefense() - 30)/(level - 1);
				double numB = ( cityHero.getMilitarySpirit() / 10 ) * 2;
				if (numA <= numB)
					unsetPoint = 30;
				else
					unsetPoint = 30 + (int)((numA - numB) * (level - 1));
				
				// 随机分配
				// 英雄属性点数数组
				int[] pointArray = new int[4];
				for(int i = 0; i < unsetPoint; i++){
					pointArray[random.nextInt(4)] += 1;
				}
				command += pointArray[0];
				defense += pointArray[1];
				mind += pointArray[2];
				executivepower += pointArray[3];
				
				heroService.deleteCityHeroLevelupLog(cityHeroID);
				
				// 重设英雄升级日志
				cityHeroLevelupLog = new CityHeroLevelupLog();
				cityHeroLevelupLog.setCityHeroID(cityHeroID);
				cityHeroLevelupLog.setLevel(1);
				cityHeroLevelupLog.setAddCommand(command);
				cityHeroLevelupLog.setAddDefense(defense);
				cityHeroLevelupLog.setAddMind(mind);
				cityHeroLevelupLog.setAddExecutivepower(executivepower);
				heroService.createCityHeroLevelupLog(cityHeroLevelupLog);
				
			} else {
				command += cityHeroLevelupLog.getAddCommand();
				defense += cityHeroLevelupLog.getAddDefense();
				mind += cityHeroLevelupLog.getAddMind();
				executivepower += cityHeroLevelupLog.getAddExecutivepower();
				
				heroService.deleteCityHeroLevelupLog(cityHeroID, 2);
			}
			
			command += cityHeroExt.getCommandEquipmentAdd() + cityHeroExt.getCommandTreasureAdd();
			mind += cityHeroExt.getMindEquipmentAdd() + cityHeroExt.getMindTreasureAdd();
			defense += cityHeroExt.getDefenseEquipmentAdd() + cityHeroExt.getDefenseTreasureAdd();
			executivepower += cityHeroExt.getExecutivepowerEquipmentAdd() + cityHeroExt.getExecutivepowerTreasureAdd();
			
			// 返回经验
			for (int i = level - 1; i > 0; i--) {
				exp += heroService.getCityHeroExpMax(i);
			}
			
			// 重置指挥官状态
			cityHero.setLevel(1);
			cityHero.setExp(exp);
			cityHero.setUnsetPoint(0);
			cityHero.setCommand(command);
			cityHero.setDefense(defense);
			cityHero.setMind(mind);
			cityHero.setExecutivepower(executivepower);
			
			// 重置指挥官体力
			cityHero.setStaminaMax(HeroConstant.HERO_BASE_STAMINA + cityHero.getMind() * HeroConstant.HERO_MIND_ADD_STAMINA);
			
			// 重置指挥官统御值
			int basicRein = heroService.getCityHeroBasicRein(cityHero.getQuality(), cityHero.getLevel());
			int rein = basicRein;
			// 统御道具加成
			TreasureQueue treasureQueue = treasureQueueService.getTreasureQueueByType(cityHero.getCityHeroID(), TreasureCategoryConstant.COMMANDER, TreasureTypeConstant.COMMANDER_REIN_ADD);
			if (treasureQueue != null)
				rein += basicRein * 10 / 100;
			// 统御星级加成
			if (cityHero.getStar() == 5)
				rein += basicRein * 10 / 100;
			// 统御军团加成
			rein += cityHeroExt.getReinGuildAdd();
			
			cityHero.setRein(rein);
			heroService.updateCityHero(cityHero);
			
			// 重置指挥官扩展信息
			heroService.updateCityHeroMilitaryAdd(cityHero.getCityHeroID(), cityHero.getCommand()/10, cityHero.getDefense()/10, 0);
			
			
		} finally {
			useRebornMedicamentLock.unlock();
		}
		
	}
	
	public void useAdvanceRebornMedicament(Integer cityHeroID) {
		try {
			useAdvanceRebornMedicamentLock.lock();
			
			CityHero cityHero = heroService.getCityHero(cityHeroID);
			CityHeroLevelupLog cityHeroLevelupLog = heroService.getCityHeroLevelupLog(cityHero.getCityHeroID(), cityHero.getLevel() - 1);
			CityHeroExt cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHeroID);
			
			PlayerTreasure playerTreasure = treasureService.getPlayerTreasureByID(player.getPlayerID(), TreasureConstant.ADVANCE_REBORN_MEDICAMENT);
			if ( playerTreasure == null || playerTreasure.getNum() < 1 ) {
				throw new GameException("高级再生药水数量不足。");
			}
			
			if (cityHero.getLevel() == 1) {
				throw new GameException("当前等级不能够使用高级再生药水。");
			}
			
			if (cityHeroLevelupLog == null) {
				throw new GameException("高级再生药水只能在升级后才能使用，无法降低该指挥官的原始等级。");
				
			} else {
				
				// 返回经验
				long exp = cityHero.getExp() + heroService.getCityHeroExpMax(cityHero.getLevel() - 1);
				heroService.deleteCityHeroLevelupLog(cityHeroID, cityHero.getLevel());
				
				cityHero.setLevel(cityHero.getLevel() - 1);
				cityHero.setUnsetPoint(0);
				cityHero.setExp(exp);
				cityHero.setCommand(cityHeroLevelupLog.getAddCommand() + cityHeroExt.getCommandEquipmentAdd() + cityHeroExt.getCommandTreasureAdd());
				cityHero.setDefense(cityHeroLevelupLog.getAddDefense() + cityHeroExt.getDefenseEquipmentAdd() + cityHeroExt.getDefenseTreasureAdd());
				cityHero.setMind(cityHeroLevelupLog.getAddMind() + cityHeroExt.getMindEquipmentAdd() + cityHeroExt.getMindTreasureAdd());
				cityHero.setExecutivepower(cityHeroLevelupLog.getAddExecutivepower() + cityHeroExt.getExecutivepowerEquipmentAdd() + cityHeroExt.getExecutivepowerTreasureAdd());
				
				// 重置指挥官体力
				cityHero.setStaminaMax(HeroConstant.HERO_BASE_STAMINA + cityHero.getMind() * HeroConstant.HERO_MIND_ADD_STAMINA);
				
				// 重置指挥官统御值
				int basicRein = heroService.getCityHeroBasicRein(cityHero.getQuality(), cityHero.getLevel());
				int rein = basicRein;
				// 统御道具加成
				TreasureQueue treasureQueue = treasureQueueService.getTreasureQueueByType(cityHero.getCityHeroID(), TreasureCategoryConstant.COMMANDER, TreasureTypeConstant.COMMANDER_REIN_ADD);
				if (treasureQueue != null)
					rein += basicRein * 10 / 100;
				// 统御星级加成
				if (cityHero.getStar() == 5)
					rein += basicRein * 10 / 100;
				// 统御军团加成
				rein += cityHeroExt.getReinGuildAdd();
				
				cityHero.setRein(rein);
				heroService.updateCityHero(cityHero);
				
				// 重置指挥官扩展信息
				heroService.updateCityHeroMilitaryAdd(cityHero.getCityHeroID(), cityHero.getCommand()/10, cityHero.getDefense()/10, 0);
			}
			
		} finally {
			useAdvanceRebornMedicamentLock.unlock();
		}
		
	}
	
	public void useGuildExpandCommand() {
		
		if (player.getGuildID() == null) {
			throw new GameException("军团扩展令只能是军团长使用，您还未加入任何军团。");
		}
		
		GuildPlayer guildPlayer = guildService.getGuildPlayerByGuildIDAndPlayerID(player.getGuildID(), player.getPlayerID());
		
		if ( !guildPlayer.getPermission().equals("1-1-1-1-1-1") ) {
			throw new GameException("军团扩展令只能是军团长使用。");
		}
		
		Guild guild = guildService.getGuildByID(guildPlayer.getGuildID());
		guild.setPopulationMax(guild.getPopulationMax() + 30);
		
		guildService.updateGuild(guild);
	}
	
	public void useFightSoulCopperMedal(Integer cityHeroID) {
		CityHero cityHero = heroService.getCityHero(cityHeroID);
		
		if (cityHero.getQuality() != HeroConstant.QUALITY_NORMAL) {
			throw new GameException("无法使用，战魂铜章仅能普通品质的指挥官使用。");
		}
		
		cityHero.setMilitarySoul(cityHero.getMilitarySoul() + 10);
		heroService.updateCityHero(cityHero);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(player.getPlayerID(), OperationLogConstant.UPGRADE_HERO_MILITARY_SOUL);
	}
	
	public void useFightSoulSilverMedal(Integer cityHeroID) {
		CityHero cityHero = heroService.getCityHero(cityHeroID);
		
		if (cityHero.getQuality() == HeroConstant.QUALITY_EPIC) {
			throw new GameException("无法使用，史诗品质的指挥官仅能使用战魂金章。");
		}
		
		int militarySoul = cityHero.getMilitarySoul();
		
		switch (cityHero.getQuality()) {
			case HeroConstant.QUALITY_NORMAL:
				militarySoul += 20;
				break;
				
			case HeroConstant.QUALITY_SINGULARITY:
				militarySoul += 10;
				break;
		}
		
		cityHero.setMilitarySoul(militarySoul);
		heroService.updateCityHero(cityHero);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(player.getPlayerID(), OperationLogConstant.UPGRADE_HERO_MILITARY_SOUL);
	}
	
	public void useFightSoulGoldMedal(Integer cityHeroID) {
		CityHero cityHero = heroService.getCityHero(cityHeroID);
		int militarySoul = cityHero.getMilitarySoul();
		
		switch (cityHero.getQuality()) {
			case HeroConstant.QUALITY_NORMAL:
				militarySoul += 30;
				break;
				
			case HeroConstant.QUALITY_SINGULARITY:
				militarySoul += 20;
				break;
						
			case HeroConstant.QUALITY_EPIC:
				militarySoul += 10;
				break;
		}
		
		cityHero.setMilitarySoul(militarySoul);
		heroService.updateCityHero(cityHero);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(player.getPlayerID(), OperationLogConstant.UPGRADE_HERO_MILITARY_SOUL);
	}
	
	public void useDeclareWarAnnouncement(String playerName) {
		Integer targetPlayerID = playerService.getPlayerIDByPlayerName(playerName);
		if (targetPlayerID == null) {
			throw new GameException("玩家" + playerName + "不存在。");
		}
		
		declareWarService.declareWarImmediately(player.getPlayerID(), targetPlayerID);
	}
	
	public void setCityHeroCommandAdd(int cityHeroID, int addCommandPoints, int days) {
		
		// 宝物分类
		int category = TreasureCategoryConstant.COMMANDER;
		
		// 宝物类型
		int type = TreasureTypeConstant.COMMANDER_COMMAND_ADD;
		
		if (this.updateTreasureQueue(cityHeroID, category, type, days * 24)) {
			CityHero cityHero = heroService.getCityHero(cityHeroID);
			cityHero.setCommand(cityHero.getCommand() + addCommandPoints);
			heroService.updateCityHero(cityHero);
			
			// 更新指挥官扩展信息
			CityHeroExt cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHeroID);
			cityHeroExt.setMilitaryAttackAdd(cityHero.getCommand()/10);
			cityHeroExt.setCommandTreasureAdd(addCommandPoints);
			heroService.updateCityHeroExt(cityHeroExt);
		}
	}
	
	public void setCityHeroDefenseAdd(int cityHeroID, int addDefensePoints, int days) {
		
		// 宝物分类
		int category = TreasureCategoryConstant.COMMANDER;
		
		// 宝物类型
		int type = TreasureTypeConstant.COMMANDER_DEFENSE_ADD;
		
		if (this.updateTreasureQueue(cityHeroID, category, type, days * 24)) {
			CityHero cityHero = heroService.getCityHero(cityHeroID);
			cityHero.setDefense(cityHero.getDefense() + addDefensePoints);
			heroService.updateCityHero(cityHero);
			
			// 更新指挥官扩展信息
			CityHeroExt cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHeroID);
			cityHeroExt.setMilitaryDefenseAdd(cityHero.getDefense()/10);
			cityHeroExt.setDefenseTreasureAdd(addDefensePoints);
			heroService.updateCityHeroExt(cityHeroExt);
		}
	}
	
	public void setCityHeroMindAdd(int cityHeroID, int addMindPoints, int days) {
		
		// 宝物分类
		int category = TreasureCategoryConstant.COMMANDER;
		
		// 宝物类型
		int type = TreasureTypeConstant.COMMANDER_MIND_ADD;
		
		if (this.updateTreasureQueue(cityHeroID, category, type, days * 24)) {
			CityHero cityHero = heroService.getCityHero(cityHeroID);
			cityHero.setMind(cityHero.getMind() + addMindPoints);
			cityHero.setStaminaMax(cityHero.getStaminaMax() + addMindPoints * HeroConstant.HERO_MIND_ADD_STAMINA);
			
			heroService.updateCityHero(cityHero);
			
			// 更新指挥官扩展信息
			CityHeroExt cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHeroID);
			cityHeroExt.setMindTreasureAdd(addMindPoints);
			heroService.updateCityHeroExt(cityHeroExt);
		}
	}
	
	public void setCityHeroExecutivepowerAdd(int cityHeroID, int addExecutivepowerPoints, int days) {
		
		// 宝物分类
		int category = TreasureCategoryConstant.COMMANDER;
		
		// 宝物类型
		int type = TreasureTypeConstant.COMMANDER_EXECUTIVEPOWER_ADD;
		
		if (this.updateTreasureQueue(cityHeroID, category, type, days * 24)) {
			CityHero cityHero = heroService.getCityHero(cityHeroID);
			cityHero.setExecutivepower(cityHero.getExecutivepower() + addExecutivepowerPoints);
			heroService.updateCityHero(cityHero);
			
			// 设置指挥官行政属性对城市资源生产的影响
			if(cityHero.getExecutivepower() > 0 && cityHero.getState() == CityHeroStateConstant.REIGN){
				City city = cityService.getCityByID(cityHero.getCityID());
				CityResource cityResource = cityService.getCityResourceByCityID(cityHero.getCityID());
				
				//资源生产的执政官加成值
				int officerAdd = cityHero.getExecutivepower() / 10;
				long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(),cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), officerAdd, cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
				long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(),cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), officerAdd, cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
				long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(),cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), officerAdd, cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
				long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(),cityResource.getWoodWorkerNum(), cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), officerAdd, cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
				long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum(), city.getPopulationFree(), city.getTax(), cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
										
				Map<String, Object> cityResourceParams = new HashMap<String, Object>();
				cityResourceParams.put("cityID", city.getCityID());
				cityResourceParams.put("woodOutput", woodOutput);
				cityResourceParams.put("foodOutput", foodOutput);
				cityResourceParams.put("steelOutput", steelOutput);
				cityResourceParams.put("oilOutput", oilOutput);
				cityResourceParams.put("moneyOutput", moneyOutput); 
				cityResourceParams.put("foodOfficerAdd", officerAdd);
				cityResourceParams.put("oilOfficerAdd", officerAdd);
				cityResourceParams.put("steelOfficerAdd", officerAdd);
				cityResourceParams.put("woodOfficerAdd", officerAdd);
				cityResourceParams.put("moneyOfficerAdd", officerAdd);
				
				cityService.updateCityResource(cityResourceParams);
			}
			
			// 更新指挥官扩展信息
			CityHeroExt cityHeroExt = heroService.getCityHeroExtByCityHeroID(cityHeroID);
			cityHeroExt.setExecutivepowerTreasureAdd(addExecutivepowerPoints);
			heroService.updateCityHeroExt(cityHeroExt);
		}
	}
	
	
	public IGameScriptService getGameScriptService() {
		return gameScriptService;
	}

	public void setGameScriptService(IGameScriptService gameScriptService) {
		this.gameScriptService = gameScriptService;
	}

	public IOrdnanceService getOrdnanceService() {
		return ordnanceService;
	}

	public void setOrdnanceService(IOrdnanceService ordnanceService) {
		this.ordnanceService = ordnanceService;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public ICityDefenseService getCityDefenseService() {
		return cityDefenseService;
	}

	public void setCityDefenseService(ICityDefenseService cityDefenseService) {
		this.cityDefenseService = cityDefenseService;
	}

	public IProcessQueueService getProcessQueueService() {
		return processQueueService;
	}

	public void setProcessQueueService(IProcessQueueService processQueueService) {
		this.processQueueService = processQueueService;
	}

	public IProductionQueueService getProductionQueueService() {
		return productionQueueService;
	}

	public void setProductionQueueService(IProductionQueueService productionQueueService) {
		this.productionQueueService = productionQueueService;
	}

	public ITreasureQueueService getTreasureQueueService() {
		return treasureQueueService;
	}

	public void setTreasureQueueService(ITreasureQueueService treasureQueueService) {
		this.treasureQueueService = treasureQueueService;
	}

	public ITradeQueueService getTradeQueueService() {
		return tradeQueueService;
	}

	public void setTradeQueueService(ITradeQueueService tradeQueueService) {
		this.tradeQueueService = tradeQueueService;
	}

	public IHeroService getHeroService() {
		return heroService;
	}

	public void setHeroService(IHeroService heroService) {
		this.heroService = heroService;
	}

	public IMilitaryService getMilitaryService() {
		return militaryService;
	}

	public void setMilitaryService(IMilitaryService militaryService) {
		this.militaryService = militaryService;
	}

	public IGuildService getGuildService() {
		return guildService;
	}

	public void setGuildService(IGuildService guildService) {
		this.guildService = guildService;
	}

	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public IDeclareWarService getDeclareWarService() {
		return declareWarService;
	}

	public void setDeclareWarService(IDeclareWarService declareWarService) {
		this.declareWarService = declareWarService;
	}

	public IPlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public IEquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(IEquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public IChatService getChatService() {
		return chatService;
	}

	public void setChatService(IChatService chatService) {
		this.chatService = chatService;
	}

	public IMarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(IMarketService marketService) {
		this.marketService = marketService;
	}

	public IMapService getMapService() {
		return mapService;
	}

	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}

	public IBattleService getBattleService() {
		return battleService;
	}

	public void setBattleService(IBattleService battleService) {
		this.battleService = battleService;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	public ITechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(ITechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	public IArmyService getArmyService() {
		return armyService;
	}

	public void setArmyService(IArmyService armyService) {
		this.armyService = armyService;
	}

}
