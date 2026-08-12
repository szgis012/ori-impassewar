package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IDefenseDAO;
import com.war.domain.Defense;

public class DefenseDAO extends SqlMapClientDaoSupport implements IDefenseDAO {

	public Integer createDefense(Defense defense) {
		return (Integer)this.getSqlMapClientTemplate().insert("Defense.createDefense", defense);
	}

	public void updateDefense(Defense defense) {
		this.getSqlMapClientTemplate().update("Defense.updateDefense", defense);
	}

	public void deleteDefenseByID(Integer defenseID) {
		this.getSqlMapClientTemplate().delete("Defense.deleteDefenseByID", defenseID);
	}

	public Defense getDefenseByID(Integer defenseID) {
		return (Defense)this.getSqlMapClientTemplate().queryForObject("Defense.getDefenseByID", defenseID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Defense> getDefenseList() {
		return this.getSqlMapClientTemplate().queryForList("Defense.getDefenseList");
	}

}
