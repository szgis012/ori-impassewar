package com.war.service.building;

/**
 * 重型工厂service
 *
 * @author ghleed
 * @version 1.0
 */
public interface IHeavyFactoryService {

	/**
	 * 组装车辆
	 * 
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void assembleVehicle(Integer cityID, Integer armyID,Integer num);
	
	/**
	 * 拆卸车辆
	 * 
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void disassembleVehicle(Integer cityID,Integer armyID, Integer num);
	
}
