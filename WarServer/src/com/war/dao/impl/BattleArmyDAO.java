package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IBattleArmyDAO;
import com.war.domain.BattleArmy;

public class BattleArmyDAO extends SqlMapClientDaoSupport implements IBattleArmyDAO{

	private static Logger logger = Logger.getLogger(BattleArmyDAO.class);
	
	public Integer createBattleArmy(BattleArmy battleArmy) {
		return (Integer)this.getSqlMapClientTemplate().insert("BattleArmy.createBattleArmy", battleArmy);
	}
	
	public void updateBattleArmyByParams(Map<String,Object> params){
		this.getSqlMapClientTemplate().update("BattleArmy.updateBattleArmyByParams",params);
	}
	
	public void updateBattleArmyByParamsBatch(List<Map<String,Object>> paramsList){
		
		try {
			for(int i=0;i<paramsList.size();i++){
				this.getSqlMapClient().update("BattleArmy.updateBattleArmyByParams",paramsList.get(i));
			}
		} catch (SQLException e) {
			logger.error("异常：", e);
		}
		
	}

	public void updateBattleArmy(BattleArmy battleArmy) {
		this.getSqlMapClientTemplate().update("BattleArmy.updateBattleArmy", battleArmy);
	}
	
	public void deleteBattleArmyByID(Integer battleArmyID) {
		this.getSqlMapClientTemplate().delete("BattleArmy.deleteBattleArmyByID", battleArmyID);
	}
	
	public void deleteBattleArmyByBattleID(Integer battleID){
		this.getSqlMapClientTemplate().delete("BattleArmy.deleteBattleArmyByBattleID",battleID);
	}
	
	public BattleArmy getBattleArmyByBattleIDAndArmyForceAndArmyIndex(Integer battleID,Integer armyForce,
			Integer armyIndex) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("battleID", battleID);
		params.put("armyForce", armyForce);
		params.put("armyIndex", armyIndex);
		
		return (BattleArmy)this.getSqlMapClientTemplate().queryForObject("BattleArmy.getBattleArmyByBattleIDAndArmyIndex",params);
	}
	
	public BattleArmy getBattleArmyByID(Integer battleArmyID) {
		return (BattleArmy)this.getSqlMapClientTemplate().queryForObject("BattleArmy.getBattleArmyByID", battleArmyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleArmy> getBattleArmyListByBattleIDAndArmyForce(Integer battleID,Integer armyForce){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("battleID", battleID);
		params.put("armyForce", armyForce);
		
		return this.getSqlMapClientTemplate().queryForList("BattleArmy.getBattleArmyListByBattleIDAndArmyForce",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<BattleArmy> getBattleArmyListByBattleID(Integer battleID) {
		return this.getSqlMapClientTemplate().queryForList("BattleArmy.getBattleArmyListByBattleID",battleID);
	}

}