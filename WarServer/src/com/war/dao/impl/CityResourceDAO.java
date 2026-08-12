package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityResourceDAO;
import com.war.domain.City;
import com.war.domain.CityResource;
import com.war.exception.GameException;

public class CityResourceDAO extends SqlMapClientDaoSupport implements ICityResourceDAO {
    
	public void createCityResource(CityResource cityResource) {
		this.getSqlMapClientTemplate().insert("CityResource.createCityResource", cityResource);
	}

	public void updateCityResource(CityResource cityResource) {
		this.getSqlMapClientTemplate().update("CityResource.updateCityResource", cityResource);
	}
	
	public void deleteCityResourceByCityID(Integer cityID) {
		this.getSqlMapClientTemplate().delete("CityResource.deleteCityResourceByCityID", cityID);
	}

	public CityResource getCityResourceByCityID(Integer cityID) {
		return (CityResource) this.getSqlMapClientTemplate().queryForObject("CityResource.getCityResourceByCityID", cityID);
	}
	
    public void updateCityResource(java.util.Map<String, Object> params){
    	if(params == null || !params.containsKey("cityID") || params.size()<2)
    		throw new GameException("参数有误");
    	
    	this.getSqlMapClientTemplate().update("CityResource.updateCityResourceParams",params);		
    }

	@SuppressWarnings("unchecked")
	public List<CityResource> getCityResourceList() {
		return this.getSqlMapClientTemplate().queryForList("CityResource.getCityResourceList");
	}
	
    public void updateResourcesByCityID(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long money) {
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("cityID", cityID);
    	map.put("woodNum", woodNum);
    	map.put("steelNum", steelNum);
    	map.put("oilNum", oilNum);
    	map.put("foodNum", foodNum);
    	map.put("moneyNum", money);
    	
    	this.getSqlMapClientTemplate().update("CityResource.updateResourcesByCityID",map);
	}
    
    public void updateResourcesMaxByCityID(Integer cityID, Long resourceNumMax) {
		
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("cityID", cityID);
    	map.put("resourceNumMax", resourceNumMax);
    	
    	this.getSqlMapClientTemplate().update("CityResource.updateResourcesMaxByCityID",map);
	}

    public void updateResourcesOutputByCityID(Integer cityID, Long woodOutput, Long steelOutput, Long oilOutput, Long foodOutput){
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("cityID", cityID);
    	map.put("woodOutput", woodOutput);
    	map.put("steelOutput", steelOutput);
    	map.put("oilOutput", oilOutput);
    	map.put("foodOutput", foodOutput);
    	
    	this.getSqlMapClientTemplate().update("CityResource.updateResourcesOutputByCityID",map);
    }
    
    public void updateResourceConsumeByCityID(Integer cityID, Long oilConsume, Long foodConsume, Long moneyConsume) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("cityID", cityID);
    	map.put("oilConsume", oilConsume);
    	map.put("foodConsume", foodConsume);
    	map.put("moneyConsume", moneyConsume);
    	
    	this.getSqlMapClientTemplate().update("CityResource.updateResourceConsumeByCityID",map);
    }
    
    public void addCityResourcesByCityID(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long moneyNum){
		
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("cityID", cityID);
    	map.put("woodNum", woodNum);
    	map.put("steelNum", steelNum);
    	map.put("oilNum", oilNum);
    	map.put("foodNum", foodNum);
    	map.put("moneyNum", moneyNum);
    	
    	this.getSqlMapClientTemplate().update("CityResource.addCityResourcesByCityID",map);
	}
    
    public void minusCityResourcesByCityID(Integer cityID, Long woodNum, Long steelNum, Long oilNum, Long foodNum, Long moneyNum){
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("cityID", cityID);
    	map.put("woodNum", woodNum);
    	map.put("steelNum", steelNum);
    	map.put("oilNum", oilNum);
    	map.put("foodNum", foodNum);
    	map.put("moneyNum", moneyNum);
    	
    	this.getSqlMapClientTemplate().update("CityResource.minusCityResourcesByCityID",map);
    }
    
    @SuppressWarnings("unchecked")
	public Map<String,Long> getResourcesNumByCityID(Integer cityID){
    	return (Map<String,Long>)this.getSqlMapClientTemplate().queryForObject("CityResource.getResourcesNumByCityID",cityID);
    }
    
    @SuppressWarnings("unchecked")
	public Long getResourcesNumMaxByCityID(Integer cityID){
    	return (Long) this.getSqlMapClientTemplate().queryForObject("CityResource.getResourcesNumMaxByCityID",cityID);
    }
    
    @SuppressWarnings("unchecked")
	public Map<String,Long> getResourcesOutputByCityID(Integer cityID){
    	return (Map<String,Long>)this.getSqlMapClientTemplate().queryForObject("CityResource.getResourcesOutputByCityID",cityID);
    }
    
    @SuppressWarnings("unchecked")
	public Map<String,Long> getResourcesConsumeByCityID(Integer cityID){
    	return (Map<String,Long>)this.getSqlMapClientTemplate().queryForObject("CityResource.getResourcesConsumeByCityID",cityID);
    }
    
    public void computeCityResource() {
    	this.getSqlMapClientTemplate().update("CityResource.computeCityResource");
    }
    
    @SuppressWarnings("unchecked")
	public List<City> getCityListOfResourceZero(){
    	return this.getSqlMapClientTemplate().queryForList("CityResource.getCityListOfResourceZero");
    }
}
