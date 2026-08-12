package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.war.dao.ICityDAO;
import com.war.domain.City;
import com.war.domain.CityInfo;
import com.war.exception.GameException;

public class CityDAO extends SqlMapClientDaoSupport implements ICityDAO {

	public Integer createCity(City city) {
		return (Integer)this.getSqlMapClientTemplate().insert("City.createCity", city);
	}
	
	public void updatePopulationMaxByCityID(Integer cityID, Long populationMax){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cityID", cityID);
		map.put("populationMax", populationMax);
		
		this.getSqlMapClientTemplate().update("City.updatePopulationMaxByCityID",map);
	}
	
	public void updateStateByCityID(Integer cityID, Integer state) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("cityID", cityID);
		map.put("state", state);
		
		this.getSqlMapClientTemplate().update("City.updateStateByCityID",map);
	}
	
	public void batchAddCitySecurity() {
		this.getSqlMapClientTemplate().update("City.batchAddCitySecurity");
	}
	
	public void updateCity(City city) {
		this.getSqlMapClientTemplate().update("City.updateCity", city);
	}
	
	public void deleteCityByID(Integer cityID) {
		this.getSqlMapClientTemplate().delete("City.deleteCityByID", cityID);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Long> getPopulationByCityID(Integer cityID){
		return (Map<String,Long>)this.getSqlMapClientTemplate().queryForObject("City.getPopulationByCityID",cityID);
	}
	
	public Integer getBusinessFreeByCityID(Integer cityID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("City.getBusinessFreeByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Integer> getCityTaxAndSecurity(Integer cityID){
		return (Map<String,Integer>)this.getSqlMapClientTemplate().queryForObject("City.getCityTaxAndSecurity",cityID);
	}
	
	public Integer getPlayerIDByCityID(Integer cityID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("City.getPlayerIDByCityID",cityID);
	}
	
	public Integer getCityIDByPlayerID(Integer playerID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("City.getCityIDByPlayerID",playerID);
	}
	
	public Integer getCityIDByCityName(String cityName){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("City.getCityIDByCityName",cityName);
	}
	
	public String getCityNameByCityID(Integer cityID){
		return (String)this.getSqlMapClientTemplate().queryForObject("City.getCityNameByCityID",cityID);
	}
	
	public City getCityByPlayerID(Integer playerID){
		return (City)this.getSqlMapClientTemplate().queryForObject("City.getCityByPlayerID",playerID);
	}
	
	public City getCityByPosXAndPosY(Integer posX, Integer posY) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("posX", posX);
		map.put("posY", posY);
		
		return (City) this.getSqlMapClientTemplate().queryForObject("City.getCityByPosXAndPosY", map);
	}
	
	public Integer getCityIDByPosXAndPosY(Integer posX,Integer posY){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("posX", posX);
		map.put("posY", posY);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("City.getCityIDByPosXAndPosY",map);
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Integer> getCityPosXAndPosYByCityID(Integer cityID){
		
		return (Map<String,Integer>)this.getSqlMapClientTemplate().queryForObject("City.getCityPosXAndPosYByCityID", cityID);
	}
	
	public City getCityByID(Integer cityID) {
		return (City)this.getSqlMapClientTemplate().queryForObject("City.getCityByID", cityID);
	}
	
	public CityInfo getCityInfoByCityID(Integer cityID){
		return (CityInfo)this.getSqlMapClientTemplate().queryForObject("City.getCityInfoByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<City> getCityList() {
		return this.getSqlMapClientTemplate().queryForList("City.getCityList");
	}
	
	public void updateCity(java.util.Map<String, Object> params){
		if(params == null || !params.containsKey("cityID") || params.size()<2)
			throw new GameException("参数有误");
		
		this.getSqlMapClientTemplate().update("City.updateCityParams",params);		
	}
	
	public void updateSecurityOfResourceEffect(){
		this.getSqlMapClientTemplate().update("City.updateSecurityOfResourceEffect");		
	}

}