package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ITradeQueueDAO;
import com.war.domain.TradeQueue;
import com.war.domain.TreasureQueue;

public class TradeQueueDAO extends SqlMapClientDaoSupport implements ITradeQueueDAO{

	public Integer createTradeQueue(TradeQueue tradeQueue) {
		return (Integer)this.getSqlMapClientTemplate().insert("TradeQueue.createTradeQueue", tradeQueue);
	}
	
	public void updateTradeQueue(TradeQueue tradeQueue) {
		this.getSqlMapClientTemplate().update("TradeQueue.updateTradeQueue", tradeQueue);
	}
	
	public void deleteTradeQueueByID(Integer tradeQueueID) {
		this.getSqlMapClientTemplate().delete("TradeQueue.deleteTradeQueueByID", tradeQueueID);
	}
	
	public TradeQueue getTradeQueueByID(Integer tradeQueueID) {
		return (TradeQueue)this.getSqlMapClientTemplate().queryForObject("TradeQueue.getTradeQueueByID", tradeQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeQueue> getTradeQueueListBySellerIDAndType(Integer sellerID, Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("sellerID", sellerID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("TradeQueue.getTradeQueueListBySellerIDAndType", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeQueue> getTradeQueueListByBuyerIDAndType(Integer buyerID, Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("buyerID", buyerID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("TradeQueue.getTradeQueueListByBuyerIDAndType", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeQueue> getTradeQueueListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("TradeQueue.getTradeQueueListByCityID", cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeQueue> getTradeQueueListByCityIDAndType(Integer cityID, Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("TradeQueue.getTradeQueueListByCityIDAndType", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeQueue> getArrivedTradeQueueList(){
		return this.getSqlMapClientTemplate().queryForList("TradeQueue.getArrivedTradeQueueList");
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeQueue> getTradeQueueList() {
		return this.getSqlMapClientTemplate().queryForList("TradeQueue.getTradeQueueList");
	}

	@SuppressWarnings("unchecked")
	public List<TradeQueue> getResourceTradeQueueListByTargetCityID(Integer targetCityID) {
		return this.getSqlMapClientTemplate().queryForList("TradeQueue.getResourceTradeQueueListByTargetCityID",targetCityID);
	}
	
	public Integer getImportResourceTradeQueueNum(Integer targetCityID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("TradeQueue.getImportResourceTradeQueueNum",targetCityID);
	}
}