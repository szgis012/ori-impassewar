package com.war.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IArmyDependDAO;
import com.war.domain.ArmyDepend;


/**
 * 兵种依赖dao实现
 *
 * @author ghleed
 * @version 1.0
 */
public class ArmyDependDAO extends SqlMapClientDaoSupport implements IArmyDependDAO{

	public Integer createArmyDepend(ArmyDepend armyDepend) {
		return (Integer)this.getSqlMapClientTemplate().insert("ArmyDepend.createArmyDepend", armyDepend);
	}
	
	public void updateArmyDepend(ArmyDepend armyDepend) {
		this.getSqlMapClientTemplate().update("ArmyDepend.updateArmyDepend", armyDepend);
	}
	
	public void deleteArmyDependByID(Integer armyDependID) {
		this.getSqlMapClientTemplate().delete("ArmyDepend.deleteArmyDependByID", armyDependID);
	}
	
	public ArmyDepend getArmyDependByID(Integer armyDependID) {
		return (ArmyDepend)this.getSqlMapClientTemplate().queryForObject("ArmyDepend.getArmyDependByID", armyDependID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ArmyDepend> getArmyDependList() {
		return this.getSqlMapClientTemplate().queryForList("ArmyDepend.getArmyDependList");
	}

	public ArmyDepend getArmyDepend(Integer armyID, Integer ordnanceID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("armyID", armyID);
		params.put("ordnanceID", ordnanceID);
		
		return (ArmyDepend)this.getSqlMapClientTemplate().queryForObject("ArmyDepend.getArmyDepend", params);
	}

	@SuppressWarnings("unchecked")
	public List<ArmyDepend> getArmyDependList(Integer armyID) {
		return this.getSqlMapClientTemplate().queryForList("ArmyDepend.getArmyDependListByArmyID",armyID);
	}

}
