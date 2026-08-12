package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityRankDAO;
import com.war.domain.CityRank;

public class CityRankDAO extends SqlMapClientDaoSupport implements ICityRankDAO {

	public CityRank getCityRankByCityID(Integer cityID) {
		return (CityRank)this.getSqlMapClientTemplate().queryForObject("CityRank.getCityRankByCityID",cityID);
	}
	
	
	
	public Integer getCityConstructionPointRankByCityID(Integer cityID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityRank.getCityConstructionPointRankByCityID",cityID);
	}

	public void refreshCityConstructionPointRank() {
		this.getSqlMapClientTemplate().update("CityRank.refreshCityConstructionPointRank");
	}
	
	@SuppressWarnings("unchecked")
	public List<CityRank> getCityConstructionPointRankPagingList(Integer start,
			Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("CityRank.getCityConstructionPointRankPagingList",map);
	}
	
	
	
	public Integer getCityTechnologyPointRankByCityID(Integer cityID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityRank.getCityTechnologyPointRankByCityID",cityID);
	}

	public void refreshCityTechnologyPointRank() {
		this.getSqlMapClientTemplate().update("CityRank.refreshCityTechnologyPointRank");
	}
	
	@SuppressWarnings("unchecked")
	public List<CityRank> getCityTechnologyPointRankPagingList(Integer start,
			Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("CityRank.getCityTechnologyPointRankPagingList",map);
	}

	
	
	public Integer getCityPopulationRankByCityID(Integer cityID) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("CityRank.getCityPopulationRankByCityID",cityID);
	}

	public void refreshCityPopulationRank() {
		this.getSqlMapClientTemplate().update("CityRank.refreshCityPopulationRank");
	}
	
	@SuppressWarnings("unchecked")
	public List<CityRank> getCityPopulationRankPagingList(Integer start,
			Integer offset) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("CityRank.getCityPopulationRankPagingList",map);
	}
	
}
