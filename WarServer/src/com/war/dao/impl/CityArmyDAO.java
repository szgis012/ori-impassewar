package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ICityArmyDAO;
import com.war.domain.CityArmy;

/**
 * 城市兵力DAO实现
 * 
 * @author TopTong
 * @version 1.0
 */
public class CityArmyDAO extends SqlMapClientDaoSupport implements ICityArmyDAO{

	private static Logger logger = Logger.getLogger(CityArmyDAO.class);
	
	public Integer createCityArmy(CityArmy cityArmy) {
		return (Integer)this.getSqlMapClientTemplate().insert("CityArmy.createCityArmy", cityArmy);
	}
	
	public void updateCityArmy(CityArmy cityArmy) {
		this.getSqlMapClientTemplate().update("CityArmy.updateCityArmy", cityArmy);
	}
	
	public void deleteCityArmyByID(Integer cityArmyID) {
		this.getSqlMapClientTemplate().delete("CityArmy.deleteCityArmyByID", cityArmyID);
	}
	
	public CityArmy getCityArmyByID(Integer cityArmyID) {
		return (CityArmy)this.getSqlMapClientTemplate().queryForObject("CityArmy.getCityArmyByID", cityArmyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<CityArmy> getCityArmyList() {
		return this.getSqlMapClientTemplate().queryForList("CityArmy.getCityArmyList");
	}

	@SuppressWarnings("unchecked")
	public List<CityArmy> getCityArmyListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("CityArmy.getCityArmyListByCityID",cityID);
	}

	public CityArmy getCityArmyByCityIDAndArmyID(Integer cityID, Integer armyID) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("armyID", armyID);
		
		return (CityArmy)this.getSqlMapClientTemplate().queryForObject("CityArmy.getCityArmyListByCityIDAndArmyID",params);
	}

	public void batchUpdateCityArmyNumByCityIDAndArmyIDs(int cityID, int[] armyIDs,int[] nums) {
		
		if(armyIDs.length==0)
			return;
		
		Map<String, Integer> params;
		
		try {
			
			for(int i=0; i<armyIDs.length; i++){
				params = new HashMap<String, Integer>();
				params.put("cityID", cityID);
				params.put("armyID", armyIDs[i]);
				params.put("num", nums[i]);
				
				this.getSqlMapClient().update("CityArmy.batchUpdateCityArmyNumByCityIDAndArmyIDs", params);
			}
			
		} catch (SQLException e) {
			logger.error("异常：", e);
		}
		
	}
	
	public void deleteCityArmyByCityIDAndArmyID(Integer cityID, Integer armyID) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("armyID", armyID);
		
		this.getSqlMapClientTemplate().delete("CityArmy.deleteCityArmyByCityIDAndArmyID", params);
	}

}