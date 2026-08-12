package com.war.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IOrdnanceDAO;
import com.war.domain.Ordnance;

/**
 * 军械dao实现
 *
 * @author ghleed
 * @version 1.0
 */
public class OrdnanceDAO extends SqlMapClientDaoSupport implements IOrdnanceDAO{

	public Integer createOrdnance(Ordnance ordnance) {
		return (Integer)this.getSqlMapClientTemplate().insert("Ordnance.createOrdnance", ordnance);
	}
	
	public void updateOrdnance(Ordnance ordnance) {
		this.getSqlMapClientTemplate().update("Ordnance.updateOrdnance", ordnance);
	}
	
	public void deleteOrdnanceByID(Integer ordnanceID) {
		this.getSqlMapClientTemplate().delete("Ordnance.deleteOrdnanceByID", ordnanceID);
	}
	
	public Ordnance getOrdnanceByID(Integer ordnanceID) {
		return (Ordnance)this.getSqlMapClientTemplate().queryForObject("Ordnance.getOrdnanceByID", ordnanceID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ordnance> getOrdnanceList() {
		return this.getSqlMapClientTemplate().queryForList("Ordnance.getOrdnanceList");
	}

}