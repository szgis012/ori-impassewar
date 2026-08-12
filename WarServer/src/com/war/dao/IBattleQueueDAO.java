package com.war.dao;

import java.util.List;

import com.war.domain.BattleQueue;

public interface IBattleQueueDAO {
	
	/**
	 * 创建战斗序列信息
	 * @param battleQueue
	 * @return
	 */
	public Integer createBattleQueue(BattleQueue battleQueue);

	/**
	 * 更新战斗序列信息
	 * @param battleQueue
	 */
	public void updateBattleQueue(BattleQueue battleQueue);

	/**
	 * 根据军队编号更新其战斗队列顺序
	 * @param cityMilitaryID
	 */
	public void updateBattleQueueOrderByCityMilitaryID(Integer cityMilitaryID, Integer order);
	
	/**
	 * 根据编号删除战斗序列信息
	 * @param battleQueueID
	 */
	public void deleteBattleQueueByID(Integer battleQueueID);

	/**
	 * 根据编号删除战斗序列信息
	 * @param mapID
	 */
	public void deleteBattleQueueByMapID(Integer mapID);
	
	/**
	 * 根据编号获得战斗序列信息
	 * @param battleQueueID
	 * @return
	 */
	public BattleQueue getBattleQueueByID(Integer battleQueueID);

	/**
	 * 获得战斗序列信息列表
	 * @return
	 */
	public List<BattleQueue> getBattleQueueList();

	/**
	 * 根据地图编号通过城市编号获得战斗队列数目
	 * @param cityID
	 * @return
	 */
	public Integer getBattleQueueNumWithCityIDByMapID(Integer cityID);
	
	/**
	 * 根据地图编号获得战斗队列数目
	 * @param mapID
	 * @return
	 */
	public Integer getBattleQueueNumByMapID(Integer mapID);
	
	/**
	 * 根据坐标获得战斗队列数目
	 * @param posX
	 * @param posY
	 * @return
	 */
	public Integer getBattleQueueNumByPosXAndPosY(Integer posX, Integer posY);
	
	/**
	 * 根据地图编号获得战斗队列列表(升序)
	 * @param mapID
	 * @return
	 */
	public List<BattleQueue> getBattleQueueListByMapIDOrderByOrder(Integer mapID);
	
	/**
	 * 根据军队编号获得战斗队列编号
	 * @param cityMilitaryID
	 * @return
	 */
	public Integer getBattleQueueIDByCityMilitaryID(Integer cityMilitaryID);
	
	/**
	 * 根据地图编号更新战斗队列排序
	 * @param mapID
	 * @return
	 */
	public void refreshBattleQueue(Integer mapID);
}
