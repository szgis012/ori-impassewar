package com.war.dao.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityDefenseDAO;
import com.war.domain.CityDefense;

public class CityDefenseDAO extends SqlMapClientDaoSupport implements ICityDefenseDAO{

	public Integer createCityDefense(CityDefense cityDefense) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityDefense.createCityDefense", cityDefense);
	}
	
	public void updateCityDefense(CityDefense cityDefense) {
		this.getSqlMapClientTemplate().update("CityDefense.updateCityDefense", cityDefense);
	}
	
	public void deleteCityDefenseByID(Integer cityDefenseID) {
		this.getSqlMapClientTemplate().delete("CityDefense.deleteCityDefenseByID", cityDefenseID);
	}
	
	public CityDefense getCityDefenseByID(Integer cityDefenseID) {
		return (CityDefense)this.getSqlMapClientTemplate().queryForObject("CityDefense.getCityDefenseByID", cityDefenseID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityDefense> getCityDefenseList() {
		return this.getSqlMapClientTemplate().queryForList("CityDefense.getCityDefenseList");
	}
	
	public CityDefense getCityDefense(Integer cityID,Integer defenseID){
		java.util.Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("defenseID", defenseID);
		
		return (CityDefense)this.getSqlMapClientTemplate().queryForObject("CityDefense.getCityDefense", params);
	}

	@SuppressWarnings("unchecked")
	public List<CityDefense> getCityDefenseList(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("CityDefense.getCityDefenseListByCityID",cityID);
	}

}