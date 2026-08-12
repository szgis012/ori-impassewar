package com.war.service;

import java.util.List;

import com.war.domain.TradeQueue;

public interface ITradeQueueService {

	/**
	 * 创建交易队列
	 * @param tradeQueue
	 * @return
	 */
	public Integer createTradeQueue(TradeQueue tradeQueue);

	/**
	 * 更新交易队列
	 * @param tradeQueue
	 */
	public void updateTradeQueue(TradeQueue tradeQueue);

	/**
	 * 根据交易队列编号删除交易队列
	 * @param tradeQueueID
	 */
	public void deleteTradeQueueByID(Integer tradeQueueID);
	
	/**
	 * 减少交易花费的时间
	 * @param queueID 进程编号
	 */
	public void reduceTradeCostTime(int queueID);

	/**
	 * 根据交易队列编号获得交易队列
	 * @param tradeQueueID
	 * @return
	 */
	public TradeQueue getTradeQueueByID(Integer tradeQueueID);

	/**
	 * 根据城市编号获得资源交易队列列表
	 * @param cityID
	 * @return
	 */
	public List<TradeQueue> getTradeQueueListByCityID(Integer cityID);
	
	/**
	 * 获得已到达交易队列列表
	 * @return
	 */
	public List<TradeQueue> getArrivedTradeQueueList();
	
	/**
	 * 获得交易队列列表
	 * @return
	 */
	public List<TradeQueue> getTradeQueueList();
	
	/**
	 * 获得买入的资源交易笔数数目
	 * @param targetCityID
	 * @return
	 */
	public int getImportResourceTradeQueueNum(Integer targetCityID);
}
