package com.war.service.building;

import java.util.List;

import com.war.domain.ResTrade;
import com.war.domain.TradeQueue;

public interface IMarketService {

	/**
	 * 运输资源
	 * @param cityID
	 * @param targetPosX
	 * @param targetPosY
	 * @param woodAmount
	 * @param steelAmount
	 * @param oilAmount
	 * @param foodAmount
	 * @param moneyAmount
	 */
	public void transportResouce(Integer cityID, Integer targetPosX, Integer targetPosY, Long woodAmount, Long steelAmount, Long oilAmount, Long foodAmount, Long moneyAmount);
	
	/**
	 * 获得资源销售列表
	 * @param cityID
	 * @param resourceType
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<ResTrade> getResourceSalesList(Integer cityID,Integer resourceType,Integer start,Integer offset);
	
	/**
	 * 获得资源销售(挂单)数量
	 * @param cityID
	 * @param resourceType
	 * @return
	 */
	public Integer getResourceSalesAmount(Integer cityID,Integer resourceType);
	
	/**
	 * 购买资源
	 * @param resTradeID
	 * @param cityID
	 */
	public void buyResource(Integer resTradeID,Integer cityID);
	
	/**
	 * 获得城市资源销售(挂单)列表
	 * @param cityID
	 * @return
	 */
	public List<ResTrade> getCityResourceSalesList(Integer cityID);
	
	/**
	 * 出售资源
	 * @param resTrade
	 */
	public void sellResource(ResTrade resTrade);
	
	/**
	 * 取消销售资源
	 * @param resTradeID
	 */
	public void cancelResourceSale(Integer resTradeID);
	
	/**
	 * 取消城市所有资源销售
	 * @param cityID
	 */
	public void cancelCityAllResourceSales(Integer cityID);
	
	/**
	 * 获得城市资源运输列表
	 * @param cityID
	 * @return
	 */
	public List<TradeQueue> getCityResourceTransportationList(Integer cityID);
	
	/**
	 * 获得城市交易队列列表
	 * @param cityID
	 * @return
	 */
	public List<TradeQueue> getCityTradeQueueList(Integer cityID);
	
	/**
	 * 完成资源运送(Quartz调用)
	 * @param tradeQueue
	 */
	public void finishResourceTransportation(TradeQueue tradeQueue);
	
	/**
	 * 资源运送商人返回(Quartz调用)
	 * @param tradeQueue
	 */
	public void resourceTransportationReturn(TradeQueue tradeQueue);
	
	/**
	 * 完成资源交易(Quartz调用)
	 * @param tradeQueue
	 */
	public void finishResourceTrade(TradeQueue tradeQueue);
	
	
}
