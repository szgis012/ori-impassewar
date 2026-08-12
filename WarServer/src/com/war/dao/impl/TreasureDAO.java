package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ITreasureDAO;
import com.war.domain.Treasure;

/**
 * 宝物dao实现
 *
 * @author ghleed
 * @version 1.0
 */
public class TreasureDAO extends SqlMapClientDaoSupport implements ITreasureDAO{

	public Integer createTreasure(Treasure treasure) {
		return (Integer)this.getSqlMapClientTemplate().insert("Treasure.createTreasure", treasure);
	}
	
	public void updateTreasure(Treasure treasure) {
		this.getSqlMapClientTemplate().update("Treasure.updateTreasure", treasure);
	}
	
	public void deleteTreasureByID(Integer treasureID) {
		this.getSqlMapClientTemplate().delete("Treasure.deleteTreasureByID", treasureID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getTreasureTypeList() {
		return this.getSqlMapClientTemplate().queryForList("Treasure.getTreasureTypeList");
	}
	
	public Treasure getTreasureByID(Integer treasureID) {
		return (Treasure)this.getSqlMapClientTemplate().queryForObject("Treasure.getTreasureByID", treasureID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Treasure> getTreasureList() {
		return this.getSqlMapClientTemplate().queryForList("Treasure.getTreasureList");
	}

	@SuppressWarnings("unchecked")
	public List<Treasure> getTreasureListByCategory(Integer category) {
		return this.getSqlMapClientTemplate().queryForList("Treasure.getTreasureListByCategory",category);
	}

	@SuppressWarnings("unchecked")
	public List<Treasure> getTreasureListByType(Integer type) {
		return this.getSqlMapClientTemplate().queryForList("Treasure.getTreasureListByType", type);
	}
	
	@SuppressWarnings("unchecked")
	public List<Treasure> getTreasureListByState(Integer state){
		return this.getSqlMapClientTemplate().queryForList("Treasure.getTreasureListByState",state);
	}
	
	@SuppressWarnings("unchecked")
	public List<Treasure> getRecommendTreasureList(){
		return this.getSqlMapClientTemplate().queryForList("Treasure.getRecommendTreasureList");
	}

}
