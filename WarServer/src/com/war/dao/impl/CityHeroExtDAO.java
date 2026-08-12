package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityHeroExtDAO;
import com.war.domain.CityHeroExt;
import com.war.exception.GameException;

public class CityHeroExtDAO extends SqlMapClientDaoSupport implements ICityHeroExtDAO {

	public Integer createCityHeroExt(CityHeroExt cityHeroExt) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityHeroExt.createCityHeroExt", cityHeroExt);
	}

	public void updateCityHeroExt(CityHeroExt cityHeroExt) {
		this.getSqlMapClientTemplate().update("CityHeroExt.updateCityHeroExt", cityHeroExt);
	}
	
	public void updateCityHeroExtParams(Map<String, Object> params) {
		if (params == null || !params.containsKey("cityHeroID") || params.size() < 2)
			throw new GameException("参数有误");
		
		this.getSqlMapClientTemplate().update("CityHeroExt.updateCityHeroExtParams", params);
	}
	
	public void updateCityHeroExtEquipmentAddByID(Integer cityHeroID, Integer commandEquipmentAdd, Integer defenseEquipmentAdd, Integer mindEquipmentAdd, Integer executivepowerEquipmentAdd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityHeroID", cityHeroID);
		params.put("commandEquipmentAdd", commandEquipmentAdd);
		params.put("defenseEquipmentAdd", defenseEquipmentAdd);
		params.put("mindEquipmentAdd", mindEquipmentAdd);
		params.put("executivepowerEquipmentAdd", executivepowerEquipmentAdd);
		
		this.getSqlMapClientTemplate().update("CityHeroExt.updateCityHeroExtEquipmentAddByID", params);
	}
	
	public void updateCityHeroMilitaryAddByID(Integer cityHeroID, Integer militaryAttackAdd, Integer militaryDefenseAdd, Integer militaryLifeAdd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityHeroID", cityHeroID);
		params.put("militaryAttackAdd", militaryAttackAdd);
		params.put("militaryDefenseAdd", militaryDefenseAdd);
		params.put("militaryLifeAdd", militaryLifeAdd);
		
		this.getSqlMapClientTemplate().update("CityHeroExt.updateCityHeroMilitaryAddByID", params);
	}

	public void deleteCityHeroExtByID(Integer cityHeroExtID) {
		this.getSqlMapClientTemplate().delete("CityHeroExt.deleteCityHeroExtByID", cityHeroExtID);
	}

	public CityHeroExt getCityHeroExtByID(Integer cityHeroExtID) {
		return (CityHeroExt)this.getSqlMapClientTemplate().queryForObject("CityHeroExt.getCityHeroExtByID", cityHeroExtID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityHeroExt> getCityHeroExtList() {
		return this.getSqlMapClientTemplate().queryForList("CityHeroExt.getCityHeroExtList");
	}

	@SuppressWarnings("unchecked")
	public List<CityHeroExt> getCityHeroExtListBycityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("CityHeroExt.getCityHeroExtListBycityID", cityID);
	}
	
	public void updateGuildAddByCityIDWithParams(java.util.Map<String, Integer> map, Integer cityID) {
		
		if (map == null || map.size() < 2) {
			throw new RuntimeException("参数不合法!");
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityID", cityID);
		params.put("reinGuildAdd", map.get("reinGuildAdd"));
		params.put("expGuildAdd", map.get("expGuildAdd"));
		
		this.getSqlMapClientTemplate().update("CityHeroExt.updateGuildAddByCityIDWithParams", params);
	}
}
