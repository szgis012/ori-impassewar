package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IBattleQueueDAO;
import com.war.domain.BattleQueue;

public class BattleQueueDAO extends SqlMapClientDaoSupport implements IBattleQueueDAO {

	public Integer createBattleQueue(BattleQueue battleQueue) {
		return (Integer)this.getSqlMapClientTemplate().insert("BattleQueue.createBattleQueue", battleQueue);
	}

	public void updateBattleQueue(BattleQueue battleQueue) {
		this.getSqlMapClientTemplate().update("BattleQueue.updateBattleQueue", battleQueue);
	}

	public void updateBattleQueueOrderByCityMilitaryID(Integer cityMilitaryID, Integer order) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityMilitaryID", cityMilitaryID);
		params.put("order", order);
		
		this.getSqlMapClientTemplate().update("BattleQueue.updateBattleQueueOrderByCityMilitaryID", params);
	}
	
	public void deleteBattleQueueByID(Integer battleQueueID) {
		this.getSqlMapClientTemplate().delete("BattleQueue.deleteBattleQueueByID", battleQueueID);
	}
	
	public void deleteBattleQueueByMapID(Integer mapID) {
		this.getSqlMapClientTemplate().delete("BattleQueue.deleteBattleQueueByMapID", mapID);
	}

	public BattleQueue getBattleQueueByID(Integer battleQueueID) {
		return (BattleQueue)this.getSqlMapClientTemplate().queryForObject("BattleQueue.getBattleQueueByID", battleQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleQueue> getBattleQueueList() {
		return this.getSqlMapClientTemplate().queryForList("BattleQueue.getBattleQueueList");
	}

	public Integer getBattleQueueNumWithCityIDByMapID(Integer cityID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleQueue.getBattleQueueNumWithCityIDByMapID", cityID);
	}

	public Integer getBattleQueueNumByMapID(Integer mapID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleQueue.getBattleQueueNumByMapID", mapID);
	}

	public Integer getBattleQueueNumByPosXAndPosY(Integer posX, Integer posY) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("posX", posX);
		params.put("posY", posY);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleQueue.getBattleQueueNumByPosXAndPosY", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleQueue> getBattleQueueListByMapIDOrderByOrder(Integer mapID) {
		return this.getSqlMapClientTemplate().queryForList("BattleQueue.getBattleQueueListByMapIDOrderByOrder", mapID);
	}
	
	public Integer getBattleQueueIDByCityMilitaryID(Integer cityMilitaryID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleQueue.getBattleQueueIDByCityMilitaryID", cityMilitaryID);
	}
	
	public void refreshBattleQueue(Integer mapID) {
		this.getSqlMapClientTemplate().update("BattleQueue.refreshBattleQueue", mapID);
	}
}
