package com.war.service;

import java.util.List;

import com.war.domain.Map;
import com.war.domain.MapFavourite;

/**
 * 地图Service
 *
 * @author TopTong
 * @version 1.0
 */
public interface IMapService {
	
	/**
	 * 创建地图
	 * @param map
	 * @return
	 */
	public Integer createMap(Map map);
	
	/**
	 * 更新地图编号及类别
	 * @param mapID
	 * @param category
	 */
	public void updateMapCategoryByID(Integer mapID, Integer category);
	
	/**
	 * 更新地图
	 * @param map
	 */
	public void updateMap(Map map);

	/**
	 * 根据类别及区域获得地图数量
	 * @param category
	 * @param area
	 * @return
	 */
	public Integer getMapNumByCategoryAndArea(Integer category, Integer area);
	
	/**
	 * 根据编号获得地图
	 * @param mapID
	 * @return
	 */
	public Map getMapByID(Integer mapID);
	
	/**
	 * 获得地图信息列表
	 * 
	 * @param startX 起始X坐标
	 * @param startY 起始Y坐标
	 * @param endX 结束X坐标
	 * @param endY 结束Y坐标
	 * @return
	 */
	public List<Map> getMapList(Integer startX, Integer startY, Integer endX, Integer endY);

	/**
	 * 根据地图XY坐标列表获得地图列表
	 * @param mapPosXYList
	 * @return
	 */
	public List<Map> getMapListByMapPosXYList(List<Map> mapPosXYList);
	
	/**
	 * 根据目标编号及类别获得地图
	 * @param targerID
	 * @param category
	 * @return
	 */
	public Map getMapByTargetIDAndCategory(Integer targerID, Integer category);
	
	/**
	 * 根据X坐标及Y坐标获得地图信息
	 * 
	 * @param posX 地图X坐标
	 * @param posY 地图Y坐标
	 * @return
	 */
	public Map getMapByPos(Integer posX, Integer posY);
	
	/**
	 * 根据地图区域获得空地
	 * @param mapArea
	 * @return
	 */
	public Map getAreaBlankMap(Integer mapArea);
	
	/**
	 * 生成游戏地图
	 */
	public void generateGameMap();
	
	/**
	 * 添加地图收藏信息
	 * @param favourite
	 */
	public void createMapFavourite(Integer playerID, Integer posX, Integer posY);
	
	/**
	 * 根据编号删除地图收藏信息
	 * @param mapFavouriteID
	 */
	public void deleteMapFavourite(Integer mapFavouriteID);
	
	/**
	 * 根据玩家编号获得地图收藏信息列表
	 * @param playerID
	 * @return
	 */
	public List<MapFavourite> getMapFavouritePagingList(Integer playerID, Integer start, Integer offset);
	
	/**
	 * 根据玩家编号获得其地图收藏条数
	 * @param playerID
	 * @return
	 */
	public Integer getMapFavouriteNumOfPlayer(Integer playerID);
}
