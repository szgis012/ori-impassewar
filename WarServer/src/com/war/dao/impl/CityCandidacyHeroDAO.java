package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityCandidacyHeroDAO;
import com.war.domain.CityCandidacyHero;

public class CityCandidacyHeroDAO extends SqlMapClientDaoSupport implements ICityCandidacyHeroDAO{

	public Integer createCityCandidacyHero(CityCandidacyHero cityCandidacyHero) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityCandidacyHero.createCityCandidacyHero", cityCandidacyHero);
	}
	
	public void createCityCandidacyHeroArray(CityCandidacyHero[] cityCandidacyHeroArray) throws SQLException{
		for(int i=0;i<cityCandidacyHeroArray.length;i++){
			this.getSqlMapClient().insert("CityCandidacyHero.createCityCandidacyHero",cityCandidacyHeroArray[i]);
		}
	}
	
	public void updateCityCandidacyHeroState(Integer cityCandidacyHeroID,Integer state){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityCandidacyHeroID", cityCandidacyHeroID);
		params.put("state", state);
		
		this.getSqlMapClientTemplate().update("CityCandidacyHero.updateCityCandidacyHeroState",params);
	}
	
	public void updateCityCandidacyHero(CityCandidacyHero cityCandidacyHero) {
		this.getSqlMapClientTemplate().update("CityCandidacyHero.updateCityCandidacyHero", cityCandidacyHero);
	}
	
	public void deleteCityCandidacyHeroByID(Integer cityCandidacyHeroID) {
		this.getSqlMapClientTemplate().delete("CityCandidacyHero.deleteCityCandidacyHeroByID", cityCandidacyHeroID);
	}
	
	public void deleteCityCandidacyHeroListByCityID(Integer cityID){
		this.getSqlMapClientTemplate().delete("CityCandidacyHero.deleteCityCandidacyHeroListByCityID",cityID);
	}
	
	public void deleteCityCandidacyHeroList(){
		this.getSqlMapClientTemplate().delete("CityCandidacyHero.deleteCityCandidacyHeroList");
	}
	
	public Integer getCityCandidacyHeroNum(Integer cityID){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityCandidacyHero.getCityCandidacyHeroNum",cityID);
	}
	
	public CityCandidacyHero getCityCandidacyHeroByID(Integer cityCandidacyHeroID) {
		return (CityCandidacyHero)this.getSqlMapClientTemplate().queryForObject("CityCandidacyHero.getCityCandidacyHeroByID", cityCandidacyHeroID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityCandidacyHero> getCityCandidacyHeroListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("CityCandidacyHero.getCityCandidacyHeroListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityCandidacyHero> getCityCandidacyHeroList() {
		return this.getSqlMapClientTemplate().queryForList("CityCandidacyHero.getCityCandidacyHeroList");
	}

}