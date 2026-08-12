package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IPlayerTreasureDAO;
import com.war.domain.PlayerTreasure;

/**
 *  玩家宝物DAO接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class PlayerTreasureDAO extends SqlMapClientDaoSupport implements IPlayerTreasureDAO{

	public void createPlayerTreasure(PlayerTreasure playerTreasure) {
		 this.getSqlMapClientTemplate().insert("PlayerTreasure.createPlayerTreasure", playerTreasure);
	}

	public void deletePlayerTreasure(Integer playerID) {
		this.getSqlMapClientTemplate().delete("PlayerTreasure.deletePlayerTreasure", playerID);
	}

	public void deletePlayerTreasureByPlayerIDAndTreasureID(Integer playerID, Integer treasureID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("treasureID", treasureID);
		
		this.getSqlMapClientTemplate().delete("PlayerTreasure.deletePlayerTreasureByPlayerIDAndTreasureID", params);
	}

	public PlayerTreasure getPlayerTreasureByPlayerIDAndTreasureID(Integer playerID, Integer treasureID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("treasureID", treasureID);
		
		return (PlayerTreasure)this.getSqlMapClientTemplate().queryForObject("PlayerTreasure.getPlayerTreasureByPlayerIDAndTreasureID", params);
	}

	@SuppressWarnings("unchecked")
	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID) {
		return (List<PlayerTreasure>) this.getSqlMapClientTemplate().queryForList("PlayerTreasure.getPlayerTreasureListByPlayerID", playerID);
	}

	public void updatePlayerTreasure(PlayerTreasure playerTreasure) {
		this.getSqlMapClientTemplate().update("PlayerTreasure.updatePlayerTreasure", playerTreasure);
	}

	@SuppressWarnings("unchecked")
	public List<PlayerTreasure> getPlayerTreasureListByCategory(
			Integer playerID, Integer category) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("category", category);
		
		return (List<PlayerTreasure>) this.getSqlMapClientTemplate().queryForList("PlayerTreasure.getPlayerTreasureListByCategory", params);
	}

	@SuppressWarnings("unchecked")
	public List<PlayerTreasure> getPlayerTreasureListByType(Integer playerID,
			Integer category, Integer type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("category", category);
		params.put("type", type);
		
		return (List<PlayerTreasure>) this.getSqlMapClientTemplate().queryForList("PlayerTreasure.getPlayerTreasureListByType", params);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPlayerTreasureMapList(Integer playerID,
			Integer category) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("category", category);
		
		return this.getSqlMapClientTemplate().queryForList("PlayerTreasure.getPlayerTreasureMapList", params);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTreasureMapListByType(Integer playerID,
			Integer category, Integer type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("category", category);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("PlayerTreasure.getTreasureMapListByType", params);
	}


}