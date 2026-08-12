package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IArmyDAO;
import com.war.domain.Army;

/**
 * 兵种DAO实现
 * 
 * @author TopTong
 * @version 1.0
 */

public class ArmyDAO extends SqlMapClientDaoSupport implements IArmyDAO{

	public Integer createArmy(Army army) {
		return (Integer)this.getSqlMapClientTemplate().insert("Army.createArmy", army);
	}
	
	public void updateArmy(Army army) {
		this.getSqlMapClientTemplate().update("Army.updateArmy", army);
	}
	
	public void deleteArmyByID(Integer armyID) {
		this.getSqlMapClientTemplate().delete("Army.deleteArmyByID", armyID);
	}
	
	public Army getArmyByID(Integer armyID) {
		return (Army)this.getSqlMapClientTemplate().queryForObject("Army.getArmyByID", armyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Army> getArmyList() {
		return this.getSqlMapClientTemplate().queryForList("Army.getArmyList");
	}

	@SuppressWarnings("unchecked")
	public List<Army> getArmyListByType(Integer type) {
		return this.getSqlMapClientTemplate().queryForList("Army.getArmyListByType", type);
	}
	
}