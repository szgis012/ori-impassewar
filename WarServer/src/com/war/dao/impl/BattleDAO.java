package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IBattleDAO;
import com.war.domain.Battle;

public class BattleDAO extends SqlMapClientDaoSupport implements IBattleDAO{

	public Integer createBattle(Battle battle) {
		return (Integer)this.getSqlMapClientTemplate().insert("Battle.createBattle", battle);
	}
	
	public void updateBattleRoundAndPreRoundFinishTime(Integer battleID,Integer round){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("battleID", battleID);
		params.put("round", round);
		
		this.getSqlMapClientTemplate().update("Battle.updateBattleRoundAndPreRoundFinishTime",params);
	}
	
	public void updateBattleExp(Integer battleID,Long attackerExp,Long defenderExp){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("battleID", battleID);
		params.put("attackerExp", attackerExp);
		params.put("defenderExp", defenderExp);
		
		this.getSqlMapClientTemplate().update("Battle.updateBattleExp",params);
	}
	
	public void updateBattleCityDefenseAmount(Integer battleID,String cityDefenseAmount){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("battleID", battleID);
		params.put("cityDefenseAmount", cityDefenseAmount);
	
		this.getSqlMapClientTemplate().update("Battle.updateBattleCityDefenseAmount",params);
	}
	
	public void updateBattle(Battle battle) {
		this.getSqlMapClientTemplate().update("Battle.updateBattle", battle);
	}
	
	public void deleteBattleByID(Integer battleID) {
		this.getSqlMapClientTemplate().delete("Battle.deleteBattleByID", battleID);
	}
	
	public Battle getBattleByID(Integer battleID) {
		return (Battle)this.getSqlMapClientTemplate().queryForObject("Battle.getBattleByID", battleID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Battle> getAttackBattleListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("Battle.getAttackBattleListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Battle> getDefenseBattleListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("Battle.getDefenseBattleListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Battle> getBattleListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("Battle.getBattleListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Battle> getRoundFinishedBattleList(Integer roundTime){
		return this.getSqlMapClientTemplate().queryForList("Battle.getRoundFinishedBattleList",roundTime);
	}
	
	@SuppressWarnings("unchecked")
	public List<Battle> getBattleList() {
		return this.getSqlMapClientTemplate().queryForList("Battle.getBattleList");
	}
	
}