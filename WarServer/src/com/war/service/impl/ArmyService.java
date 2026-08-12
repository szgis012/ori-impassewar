package com.war.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.war.common.CacheService;
import com.war.common.GameConfig;
import com.war.constant.ArmyConstant;
import com.war.constant.CacheConstant;
import com.war.constant.ConstraintDependTypeConstant;
import com.war.constant.GameConstant;
import com.war.constant.OperationLogConstant;
import com.war.dao.IArmyDAO;
import com.war.dao.IArmyDependDAO;
import com.war.dao.ICityArmyDAO;
import com.war.dao.ICityDAO;
import com.war.dao.ICityResourceDAO;
import com.war.dao.ICityWoundedArmyDAO;
import com.war.domain.Army;
import com.war.domain.ArmyDepend;
import com.war.domain.City;
import com.war.domain.CityArmy;
import com.war.domain.CityOrdnance;
import com.war.domain.CityResource;
import com.war.domain.CityWoundedArmy;
import com.war.domain.ConstraintDepend;
import com.war.exception.GameException;
import com.war.service.IArmyService;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IConstraintDependService;
import com.war.service.IOperationLogService;
import com.war.service.IOrdnanceService;

/**
 * 军队Service实现
 * 
 * @author TopTong
 * @version 1.0
 */
public class ArmyService implements IArmyService {
	
	private ICityDAO cityDAO;
	
	private ICityResourceDAO cityResourceDAO;
	
	private IArmyDAO armyDAO;
	
	private ICityArmyDAO cityArmyDAO;
	
	private IArmyDependDAO armyDependDAO;
	
	private ICityWoundedArmyDAO cityWoundedArmyDAO;
	
	private IOrdnanceService ordnanceService;
	
	private ICityService cityService;
	
	private IConstraintDependService constraintDependService;
	
	private IBuildingService buildingService;
	
	private IOperationLogService operationLogService;
	
	private static Logger logger = Logger.getLogger(ArmyService.class);
	
	
	public Map<Integer, Army> initArmiesMap() {
		Map<Integer, Army> armyMap = new HashMap<Integer, Army>();
		List<Army> armyList = this.getArmyList();
		for (int i=0;i<armyList.size();i++) {
			armyMap.put(armyList.get(i).getArmyID(), armyList.get(i));
		}
		return armyMap;
	}
	
	public List<Army> initFreeUnionArmyList() {
		
		List<Army> armyList = this.getArmyList();
		List<Army> resultList = new ArrayList<Army>(armyList.size());
		//排除的兵种编号
		int[] excludeArmyIDs = {};
		
		// 排除士兵
		excludeArmyIDs = new int[]{ArmyConstant.SPECIAL_FORCES_LHDG,ArmyConstant.HEAVY_TANK_LHDG};
		
		//遍历列表剔除本阵营外的兵种信息
		outer:for(Army army : armyList){
			for(int i=0; i<excludeArmyIDs.length; i++){
				//如果在排除的兵种范围内就继续
				if(army.getArmyID() == excludeArmyIDs[i]){
					continue outer;
				}
			}
			
			//如果不在排除兵的范围就添加到返回列表
			resultList.add(army);
		}
		return resultList;
	}
	
	public List<Army> initUnionEmpireArmyList() {
		
		List<Army> armyList = this.getArmyList();
		List<Army> resultList = new ArrayList<Army>(armyList.size());
		//排除的兵种编号
		int[] excludeArmyIDs = {};
		
		// 排除士兵
		excludeArmyIDs = new int[]{ArmyConstant.SPECIAL_FORCES_ZYLB,ArmyConstant.HEAVY_TANK_ZYLB};
		
		//遍历列表剔除本阵营外的兵种信息
		outer:for(Army army : armyList){
			for(int i=0; i<excludeArmyIDs.length; i++){
				//如果在排除的兵种范围内就继续
				if(army.getArmyID() == excludeArmyIDs[i]){
					continue outer;
				}
			}
			
			//如果不在排除兵的范围就添加到返回列表
			resultList.add(army);
		}
		return resultList;
	}
	
	public Integer createArmy(Army army) {
		return armyDAO.createArmy(army);
	}

	public void deleteArmyByID(Integer armyID) {
		armyDAO.deleteArmyByID(armyID);
	}
	
	public void releaseCityMilitaryArmyPopulation(Integer cityID, String armyStr) {
		
		if(armyStr == null){
			return;
		}
		
		City city = cityService.getCityByID(cityID);
		
		String[] armyInfos = armyStr.split(";");
		
		int population = 0;
		String[] armyInfo;
		int armyID;
		int num;
		for (int i = 0; i < armyInfos.length; i++) {
			armyInfo = armyInfos[i].split(":");
			armyID = Integer.parseInt(armyInfo[0]);
			num = Integer.parseInt(armyInfo[1]);
			
			Army army = this.getArmyByID(armyID);
			population += army.getPopulation() * num;
		}
		
		Map<String, Object> cityParams = new HashMap<String, Object>();
		cityParams.put("cityID", cityID);
		cityParams.put("populationTotal", city.getPopulationTotal() - population);
		
		cityService.updateCity(cityParams);

	}

	@SuppressWarnings("unchecked")
	public Army getArmyByID(Integer armyID) {
		return ((Map<Integer, Army>)CacheService.getFromCache(CacheConstant.ARMIES_MAP)).get(armyID);
	}
	
	@SuppressWarnings("unchecked")
	public Army getClonedArmyByID(Integer armyID) {
		Army army = ((Map<Integer, Army>)CacheService.getFromCache(CacheConstant.ARMIES_MAP)).get(armyID);
		
		Army clonedArmy = new Army();
		clonedArmy.setArmyID(army.getArmyID());
		clonedArmy.setName(army.getName());
		clonedArmy.setImage(army.getImage());
		clonedArmy.setDescription(army.getDescription());
		clonedArmy.setLife(army.getLife());
		clonedArmy.setAttack(army.getAttack());
		clonedArmy.setDefense(army.getDefense());
		clonedArmy.setRange(army.getRange());
		clonedArmy.setSpeed(army.getSpeed());
		clonedArmy.setCarry(army.getCarry());
		clonedArmy.setCostFood(army.getCostFood());
		clonedArmy.setCostMoney(army.getCostMoney());
		clonedArmy.setCostOil(army.getCostOil());
		clonedArmy.setPopulation(army.getPopulation());
		clonedArmy.setAttackType(army.getAttackType());
		clonedArmy.setDefenseType(army.getDefenseType());
		clonedArmy.setType(army.getType());
		clonedArmy.setArmyDependList(army.getArmyDependList());
		clonedArmy.setConstraintDepend(army.getConstraintDepend());
		
		return clonedArmy;
	}
	
	public Integer getArmySpeed(Integer armyID){
		return this.getArmyByID(armyID).getSpeed();
	}

	public List<Army> getArmyList(Integer type){
		
		List<Army> armyList = armyDAO.getArmyListByType(type);
		Army army;
		
		//设置依赖关系
		for(int i=0;i<armyList.size();i++){
			army = armyList.get(i);
			army.setArmyDependList(this.getArmyDependList(army.getArmyID()));
			army.setConstraintDepend(constraintDependService.getConstraintDependByTypeAndTargetIDAndLevel(ConstraintDependTypeConstant.SOILDER, army.getArmyID(), 1));
		}
		
		return armyList;
	}

	public List<Army> getArmyList() {
		
		 List<Army> armyList = armyDAO.getArmyList();
		 Army army;
			
		//设置依赖关系
		for(int i=0;i<armyList.size();i++){
			army = armyList.get(i);
			army.setArmyDependList(this.getArmyDependList(army.getArmyID()));
			army.setConstraintDepend(constraintDependService.getConstraintDependByTypeAndTargetIDAndLevel(ConstraintDependTypeConstant.SOILDER, army.getArmyID(), 1));
		}
		 
		return armyList;
	}
	
	public String getArmyNameByID(Integer armyID){
		return this.getArmyByID(armyID).getName();
	}
	
	public CityArmy getCityArmy(Integer cityID,Integer armyID){
		return cityArmyDAO.getCityArmyByCityIDAndArmyID(cityID, armyID);
	}

	public void updateArmy(Army army) {
		armyDAO.updateArmy(army);
	}

	public Integer createCityArmy(CityArmy cityArmy) {
		return cityArmyDAO.createCityArmy(cityArmy);
	}

	public void deleteCityArmyByID(Integer cityArmyID) {
		cityArmyDAO.deleteCityArmyByID(cityArmyID);
	}

	public CityArmy getCityArmyByID(Integer cityArmyID) {
		return cityArmyDAO.getCityArmyByID(cityArmyID);
	}

	public List<CityArmy> getCityArmyList() {
		return cityArmyDAO.getCityArmyList();
	}

	public void updateCityArmy(CityArmy cityArmy) {
		cityArmyDAO.updateCityArmy(cityArmy);
	}
	
	public List<CityArmy> getCityArmyList(Integer cityID) {
		return cityArmyDAO.getCityArmyListByCityID(cityID);
	}

	public Integer createArmyDepend(ArmyDepend armyDepend) {
		return armyDependDAO.createArmyDepend(armyDepend);
	}

	public void deleteArmyDependByID(Integer armyDependID) {
		armyDependDAO.deleteArmyDependByID(armyDependID);	
	}

	public ArmyDepend getArmyDepend(Integer armyID, Integer ordnanceID) {
		return armyDependDAO.getArmyDepend(armyID, ordnanceID);
	}

	public ArmyDepend getArmyDependByID(Integer armyDependID) {
		return armyDependDAO.getArmyDependByID(armyDependID);
	}

	public List<ArmyDepend> getArmyDependList() {
		return armyDependDAO.getArmyDependList();
	}

	public List<ArmyDepend> getArmyDependList(Integer armyID) {
		return armyDependDAO.getArmyDependList(armyID);
	}

	public void updateArmyDepend(ArmyDepend armyDepend) {
		armyDependDAO.updateArmyDepend(armyDepend);
	}
	
	public void batchUpdateCityArmyNumByCityIDAndArmyIDs(int cityID, int[] armyIDs, int[] nums) {
		cityArmyDAO.batchUpdateCityArmyNumByCityIDAndArmyIDs(cityID, armyIDs, nums);
	}
	
	public void checkResources(int cityID,int armyID, int num){
		//获得兵种所需的建筑及科技依赖信息
		ConstraintDepend constraintDepend = constraintDependService.getConstraintDependByTypeAndTargetIDAndLevel(ConstraintDependTypeConstant.SOILDER, armyID, 1);
		
		if(constraintDepend != null){
			//条件检验
			buildingService.checkPreBuildingAndTech(cityID, constraintDepend);
		}
		
		City city = cityService.getCityByID(cityID);
		Army army = this.getArmyByID(armyID);
		//剩余的新兵
		int recruitNum = city.getRecruitNum() - num * army.getPopulation();
		
		if(recruitNum < 0){
			throw new GameException("城市新兵数量不足。");
		}
		
		List<ArmyDepend> dependList = this.getArmyDependList(armyID);
		
		if(dependList.isEmpty()){
			return;
		}
		
		CityOrdnance co;
		
		for(ArmyDepend depend : dependList){
			co = this.ordnanceService.getCityOrdnance(cityID, depend.getOrdnanceID());
			//如果没有该军械或者军械数量不足
			if(co == null || co.getNum() < (depend.getNum() * num)){
				throw new GameException("所需军械不足");
			}
		}
	}
	
	/**
	 * 
	 * @param cityID
	 * @param armyID
	 * @param num 数量
	 * @param isReward 是否为奖励（得到得部队）
	 */
	private void addCityArmy(int cityID, int armyID, int num, boolean isReward) { 
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		Army army = this.getArmyByID(armyID);
		
		List<ArmyDepend> dependList = getArmyDependList(armyID);
		
		if(dependList.isEmpty()){
			return;
		}
		
		CityOrdnance co = null;
		
		Map<String, Object> cityParams = new HashMap<String, Object>();
		cityParams.put("cityID", cityID);
		
		long moneyConsume = 0, foodConsume = 0, oilConsume = 0;
		
		if (!isReward) {
			
			//更新军备数量
			for(ArmyDepend depend : dependList){
				co = ordnanceService.getCityOrdnance(cityID, depend.getOrdnanceID());
				co.setNum(co.getNum() - depend.getNum() * num);
				ordnanceService.updateCityOrdnance(co);
			}
			
			//剩余的新兵
			int recruitNum = city.getRecruitNum() - num * army.getPopulation();
			//资源消耗：减去新兵的消耗，加上当前兵种的消耗
			moneyConsume = cityResource.getMoneyConsume() + army.getCostMoney()*num -  army.getPopulation()*GameConfig.SOLDIER_CONSUME_MONEY*num;
			foodConsume = cityResource.getFoodConsume() + army.getCostFood()*num - army.getPopulation()*GameConfig.SOLDIER_CONSUME_FOOD*num;
			oilConsume = cityResource.getOilConsume() + army.getCostOil()*num ;
			
			cityParams.put("recruitNum", recruitNum);
			
			
		} else {
			// 奖励的士兵消耗中没有消耗任何新兵
			moneyConsume = cityResource.getMoneyConsume() + army.getCostMoney()*num ;
			foodConsume = cityResource.getFoodConsume() + army.getCostFood()*num;
			oilConsume = cityResource.getOilConsume() + army.getCostOil()*num ;
			
			cityParams.put("populationTotal", city.getPopulationTotal() + army.getPopulation() * num);
		}
		
		// 更新城市信息
		cityService.updateCity(cityParams);
		
		// 更新城市资源消耗
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		cityResourceParams.put("cityID", cityID);
		cityResourceParams.put("moneyConsume", moneyConsume);
		cityResourceParams.put("foodConsume", foodConsume);
		cityResourceParams.put("oilConsume", oilConsume);
		
		cityService.updateCityResource(cityResourceParams);
		
		//获得未编制的兵种信息
		CityArmy ca = cityArmyDAO.getCityArmyByCityIDAndArmyID(cityID, armyID);
		
		//如果还没有记录
		if(ca == null){
			ca = new CityArmy();
			ca.setArmyID(armyID);
			ca.setCityID(cityID);
			ca.setNum(num);
			this.createCityArmy(ca);
		}else{
			ca.setNum(ca.getNum() + num);
			
			this.updateCityArmy(ca);
		}
		
	}
	
	public void addCityArmy(int cityID,int armyID, int num){
		addCityArmy(cityID, armyID, num, false);
	}
	
	public void rewardCityArmy(int cityID,int armyID, int num) {
		addCityArmy(cityID, armyID, num, true);
	}

	public void reduceCityArmy(int cityID,int armyID, int num){
		reduceCityArmy(cityID, armyID, num, false);
	}
	
	public void reduceCityArmyForTask(int cityID, int armyID, int num) {
		reduceCityArmy(cityID, armyID, num, true);
	}
	
	private void reduceCityArmy(int cityID, int armyID, int num, boolean isTask) {
		
		City city  = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		Army army = this.getArmyByID(armyID);
		
		//获得未编制的兵种信息
		CityArmy ca = cityArmyDAO.getCityArmyByCityIDAndArmyID(cityID, armyID);
		
		//如果还没有记录
		if(ca == null){
			throw new GameException("无效操作");
		}else{
			if(ca.getNum() < num){
				throw new GameException("数量不足");
			}
			
			ca.setNum(ca.getNum() - num);
			
			this.updateCityArmy(ca);
		}
		
		Map<String, Object> cityParams = new HashMap<String, Object>();
		cityParams.put("cityID", cityID);
		
		//资源消耗
		Long moneyConsume = null;
		Long foodConsume = null;
		Long oilConsume = null;
		
		if (!isTask) {	// 减去当前兵种的消耗，加上新兵的消耗
			moneyConsume = cityResource.getMoneyConsume() - army.getCostMoney() * num + GameConfig.SOLDIER_CONSUME_MONEY * num;
			foodConsume = cityResource.getFoodConsume() - army.getCostFood() * num + GameConfig.SOLDIER_CONSUME_FOOD * num;
			oilConsume = cityResource.getOilConsume() - army.getCostOil() * num ;
			
			//武装的士兵返回到新兵状态
			int recruitNum = city.getRecruitNum() + army.getPopulation() * num;
			
			cityParams.put("recruitNum", recruitNum);
			
		} else {	// 减去当前兵种的消耗
			moneyConsume = cityResource.getMoneyConsume() - army.getCostMoney() * num ;
			foodConsume = cityResource.getFoodConsume() - army.getCostFood() * num ;
			oilConsume = cityResource.getOilConsume() - army.getCostOil() * num ;
			
			// 更新人口
			cityParams.put("populationTotal", city.getPopulationTotal() - army.getPopulation() * num);
		}

		// 更新城市信息
		cityService.updateCity(cityParams);

		// 更新城市资源信息
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		cityResourceParams.put("cityID", cityID);
		cityResourceParams.put("moneyConsume", moneyConsume);
		cityResourceParams.put("foodConsume", foodConsume);
		cityResourceParams.put("oilConsume", oilConsume);
		
		cityService.updateCityResource(cityResourceParams);
	}
	
	@SuppressWarnings("unchecked")
	public List<Army> getArmyListByContry(int country){
		if (country==GameConstant.COUNTRY_FREE_UNION) {
			return (List<Army>)CacheService.getFromCache(CacheConstant.FREE_UNION_ARMY_LIST);
		} else if (country==GameConstant.COUNTRY_UNION_EMPIRE) {
			return (List<Army>)CacheService.getFromCache(CacheConstant.UNION_EMPIRE_ARMY_LIST);
		}
		return null;
	}
	
	public Integer createCityWoundedArmy(CityWoundedArmy cityWoundedArmy) {
		cityWoundedArmy.setDeathTime(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
		return cityWoundedArmyDAO.createCityWoundedArmy(cityWoundedArmy);
	}

	public void dismissCityWoundedArmy(Integer cityWoundedArmyID, Integer num) {
		CityWoundedArmy cityWoundedArmy = cityWoundedArmyDAO.getCityWoundedArmyByID(cityWoundedArmyID);
		
		Army army = armyDAO.getArmyByID(cityWoundedArmy.getArmyID());
		City city = cityDAO.getCityByID(cityWoundedArmy.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(cityWoundedArmy.getCityID());
		
		if (num > cityWoundedArmy.getNum()){
			throw new GameException("遣散数量超过可治愈伤兵上限。");
		}
		
		if (num < cityWoundedArmy.getNum()) {
			// 删除一定数量的伤兵，该数量小于该条记录的伤兵数量
			cityWoundedArmy.setNum(cityWoundedArmy.getNum() - num);
			cityWoundedArmyDAO.updateCityWoundedArmy(cityWoundedArmy);
		} else {
			// 删除伤兵记录信息
			cityWoundedArmyDAO.deleteCityWoundedArmyByID(cityWoundedArmyID);
		}
		
		// 更新（减少）城市总费用支出		
		Map<String, Object> cityParams = new HashMap<String, Object>();
		Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		
		cityResourceParams.put("cityID",city.getCityID());
		cityResourceParams.put("foodConsume", cityResource.getFoodConsume() - army.getCostFood() * num);
		cityResourceParams.put("oilConsume", cityResource.getOilConsume() - army.getCostOil() * num);
		cityResourceParams.put("moneyConsume", cityResource.getMoneyConsume() - army.getCostMoney() * num);
		
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationTotal", city.getPopulationTotal() - army.getPopulation() * num);
		
		cityService.updateCityResource(cityResourceParams);
		
		// 更新总人口
		cityService.updateCity(cityParams);
	}

	public CityWoundedArmy getCityWoundedArmyByID(Integer cityWoundedArmyID) {
		return cityWoundedArmyDAO.getCityWoundedArmyByID(cityWoundedArmyID);
	}

	public List<CityWoundedArmy> getCityWoundedArmyList(Integer cityID) {
		List<CityWoundedArmy> cityWoundedArmyList = cityWoundedArmyDAO.getCityWoundedArmyListByCityID(cityID);
		for (int i=0;i<cityWoundedArmyList.size();i++) {
			cityWoundedArmyList.get(i).setArmy(this.getArmyByID(cityWoundedArmyList.get(i).getArmyID()));
		}
		return cityWoundedArmyList;
	}

	public void cureCityWoundedArmy(Integer cityWoundedArmyID, Integer num) {
		CityWoundedArmy cityWoundedArmy = cityWoundedArmyDAO.getCityWoundedArmyByID(cityWoundedArmyID);
		
		// 治愈伤兵
		Integer woundedArmyNum = cityWoundedArmy.getNum() - num ;
		
		if (woundedArmyNum < 0){
			throw new GameException("治愈伤兵数量超过可治愈伤兵数量上限。");
		}
		
		if (woundedArmyNum == 0) {
			cityWoundedArmyDAO.deleteCityWoundedArmyByID(cityWoundedArmyID);
		} else {
			cityWoundedArmy.setNum(woundedArmyNum);
			cityWoundedArmyDAO.updateCityWoundedArmy(cityWoundedArmy);
		}
		
		// 消耗金钱
		Army army = this.getArmyByID(cityWoundedArmy.getArmyID());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityWoundedArmy.getCityID());
		params.put("moneyNum", cityService.getCityResourceByCityID(cityWoundedArmy.getCityID()).getMoneyNum() - army.getCostMoney() * 1.0/5 * num);
		cityService.updateCityResource(params);
		
		// 增加城市兵力
		if (cityArmyDAO.getCityArmyByCityIDAndArmyID(cityWoundedArmy.getCityID(), cityWoundedArmy.getArmyID()) == null) {
			CityArmy cityArmy = new CityArmy();
			cityArmy.setCityID(cityWoundedArmy.getCityID());
			cityArmy.setArmyID(cityWoundedArmy.getArmyID());
			cityArmy.setNum(num);
			
			cityArmyDAO.createCityArmy(cityArmy);
		} else {
			CityArmy cityArmy = cityArmyDAO.getCityArmyByCityIDAndArmyID(cityWoundedArmy.getCityID(), cityWoundedArmy.getArmyID());
			cityArmy.setNum(cityArmy.getNum() + num);
			cityArmyDAO.updateCityArmy(cityArmy);
		}
		
		// 记录玩家操作日志
		operationLogService.createOperationLog(cityService.getPlayerIDByCityID(cityWoundedArmy.getCityID()), OperationLogConstant.CURE_WOUNDED_ARMY);
	}
	
	public void handleAutoDismissedCityWoundedArmy() {
		try {
			List<CityWoundedArmy> cityWoundedArmyList = cityWoundedArmyDAO.getAutoDismissedCityWoundedArmyList();
			if (cityWoundedArmyList != null && !cityWoundedArmyList.isEmpty()) {
				for (CityWoundedArmy cityWoundedArmy : cityWoundedArmyList) {
					this.dismissCityWoundedArmy(cityWoundedArmy.getCityWoundedArmyID(), cityWoundedArmy.getNum());
				}
			}
			
		} catch (Exception e) {
			logger.error("异常：", e);
		}
	}
	
	public Integer getArmyPopulation(Integer armyID) {
		return this.getArmyByID(armyID).getPopulation();
	}
	
	
	public IArmyDAO getArmyDAO() {
		return armyDAO;
	}

	public void setArmyDAO(IArmyDAO armyDAO) {
		this.armyDAO = armyDAO;
	}

	public ICityArmyDAO getCityArmyDAO() {
		return cityArmyDAO;
	}

	public void setCityArmyDAO(ICityArmyDAO cityArmyDAO) {
		this.cityArmyDAO = cityArmyDAO;
	}

	public IArmyDependDAO getArmyDependDAO() {
		return armyDependDAO;
	}

	public void setArmyDependDAO(IArmyDependDAO armyDependDAO) {
		this.armyDependDAO = armyDependDAO;
	}

	public IOrdnanceService getOrdnanceService() {
		return ordnanceService;
	}

	public void setOrdnanceService(IOrdnanceService ordnanceService) {
		this.ordnanceService = ordnanceService;
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

	public void setConstraintDependService(IConstraintDependService constraintDependService) {
		this.constraintDependService = constraintDependService;
	}

	public IBuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(IBuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public ICityWoundedArmyDAO getCityWoundedArmyDAO() {
		return cityWoundedArmyDAO;
	}

	public void setCityWoundedArmyDAO(ICityWoundedArmyDAO cityWoundedArmyDAO) {
		this.cityWoundedArmyDAO = cityWoundedArmyDAO;
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

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	
}
