package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ITechnologyDAO;
import com.war.domain.Technology;

public class TechnologyDAO extends SqlMapClientDaoSupport implements ITechnologyDAO{

	public Integer createTechnology(Technology technology) {
		return (Integer)this.getSqlMapClientTemplate().insert("Technology.createTechnology", technology);
	}
	
	public void updateTechnology(Technology technology) {
		this.getSqlMapClientTemplate().update("Technology.updateTechnology", technology);
	}
	
	public void deleteTechnologyByID(Integer technologyID) {
		this.getSqlMapClientTemplate().delete("Technology.deleteTechnologyByID", technologyID);
	}
	
	public Technology getTechnologyByID(Integer technologyID) {
		return (Technology)this.getSqlMapClientTemplate().queryForObject("Technology.getTechnologyByID", technologyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Technology> getTechnologyListByType(Integer type){
		return this.getSqlMapClientTemplate().queryForList("Technology.getTechnologyListByType",type);
	}
	
	@SuppressWarnings("unchecked")
	public List<Technology> getTechnologyList() {
		return this.getSqlMapClientTemplate().queryForList("Technology.getTechnologyList");
	}

}