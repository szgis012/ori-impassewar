package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IMapFavouriteDAO;
import com.war.domain.MapFavourite;

public class MapFavouriteDAO extends SqlMapClientDaoSupport implements IMapFavouriteDAO {

	public Integer createMapFavourite(MapFavourite mapFavourite) {
		return (Integer)this.getSqlMapClientTemplate().insert("MapFavourite.createMapFavourite", mapFavourite);
	}

	public void updateMapFavourite(MapFavourite mapFavourite) {
		this.getSqlMapClientTemplate().update("MapFavourite.updateMapFavourite", mapFavourite);
	}

	public void deleteMapFavouriteByID(Integer mapFavouriteID) {
		this.getSqlMapClientTemplate().delete("MapFavourite.deleteMapFavouriteByID", mapFavouriteID);
	}

	public MapFavourite getMapFavouriteByID(Integer mapFavouriteID) {
		return (MapFavourite)this.getSqlMapClientTemplate().queryForObject("MapFavourite.getMapFavouriteByID", mapFavouriteID);
	}
	
	@SuppressWarnings("unchecked")
	public List<MapFavourite> getMapFavouriteList() {
		return this.getSqlMapClientTemplate().queryForList("MapFavourite.getMapFavouriteList");
	}

	@SuppressWarnings("unchecked")
	public List<MapFavourite> getMapPagingFavouriteListByPlayerID(Integer playerID, Integer start, Integer offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("start", start);
		params.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("MapFavourite.getMapPagingFavouriteListByPlayerID", params);
	}

	public Integer getMapFavouriteNumByPlayerID(Integer playerID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("MapFavourite.getMapFavouriteNumByPlayerID", playerID);
	}

	public MapFavourite getMapFavouriteByPosXAndPosYAndPlayerID(Integer playerID, Integer posX, Integer posY) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("posX", posX);
		params.put("posY", posY);
		
		return (MapFavourite) this.getSqlMapClientTemplate().queryForObject("MapFavourite.getMapFavouriteByPosXAndPosYAndPlayerID", params);
	}
}
