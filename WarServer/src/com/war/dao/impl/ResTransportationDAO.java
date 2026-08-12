package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IResTransportationDAO;
import com.war.domain.ResTransportation;

public class ResTransportationDAO extends SqlMapClientDaoSupport implements IResTransportationDAO{

	public Integer createResTransportation(ResTransportation resTransportation) {
		return (Integer)this.getSqlMapClientTemplate().insert("ResTransportation.createResTransportation", resTransportation);
	}
	
	public void deleteResTransportationByID(Integer resTransportationID) {
		this.getSqlMapClientTemplate().delete("ResTransportation.deleteResTransportationByID", resTransportationID);
	}
	
	public ResTransportation getResTransportationByID(Integer resTransportationID) {
		return (ResTransportation)this.getSqlMapClientTemplate().queryForObject("ResTransportation.getResTransportationByID", resTransportationID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ResTransportation> getResTransportationList() {
		return this.getSqlMapClientTemplate().queryForList("ResTransportation.getResTransportationList");
	}

}