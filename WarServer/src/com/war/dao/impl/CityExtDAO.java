package com.war.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityExtDAO;
import com.war.domain.CityExt;

public class CityExtDAO extends SqlMapClientDaoSupport implements ICityExtDAO{

	public void createCityExt(CityExt cityExt) {
		this.getSqlMapClientTemplate().insert("CityExt.createCityExt", cityExt);
	}
	
	public void updateCityExtParams(Map<String,Object> params){
		this.getSqlMapClientTemplate().update("CityExt.updateCityExtParams",params);
	}
	
	public void updateCityExt(CityExt cityExt) {
		this.getSqlMapClientTemplate().update("CityExt.updateCityExt", cityExt);
	}
	
	public void deleteCityExtByID(Integer cityID) {
		this.getSqlMapClientTemplate().delete("CityExt.deleteCityExtByID", cityID);
	}
	
	public CityExt getCityExtByID(Integer cityID) {
		return (CityExt)this.getSqlMapClientTemplate().queryForObject("CityExt.getCityExtByID", cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityExt> getCityExtList() {
		return this.getSqlMapClientTemplate().queryForList("CityExt.getCityExtList");
	}

}