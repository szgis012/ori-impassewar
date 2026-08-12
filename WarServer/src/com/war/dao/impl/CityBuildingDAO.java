package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityBuildingDAO;
import com.war.domain.CityBuilding;

public class CityBuildingDAO extends SqlMapClientDaoSupport implements ICityBuildingDAO{

	public Integer createCityBuilding(CityBuilding cityBuilding) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityBuilding.createCityBuilding", cityBuilding);
	}
	
	public void updateCityBuilding(CityBuilding cityBuilding) {
		this.getSqlMapClientTemplate().update("CityBuilding.updateCityBuilding", cityBuilding);
	}
	
	public void deleteCityBuildingByID(Integer cityBuildingID) {
		this.getSqlMapClientTemplate().delete("CityBuilding.deleteCityBuilding", cityBuildingID);
	}
	
	public Integer getCityBuildingLevelByCityIDAndBuildingID(Integer cityID,Integer buildingID){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("buildingID", buildingID);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityBuilding.getCityBuildingLevelByCityIDAndBuildingID",params);
	}
	
	public CityBuilding getCityBuildingByCityIDAndBuildingIDAndPosition(Integer cityID,Integer buildingID,Integer position){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("buildingID", buildingID);
		params.put("position", position);
		
		return (CityBuilding)this.getSqlMapClientTemplate().queryForObject("CityBuilding.getCityBuildingByCityIDAndBuildingIDAndPosition",params);
	}
	
	public CityBuilding getCityBuildingByCityIDAndBuildingID(Integer cityID,Integer buildingID){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("buildingID", buildingID);
		
		return (CityBuilding)this.getSqlMapClientTemplate().queryForObject("CityBuilding.getCityBuildingByCityIDAndBuildingID",params);
	}
	
	public CityBuilding getCityBuildingByID(Integer cityBuildingID) {
		return (CityBuilding)this.getSqlMapClientTemplate().queryForObject("CityBuilding.getCityBuildingByID", cityBuildingID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityBuilding> getCityBuildingListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("CityBuilding.getCityBuildingListByCityID",cityID);
	}
	
	public CityBuilding getCityBuildingByPosition(Integer cityID,Integer position) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("position", position);
		
		return (CityBuilding) this.getSqlMapClientTemplate().queryForObject("CityBuilding.getCityBuildingByPosition",params);
	}

}