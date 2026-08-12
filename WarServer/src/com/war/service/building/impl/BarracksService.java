package com.war.service.building.impl;

import java.util.HashMap;
import java.util.Map;

import com.war.common.GameConfig;
import com.war.dao.ICityResourceDAO;
import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.exception.GameException;
import com.war.service.IArmyService;
import com.war.service.ICityService;
import com.war.service.building.IBarracksService;
import com.war.util.ResourceCalculateUtil;
import static com.war.common.GameConfig.*;

/**
 * 兵营service实现
 *
 * @author ghleed
 * @version 1.0
 */
public class BarracksService implements IBarracksService {
	
	private ICityService cityService;
		
	private IArmyService armyService;
	
	/**
	 * 武装新兵
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void armSoldier(Integer cityID, Integer armyID, Integer num) {
		//检查是否有足够的资源
		armyService.checkResources(cityID, armyID, num);
		//减少资源
		armyService.addCityArmy(cityID, armyID, num);
	}

	/**
	 * 解除士兵的武装
	 * 注：解除武装的士兵将成为新兵
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */
	public void disarmSoldier(Integer cityID, Integer armyID, Integer num) {
		armyService.reduceCityArmy(cityID, armyID, num);
	}

	/**
	 * 招募新兵
	 * @param cityID 城市编号
	 * @param enlistNum 招募数量
	 */ 
	public void enlistSoldier(Integer cityID, Integer enlistNum) {
		if(enlistNum < 0){
			throw new GameException("招募数量必须大于0");
		}
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null)
			throw new GameException("城市不存在");
		
		//空闲市民数
		long populationFree = city.getPopulationFree() - enlistNum;
		
		if(populationFree < 0){
			throw new GameException("空闲市民不足");
		}
		
		//计算招募后的金钱数量
		long moneyNum = cityResource.getMoneyNum() - enlistNum * SOLDIER_COST_MONEY;
		
		if(moneyNum < 0 ){
			throw new GameException("金钱不足");
		}
		
		//计算招募后的食物数量
		long foodNum = cityResource.getFoodNum() - enlistNum * SOLDIER_COST_FOOD;
		
		if(foodNum < 0){
			throw new GameException("食物不足");
		}

		//计算食物消耗：减去市民的食物消耗，加上新兵的消耗
		long foodConsume = cityResource.getFoodConsume() - enlistNum * GameConfig.CITIZEN_CONSUME_FOOD + enlistNum * GameConfig.SOLDIER_CONSUME_FOOD;
		//计算金钱消耗：因为市民不消耗金钱，这里只需增加士兵的金钱消耗
		long moneyConsume = cityResource.getMoneyConsume() + enlistNum * GameConfig.SOLDIER_CONSUME_MONEY;
		//城市新兵
		long recruitNum = city.getRecruitNum() + enlistNum;
		Map<String,Object> cityParams = new HashMap<String, Object>();
		Map<String,Object> cityResourceParams = new HashMap<String, Object>();
		cityParams.put("cityID", city.getCityID());
		cityParams.put("populationFree", populationFree);
		cityParams.put("recruitNum", recruitNum);
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("moneyNum", moneyNum);
		cityResourceParams.put("foodNum", foodNum);
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),populationFree ,
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		cityResourceParams.put("moneyOutput", moneyOutput);
		cityResourceParams.put("foodConsume", foodConsume);
		cityResourceParams.put("moneyConsume", moneyConsume);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);

	}

	/**
	 * 裁减新兵
	 * @param cityID 城市编号
	 * @param reduceNum 裁减数量
	 */ 
	public void reduceSoldier(Integer cityID, Integer reduceNum) {
		if(reduceNum < 0){
			throw new GameException("裁减数量必须大于0");
		}
		
		City city = cityService.getCityByID(cityID);
		CityResource cityResource = cityService.getCityResourceByCityID(cityID);
		
		if(city == null){
			throw new GameException("城市不存在");
		}
		
		if(reduceNum > city.getRecruitNum()){
			throw new GameException("新兵不足");
		}
		
		//计算食物消耗：减去新兵的食物消耗，加上市民的消耗
		long foodConsume = cityResource.getFoodConsume() + reduceNum * GameConfig.CITIZEN_CONSUME_FOOD - reduceNum * GameConfig.SOLDIER_CONSUME_FOOD;
		//计算金钱消耗：因为市民不消耗金钱，这里只需减去士兵的金钱消耗
		long moneyConsume = cityResource.getMoneyConsume() - reduceNum * GameConfig.SOLDIER_CONSUME_MONEY;
		//空闲市民数
		long populationFree = city.getPopulationFree() + reduceNum;
		//城市新兵
		long recruitNum = city.getRecruitNum() - reduceNum;
		Map<String,Object> cityParams = new HashMap<String, Object>();
		Map<String,Object> cityResourceParams = new HashMap<String, Object>();
		cityParams.put("cityID", city.getCityID());
		cityParams.put("recruitNum", recruitNum);
		cityParams.put("populationFree", populationFree);
		long moneyOutput = ResourceCalculateUtil.calculateMoneyOutput(cityResource.getFoodWorkerNum()+cityResource.getWoodWorkerNum()+cityResource.getOilWorkerNum()+cityResource.getSteelWorkerNum(),populationFree ,
				city.getTax(),cityResource.getMoneyTechAdd(), cityResource.getMoneyOfficerAdd(), cityResource.getMoneyGuildAdd(), cityResource.getMoneyFieldAdd(), cityResource.getMoneyTreasureAdd());
		cityResourceParams.put("cityID", city.getCityID());
		cityResourceParams.put("moneyOutput", moneyOutput);
		cityResourceParams.put("foodConsume", foodConsume);
		cityResourceParams.put("moneyConsume", moneyConsume);
		
		cityService.updateCity(cityParams);
		cityService.updateCityResource(cityResourceParams);
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IArmyService getArmyService() {
		return armyService;
	}

	public void setArmyService(IArmyService armyService) {
		this.armyService = armyService;
	}

}
