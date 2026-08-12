package com.war.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IMapDAO;
import com.war.domain.Map;

public class MapDAO extends SqlMapClientDaoSupport implements IMapDAO{

	private static Logger logger = Logger.getLogger(MapDAO.class);
	
	
	public Integer createMap(Map map) {
		return (Integer)this.getSqlMapClientTemplate().insert("Map.createMap", map);
	}
	
	public void updateMapCategoryByID(Integer mapID,Integer category){
		
		java.util.Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("mapID", mapID);
		params.put("category", category);

		this.getSqlMapClientTemplate().update("Map.updateMapCategoryByID", params);
	}
	
	public void updateTargetIDAndCategoryByID(Integer mapID, Integer targetID, Integer category) {
		java.util.Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("mapID", mapID);
		params.put("targetID", targetID);
		params.put("category", category);
		
		this.getSqlMapClientTemplate().update("Map.updateTargetIDAndCategoryByID", params);
	}
	
	public void updateMapTargetIDAndCategoryBatch(Map[] mapArray){
		try {
			for(int i=0;i<mapArray.length;i++){
				this.getSqlMapClient().update("Map.updateMapTargetIDAndCategoryBatch",mapArray[i]);
			}
		} catch (SQLException e) {
			logger.error("异常：", e);
		}
	}
	
	public void updateMap(Map map) {
		this.getSqlMapClientTemplate().update("Map.updateMap", map);
	}
	
	public void deleteMapByID(Integer mapID) {
		this.getSqlMapClientTemplate().delete("Map.deleteMapByID", mapID);
	}
	
	public Integer getMapNumByCategoryAndArea(Integer category, Integer area) {
		java.util.Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("category", category);
		params.put("area", area);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Map.getMapNumByCategoryAndArea", params);
	}
	
	public Integer getMapNumByAreaAndCategoryAndMapMonsterLevel(Integer area, Integer category, Integer mapMonsterLevel) {
		java.util.Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("area", area);
		params.put("category", category);
		params.put("mapMonsterLevel", mapMonsterLevel);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Map.getMapNumByAreaAndCategoryAndMapMonsterLevel", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getNoDepoyQueueAndNotInBattleMapList(){
		return this.getSqlMapClientTemplate().queryForList("Map.getNoDepoyQueueAndNotInBattleMapList");
	}
	
	public Map getMapByTargetIDAndCategory(Integer targerID,Integer category){
		java.util.Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("targetID", targerID);
		params.put("category", category);
		
		return (Map)this.getSqlMapClientTemplate().queryForObject("Map.getMapByTargetIDAndCategory",params);
	}
	
	public Map getMapByID(Integer mapID) {
		return (Map)this.getSqlMapClientTemplate().queryForObject("Map.getMapByID", mapID);
	}
	
	public Integer getBlankFieldAndMonsterFieldMapNumByArea(Integer blankFieldCategory, Integer monsterCategory, Integer area) {
		java.util.Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("blankFieldCategory", blankFieldCategory);
		params.put("monsterCategory", monsterCategory);
		params.put("area", area);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Map.getBlankFieldAndMonsterFieldMapNumByArea", params);
	}
	
	public Integer getMapAmountByCategory(Integer category){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Map.getMapAmountByCategory",category);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getBlankFieldIDList(){
		return this.getSqlMapClientTemplate().queryForList("Map.getBlankFieldIDList");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getMapList() {
		return this.getSqlMapClientTemplate().queryForList("Map.getMapList");
	}
	
	public Map getRandomMapByAreaAndCategory(Integer area, Integer category) {
		java.util.Map<String, Integer> params = new java.util.HashMap<String, Integer>();
		params.put("area", area);
		params.put("category", category);
		return (Map)this.getSqlMapClientTemplate().queryForObject("Map.getRandomMapByAreaAndCategory", params);
	}
	
	@SuppressWarnings("unchecked")
	public Map getRandomMapByStartPosXYAndEndPosXYAndCategory(Integer startX, Integer startY, Integer endX, Integer endY, Integer category) {
		java.util.Map<String, Integer> params = new java.util.HashMap<String, Integer>();
		params.put("startX", startX);
		params.put("startY", startY);
		params.put("endX", endX);
		params.put("endY", endY);
		params.put("category", category);
		return (Map)this.getSqlMapClientTemplate().queryForObject("Map.getRandomMapByStartPosXYAndEndPosXYAndCategory", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getMapListByStartPosXYAndEndPosXY(Integer startX, Integer startY, Integer endX, Integer endY){
		java.util.Map<String, Integer> params = new java.util.HashMap<String, Integer>();
		params.put("startX", startX);
		params.put("startY", startY);
		params.put("endX", endX);
		params.put("endY", endY);
		return this.getSqlMapClientTemplate().queryForList("Map.getMapListByStartPosXYAndEndPosXY", params);
	}
	
	public Map getMapByPosXAndPoxY(int posX, int posY){
		java.util.Map<String, Integer> params = new java.util.HashMap<String, Integer>();
		params.put("posX", posX);
		params.put("posY", posY);
		return (Map) this.getSqlMapClientTemplate().queryForObject("Map.getMapByPosXAndPoxY", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getMapListBySQL(String sql) {
		return this.getSqlMapClientTemplate().queryForList("Map.getMapListBySQL", sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getRandomMapIDListByCategoryAndAreaAndNum(Integer category, Integer area, Integer num) {
		java.util.Map<String, Integer> params = new java.util.HashMap<String, Integer>();
		params.put("category", category);
		params.put("area", area);
		params.put("num", num);
		return this.getSqlMapClientTemplate().queryForList("Map.getRandomMapIDListByCategoryAndAreaAndNum", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getBlankFieldList(){
		return this.getSqlMapClientTemplate().queryForList("Map.getBlankFieldList");
	}

	@SuppressWarnings("unchecked")
	public List<Map> getMapList(int radius,int range){
		java.util.Map<String, Integer> params = new java.util.HashMap<String, Integer>();
		params.put("radius", radius);
		params.put("range", range);
		
		return this.getSqlMapClientTemplate().queryForList("Map.getMapListByDistance",params);
	}

}