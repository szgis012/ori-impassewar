package com.war.service.building.impl;

import com.war.service.IArmyService;
import com.war.service.building.IHeavyFactoryService;


/**
 * 重型工厂service实现
 *
 * @author ghleed
 * @version 1.0
 */
public class HeavyFactoryService implements IHeavyFactoryService {
	private IArmyService armyService;
	
	/**
	 * 组装车辆
	 * 
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void assembleVehicle(Integer cityID, Integer armyID,Integer num){
		//检查是否有足够的资源
		armyService.checkResources(cityID, armyID, num);
		//减少资源
		armyService.addCityArmy(cityID, armyID, num);
	}
	
	/**
	 * 拆卸车辆
	 * 
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void disassembleVehicle(Integer cityID,Integer armyID, Integer num){
		armyService.reduceCityArmy(cityID, armyID, num);
	}

	public IArmyService getArmyService() {
		return armyService;
	}

	public void setArmyService(IArmyService armyService) {
		this.armyService = armyService;
	}

}
