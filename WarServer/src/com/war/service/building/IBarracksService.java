package com.war.service.building;

/**
 * 兵营service
 *
 * @author ghleed
 * @version 1.0
 */
public interface IBarracksService {
	/**
	 * 招募新兵
	 * @param cityID 城市编号
	 * @param enlistNum 招募数量
	 */ 
	public void enlistSoldier(Integer cityID, Integer enlistNum);
	
	/**
	 * 裁减新兵
	 * @param cityID 城市编号
	 * @param reduceNum 裁减数量
	 */ 
	public void reduceSoldier(Integer cityID, Integer reduceNum);
	
	/**
	 * 武装新兵
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void armSoldier(Integer cityID, Integer armyID,Integer num);
	
	/**
	 * 解除士兵的武装
	 * 注：解除武装的士兵将成为新兵
	 * @param cityID 城市编号
	 * @param armyID 士兵类型
	 * @param num 人数
	 */ 
	public void disarmSoldier(Integer cityID,Integer armyID, Integer num);
}
