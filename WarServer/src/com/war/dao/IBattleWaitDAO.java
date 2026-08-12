package com.war.dao;

import java.util.List;

import com.war.domain.BattleWait;

public interface IBattleWaitDAO {
	
	/**
	 * 创建战争等待信息
	 * @param battleWait
	 * @return
	 */
	public Integer createBattleWait(BattleWait battleWait);

	/**
	 * 更新战争等待信息
	 * @param battleWait
	 */
	public void updateBattleWait(BattleWait battleWait);

	/**
	 * 根据编号删除战争等待信息
	 * @param battleWaitID
	 */
	public void deleteBattleWaitByID(Integer battleWaitID);

	/**
	 * 根据地图编号删除战斗等待信息
	 * @param mapID
	 */
	public void deleteBattleWaitByMapID(Integer mapID);
	
	/**
	 * 根据编号获得战争等待信息
	 * @param battleWaitID
	 * @return
	 */
	public BattleWait getBattleWaitByID(Integer battleWaitID);

	/**
	 * 获得战争等待信息列表
	 * @return
	 */
	public List<BattleWait> getBattleWaitList();
	
	/**
	 * 根据地图编号通过城市编号获得战斗等待数目
	 * @param cityID
	 * @return
	 */
	public Integer getBattleWaitNumWithCityIDByMapID(Integer cityID);

	/**
	 * 根据坐标获得战斗等待数目
	 * @param posX
	 * @param posY
	 * @return
	 */
	public Integer getBattleWaitNumByPosXAndPosY(Integer posX, Integer posY);
	
	/**
	 * 根据攻击方军队编号获得战斗等待信息
	 * @param attackerCityMilitaryID
	 * @return
	 */
	public BattleWait getBattleWaitByCityMilitaryID(Integer cityMilitaryID);
	
	/**
	 * 获得战斗等待中到期的等待列表信息
	 * @return
	 */
	public List<BattleWait> getIntervalFinishedBattleWaitList();
}
