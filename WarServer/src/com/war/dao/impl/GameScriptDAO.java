package com.war.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IGameScriptDAO;


/**
 * 游戏脚本DAO接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class GameScriptDAO extends SqlMapClientDaoSupport implements IGameScriptDAO {

	public int getBuildingMaxLevel(int cityID, int buildingID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("buildingID", buildingID);
		
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getBuildingMaxLevel", params);
		
		return (result == null ? 0 : result.intValue());
	}

	public int getBuildingNum(int cityID, int buildingID, int level) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("buildingID", buildingID);
		params.put("level", level);
		
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getBuildingNumWithLevel", params);
		
		return (result == null ? 0 : result.intValue());
	}
	
	public int getBuildingNum(int cityID,int buildingID){
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("buildingID", buildingID);
		
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getBuildingNum", params);
		
		return (result == null ? 0 : result.intValue());
	}

	public int getCityArmyNum(int cityID, int armyID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("armyID", armyID);
		
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getCityArmyNum", params);
		
		return (result == null ? 0 : result.intValue());
	}

	public int getCityDefenseNum(int cityID, int defenseID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("defenseID", defenseID);
		
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getCityDefenseNum", params);
		
		return (result == null ? 0 : result.intValue());
	}

	public int getCityHeroNum(int cityID) {
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getCityHeroNum", cityID);
		
		return (result == null ? 0 : result.intValue());
	}

	public int getCityMilitaryNum(int cityID) {
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getCityMilitaryNum", cityID);
		
		return (result == null ? 0 : result.intValue());
	}

	public int getTechnologyLevel(int cityID, int technologyID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("technologyID", technologyID);
		
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getTechnologyLevel", params);
		
		return (result == null ? 0 : result.intValue());
	}

	public int getCityOrdnanceNum(int cityID,int ordnanceID){
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("ordnanceID", ordnanceID);
		
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("GameScript.getCityOrdnanceNum", params);
		
		return (result == null ? 0 : result.intValue());
	}

	@SuppressWarnings("unchecked")
	public Map<String, Integer> getBuildingMaxLevelAndStateByBuildingIDAndCityID(Integer buildingID, Integer cityID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("buildingID", buildingID);
		params.put("cityID", cityID);
		
		return (Map<String, Integer>) this.getSqlMapClientTemplate().queryForObject("GameScript.getBuildingMaxLevelAndStateByBuildingIDAndCityID", params);
	}
}
