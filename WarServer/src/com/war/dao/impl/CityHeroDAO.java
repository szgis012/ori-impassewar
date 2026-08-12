package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityHeroDAO;
import com.war.domain.CityHero;

public class CityHeroDAO extends SqlMapClientDaoSupport implements ICityHeroDAO{

	public Integer createCityHero(CityHero cityHero) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityHero.createCityHero", cityHero);
	}
	
	public void updateStaminaByCityHeroID(Integer cityHeroID, Integer stamina) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityHeroID", cityHeroID);
		params.put("stamina", stamina);
		
		this.getSqlMapClientTemplate().update("CityHero.updateStaminaByCityHeroID", params);
	}
	
	public void addExpByCityHeroID(Integer cityHeroID, Long exp) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityHeroID", cityHeroID);
		params.put("exp", exp);
		
		this.getSqlMapClientTemplate().update("CityHero.addExpByCityHeroID",params);
	}
	
	public void addCityHeroExpBatch() {
		this.getSqlMapClientTemplate().update("CityHero.updateCityHeroExpBatch");
	}
	
	public void batchAddCityHeroStamina() {
		this.getSqlMapClientTemplate().update("CityHero.batchAddCityHeroStamina");
	}
	
	public void updateMaxSkillNumByCityHeroID(Integer cityHeroID, Integer maxSkillNum) {
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("maxSkillNum", maxSkillNum);
		
		this.getSqlMapClientTemplate().update("CityHero.updateMaxSkillNumByCityHeroID", params);
	}
	
	public void updatePointByCityHeroID(Integer cityHeroID,Integer command,Integer defense,Integer mind,Integer executivepower,Integer unsetPoint){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("command", command);
		params.put("defense", defense);
		params.put("mind", mind);
		params.put("executivepower", executivepower);
		params.put("unsetPoint", unsetPoint);
		
		this.getSqlMapClientTemplate().update("CityHero.updatePointByCityHeroID",params);
	}
	
	public void updateStateByCityHeroID(Integer cityHeroID,Integer state){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("state", state);
		
		this.getSqlMapClientTemplate().update("CityHero.updateStateByCityHeroID",params);
	}
	
	public void updateMilitarySoulByCityHeroID(Integer cityHeroID,Integer militarySoul) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("militarySoul", militarySoul);
		
		this.getSqlMapClientTemplate().update("CityHero.updateMilitarySoulByCityHeroID",params);
		
	}
	
	public void updateLeadershipByCityHeroID(Integer cityHeroID,Integer leadership) {
		Map<String,Integer> params = new HashMap<String, Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("leadership", leadership);
		
		this.getSqlMapClientTemplate().update("CityHero.updateLeadershipByCityHeroID",params);
	}
	
	public void updateMilitarySpiritByCityHeroID(Integer cityHeroID,Integer militarySpirit) {
		Map<String,Integer> params = new HashMap<String, Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("militarySpirit", militarySpirit);
		
		this.getSqlMapClientTemplate().update("CityHero.updateMilitarySpiritByCityHeroID",params);
	}
	
	public void updateQualityByCityHeroID(Integer cityHeroID, Integer quality) {
		Map<String,Integer> params = new HashMap<String, Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("quality", quality);
		
		this.getSqlMapClientTemplate().update("CityHero.updateQualityByCityHeroID",params);
	}

	public void updateReinByCityHeroID(Integer cityHeroID, Integer rein) {
		Map<String,Integer> params = new HashMap<String, Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("rein", rein);
		
		this.getSqlMapClientTemplate().update("CityHero.updateReinByCityHeroID",params);
	}
	
	public void updateStarByCityHeroID(Integer cityHeroID, Integer star) {
		Map<String,Integer> params = new HashMap<String, Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("star", star);
		
		this.getSqlMapClientTemplate().update("CityHero.updateStarByCityHeroID",params);
	}
	
	public void updateAddedMilitarySpiritByCityHeroID(Integer cityHeroID,Integer addedMilitarySpirit) {
		Map<String,Integer> params = new HashMap<String, Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("addedMilitarySpirit", addedMilitarySpirit);
		
		this.getSqlMapClientTemplate().update("CityHero.updateAddedMilitarySpiritByCityHeroID",params);
	}
	
	public void updateLoyaltyByCityHeroID(Integer cityHeroID,Integer loyalty){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("loyalty", loyalty);
		
		this.getSqlMapClientTemplate().update("CityHero.updateLoyaltyByCityHeroID",params);
	}
	
	public void updateNameByCityHeroID(Integer cityHeroID, String name) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("cityHeroID", cityHeroID);
		params.put("name", name);
		
		this.getSqlMapClientTemplate().update("CityHero.updateNameByCityHeroID",params);
	}

	public void updateStaminaMaxByCityHeroID(Integer cityHeroID,Integer staminaMax){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityHeroID", cityHeroID);
		params.put("staminaMax", staminaMax);
		
		this.getSqlMapClientTemplate().update("CityHero.updateStaminaMaxByCityHeroID",params);
	}
	
	public void updateHeroEquipmentByCityHeroID(Map<String,Integer> params){
		this.getSqlMapClientTemplate().update("CityHero.updateHeroEquipmentByCityHeroID",params);
	}
	
	public void updateCityHero(CityHero cityHero) {
		this.getSqlMapClientTemplate().update("CityHero.updateCityHero", cityHero);
	}
	
	public void deleteCityHeroByID(Integer cityHeroID) {
		this.getSqlMapClientTemplate().delete("CityHero.deleteCityHeroByID", cityHeroID);
	}
	
	public String getCityHeroNameByCityHeroID(Integer cityHeroID){
		return (String)this.getSqlMapClientTemplate().queryForObject("CityHero.getCityHeroNameByCityHeroID", cityHeroID);
	}
	
	public Integer getUnsetPointByCityHeroID(Integer cityHeroID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityHero.getUnsetPointByCityHeroID",cityHeroID);
	}
	
	public Integer getCityHeroNumByCityID(Integer cityID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityHero.getCityHeroNumByCityID",cityID);
	}
	
	public CityHero getCityHeroByID(Integer cityHeroID) {
		return (CityHero)this.getSqlMapClientTemplate().queryForObject("CityHero.getCityHeroByID", cityHeroID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityHero> getCityHeroListByCityIDAndState(Integer cityID,Integer state){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("state", state);
		
		return this.getSqlMapClientTemplate().queryForList("CityHero.getCityHeroListByCityIDAndState",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityHero> getCityHeroListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("CityHero.getCityHeroListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityHero> getCityHeroListByState(Integer state) {
		return this.getSqlMapClientTemplate().queryForList("CityHero.getCityHeroListByState",state);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityHero> getCityHeroList() {
		return this.getSqlMapClientTemplate().queryForList("CityHero.getCityHeroList");
	}
	
	public boolean existsCityOfficer(Integer cityID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityHero.existsCityOfficer", cityID) > 0;
	}

	public Integer getEquipedCityHeroNumByCityID(Integer cityID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("CityHero.getEquipedCityHeroNumByCityID", cityID);
	}

	public void addReinByCityIDWithMultiple(Integer cityID, Integer percent) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("percent", percent);
		
		this.getSqlMapClientTemplate().update("CityHero.addReinByCityIDWithMultiple", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityHero> getBugCityHeroList() {
		return this.getSqlMapClientTemplate().queryForList("CityHero.getBugCityHeroList");
	}
}