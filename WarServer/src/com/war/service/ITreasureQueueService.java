package com.war.service;


import java.util.List;

import com.war.domain.TreasureQueue;


/**
 * 宝物效果持续时间队列service接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface ITreasureQueueService {

	public Integer createTreasureQueue(TreasureQueue treasureQueue);

	public void updateTreasureQueue(TreasureQueue treasureQueue);

	public TreasureQueue getTreasureQueueByID(Integer treasureQueueID);

	public List<TreasureQueue> getTreasureQueueList();

	/** 获得宝物效果结束的进程 */
	public List<TreasureQueue> getFinishedTreasureQueueList();
	
	/** 处理完成的进程 */
	public void handleTreasureQueue(TreasureQueue treasureQueue);
	
	/**
	 * 获得指定类型的宝物效果进程
	 * @param cityID 城市编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 * @param type 宝物类型，TreasureTypeConstant中定义
	 * @return
	 */
	public TreasureQueue getTreasureQueueByType(Integer cityID,Integer category,Integer type);
	
	/**
	 * 获得城市的所有宝物效果进程列表
	 * @param cityID
	 * @return
	 */
	public List<TreasureQueue> getTreasureQueueListByCityID(Integer cityID);
	
	/**
	 * 获得指挥官的所有宝物效果进程列表
	 * @param cityHeroID
	 * @return
	 */
	public List<TreasureQueue> getTreasureQueueListByCityHeroID(Integer cityHeroID);
	
	/**
	 * 取消宝物效果
	 * @param treasureQueueID 宝物效果编号
	 */
	public void cancelTreasureQueue(Integer treasureQueueID);
	
}