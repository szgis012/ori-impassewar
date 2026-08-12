package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.INameDAO;
import com.war.domain.Name;

public class NameDAO extends SqlMapClientDaoSupport implements INameDAO{

	public void createName(Name name) {
		this.getSqlMapClientTemplate().insert("Name.createName", name);
	}
	
	@SuppressWarnings("unchecked")
	public String[] getFirstNameArray(int amount) {
		return (String[]) this.getSqlMapClientTemplate().queryForList("Name.getFirstNameArray", amount).toArray(new String[amount]);
	}
	
	@SuppressWarnings("unchecked")
	public String[] getLastNameArray(int amount) {
		return (String[])this.getSqlMapClientTemplate().queryForList("Name.getLastNameArray", amount).toArray(new String[amount]);
	}
	
	@SuppressWarnings("unchecked")
	public List<Name> getNameList() {
		return this.getSqlMapClientTemplate().queryForList("Name.getNameList");
	}

}