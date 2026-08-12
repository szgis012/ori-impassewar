package com.war.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityOrdnanceDAO;
import com.war.domain.CityOrdnance;


/**
 * 城市器械dao实现
 *
 * @author ghleed
 * @version 1.0
 */
public class CityOrdnanceDAO extends SqlMapClientDaoSupport implements ICityOrdnanceDAO{

	public Integer createCityOrdnance(CityOrdnance cityOrdnance) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityOrdnance.createCityOrdnance", cityOrdnance);
	}
	
	public void updateCityOrdnance(CityOrdnance cityOrdnance) {
		this.getSqlMapClientTemplate().update("CityOrdnance.updateCityOrdnance", cityOrdnance);
	}
	
	public void deleteCityOrdnanceByID(Integer cityOrdnanceID) {
		this.getSqlMapClientTemplate().delete("CityOrdnance.deleteCityOrdnanceByID", cityOrdnanceID);
	}
	
	public CityOrdnance getCityOrdnanceByID(Integer cityOrdnanceID) {
		return (CityOrdnance)this.getSqlMapClientTemplate().queryForObject("CityOrdnance.getCityOrdnanceByID", cityOrdnanceID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityOrdnance> getCityOrdnanceList() {
		return this.getSqlMapClientTemplate().queryForList("CityOrdnance.getCityOrdnanceList");
	}

	public CityOrdnance getCityOrdnance(Integer cityID, Integer ordnanceID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("ordnanceID", ordnanceID);
		
		return (CityOrdnance)this.getSqlMapClientTemplate().queryForObject("CityOrdnance.getCityOrdnance", params);
	}

	@SuppressWarnings("unchecked")
	public List<CityOrdnance> getCityOrdnanceList(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("CityOrdnance.getCityOrdnanceListByCityID",cityID);
	}

}