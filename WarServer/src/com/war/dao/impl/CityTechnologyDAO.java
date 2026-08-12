package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityTechnologyDAO;
import com.war.domain.CityTechnology;

public class CityTechnologyDAO extends SqlMapClientDaoSupport implements ICityTechnologyDAO{

	public Integer createCityTechnology(CityTechnology cityTechnology) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityTechnology.createCityTechnology", cityTechnology);
	}
	
	public void updateCityTechnologyState(Integer cityTechnologyID,Integer state){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityTechnologyID", cityTechnologyID);
		params.put("state", state);
		
		this.getSqlMapClientTemplate().update("CityTechnology.updateCityTechnologyState",params);
	}
	
	public void updateCityTechnology(CityTechnology cityTechnology) {
		this.getSqlMapClientTemplate().update("CityTechnology.updateCityTechnology", cityTechnology);
	}
	
	public void deleteCityTechnologyByID(Integer cityTechnologyID) {
		this.getSqlMapClientTemplate().delete("CityTechnology.deleteCityTechnologyByID", cityTechnologyID);
	}
	
	public CityTechnology getCityTechnologyByID(Integer cityTechnologyID) {
		return (CityTechnology)this.getSqlMapClientTemplate().queryForObject("CityTechnology.getCityTechnologyByID", cityTechnologyID);
	}
	
	public CityTechnology getCityTechnologyByCityIDAndState(Integer cityID,Integer state){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("state", state);
		
		return (CityTechnology)this.getSqlMapClientTemplate().queryForObject("CityTechnology.getCityTechnologyByCityIDAndState",params);
	}
	
	public Integer getCityTechnologyLevelByCityIDAndTechnologyID(Integer cityID,Integer technologyID){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("technologyID", technologyID);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityTechnology.getCityTechnologyLevelByCityIDAndTechnologyID",params);
	}
	
	public CityTechnology getCityTechnologyByCityIDAndTechnologyID(Integer cityID,Integer technologyID){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("technologyID", technologyID);
		
		return (CityTechnology)this.getSqlMapClientTemplate().queryForObject("CityTechnology.getCityTechnologyByCityIDAndTechnologyID",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityTechnology> getCityTechnologyListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("CityTechnology.getCityTechnologyListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityTechnology> getCityTechnologyList() {
		return this.getSqlMapClientTemplate().queryForList("CityTechnology.getCityTechnologyList");
	}

	public Integer getCityTechnologyNumByCityIDAndLevel(Integer cityID, Integer level) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("level", level);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("CityTechnology.getCityTechnologyNumByCityIDAndLevel", params);
	}

}