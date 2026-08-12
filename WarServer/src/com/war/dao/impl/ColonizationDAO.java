package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IColonizationDAO;
import com.war.domain.Colonization;

public class ColonizationDAO extends SqlMapClientDaoSupport implements IColonizationDAO{

	public Integer createColonization(Colonization colonization) {
		return (Integer)this.getSqlMapClientTemplate().insert("Colonization.createColonization", colonization);
	}
	
	public void updateHaveImposed(Integer haveImposed) {
		this.getSqlMapClientTemplate().update("Colonization.updateHaveImposed", haveImposed);
	}
	
	public void updateHaveImposedByColonizationID(Integer colonizationID, Integer haveImposed) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("colonizationID", colonizationID);
		params.put("haveImposed", haveImposed);
		
		this.getSqlMapClientTemplate().update("Colonization.updateHaveImposedByColonizationID", params);
	}
	
	public void updateColonization(Colonization colonization) {
		this.getSqlMapClientTemplate().update("Colonization.updateColonization", colonization);
	}
	
	public void deleteColonizationByID(Integer colonizationID) {
		this.getSqlMapClientTemplate().delete("Colonization.deleteColonizationByID", colonizationID);
	}
	
	public Integer getColonizationNumByCityID(Integer cityID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Colonization.getColonizationNumByCityID", cityID);
	}
	
	public Colonization getColonizationByID(Integer colonizationID) {
		return (Colonization)this.getSqlMapClientTemplate().queryForObject("Colonization.getColonizationByID", colonizationID);
	}
	
	public Colonization getColonizationByCityIDAndTargetCityID(Integer cityID, Integer targetCityID) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("targetCityID", targetCityID);
		
		return (Colonization)this.getSqlMapClientTemplate().queryForObject("Colonization.getColonizationByCityIDAndTargetCityID", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Colonization> getColonizationListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("Colonization.getColonizationListByCityID", cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Colonization> getFinishedColonizationList() {
		return this.getSqlMapClientTemplate().queryForList("Colonization.getFinishedColonizationList");
	}
	
	@SuppressWarnings("unchecked")
	public List<Colonization> getColonizationList() {
		return this.getSqlMapClientTemplate().queryForList("Colonization.getColonizationList");
	}

}