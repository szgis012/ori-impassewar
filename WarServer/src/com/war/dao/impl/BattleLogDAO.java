package com.war.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IBattleLogDAO;
import com.war.domain.BattleLog;

public class BattleLogDAO extends SqlMapClientDaoSupport implements IBattleLogDAO {

	public Integer createBattleLog(BattleLog battleLog) {
		return (Integer)this.getSqlMapClientTemplate().insert("BattleLog.createBattleLog", battleLog);
	}

	public void updateBattleLog(BattleLog battleLog) {
		this.getSqlMapClientTemplate().update("BattleLog.updateBattleLog", battleLog);
	}

	public void deleteBattleLogByID(Integer battleLogID) {
		this.getSqlMapClientTemplate().delete("BattleLog.deleteBattleLogByID", battleLogID);
	}

	public BattleLog getBattleLogByID(Integer battleLogID) {
		return (BattleLog)this.getSqlMapClientTemplate().queryForObject("BattleLog.getBattleLogByID", battleLogID);
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleLog> getBattleLogList() {
		return this.getSqlMapClientTemplate().queryForList("BattleLog.getBattleLogList");
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleLog> getBattleLogListByPlayerID(Integer playerID) {
		return this.getSqlMapClientTemplate().queryForList("BattleLog.getBattleLogListByPlayerID", playerID);
	}

	@SuppressWarnings("unchecked")
	public List<BattleLog> getBattleLogListByPosXAndPosYOrderByStartTime(Integer posX, Integer posY) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("posX", posX);
		params.put("posY", posY);
		
		return this.getSqlMapClientTemplate().queryForList("BattleLog.getBattleLogListByPosXAndPosYOrderByStartTime", params);
	}

	public Integer getBattleLogNumByAttackerPlayerID(Integer attackerPlayerID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleLog.getBattleLogNumByAttackerPlayerID", attackerPlayerID);
	}

	public Integer getBattleLogNumForAttackTask(Integer playerID, Integer level, Date time) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("attackerPlayerID", playerID);
		params.put("level", level);
		params.put("time", time);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleLog.getBattleLogNumForAttackTask", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleLog> getBattleLogPagingListByPlayerID(Integer playerID, Integer start, Integer offset) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("start", start);
		params.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("BattleLog.getBattleLogPagingListByPlayerID", params);
	}
	
	public Integer getBattleLogNumByPlayerID(Integer playerID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("BattleLog.getBattleLogNumByPlayerID", playerID);
	}

}
