package com.war.service.building.impl;

import static com.war.common.GameConfig.CITIZEN_COST_FOOD;
import static com.war.common.GameConfig.CITIZEN_COST_TIME;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.sf.json.JSONObject;

import com.war.common.DateService;
import com.war.common.GameConfig;
import com.war.constant.OperationLogConstant;
import com.war.constant.ProductionQueueTypeConstant;
import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.domain.ProductionQueue;
import com.war.exception.GameException;
import com.war.service.IBuildingService;
import com.war.service.ICityService;
import com.war.service.IOperationLogService;
import com.war.service.IProductionQueueService;
import com.war.service.ITreasureService;
import com.war.service.building.ICityCenterService;
import com.war.util.ResourceCalculateUtil;

/**
 * 城镇中心service实现
 *
 * @author ghleedss
 * @version 1.0
 */
public class CityCenterService implements ICityCenterService {
	
	private ICityService cityService;
	private IBuildingService buildingService;
	private IProductionQueueService productionQueueService;
	private ITreasureService treasureService;
	private IOperationLogService operationLogService;
	
	/** 城市治理间隔 */
	private static final int MANGE_INTERVAL = 15*60*1000;
	
	/**
	 * 该锁主要为了解决服务端，客户端同时进行完成处理而导致的并发问题
	 * ReentrantLock比synchronized效率更高
	 */
	private final Lock lock = new ReentrantLock();
	
	//调整税率
	public void adjustTax(Integer cityID, Integer newValue) {
		City city  = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在");

		if(newValue<0 || newValue>100)
			throw new GameException("税率范围只能在0-100之间");
		
		//税率改变量
		int changeValue = newValue-city.getTax();
		
		//治安值
		int security = city.getSecurity() ;
		
		//税率调高会降低治安，但调低税率不会提高治安
		if(changeValue > 0){
			security -= changeValue;
		}
		
		//如果治安小于指定值时的特殊处理
		if(security < 0){
			security = 0;
		}
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		//重新计算金钱产量
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),city.getPopulationFree(),newValue,cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		//治安值会影响资源的产出
		long oilOutput = ResourceCalculateUtil.calculateOilOutput(newValue,cityResource.getOilWorkerNum(), cityResource.getOilBuildingAdd(), cityResource.getOilTechAdd(), cityResource.getOilOfficerAdd(), cityResource.getOilGuildAdd(), cityResource.getOilFieldAdd(), cityResource.getOilTreasureAdd());
		long foodOutput = ResourceCalculateUtil.calculateFoodOutput(newValue,cityResource.getFoodWorkerNum(), cityResource.getFoodBuildingAdd(), cityResource.getFoodTechAdd(), cityResource.getFoodOfficerAdd(), cityResource.getFoodGuildAdd(), cityResource.getFoodFieldAdd(), cityResource.getFoodTreasureAdd());
		long woodOutput = ResourceCalculateUtil.calculateWoodOutput(newValue, cityResource.getWoodWorkerNum(),cityResource.getWoodBuildingAdd()	, cityResource.getWoodTechAdd(), cityResource.getWoodOfficerAdd(), cityResource.getWoodGuildAdd(), cityResource.getWoodFieldAdd(), cityResource.getWoodTreasureAdd());
		long steelOutput = ResourceCalculateUtil.calculateSteelOutput(newValue, cityResource.getSteelWorkerNum(), cityResource.getSteelBuildingAdd(), cityResource.getSteelTechAdd(), cityResource.getSteelOfficerAdd(), cityResource.getSteelGuildAdd(), cityResource.getSteelFieldAdd(), cityResource.getSteelTreasureAdd());
		
		cityParams.put("cityID", cityID);
		cityParams.put("security", security);
		cityParams.put("tax",newValue);
		cityResourceParams.put("cityID", cityID);
		cityResourceParams.put("moneyOutput", moneyOutput);
		cityResourceParams.put("oilOutput", oilOutput);
		cityResourceParams.put("foodOutput", foodOutput);
		cityResourceParams.put("woodOutput", woodOutput);
		cityResourceParams.put("steelOutput", steelOutput);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
	}

	public void changeCityName(Integer cityID, String newCityName) {
		
		Integer cid = cityService.getCityIDByCityName(newCityName);
		
		if(cid != null)
			throw new GameException("城市名称 " + newCityName + " 已存在");

		City city = cityService.getCityByID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在");
		
		java.util.Map<String, Object> params = new HashMap<String, Object>();
		
		//更新城市名称
		params.put("cityID", cityID);
		params.put("name", newCityName);
		cityService.updateCity(params);
		
		// 记录用户操作日志
		operationLogService.createOperationLog(city.getPlayerID(), OperationLogConstant.CHANGE_CITY_NAME);
	}

	public void doSafetyPatrol(Integer cityID) {
		
		if(System.currentTimeMillis()-cityService.getCityExt(cityID).getLastManageTime().getTime()<MANGE_INTERVAL){
			throw new GameException("两次城市治理间隔需大于15分钟");
		}
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在");

		//提升治安5，每1市民消耗金钱10
		//金钱消耗
		long moneyNum = cityResource.getMoneyNum() - city.getPopulationTotal() * 10;
		
		if(moneyNum<0){
			throw new GameException("城市金钱不足。");
		}
		
		//城市治安+税率最大值为100
		int security = Math.min(city.getSecurity()+20, 100-city.getTax());
		
		java.util.Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityID);
		params.put("security", security);
		params.put("moneyNum", moneyNum);
		cityService.updateCity(params);

		//更新城市最后治理时间
		java.util.Map<String,Object> cityExtParams = new HashMap<String,Object>();
		cityExtParams.put("cityID", cityID);
		cityExtParams.put("lastManageTime", DateService.getCurrentUtilDate());
		cityService.updateCityExt(cityExtParams);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(city.getPlayerID(),OperationLogConstant.SAFETY_PATROL);
	}

	public void doGuardsParade(Integer cityID) {
		
		if(System.currentTimeMillis()-cityService.getCityExt(cityID).getLastManageTime().getTime()<MANGE_INTERVAL){
			throw new GameException("两次城市治理间隔需大于15分钟");
		}
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在");

		//提升治安5，每1市民消耗金钱5
		//金钱消耗
		long moneyNum = cityResource.getMoneyNum() - city.getPopulationTotal() * 5;
		
		if(moneyNum<0){
			throw new GameException("城市金钱不足。");
		}
		
		//城市治安+税率最大值为100
		int security = Math.min(city.getSecurity()+10,100-city.getTax());
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		//重新计算金钱收益
		cityParams.put("cityID", cityID);
		cityParams.put("security", security);
		cityResourceParams.put("cityID", cityID);
		cityResourceParams.put("moneyNum", moneyNum);
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
		
		//更新城市最后治理时间
		java.util.Map<String,Object> cityExtParams = new HashMap<String,Object>();
		cityExtParams.put("cityID", cityID);
		cityExtParams.put("lastManageTime", DateService.getCurrentUtilDate());
		cityService.updateCityExt(cityExtParams);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(city.getPlayerID(),OperationLogConstant.GUARDS_PARADE);
	}

	public void doHolidayCelebrate(Integer cityID) {
		
		if(System.currentTimeMillis()-cityService.getCityExt(cityID).getLastManageTime().getTime()<MANGE_INTERVAL){
			throw new GameException("两次城市治理间隔需大于15分钟");
		}
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在");

		//提升治安5，每1市民消耗金钱2
		//金钱消耗
		long moneyNum = cityResource.getMoneyNum() - city.getPopulationTotal() * 2;
		
		if(moneyNum<0){
			throw new GameException("城市金钱不足。");
		}
		
		//城市治安+税率最大值为100
		int security = Math.min(city.getSecurity()+5, 100-city.getTax());
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();

		cityParams.put("cityID", cityID);
		cityParams.put("security", security);
		cityResourceParams.put("cityID", cityID);
		cityResourceParams.put("moneyNum", moneyNum);
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
		
		//更新城市最后治理时间
		java.util.Map<String,Object> cityExtParams = new HashMap<String,Object>();
		cityExtParams.put("cityID", cityID);
		cityExtParams.put("lastManageTime", DateService.getCurrentUtilDate());
		cityService.updateCityExt(cityExtParams);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(city.getPlayerID(),OperationLogConstant.HOLIDAY_CELEBRATE);
	}

	public void imposeMaterial(Integer cityID) {
		
		if(System.currentTimeMillis()-cityService.getCityExt(cityID).getLastManageTime().getTime()<MANGE_INTERVAL){
			throw new GameException("两次城市治理间隔需大于15分钟");
		}
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在");
		
		if(city.getSecurity() < 40){
			throw new GameException("治安值不足40不能进行该操作");
		}
		
		//物资征收=每人征收木材10，钢铁10，石油10，食物30，但是降低治安20
		int security = Math.max(city.getSecurity()-20,0);
		
		//不能超过上限
		long woodNum  = Math.min(cityResource.getWoodNum()+city.getPopulationTotal() * 5,cityResource.getResourceNumMax());
		long oilNum = Math.min(cityResource.getOilNum()+city.getPopulationTotal() * 5,cityResource.getResourceNumMax());
		long steelNum = Math.min(cityResource.getSteelNum()+city.getPopulationTotal() * 5,cityResource.getResourceNumMax());
		long foodNum  = Math.min(cityResource.getFoodNum()+city.getPopulationTotal() * 10,cityResource.getResourceNumMax());
		
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		cityParams.put("cityID", cityID);
		cityParams.put("security", security);
		cityResourceParams.put("cityID", cityID);
		cityResourceParams.put("woodNum", woodNum);
		cityResourceParams.put("oilNum", oilNum);
		cityResourceParams.put("steelNum", steelNum);
		cityResourceParams.put("foodNum", foodNum);
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
		
		//更新城市最后治理时间
		java.util.Map<String,Object> cityExtParams = new HashMap<String,Object>();
		cityExtParams.put("cityID", cityID);
		cityExtParams.put("lastManageTime", DateService.getCurrentUtilDate());
		cityService.updateCityExt(cityExtParams);
		
		// 记入用户操作日志
		operationLogService.createOperationLog(city.getPlayerID(), OperationLogConstant.IMPOSE_MATERIAL);
	}

	public ProductionQueue enlistCitizen(Integer cityID, Integer enlistNumber) {
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在。");
		
		if((city.getPopulationTotal() + enlistNumber) > city.getPopulationMax()){
			throw new GameException("剩余人口空间不足。");
		}
		
		// 进程队列
		ProductionQueue productionQueue = productionQueueService.getProductionQueue(cityID, 0, ProductionQueueTypeConstant.PROCESS_ENLIST_CITIZEN);
		//如果进程已存在
		if(productionQueue != null){
			throw new GameException("目前已有征召市民的进程，完成后才能进行再次征召。");
		}
		
		int needFood = enlistNumber * CITIZEN_COST_FOOD;
		
		if(cityResource.getFoodNum() < needFood){
			throw new GameException("食物不足");
		}
		
		//扣除资源
		long foodNum = cityResource.getFoodNum() - needFood;

		java.util.Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityID);
		params.put("foodNum", foodNum);
		
		cityService.updateCityResource(params);
		
		productionQueue = new ProductionQueue();
		productionQueue.setCityID(cityID);
		productionQueue.setTargetID(0);//目前征召市民只有一个队列，所以该字段暂时没有用。
		productionQueue.setType(ProductionQueueTypeConstant.PROCESS_ENLIST_CITIZEN);
		productionQueue.setAmount(enlistNumber);
		productionQueue.setStartTime(DateService.getCurrentUtilDate());
		// 完成时间
		Date finishTime = new Date();
		//建造时间
		finishTime.setTime(System.currentTimeMillis() + (enlistNumber * CITIZEN_COST_TIME * 1000));
		productionQueue.setFinishTime(finishTime);
		productionQueue.setProductionQueueID(productionQueueService.createProductionQueue(productionQueue));
		
		return productionQueue;
	}

	public void cancelEnlistCitizen(Integer productionProcessID) {
		ProductionQueue pg = productionQueueService.getProductionQueueByID(productionProcessID);
		
		if(pg == null)
			return;
		
		City city = cityService.getCityByID(pg.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(pg.getCityID());
		
		if(city == null)
			throw new GameException("城市不存在");
		
		//返回一半的资源
		long foodNum = cityResource.getFoodNum() + pg.getAmount() * CITIZEN_COST_FOOD / 2;
		
		//资源上限判断
		foodNum = Math.min(foodNum, cityResource.getResourceNumMax());
		
		java.util.Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", city.getCityID());
		params.put("foodNum", foodNum);
		
		cityService.updateCityResource(params);
		
		productionQueueService.deleteProductionQueueByID(productionProcessID);
		
	}
	
	//完成征召市民的进程
	public void finishEnlistCitizen(ProductionQueue produnctionProcess) {
		//保证后面的操作是同步的
		lock.lock();
		try{
			if(productionQueueService.getProductionQueueByID(produnctionProcess.getProductionQueueID()) == null){
				return;
			}
			//删除进程
			productionQueueService.deleteProductionQueueByID(produnctionProcess.getProductionQueueID());
			
		}finally{
			lock.unlock();
		}
		
		City city = cityService.getCityByID(produnctionProcess.getCityID());
		CityResource cityResource = cityService.getCityResourceByCityID(produnctionProcess.getCityID());
		
		if(city == null)
			throw new GameException("城市不存在");
		
		//设置市民数量
		java.util.Map<String, Object> cityParams = new HashMap<String, Object>();
		java.util.Map<String, Object> cityResourceParams = new HashMap<String, Object>();
		//增加的人数 
		long num = produnctionProcess.getAmount();
		long populationFree = city.getPopulationFree()+num;
		long populationTotal = city.getPopulationTotal()+num;
		//重新计算金钱产量
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),populationFree ,
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		//食物消耗
		long foodConsume = cityResource.getFoodConsume() + GameConfig.CITIZEN_CONSUME_FOOD * num;
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree",populationFree);
		cityParams.put("populationTotal",populationTotal);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("moneyOutput",moneyOutput);
		cityResourceParams.put("foodConsume",foodConsume);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
	}
	
	/**
	 *  客户端完成征召市民计算时调用该方法可以及时刷新信息
	 * @param productionProcessID
	 */
	public void clientEnlistCitizenFinished(Integer productionProcessID) {
		ProductionQueue pq = productionQueueService.getProductionQueueByID(productionProcessID);
		//已经被处理
		if(pq == null)
			return;
		
		//进程结束时间和当前时间的间隔毫秒数
		long clips = pq.getFinishTime().getTime()-(new Date()).getTime();
		
		//如果过了完成时间就对该进程进行处理
		if(clips <= 0){
			finishEnlistCitizen(pq);
		}
	}
	
	/**
	 * 获得招募市民的进程
	 * @param cityID 
	 * @return
	 */
	public ProductionQueue getEnlistCitizenProcess(Integer cityID){
		List<ProductionQueue> plist = productionQueueService.getProductionQueueList(cityID, ProductionQueueTypeConstant.PROCESS_ENLIST_CITIZEN);
		if(plist.isEmpty()){
			return null;
		}else{
			return plist.get(0);
		}
	}


	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	
	public IBuildingService getBuildingService() {
		return buildingService;
	}

	public void setBuildingService(IBuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public IProductionQueueService getProductionQueueService() {
		return productionQueueService;
	}

	public void setProductionQueueService(
			IProductionQueueService productionQueueService) {
		this.productionQueueService = productionQueueService;
	}

	public ITreasureService getTreasureService() {
		return treasureService;
	}

	public void setTreasureService(ITreasureService treasureService) {
		this.treasureService = treasureService;
	}

	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

}
