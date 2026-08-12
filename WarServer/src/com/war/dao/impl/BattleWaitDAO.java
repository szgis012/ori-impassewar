package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IBattleWaitDAO;
import com.war.domain.BattleWait;

public class BattleWaitDAO extends SqlMapClientDaoSupport implements IBattleWaitDAO {

	public Integer createBattleWait(BattleWait battleWait) {
		return (Integer)this.getSqlMapClientTemplate().insert("BattleWait.createBattleWait", battleWait);
	}

	public void updateBattleWait(BattleWait battleWait) {
		this.getSqlMapClientTemplate().update("BattleWait.updateBattleWait", battleWait);
	}

	public void deleteBattleWaitByID(Integer battleWaitID) {
		this.getSqlMapClientTemplate().delete("BattleWait.deleteBattleWaitByID", battleWaitID);
	}

	public void deleteBattleWaitByMapID(Integer mapID) {
		this.getSqlMapClientTemplate().delete("BattleWait.deleteBattleWaitByMapID", mapID);
	}
	
	public BattleWait getBattleWaitByID(Integer battleWaitID) {
		return (BattleWait)this.getSqlMapClientTemplate().queryForObject("BattleWait.getBattleWaitByID", battleWaitID);
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleWait> getBattleWaitList() {
		return this.getSqlMapClientTemplate().queryForList("BattleWait.getBattleWaitList");
	}

	public Integer getBattleWaitNumWithCityIDByMapID(Integer cityID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleWait.getBattleWaitNumWithCityIDByMapID", cityID);
	}

	public Integer getBattleWaitNumByPosXAndPosY(Integer posX, Integer posY) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("posX", posX);
		params.put("posY", posY);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("BattleWait.getBattleWaitNumByPosXAndPosY", params);
	}
	
	public BattleWait getBattleWaitByCityMilitaryID(Integer cityMilitaryID) {
		return (BattleWait) this.getSqlMapClientTemplate().queryForObject("BattleWait.getBattleWaitByCityMilitaryID", cityMilitaryID);
	}

	@SuppressWarnings("unchecked")
	public List<BattleWait> getIntervalFinishedBattleWaitList() {
		return this.getSqlMapClientTemplate().queryForList("BattleWait.getIntervalFinishedBattleWaitList");
	}
	
}
