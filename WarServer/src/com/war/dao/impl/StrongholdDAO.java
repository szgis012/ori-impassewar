package com.war.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IStrongholdDAO;
import com.war.domain.Stronghold;

/**
 * 要塞DAO接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class StrongholdDAO extends SqlMapClientDaoSupport implements IStrongholdDAO{

	public Integer createStronghold(Stronghold stronghold) {
		return (Integer)this.getSqlMapClientTemplate().insert("Stronghold.createStronghold", stronghold);
	}
	
	public void updateStronghold(Stronghold stronghold) {
		this.getSqlMapClientTemplate().update("Stronghold.updateStronghold", stronghold);
	}
	
	public void deleteStrongholdByID(Integer strongholdID) {
		this.getSqlMapClientTemplate().delete("Stronghold.deleteStrongholdByID", strongholdID);
	}
	
	public Stronghold getStrongholdByID(Integer strongholdID) {
		return (Stronghold)this.getSqlMapClientTemplate().queryForObject("Stronghold.getStrongholdByID", strongholdID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Stronghold> getStrongholdList() {
		return this.getSqlMapClientTemplate().queryForList("Stronghold.getStrongholdList");
	}

}