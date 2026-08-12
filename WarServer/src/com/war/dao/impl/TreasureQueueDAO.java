package com.war.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ITreasureQueueDAO;
import com.war.domain.TreasureQueue;

/**
 * 宝物效果持续时间队列dao实现
 *
 * @author ghleed
 * @version 1.0
 */
public class TreasureQueueDAO extends SqlMapClientDaoSupport implements ITreasureQueueDAO{

	public Integer createTreasureQueue(TreasureQueue treasureQueue) {
		return (Integer)this.getSqlMapClientTemplate().insert("TreasureQueue.createTreasureQueue", treasureQueue);
	}
	
	public void updateTreasureQueue(TreasureQueue treasureQueue) {
		this.getSqlMapClientTemplate().update("TreasureQueue.updateTreasureQueue", treasureQueue);
	}
	
	public void deleteTreasureQueueByID(Integer treasureQueueID) {
		this.getSqlMapClientTemplate().delete("TreasureQueue.deleteTreasureQueueByID", treasureQueueID);
	}
	
	public void deleteTreasureQueueByCityHeroID(Integer cityHeroID) {
		this.getSqlMapClientTemplate().delete("TreasureQueue.deleteTreasureQueueByCityHeroID", cityHeroID);
	}
	
	public TreasureQueue getTreasureQueueByID(Integer treasureQueueID) {
		return (TreasureQueue)this.getSqlMapClientTemplate().queryForObject("TreasureQueue.getTreasureQueueByID", treasureQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<TreasureQueue> getTreasureQueueList() {
		return this.getSqlMapClientTemplate().queryForList("TreasureQueue.getTreasureQueueList");
	}
	
	@SuppressWarnings("unchecked")
	public List<TreasureQueue> getFinishedTreasureQueueList(){
		return this.getSqlMapClientTemplate().queryForList("TreasureQueue.getFinishedTreasureQueueList");
	}

	public TreasureQueue getTreasureQueueByType(Integer targetID,Integer category,Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("targetID", targetID);
		params.put("category", category);
		params.put("type", type);
		
		return (TreasureQueue)this.getSqlMapClientTemplate().queryForObject("TreasureQueue.getTreasureQueueByType", params);
	}

	@SuppressWarnings("unchecked")
	public List<TreasureQueue> getTreasureQueueListByTargetID(Integer targetID) {
		return this.getSqlMapClientTemplate().queryForList("TreasureQueue.getTreasureQueueListByTargetID",targetID);
	}
	
	@SuppressWarnings("unchecked")
	public List<TreasureQueue> getTreasureQueueListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("TreasureQueue.getTreasureQueueListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<TreasureQueue> getTreasureQueueListByCityHeroID(Integer cityHeroID) {
		return this.getSqlMapClientTemplate().queryForList("TreasureQueue.getTreasureQueueListByCityHeroID",cityHeroID);
	}
}
