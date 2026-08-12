package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IBattleDetailDAO;
import com.war.domain.BattleDetail;

public class BattleDetailDAO extends SqlMapClientDaoSupport implements IBattleDetailDAO {

	public void createBattleDetail(BattleDetail battleDetail) {
		this.getSqlMapClientTemplate().insert("BattleDetail.createBattleDetail", battleDetail);
	}

	public void deleteBattleDetailByID(Integer battleLogID, Integer round) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("battleLogID", battleLogID);
		params.put("round", round);
		
		this.getSqlMapClientTemplate().delete("BattleDetail.deleteBattleDetailByID", params);
	}

	public BattleDetail getBattleDetailByID(Integer battleLogID, Integer round) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("battleLogID", battleLogID);
		params.put("round", round);
		
		return (BattleDetail) this.getSqlMapClientTemplate().queryForObject("BattleDetail.getBattleDetailByID", params);
	}

	@SuppressWarnings("unchecked")
	public List<BattleDetail> getBattleDetailList() {
		return this.getSqlMapClientTemplate().queryForList("BattleDetail.getBattleDetailList");
	}

	@SuppressWarnings("unchecked")
	public List<BattleDetail> getBattleDetailListByBattleLogID(Integer battleLogID) {
		return this.getSqlMapClientTemplate().queryForList("BattleDetail.getBattleDetailListByBattleLogID", battleLogID);
	}

	public void updateBattleDetail(BattleDetail battleDetail) {
		this.getSqlMapClientTemplate().update("BattleDetail.updateBattleDetail", battleDetail);
	}

}
