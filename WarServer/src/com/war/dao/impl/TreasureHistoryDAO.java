package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ITreasureHistoryDAO;
import com.war.domain.TreasureHistory;

public class TreasureHistoryDAO extends SqlMapClientDaoSupport implements ITreasureHistoryDAO{

	public Integer createTreasureHistory(TreasureHistory treasureHistory) {
		return (Integer)this.getSqlMapClientTemplate().insert("TreasureHistory.createTreasureHistory", treasureHistory);
	}
	
	public void updateTreasureHistory(TreasureHistory treasureHistory) {
		this.getSqlMapClientTemplate().update("TreasureHistory.updateTreasureHistory", treasureHistory);
	}
	
	public void deleteTreasureHistoryByID(Integer treasureHistoryID) {
		this.getSqlMapClientTemplate().delete("TreasureHistory.deleteTreasureHistoryByID", treasureHistoryID);
	}
	
	public TreasureHistory getTreasureHistoryByID(Integer treasureHistoryID) {
		return (TreasureHistory)this.getSqlMapClientTemplate().queryForObject("TreasureHistory.getTreasureHistoryByID", treasureHistoryID);
	}
	
	@SuppressWarnings("unchecked")
	public List<TreasureHistory> getTreasureHistoryList() {
		return this.getSqlMapClientTemplate().queryForList("TreasureHistory.getTreasureHistoryList");
	}

	@SuppressWarnings("unchecked")
	public List<TreasureHistory> getTreasureHistoryListByPlayerIDAndTreasureIDAndType(Integer playerID, Integer treasureID, Integer type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("treasureID", treasureID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("TreasureHistory.getTreasureHistoryListByPlayerIDAndTreasureIDAndType", params);
	}
	
	public Integer getDailyTreasureHistoryNumByPlayerIDAndTreasureIDAndType(Integer playerID, Integer treasureID, Integer type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("treasureID", treasureID);
		params.put("type", type);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("TreasureHistory.getDailyTreasureHistoryNumByPlayerIDAndTreasureIDAndType", params);
	}
}