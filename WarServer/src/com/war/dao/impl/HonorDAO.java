package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IHonorDAO;
import com.war.domain.Honor;

public class HonorDAO extends SqlMapClientDaoSupport implements IHonorDAO{

	public Integer createHonor(Honor honor) {
		return (Integer)this.getSqlMapClientTemplate().insert("Honor.createHonor", honor);
	}
	
	public void updateHonor(Honor honor) {
		this.getSqlMapClientTemplate().update("Honor.updateHonor", honor);
	}
	
	public void deleteHonorByID(Integer honorID) {
		this.getSqlMapClientTemplate().delete("Honor.deleteHonorByID", honorID);
	}
	
	public Honor getHonorByID(Integer honorID) {
		return (Honor)this.getSqlMapClientTemplate().queryForObject("Honor.getHonorByID", honorID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Honor> getHonorList() {
		return this.getSqlMapClientTemplate().queryForList("Honor.getHonorList");
	}

}