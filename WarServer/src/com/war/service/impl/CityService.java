package com.war.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.war.common.CacheService;
import com.war.common.DateService;
import com.war.constant.BuildingConstant;
import com.war.constant.BuildingPositionConstant;
import com.war.constant.CacheConstant;
import com.war.constant.CityBuildingStateConstant;
import com.war.constant.CityConstant;
import com.war.constant.CityStateConstant;
import com.war.constant.DefenseConstant;
import com.war.constant.MapConstant;
import com.war.constant.TreasureConstant;
import com.war.dao.IBattleDAO;
import com.war.dao.ICityBuildingDAO;
import com.war.dao.ICityDAO;
import com.war.dao.ICityDefenseDAO;
import com.war.dao.ICityExtDAO;
import com.war.dao.ICityMilitaryDAO;
import com.war.dao.ICityMilitarySuccorDAO;
import com.war.dao.ICityResourceDAO;
import com.war.dao.IDepoyQueueDAO;
import com.war.dao.IPlayerDAO;
import com.war.dao.IResTradeDAO;
import com.war.dao.ITradeQueueDAO;
import com.war.domain.Battle;
import com.war.domain.City;
import com.war.domain.CityBuilding;
import com.war.domain.CityDefense;
import com.war.domain.CityExt;
import com.war.domain.CityInfo;
import com.war.domain.CityMilitarySuccor;
import com.war.domain.CityResource;
import com.war.domain.DepoyQueue;
import com.war.domain.ResTrade;
import com.war.domain.TradeQueue;
import com.war.exception.GameException;
import com.war.service.ICityService;
import com.war.service.IMapService;
import com.war.service.IReportService;
import com.war.service.ITreasureService;
import com.war.util.ResourceCalculateUtil;

public class CityService implements ICityService {
	
	private ICityDAO cityDAO;
	
	private ICityResourceDAO cityResourceDAO;
	
	private ICityBuildingDAO cityBuildingDAO;
	
	private ICityDefenseDAO cityDefenseDAO;
	
	private ICityExtDAO cityExtDAO;
	
	private IPlayerDAO playerDAO;
	
	private ICityMilitaryDAO cityMilitaryDAO;
	
	private ITradeQueueDAO tradeQueueDAO;
	
	private IDepoyQueueDAO depoyQueueDAO;
	
	private IBattleDAO battleDAO;
	
	private ICityMilitarySuccorDAO cityMilitarySuccorDAO;
	
	private IResTradeDAO resTradeDAO;
	
	private IMapService mapService;
	
	private IReportService reportService;
	
	private ITreasureService treasureService;
	
	
	public Map<Integer, String> initCityIDCityNameMap() {
		Map<Integer, String> cityIDCityNameMap = new HashMap<Integer, String>();
		List<City> cityList = cityDAO.getCityList();
		for (int i=0;i<cityList.size();i++) {
			cityIDCityNameMap.put(cityList.get(i).getCityID(), cityList.get(i).getName());
		}
		return cityIDCityNameMap;
	}
	
	public Map<Integer, Integer> initCityIDPlayerIDMap() {
		Map<Integer, Integer> cityIDPlayerIDMap = new HashMap<Integer, Integer>();
		List<City> cityList = cityDAO.getCityList();
		for (int i=0;i<cityList.size();i++) {
			cityIDPlayerIDMap.put(cityList.get(i).getCityID(), cityList.get(i).getPlayerID());
		}
		return cityIDPlayerIDMap;
	}
	
	public Map<Integer, Integer> initPlayerIDCityIDMap() {
		Map<Integer, Integer> playerIDCityIDMap = new HashMap<Integer, Integer>();
		List<City> cityList = cityDAO.getCityList();
		for (int i=0;i<cityList.size();i++) {
			playerIDCityIDMap.put(cityList.get(i).getPlayerID(), cityList.get(i).getCityID());
		}
		return playerIDCityIDMap;
	}
	
	@SuppressWarnings("unchecked")
	public Integer createCity(Integer playerID, String name, Integer mapArea) {
		
		// 获得可建新城的空地
		com.war.domain.Map map = mapService.getAreaBlankMap(mapArea);
		
		if (map==null) {
			throw new GameException("该区域玩家数量已达到上限，获取地图信息失败。");
		}
		
		// 创建城市信息
		City city = new City();
		
		city.setCityID(playerID);
		city.setPlayerID(playerID);
		city.setMapID(map.getMapID());
		city.setPosX(map.getPosX());
		city.setPosY(map.getPosY());
		city.setName(name);
		
		city.setState(CityStateConstant.FRESHMAN);
		city.setConstructionPoint(0L);
		city.setTechnologyPoint(0L);
		city.setPopulationFree(CityConstant.INIT_CITIZEN_NUM);
		city.setPopulationTotal(CityConstant.INIT_CITIZEN_NUM);
		city.setPopulationMax(CityConstant.INIT_CITIZEN_NUM);
		city.setRecruitNum(0);
		city.setTax(10);
		city.setSecurity(90);
		city.setBusinessmanFree(0);
		
		Integer cityID = cityDAO.createCity(city);
		
		// 创建城市扩展信息
		CityExt cityExt = new CityExt();
		
		cityExt.setCityID(cityID);
		
		cityExt.setTechArmyAttack(0);
		cityExt.setTechArmyDefense(0);
		cityExt.setTechArmySpeed(0);
		cityExt.setTechArmyRange(0);
		
		cityExt.setTechTruckAttack(0);
		cityExt.setTechTruckDefense(0);
		cityExt.setTechTruckSpeed(0);
		cityExt.setTechTruckRange(0);
		
		cityExt.setTechAirplaneAttack(0);
		cityExt.setTechAirplaneDefense(0);
		cityExt.setTechAirplaneSpeed(0);
		cityExt.setTechAirplaneRange(0);
		
		cityExt.setTechCarryAdd(0);
		cityExt.setTechDefenseAttackAdd(0);
		cityExt.setTechWoundedArmyRate(0);
		cityExt.setTechProtectResourcePercent(0);
		
		cityExt.setLastManageTime(DateService.getCurrentUtilDate());
		
		cityExtDAO.createCityExt(cityExt);
		
		// 创建城市资源信息
		CityResource cityResource = new CityResource();
		cityResource.setCityID(cityID);
		cityResource.setResourceNumMax(5000L);
		cityResource.setWoodNum(3000L);
		cityResource.setWoodOutput(CityConstant.BASE_WOOD_OUTPUT);
		cityResource.setWoodWorkerNum(0);
		cityResource.setWoodBuildingAdd(0);
		cityResource.setWoodTechAdd(0);
		cityResource.setWoodFieldAdd(0);
		cityResource.setWoodOfficerAdd(0);
		cityResource.setWoodGuildAdd(0);
		cityResource.setWoodTreasureAdd(0);
		cityResource.setSteelNum(3000L);
		cityResource.setSteelOutput(CityConstant.BASE_STEEL_OUTPUT);
		cityResource.setSteelWorkerNum(0);
		cityResource.setSteelBuildingAdd(0);
		cityResource.setSteelTechAdd(0);
		cityResource.setSteelFieldAdd(0);
		cityResource.setSteelOfficerAdd(0);
		cityResource.setSteelGuildAdd(0);
		cityResource.setSteelTreasureAdd(0);
		cityResource.setOilNum(3000L);
		cityResource.setOilOutput(CityConstant.BASE_OIL_OUTPUT);
		cityResource.setOilWorkerNum(0);
		cityResource.setOilBuildingAdd(0);
		cityResource.setOilTechAdd(0);
		cityResource.setOilFieldAdd(0);
		cityResource.setOilOfficerAdd(0);
		cityResource.setOilGuildAdd(0);
		cityResource.setOilTreasureAdd(0);
		cityResource.setOilConsume(0L);
		cityResource.setFoodNum(3000L);
		cityResource.setFoodOutput(CityConstant.BASE_FOOD_OUTPUT);
		cityResource.setFoodWorkerNum(0);
		cityResource.setFoodBuildingAdd(0);
		cityResource.setFoodTechAdd(0);
		cityResource.setFoodFieldAdd(0);
		cityResource.setFoodOfficerAdd(0);
		cityResource.setFoodGuildAdd(0);
		cityResource.setFoodTreasureAdd(0);
		cityResource.setFoodConsume(50L);
		cityResource.setMoneyNum(3000L);
		cityResource.setMoneyOutput(CityConstant.BASE_MONEY_OUTPUT);
		cityResource.setMoneyTechAdd(0);
		cityResource.setMoneyFieldAdd(0);
		cityResource.setMoneyOfficerAdd(0);
		cityResource.setMoneyGuildAdd(0);
		cityResource.setMoneyTreasureAdd(0);
		cityResource.setMoneyConsume(0L);
		
		cityResourceDAO.createCityResource(cityResource);
		
		// 初始化城市基本建筑信息
		
		// 城镇中心
		CityBuilding cb = new CityBuilding();
		cb.setCityID(cityID);
		cb.setLevel(1);
		cb.setState(CityBuildingStateConstant.NORMAL);
		cb.setBuildingID(BuildingConstant.CITY_CENTER);
		cb.setPosition(BuildingPositionConstant.CITYCENTER);
		cityBuildingDAO.createCityBuilding(cb);
		
		// 围墙
		CityDefense fence = new CityDefense();
		fence.setCityID(cityID);
		fence.setDefenseID(DefenseConstant.FENCE);
		fence.setNum(0);
		cityDefenseDAO.createCityDefense(fence);
		
		// 碉堡
		CityDefense bunker = new CityDefense();
		bunker.setCityID(cityID);
		bunker.setDefenseID(DefenseConstant.BUNKER);
		bunker.setNum(0);
		cityDefenseDAO.createCityDefense(bunker);
		
		// 火炮
		CityDefense gun = new CityDefense();
		gun.setCityID(cityID);
		gun.setDefenseID(DefenseConstant.GUN);
		gun.setNum(0);
		cityDefenseDAO.createCityDefense(gun);

		// 防空火炮
		CityDefense antiguns = new CityDefense();
		antiguns.setCityID(cityID);
		antiguns.setDefenseID(DefenseConstant.ANTIGUN);
		antiguns.setNum(0);
		cityDefenseDAO.createCityDefense(antiguns);

		// 更新地图信息
		map.setCategory(MapConstant.CATEGORY_CITY);
		map.setTargetID(cityID);
		map.setType(MapConstant.TYPE_CITY);
		map.setState(MapConstant.STATE_NORMAL);
		mapService.updateMap(map);
		
		// 添加城市编号城市名称Map缓存
		((Map<Integer, String>)CacheService.getFromCache(CacheConstant.CITYID_CITYNAME_MAP)).put(cityID, name);
		// 添加城市编号玩家编号Map缓存
		((Map<Integer, Integer>)CacheService.getFromCache(CacheConstant.CITYID_PLAYERID_MAP)).put(cityID, playerID);
		// 添加玩家编号城市编号Map缓存
		((Map<Integer, Integer>)CacheService.getFromCache(CacheConstant.PLAYERID_CITYID_MAP)).put(playerID, cityID);
		
		return cityID;
	}
	
	public void updateCityPopulationMax(Integer cityID, Long populationMax) {
		cityDAO.updatePopulationMaxByCityID(cityID, populationMax);
	}
	
	public void updateCityResources(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long money) {
		cityResourceDAO.updateResourcesByCityID(cityID, woodNum, steelNum, oilNum, foodNum, money);
	}
	
	public void updateCityResourcesMax(Integer cityID, Long resourceNumMax) {
		cityResourceDAO.updateResourcesMaxByCityID(cityID, resourceNumMax);
	}
	
	public void updateCityResourcesOutput(Integer cityID, Long woodOutput, Long steelOutput, Long oilOutput, Long foodOutput) {
		cityResourceDAO.updateResourcesOutputByCityID(cityID, woodOutput, steelOutput, oilOutput, foodOutput);
	}
	
	public void updateCityResourceConsumeByCityID(Integer cityID, Long oilConsume, Long foodConsume, Long moneyConsume) {
		cityResourceDAO.updateResourceConsumeByCityID(cityID, oilConsume, foodConsume, moneyConsume);
	}

	public void handleBatchAddCitySecurity() {
		cityDAO.batchAddCitySecurity();
	}
	
	public void changeCityState(Integer cityID, Integer state) {
		cityDAO.updateStateByCityID(cityID, state);
	}

	public void addCityResources(int cityID, long woodNum, long steelNum, long oilNum, long foodNum, long moneyNum){
		
		Map<String,Long> resourcesNumMap = cityResourceDAO.getResourcesNumByCityID(cityID);
		Long resrouceNumMax = cityResourceDAO.getResourcesNumMaxByCityID(cityID);
		
		//资源上限判断
		if(resourcesNumMap.get("woodNum") + woodNum > resrouceNumMax){
			woodNum = resrouceNumMax - resourcesNumMap.get("woodNum");
		}
		if(resourcesNumMap.get("steelNum") + steelNum > resrouceNumMax){
			steelNum = resrouceNumMax - resourcesNumMap.get("steelNum");
		}
		if(resourcesNumMap.get("oilNum") + oilNum > resrouceNumMax){
			oilNum = resrouceNumMax - resourcesNumMap.get("oilNum");
		}
		if(resourcesNumMap.get("foodNum") + foodNum > resrouceNumMax){
			foodNum = resrouceNumMax - resourcesNumMap.get("foodNum");
		}
		
		cityResourceDAO.addCityResourcesByCityID(cityID, woodNum, steelNum, oilNum, foodNum, moneyNum);
	}
    
    public void minusCityResources(int cityID, long woodNum, long steelNum, long oilNum, long foodNum, long moneyNum){
    	
    	Map<String,Long> resourcesNumMap = cityResourceDAO.getResourcesNumByCityID(cityID);
    	
    	if(woodNum>resourcesNumMap.get("woodNum")){
    		throw new GameException("城市木材不足。");
    	}
    	if(steelNum>resourcesNumMap.get("steelNum")){
    		throw new GameException("城市钢铁不足。");
    	}
    	if(oilNum>resourcesNumMap.get("oilNum")){
    		throw new GameException("城市石油不足。");
    	}
    	if(foodNum>resourcesNumMap.get("foodNum")){
    		throw new GameException("城市食物不足。");
    	}
    	if(moneyNum>resourcesNumMap.get("moneyNum")){
    		throw new GameException("城市金钱不足。");
    	}
    	
    	cityResourceDAO.minusCityResourcesByCityID(cityID, woodNum, steelNum, oilNum, foodNum, moneyNum);
    }
    
    public void minusCityResourcesClear(int cityID, long woodNum, long steelNum, long oilNum, long foodNum, long moneyNum){
    	
    	Map<String,Long> resourcesNumMap = cityResourceDAO.getResourcesNumByCityID(cityID);
    	
    	if(woodNum>resourcesNumMap.get("woodNum")){
    		woodNum = resourcesNumMap.get("woodNum");
    	}
    	if(steelNum>resourcesNumMap.get("steelNum")){
    		steelNum = resourcesNumMap.get("steelNum");
    	}
    	if(oilNum>resourcesNumMap.get("oilNum")){
    		oilNum = resourcesNumMap.get("oilNum");
    	}
    	if(foodNum>resourcesNumMap.get("foodNum")){
    		foodNum = resourcesNumMap.get("foodNum");
    	}
    	if(moneyNum>resourcesNumMap.get("moneyNum")){
    		moneyNum = resourcesNumMap.get("moneyNum");
    	}
    	
    	cityResourceDAO.minusCityResourcesByCityID(cityID, woodNum, steelNum, oilNum, foodNum, moneyNum);
    }
    
	public void updateCity(City city) {
		cityDAO.updateCity(city);
	}
	
	public Map<String, Long> getCityResourcesNum(Integer cityID) {
		return cityResourceDAO.getResourcesNumByCityID(cityID);
	}
	
	public Long getCityResourcesNumMax(Integer cityID){
		return cityResourceDAO.getResourcesNumMaxByCityID(cityID);
	}
	
	public Map<String,Long> getCityResourcesOutput(Integer cityID){
		return cityResourceDAO.getResourcesOutputByCityID(cityID);
	}
	
	public Map<String,Long> getCityResourcesConsume(Integer cityID){
		return cityResourceDAO.getResourcesConsumeByCityID(cityID);
	}
	
	public Map<String,Long> getCityPopulation(Integer cityID){
		return cityDAO.getPopulationByCityID(cityID);
	}
	
	public Integer getCityBusinessFree(Integer cityID){
		return cityDAO.getBusinessFreeByCityID(cityID);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getPlayerIDByCityID(Integer cityID){
		return ((Map<Integer, Integer>)CacheService.getFromCache(CacheConstant.CITYID_PLAYERID_MAP)).get(cityID);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getCityIDByPlayerID(Integer playerID){
		return ((Map<Integer, Integer>)CacheService.getFromCache(CacheConstant.PLAYERID_CITYID_MAP)).get(playerID);
	}
	
	public Integer getCityIDByCityName(String cityName){
		return cityDAO.getCityIDByCityName(cityName);
	}
	
	@SuppressWarnings("unchecked")
	public String getCityNameByCityID(Integer cityID){
		return ((Map<Integer, String>)CacheService.getFromCache(CacheConstant.CITYID_CITYNAME_MAP)).get(cityID);
	}
	
	public City getCityByPlayerID(Integer playerID){
		return cityDAO.getCityByPlayerID(playerID);
	}
	
	public City getCityWithCityResourceByPlayerID(Integer playerID) {
		City city = cityDAO.getCityByPlayerID(playerID);
		city.setCityResource(cityResourceDAO.getCityResourceByCityID(city.getCityID()));
		return city;
	}
	
	public Integer getCityIDByCityPos(Integer posX,Integer posY){
		return cityDAO.getCityIDByPosXAndPosY(posX, posY);
	}
	
	public Map<String,Integer> getCityPosByCityID(Integer cityID){
		return cityDAO.getCityPosXAndPosYByCityID(cityID);
	}
	
	public City getCityByID(Integer cityID) {
		return cityDAO.getCityByID(cityID);
	}
	
	public City getCityWithCityResourceByID(Integer cityID) {
		City city = cityDAO.getCityByID(cityID);
		city.setCityResource(cityResourceDAO.getCityResourceByCityID(cityID));
		return city;
	}
	
	public CityInfo getCityInfoByCityID(Integer cityID){
		CityInfo cityInfo = cityDAO.getCityInfoByCityID(cityID);
		cityInfo.setPlayerName(this.getPlayerNameByPlayerID(cityInfo.getPlayerID()));
		return cityInfo;
	}
	
	public Map<String,Integer> getCityTaxAndSecurity(Integer cityID){
		return cityDAO.getCityTaxAndSecurity(cityID);
	}
	
	public List<City> getCityList() {
		return cityDAO.getCityList();
	}

	public void computeCityResource() {
		cityResourceDAO.computeCityResource();
	}

	public void handleCitySecurityEffect(){
		
		List<City> cityList = cityDAO.getCityList();
		
		for(int i=0;i<cityList.size();i++){
			
			//城市治安影响处理
			if(cityList.get(i).getSecurity()<40){
				if(cityList.get(i).getSecurity()<40 && cityList.get(i).getSecurity()>=30){
					minusCityPopulation(cityList.get(i),10);
					continue;
				}
				
				if(cityList.get(i).getSecurity()<30 && cityList.get(i).getSecurity()>=20){
					minusCityPopulation(cityList.get(i),20);
					continue;
				}
				
				if(cityList.get(i).getSecurity()<20){
					minusCityPopulation(cityList.get(i),40);
					continue;
				}
			}
			
		}
		
	}
	
	public void handleCityResourceEffect(){
		cityDAO.updateSecurityOfResourceEffect();
	}
	
	private void minusCityPopulation(City city,int minusPopulation){
		
		Map<String,Object> cityParams = new HashMap<String,Object>();
		Map<String,Object> cityResourceParams = new HashMap<String, Object>();
		CityResource cityResource = cityResourceDAO.getCityResourceByCityID(city.getCityID());
		cityParams.put("cityID", city.getCityID());
		cityResourceParams.put("cityID", city.getCityID());
		if(city.getPopulationFree()>=minusPopulation){
			//如果空闲人口大于等于减少人口，则扣除空闲人口
			cityParams.put("populationFree",city.getPopulationFree()-minusPopulation);
			cityParams.put("populationTotal", Math.max(city.getPopulationTotal()-minusPopulation,0));
			cityResourceParams.put("foodConsume", cityResource.getFoodConsume()-minusPopulation);
			reportService.sendOtherReport(city.getPlayerID(), "城市人口报告", "由于您的城市治安值过低，民心散乱，共有" + minusPopulation + "名空闲市民逃离城市。\n请您尽快采取措施提高城市治安。");
			long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getSteelWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getFoodWorkerNum(),((Long)city.getPopulationFree()-minusPopulation), 
					city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
			cityResourceParams.put("moneyOutput", moneyOutput);
		}else{
			
			//实际流失人口数量
			int lapsedPopulation = 0;
			//仍需流失工人数量
			int requiredLapsedWorkerPopulation = 0;
			//各项流失人口
			int lapsedFreePopulation=0,lapsedWoodWorker=0,lapsedSteelWorker=0,lapsedOilWorker=0,lapsedFoodWorker=0;
			if(city.getPopulationFree()>0){
				lapsedPopulation = lapsedPopulation+city.getPopulationFree().intValue();
				requiredLapsedWorkerPopulation = minusPopulation-lapsedPopulation;
				lapsedFreePopulation = city.getPopulationFree().intValue();
				cityParams.put("populationFree",0);
			}else{
				requiredLapsedWorkerPopulation = minusPopulation;
			}
			
			if(requiredLapsedWorkerPopulation>0){
				if(cityResource.getWoodWorkerNum()>0){
					if(cityResource.getWoodWorkerNum()>=requiredLapsedWorkerPopulation){
						lapsedPopulation = lapsedPopulation+requiredLapsedWorkerPopulation;
						lapsedWoodWorker = requiredLapsedWorkerPopulation;
						cityResourceParams.put("woodWorkerNum", cityResource.getWoodWorkerNum()-lapsedWoodWorker);
						long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(), cityResource.getWoodWorkerNum()-lapsedWoodWorker, cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
						cityResourceParams.put("woodOutput", woodOutput);
						requiredLapsedWorkerPopulation = 0;
					}else{
						requiredLapsedWorkerPopulation = requiredLapsedWorkerPopulation-cityResource.getWoodWorkerNum();
						lapsedPopulation = lapsedPopulation+lapsedPopulation+cityResource.getWoodWorkerNum();
						lapsedWoodWorker = cityResource.getWoodWorkerNum();
						cityResourceParams.put("woodWorkerNum", 0);
						long woodOutput = ResourceCalculateUtil.calculateWoodOutput(city.getTax(), 0, cityResource.getWoodBuildingAdd(), cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
						cityResourceParams.put("woodOutput", woodOutput);
					}
				}
			}
			
			if(requiredLapsedWorkerPopulation>0){
				if(cityResource.getSteelWorkerNum()>0){
					if(cityResource.getSteelWorkerNum()>=requiredLapsedWorkerPopulation){
						lapsedPopulation = lapsedPopulation+requiredLapsedWorkerPopulation;
						lapsedSteelWorker = requiredLapsedWorkerPopulation;
						cityResourceParams.put("steelWorkerNum", cityResource.getSteelWorkerNum()-lapsedSteelWorker);
						long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(), cityResource.getSteelWorkerNum()-lapsedSteelWorker, cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
						cityResourceParams.put("steelOutput", steelOutput);
						requiredLapsedWorkerPopulation = 0;
					}else{
						requiredLapsedWorkerPopulation = requiredLapsedWorkerPopulation-cityResource.getSteelWorkerNum();
						lapsedPopulation = lapsedPopulation+lapsedPopulation+cityResource.getSteelWorkerNum();
						lapsedSteelWorker = cityResource.getSteelWorkerNum();
						cityResourceParams.put("steelWorkerNum", 0);
						long steelOutput = ResourceCalculateUtil.calculateSteelOutput(city.getTax(), 0, cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
						cityResourceParams.put("steelOutput", steelOutput);
					}
				}
			}
			
			if(requiredLapsedWorkerPopulation>0){
				if(cityResource.getOilWorkerNum()>0){
					if(cityResource.getOilWorkerNum()>=requiredLapsedWorkerPopulation){
						lapsedPopulation = lapsedPopulation+requiredLapsedWorkerPopulation;
						lapsedOilWorker = requiredLapsedWorkerPopulation;
						cityResourceParams.put("oilWorkerNum", cityResource.getOilWorkerNum()-lapsedOilWorker);
						long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(), cityResource.getOilWorkerNum()-lapsedOilWorker, cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
						cityResourceParams.put("oilOutput", oilOutput);
						requiredLapsedWorkerPopulation = 0;
					}else{
						requiredLapsedWorkerPopulation = requiredLapsedWorkerPopulation-cityResource.getOilWorkerNum();
						lapsedPopulation = lapsedPopulation+lapsedPopulation+cityResource.getOilWorkerNum();
						lapsedOilWorker = cityResource.getOilWorkerNum();
						cityResourceParams.put("oilWorkerNum", 0);
						long oilOutput = ResourceCalculateUtil.calculateOilOutput(city.getTax(), 0, cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
						cityResourceParams.put("oilOutput", oilOutput);
					}
				}
			}
			
			if(requiredLapsedWorkerPopulation>0){
				if(cityResource.getFoodWorkerNum()>0){
					if(cityResource.getFoodWorkerNum()>=requiredLapsedWorkerPopulation){
						lapsedPopulation = lapsedPopulation+requiredLapsedWorkerPopulation;
						lapsedFoodWorker = requiredLapsedWorkerPopulation;
						cityResourceParams.put("foodWorkerNum", cityResource.getFoodWorkerNum()-lapsedFoodWorker);
						long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(), cityResource.getFoodWorkerNum()-lapsedFoodWorker, cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
						cityResourceParams.put("foodOutput", foodOutput);
						requiredLapsedWorkerPopulation = 0;
					}else{
						requiredLapsedWorkerPopulation = requiredLapsedWorkerPopulation-cityResource.getFoodWorkerNum();
						lapsedPopulation = lapsedPopulation+lapsedPopulation+cityResource.getFoodWorkerNum();
						lapsedFoodWorker = cityResource.getFoodWorkerNum();
						cityResourceParams.put("foodWorkerNum", 0);
						long foodOutput = ResourceCalculateUtil.calculateFoodOutput(city.getTax(), 0, cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
						cityResourceParams.put("foodOutput", foodOutput);
					}
				}
			}
			
			cityParams.put("populationTotal",Math.max(city.getPopulationTotal()-lapsedPopulation,0));
			cityResourceParams.put("foodConsume", cityResource.getFoodConsume()-lapsedPopulation);
			StringBuffer reportSB = new StringBuffer();
			reportSB.append("由于您的城市治安值过低，民心散乱，");
			if(lapsedPopulation>0){
				reportSB.append("共有");
				if(lapsedFreePopulation>0){
					reportSB.append(" ");
					reportSB.append(lapsedFreePopulation);
					reportSB.append("名空闲市民");
					reportSB.append(" ");
				}
				if(lapsedWoodWorker>0){
					reportSB.append(" ");
					reportSB.append(lapsedWoodWorker);
					reportSB.append("名木材工人");
					reportSB.append(" ");
				}
				if(lapsedSteelWorker>0){
					reportSB.append(" ");
					reportSB.append(lapsedSteelWorker);
					reportSB.append("名钢铁工人");
					reportSB.append(" ");
				}
				if(lapsedOilWorker>0){
					reportSB.append(" ");
					reportSB.append(lapsedOilWorker);
					reportSB.append("名石油工人");
					reportSB.append(" ");
				}
				if(lapsedFoodWorker>0){
					reportSB.append(" ");
					reportSB.append(lapsedFoodWorker);
					reportSB.append("名食物工人");
					reportSB.append(" ");
				}
				reportSB.append("逃离城市。");
			}else{
				reportSB.append("您的城市目前已经没有空闲市民及工作市民了。");
			}
			reportSB.append("\n请您尽快采取措施提高城市治安。");
			reportService.sendOtherReport(city.getPlayerID(), "城市人口报告", reportSB.toString());
			long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()-lapsedWoodWorker+cityResource.getSteelWorkerNum()-lapsedSteelWorker+cityResource.getOilWorkerNum()-lapsedOilWorker+cityResource.getFoodWorkerNum()-lapsedFoodWorker,((Long)(city.getPopulationFree()-lapsedFreePopulation)), 
					city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
			cityResourceParams.put("moneyOutput", moneyOutput);
		}
		
		cityDAO.updateCity(cityParams);
		cityResourceDAO.updateCityResource(cityResourceParams);
		
	}
	
    public void updateCity(java.util.Map<String, Object> params){
    	cityDAO.updateCity(params);
    }
    
    public void moveCity(Integer cityID, Integer mapArea) {
    	
    	List<DepoyQueue> depoyQueueList = depoyQueueDAO.getDepoyQueueListByCityID(cityID);
    	if (depoyQueueList != null && depoyQueueList.size() > 0) {
    		throw new GameException("您的城市还有军队在外，无法进行迁城操作。");
		}
    	
    	List<DepoyQueue> beDepoyedQueueList = depoyQueueDAO.getBeDepoyedQueueListByCityID(cityID);
    	if (beDepoyedQueueList != null && beDepoyedQueueList.size() > 0) {
    		throw new GameException("您的城市正处于战争状态，无法进行迁城操作。");
		}
    	
    	List<Battle> battleList = battleDAO.getBattleListByCityID(cityID);
    	if (battleList != null && battleList.size() > 0) {
    		throw new GameException("您的城市正处于战争状态，无法进行迁城操作。");
		}
    	
    	List<ResTrade> resTradeList = resTradeDAO.getResourceSalesListByCityID(cityID);
    	if (resTradeList != null && resTradeList.size() > 0) {
    		throw new GameException("您的城市还有挂单资源，无法进行迁城操作。");
		}
    	
    	List<TradeQueue> tradeQueueList = tradeQueueDAO.getTradeQueueListByCityID(cityID);
    	if (tradeQueueList != null && tradeQueueList.size() > 0) {
    		throw new GameException("您的城市还有商人正在运输，无法进行迁城操作。");
		}
    	
    	List<CityMilitarySuccor> cityMilitarySuccorList = cityMilitarySuccorDAO.getCityMilitarySuccorListByTargetCityID(cityID);
    	if (cityMilitarySuccorList != null && cityMilitarySuccorList.size() > 0) {
    		throw new GameException("您的城市还有援军驻扎，无法进行迁城操作。");
		}
    	
		City city = cityDAO.getCityByID(cityID);
		
		com.war.domain.Map targetMap = mapService.getAreaBlankMap(mapArea);
		if(targetMap == null){
			throw new GameException("该区域玩家人数已满，请选择其他区域。");
		}
		
		// 当前Map信息
		com.war.domain.Map currentMap = mapService.getMapByPos(city.getPosX(), city.getPosY());

		// 更新当前Map信息
		currentMap.setState(MapConstant.STATE_NORMAL);
		currentMap.setType(MapConstant.TYPE_BLANK_FIELD_ARRAY[0]);
		currentMap.setTargetID(null);
		currentMap.setCategory(MapConstant.CATEGORY_BLANK_FIELD);
		mapService.updateMap(currentMap);
		
		// 更新目标Map信息
		targetMap.setCategory(MapConstant.CATEGORY_CITY);
		targetMap.setType(MapConstant.TYPE_CITY);
		targetMap.setState(MapConstant.STATE_NORMAL);
		targetMap.setTargetID(cityID);
		mapService.updateMap(targetMap);
		
		java.util.Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("posX", targetMap.getPosX());
		params.put("posY", targetMap.getPosY());
		// 更新城市地图信息
		this.updateCity(params);
	}
	
	public void moveCityToTargetPosition(Integer cityID, Integer targetPosX, Integer targetPosY) {
		
		com.war.domain.Map targetMap = mapService.getMapByPos(targetPosX, targetPosY);
		
		if (targetMap == null) {
			throw new GameException("目标地图不存在。");
		}

		if (targetMap.getCategory() != MapConstant.CATEGORY_BLANK_FIELD) {
			throw new GameException("目标地图只可为空地。");
		}
		
		List<DepoyQueue> depoyQueueList = depoyQueueDAO.getDepoyQueueListByCityID(cityID);
    	if (depoyQueueList != null && depoyQueueList.size() > 0) {
    		throw new GameException("您的城市还有军队在外，无法进行迁城操作。");
		}
    	
    	List<DepoyQueue> beDepoyedQueueList = depoyQueueDAO.getBeDepoyedQueueListByCityID(cityID);
    	if (beDepoyedQueueList != null && beDepoyedQueueList.size() > 0) {
    		throw new GameException("您的城市正处于战争状态，无法进行迁城操作。");
		}
    	
    	List<Battle> battleList = battleDAO.getBattleListByCityID(cityID);
    	if (battleList != null && battleList.size() > 0) {
    		throw new GameException("您的城市正处于战争状态，无法进行迁城操作。");
		}
    	
    	List<ResTrade> resTradeList = resTradeDAO.getResourceSalesListByCityID(cityID);
    	if (resTradeList != null && resTradeList.size() > 0) {
    		throw new GameException("您的城市还有挂单资源，无法进行迁城操作。");
		}
    	
    	List<TradeQueue> tradeQueueList = tradeQueueDAO.getTradeQueueListByCityID(cityID);
    	if (tradeQueueList != null && tradeQueueList.size() > 0) {
    		throw new GameException("您的城市还有商人正在运输，无法进行迁城操作。");
		}
    	
    	List<CityMilitarySuccor> cityMilitarySuccorList = cityMilitarySuccorDAO.getCityMilitarySuccorListByTargetCityID(cityID);
    	if (cityMilitarySuccorList != null && cityMilitarySuccorList.size() > 0) {
    		throw new GameException("您的城市还有援军驻扎，无法进行迁城操作。");
		}
		
		// 当前Map信息
		com.war.domain.Map currentMap = mapService.getMapByTargetIDAndCategory(cityID, MapConstant.CATEGORY_CITY);
		
		currentMap.setState(MapConstant.STATE_NORMAL);
		currentMap.setType(MapConstant.TYPE_BLANK_FIELD_ARRAY[0]);
		currentMap.setTargetID(null);
		currentMap.setCategory(MapConstant.CATEGORY_BLANK_FIELD);
		// 更新当前Map信息
		mapService.updateMap(currentMap);
		
		targetMap.setCategory(MapConstant.CATEGORY_CITY);
		targetMap.setType(MapConstant.TYPE_CITY);
		targetMap.setState(MapConstant.STATE_NORMAL);
		targetMap.setTargetID(cityID);
		// 更新目标Map信息
		mapService.updateMap(targetMap);
		
		java.util.Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityID);
		params.put("posX", targetMap.getPosX());
		params.put("posY", targetMap.getPosY());
		// 更新城市地图信息
		this.updateCity(params);
	}
	
	public void exchangeCityResources(Integer cityID, Long exchangedWoodNum, Long exchangedSteelNum, Long exchangedOilNum, Long exchangedFoodNum) {
		
		City city = cityDAO.getCityByID(cityID);
		CityResource cityResource = cityResourceDAO.getCityResourceByCityID(cityID);
		Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		
		//判断数量是否合法
		long totalNum = exchangedWoodNum + exchangedSteelNum + (exchangedOilNum + (long)Math.floor(exchangedFoodNum/3));
		if(totalNum!=0) {
			throw new GameException("资源数量有误，请重新分配。");
		}
		
		//扣除玩家宝物
		treasureService.decreasePlayerTreasure(city.getPlayerID(), TreasureConstant.RESOURCE_EXCHANGE_CARD, 1);
		
		long woodNum = Math.min(cityResource.getWoodNum()+exchangedWoodNum, cityResource.getResourceNumMax());
		long steelNum = Math.min(cityResource.getSteelNum()+exchangedSteelNum, cityResource.getResourceNumMax());
		long oilNum = Math.min(cityResource.getOilNum()+exchangedOilNum, cityResource.getResourceNumMax());
		long foodNum = Math.min(cityResource.getFoodNum()+exchangedFoodNum, cityResource.getResourceNumMax());
		
		cityResourceParams.put("cityID", cityID);
		cityResourceParams.put("woodNum", woodNum);
		cityResourceParams.put("steelNum", steelNum);
		cityResourceParams.put("oilNum", oilNum);
		cityResourceParams.put("foodNum", foodNum);
		cityResourceDAO.updateCityResource(cityResourceParams);
		
	}
	
	public void updateCityExt(java.util.Map<String,Object> params){
		cityExtDAO.updateCityExtParams(params);
	}
	
	public CityExt getCityExt(Integer cityID){
		return cityExtDAO.getCityExtByID(cityID);
	}
	
	public boolean isCityNameExisted(String cityName){
		
		Integer cityID = cityDAO.getCityIDByCityName(cityName);
		
		if(cityID==null)
			return false;
		else
			return true;
	}
	
	public void updateCityResource(CityResource cityResource) {
		cityResourceDAO.updateCityResource(cityResource);
	}
	
	public CityResource getCityResourceByCityID(Integer cityID) {
		return cityResourceDAO.getCityResourceByCityID(cityID);
	}
	
    public void updateCityResource(java.util.Map<String, Object> params){
    	cityResourceDAO.updateCityResource(params);
    }

	@SuppressWarnings("unchecked")
	public List<CityResource> getCityResourceList() {
		return cityResourceDAO.getCityResourceList();
	}
	
	@SuppressWarnings("unchecked")
	private String getPlayerNameByPlayerID(Integer playerID) {
		return ((Map<Integer, String>)CacheService.getFromCache(CacheConstant.PLAYERID_PLAYERNAME_MAP)).get(playerID);
	}
	
	public City getCityByPosXAndPosY(Integer posX, Integer posY) {
		return cityDAO.getCityByPosXAndPosY(posX, posY);
	}
	
	
	public ICityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(ICityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	public ICityResourceDAO getCityResourceDAO() {
		return cityResourceDAO;
	}

	public void setCityResourceDAO(ICityResourceDAO cityResourceDAO) {
		this.cityResourceDAO = cityResourceDAO;
	}

	public ICityBuildingDAO getCityBuildingDAO() {
		return cityBuildingDAO;
	}

	public void setCityBuildingDAO(ICityBuildingDAO cityBuildingDAO) {
		this.cityBuildingDAO = cityBuildingDAO;
	}

	public ICityDefenseDAO getCityDefenseDAO() {
		return cityDefenseDAO;
	}

	public void setCityDefenseDAO(ICityDefenseDAO cityDefenseDAO) {
		this.cityDefenseDAO = cityDefenseDAO;
	}

	public ICityExtDAO getCityExtDAO() {
		return cityExtDAO;
	}

	public void setCityExtDAO(ICityExtDAO cityExtDAO) {
		this.cityExtDAO = cityExtDAO;
	}
	
	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}
	
	public ICityMilitaryDAO getCityMilitaryDAO() {
		return cityMilitaryDAO;
	}

	public void setCityMilitaryDAO(ICityMilitaryDAO cityMilitaryDAO) {
		this.cityMilitaryDAO = cityMilitaryDAO;
	}

	public ITradeQueueDAO getTradeQueueDAO() {
		return tradeQueueDAO;
	}

	public void setTradeQueueDAO(ITradeQueueDAO tradeQueueDAO) {
		this.tradeQueueDAO = tradeQueueDAO;
	}

	public IDepoyQueueDAO getDepoyQueueDAO() {
		return depoyQueueDAO;
	}

	public void setDepoyQueueDAO(IDepoyQueueDAO depoyQueueDAO) {
		this.depoyQueueDAO = depoyQueueDAO;
	}

	public IBattleDAO getBattleDAO() {
		return battleDAO;
	}

	public void setBattleDAO(IBattleDAO battleDAO) {
		this.battleDAO = battleDAO;
	}
	
	public ICityMilitarySuccorDAO getCityMilitarySuccorDAO() {
		return cityMilitarySuccorDAO;
	}

	public void setCityMilitarySuccorDAO(ICityMilitarySuccorDAO cityMilitarySuccorDAO) {
		this.cityMilitarySuccorDAO = cityMilitarySuccorDAO;
	}

	public IResTradeDAO getResTradeDAO() {
		return resTradeDAO;
	}

	public void setResTradeDAO(IResTradeDAO resTradeDAO) {
		this.resTradeDAO = resTradeDAO;
	}

	public IMapService getMapService() {
		return mapService;
	}

	public void setMapService(IMapService mapService) {
		this.mapService = mapService;
	}
	
	public IReportService getReportService() {
		return reportService;
	}

	public void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

}
