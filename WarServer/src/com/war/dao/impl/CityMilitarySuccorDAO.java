package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityMilitarySuccorDAO;
import com.war.domain.CityMilitarySuccor;

public class CityMilitarySuccorDAO extends SqlMapClientDaoSupport implements ICityMilitarySuccorDAO {


	public Integer createCityMilitarySuccor(CityMilitarySuccor cityMilitarySuccor) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityMilitarySuccor.createCityMilitarySuccor", cityMilitarySuccor);
	}

	public void updateCityMilitarySuccor(CityMilitarySuccor cityMilitarySuccor) {
		this.getSqlMapClientTemplate().update("CityMilitarySuccor.updateCityMilitarySuccor", cityMilitarySuccor);
	}
	
	public void updateBattleOrderByCityMilitarySuccorID(Integer battleOrder, Integer cityMilitarySuccorID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityMilitarySuccorID", cityMilitarySuccorID);
		params.put("battleOrder", battleOrder);

		this.getSqlMapClientTemplate().update("CityMilitarySuccor.updateBattleOrderByCityMilitarySuccorID", params);
	}

	public void deleteCityMilitarySuccorByID(Integer cityMilitarySuccorID) {
		this.getSqlMapClientTemplate().delete("CityMilitarySuccor.deleteCityMilitarySuccorByID", cityMilitarySuccorID);
	}

	public CityMilitarySuccor getCityMilitarySuccorByID(Integer cityMilitarySuccorID) {
		return (CityMilitarySuccor)this.getSqlMapClientTemplate().queryForObject("CityMilitarySuccor.getCityMilitarySuccorByID", cityMilitarySuccorID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityMilitarySuccor> getCityMilitarySuccorList() {
		return this.getSqlMapClientTemplate().queryForList("CityMilitarySuccor.getCityMilitarySuccorList");
	}
	
	@SuppressWarnings("unchecked")
	public List<CityMilitarySuccor> getCityMilitarySuccorListByTargetCityID(Integer targetCityID) {
		return (List<CityMilitarySuccor>) this.getSqlMapClientTemplate().queryForObject("CityMilitarySuccor.getCityMilitarySuccorListByTargetCityID", targetCityID);
	}

	@SuppressWarnings("unchecked")
	public List<CityMilitarySuccor> getCityMilitarySuccorActiveListByTargetCityIDOrderByBattleOrder(Integer targetCityID) {
		return this.getSqlMapClientTemplate().queryForList("CityMilitarySuccor.getCityMilitarySuccorActiveListByTargetCityIDOrderByBattleOrder", targetCityID);
	}
	
	public Integer getCityMilitarySuccorActiveNumByTargetCityID(Integer cityID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("CityMilitarySuccor.getCityMilitarySuccorNumByTargetCityID", cityID);
	}

	public Integer getCityMilitarySuccorIDByCityMilitaryID(Integer cityMilitaryID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("CityMilitarySuccor.getCityMilitarySuccorIDByCityMilitaryID", cityMilitaryID);
	}

	public Integer getCityMilitarySuccorNumByTargetCityID(Integer cityID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("CityMilitarySuccor.getCityMilitarySuccorNumByTargetCityID", cityID);
	}

	public void refreshSuccorOrder(Integer cityID) {
		 this.getSqlMapClientTemplate().update("CityMilitarySuccor.refreshSuccorOrder", cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityMilitarySuccor> getOverTimeCityMilitarySuccorList() {
		return this.getSqlMapClientTemplate().queryForList("CityMilitarySuccor.getOverTimeCityMilitarySuccorList");
	}
	
	public CityMilitarySuccor getCityMilitarySuccorByCityMilitaryID(Integer cityMilitaryID) {
		return (CityMilitarySuccor) this.getSqlMapClientTemplate().queryForObject("CityMilitarySuccor.getCityMilitarySuccorByCityMilitaryID", cityMilitaryID);
	}
}
