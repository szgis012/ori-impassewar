package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityMilitaryDAO;
import com.war.domain.BattleMilitary;
import com.war.domain.CityMilitary;

public class CityMilitaryDAO extends SqlMapClientDaoSupport implements ICityMilitaryDAO{

	public Integer createCityMilitary(CityMilitary cityMilitary) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityMilitary.createCityMilitary", cityMilitary);
	}
	
	public void updateNameByCityMilitaryID(Integer cityMilitaryID,String name){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityMilitaryID", cityMilitaryID);
		params.put("name", name);
		
		this.getSqlMapClientTemplate().update("CityMilitary.updateNameByCityMilitaryID",params);
	}
	
	public void updateCityHeroIDByCityMilitaryID(Integer cityMilitaryID,Integer cityHeroID){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityMilitaryID", cityMilitaryID);
		params.put("cityHeroID", cityHeroID);
		
		this.getSqlMapClientTemplate().update("CityMilitary.updateCityHeroIDByCityMilitaryID",params);
	}
	
	public void updateCityMilitaryArmyByBattleMilitary(BattleMilitary battleMilitary){
		this.getSqlMapClientTemplate().update("CityMilitary.updateCityMilitaryArmyByBattleMilitary",battleMilitary);
	}
	
	public void updateCityMilitary(CityMilitary cityMilitary) {
		this.getSqlMapClientTemplate().update("CityMilitary.updateCityMilitary", cityMilitary);
	}
	
	public void updateCityMilitaryConsume(Integer cityMilitaryID, Integer costOil, Integer costFood, Integer costMoney) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityMilitaryID", cityMilitaryID);
		params.put("costOil", costOil);
		params.put("costFood", costFood);
		params.put("costMoney", costMoney);
		
		this.getSqlMapClientTemplate().update("CityMilitary.updateCityMilitaryConsume", params);
	}
	
	public BattleMilitary getCityMilitaryAsBattleMilitaryByID(Integer cityMilitaryID){
		return (BattleMilitary)this.getSqlMapClientTemplate().queryForObject("CityMilitary.getCityMilitaryAsBattleMilitaryByID", cityMilitaryID);
	}
	
	public void deleteCityMilitaryByID(Integer cityMilitaryID) {
		this.getSqlMapClientTemplate().delete("CityMilitary.deleteCityMilitaryByID", cityMilitaryID);
	}
	
	public Integer getCityMilitaryIDByCityHeroID(Integer cityHeroID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityMilitary.getCityMilitaryIDByCityHeroID", cityHeroID);
	}
	
	public CityMilitary getCityMilitaryByID(Integer cityMilitaryID) {
		return (CityMilitary)this.getSqlMapClientTemplate().queryForObject("CityMilitary.getCityMilitaryByID", cityMilitaryID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityMilitary> getCityMilitaryListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("CityMilitary.getCityMilitaryListByCityID",cityID);
	}

	public void updateCityMilitaryState(Integer cityMilitaryID, Integer state) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityMilitaryID", cityMilitaryID);
		params.put("state", state);
		
		this.getSqlMapClientTemplate().update("CityMilitary.updateCityMilitaryState",params);
	}

	public Integer getNotNormalStateCityMilitaryNumByCityID(Integer cityID) {
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("CityMilitary.getNotNormalStateCityMilitaryNumByCityID",cityID);
	}
	
	public boolean existsStayMilitary(Integer cityID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityMilitary.existsStayMilitary", cityID) > 0;
	}
	
	public void deleteCityMilitaryByCityHeroID(Integer cityHeroID) {
		this.getSqlMapClientTemplate().delete("CityMilitary.deleteCityMilitaryByCityHeroID", cityHeroID);
	}
	
}