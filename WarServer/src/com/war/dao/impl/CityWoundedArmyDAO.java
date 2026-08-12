package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityWoundedArmyDAO;
import com.war.domain.CityWoundedArmy;

public class CityWoundedArmyDAO extends SqlMapClientDaoSupport implements ICityWoundedArmyDAO {

	// private static Logger logger = Logger.getLogger(CityWoundedDAO.class);
	
	public Integer createCityWoundedArmy(CityWoundedArmy cityWoundedArmy) {
		return (Integer) this.getSqlMapClientTemplate().insert("CityWoundedArmy.createCityWoundedArmy",cityWoundedArmy);
	}

	public void deleteCityWoundedArmyByID(Integer cityWoundedArmyID) {
		this.getSqlMapClientTemplate().delete("CityWoundedArmy.deleteCityWoundedArmyByID", cityWoundedArmyID);
	}

	@SuppressWarnings("unchecked")
	public List<CityWoundedArmy> getCityWoundedArmyListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("CityWoundedArmy.getCityWoundedArmyListByCityID", cityID);
	}

	public void updateCityWoundedArmy(CityWoundedArmy cityWoundedArmy) {
		this.getSqlMapClientTemplate().update("CityWoundedArmy.updateCityWoundedArmy", cityWoundedArmy);
	}
	
	public CityWoundedArmy getCityWoundedArmyByID(Integer cityWoundedArmyID) {
		return (CityWoundedArmy) this.getSqlMapClientTemplate().queryForObject("CityWoundedArmy.getCityWoundedArmyByID", cityWoundedArmyID);
	}

	@SuppressWarnings("unchecked")
	public List<CityWoundedArmy> getAutoDismissedCityWoundedArmyList() {
		return this.getSqlMapClientTemplate().queryForList("CityWoundedArmy.getAutoDismissedCityWoundedArmyList");
	}
	
	public List<CityWoundedArmy> getCityWoundedArmyList() {
		return null;
	}
}
