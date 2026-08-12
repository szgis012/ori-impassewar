package com.war.service.building;

/**
 * 飞机场service
 *
 * @author ghleed
 * @version 1.0
 */
public interface IAirportService {

	/**
	 * 组装飞机
	 * 
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void assemblePlane(Integer cityID, Integer armyID,Integer num);
	
	/**
	 * 拆卸飞机
	 * 
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void disassemblePlane(Integer cityID,Integer armyID, Integer num);
	
}
