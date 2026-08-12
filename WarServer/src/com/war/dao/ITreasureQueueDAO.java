package com.war.dao;


import java.util.List;

import com.war.domain.TreasureQueue;


/**
 * 宝物效果持续时间队列dao接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface ITreasureQueueDAO {

	public Integer createTreasureQueue(TreasureQueue treasureQueue);

	public void updateTreasureQueue(TreasureQueue treasureQueue);

	public void deleteTreasureQueueByID(Integer treasureQueueID);

	public void deleteTreasureQueueByCityHeroID(Integer cityHeroID);
	
	public TreasureQueue getTreasureQueueByID(Integer treasureQueueID);

	public List<TreasureQueue> getTreasureQueueList();

	/** 获得宝物效果结束的进程*/
	public List<TreasureQueue> getFinishedTreasureQueueList();
	
	/**
	 * 获得指定类型的宝物效果进程
	 * @param cityID 城市编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 * @param type 宝物类型，TreasureTypeConstant中定义
	 * @return
	 */
	public TreasureQueue getTreasureQueueByType(Integer targetID,Integer category,Integer type); 
	
	/**
	 * 根据目标编号获得城市的宝物效果进程列表
	 * @param cityID
	 * @return
	 */
	public List<TreasureQueue> getTreasureQueueListByTargetID(Integer targetID);

	/**
	 * 根据城市编号获得城市的宝物效果进程列表
	 * @param cityID
	 * @return
	 */
	public List<TreasureQueue> getTreasureQueueListByCityID(Integer cityID);
	
	/**
	 * 根据指挥官编号获得城市的宝物效果进程列表
	 * @param cityHeroID
	 * @return
	 */
	public List<TreasureQueue> getTreasureQueueListByCityHeroID(Integer cityHeroID);
	
}