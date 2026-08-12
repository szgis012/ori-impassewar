package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityHeroLevelupLogDAO;
import com.war.domain.CityHeroLevelupLog;

public class CityHeroLevelupLogDAO extends SqlMapClientDaoSupport implements ICityHeroLevelupLogDAO {

	public Integer createCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityHeroLevelupLog.createCityHeroLevelupLog", cityHeroLevelupLog);
	}

	public void updateCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog) {
		this.getSqlMapClientTemplate().update("CityHeroLevelupLog.updateCityHeroLevelupLog", cityHeroLevelupLog);
	}

	public void deleteCityHeroLevelupLogByID(Integer cityHeroLevelupLogID) {
		this.getSqlMapClientTemplate().delete("CityHeroLevelupLog.deleteCityHeroLevelupLogByID", cityHeroLevelupLogID);
	}

	public void deleteCityHeroLevelupLogByCityHeroID(Integer cityHeroID) {
		this.getSqlMapClientTemplate().delete("CityHeroLevelupLog.deleteCityHeroLevelupLogByCityHeroID", cityHeroID);
	}
	
	public void deleteCityHeroLevelupLogByCityHeroIDAndLevel(Integer cityHeroID, Integer level) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityHeroID", cityHeroID);
		params.put("level", level);
		
		this.getSqlMapClientTemplate().delete("CityHeroLevelupLog.deleteCityHeroLevelupLogByCityHeroIDAndLevel", params);
	}
	
	public CityHeroLevelupLog getCityHeroLevelupLogByID(Integer cityHeroLevelupLogID) {
		return (CityHeroLevelupLog)this.getSqlMapClientTemplate().queryForObject("CityHeroLevelupLog.getCityHeroLevelupLogByID", cityHeroLevelupLogID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityHeroLevelupLog> getCityHeroLevelupLogList() {
		return this.getSqlMapClientTemplate().queryForList("CityHeroLevelupLog.getCityHeroLevelupLogList");
	}
	
	public CityHeroLevelupLog getCityHeroLevelupLogByCityHeroIDAndLevel(Integer cityHeroID, Integer level) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityHeroID", cityHeroID);
		params.put("level", level);
		
		return (CityHeroLevelupLog) this.getSqlMapClientTemplate().queryForObject("CityHeroLevelupLog.getCityHeroLevelupLogByCityHeroIDAndLevel", params);
	}

}
