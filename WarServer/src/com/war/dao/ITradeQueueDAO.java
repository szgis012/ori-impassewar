package com.war.dao;

import java.util.List;

import com.war.domain.TradeQueue;
import com.war.domain.TreasureQueue;

public interface ITradeQueueDAO {

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
	 * 根据交易队列编号获得交易队列
	 * @param tradeQueueID
	 * @return
	 */
	public TradeQueue getTradeQueueByID(Integer tradeQueueID);

	/**
	 * 根据出售方编号及类型获得交易队列列表
	 * @param sellerID
	 * @param type
	 * @return
	 */
	public List<TradeQueue> getTradeQueueListBySellerIDAndType(Integer sellerID, Integer type);
	
	/**
	 * 根据购买方编号及类型获得交易队列列表
	 * @param buyerID
	 * @param type
	 * @return
	 */
	public List<TradeQueue> getTradeQueueListByBuyerIDAndType(Integer buyerID, Integer type);
	
	/**
	 * 根据城市编号获得交易队列
	 * @param cityID
	 * @return
	 */
	public List<TradeQueue> getTradeQueueListByCityID(Integer cityID);
	
	/**
	 * 根据城市编号及类型获得资源交易队列列表
	 * @param cityID
	 * @param type
	 * @return
	 */
	public List<TradeQueue> getTradeQueueListByCityIDAndType(Integer cityID, Integer type);
	
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
	 * 根据目标城市编号获得资源交易列表
	 * @param targetCityID
	 * @return
	 */
	public List<TradeQueue> getResourceTradeQueueListByTargetCityID(Integer targetCityID);
	
	/**
	 * 根据目标城市编号获得买入资源数目
	 * @param targetCityID
	 * @return
	 */
	public Integer getImportResourceTradeQueueNum(Integer targetCityID);
}