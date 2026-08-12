package com.war.dao;

import java.util.List;

import com.war.domain.MapFavourite;

public interface IMapFavouriteDAO {

	/**
	 * 创建地图收藏信息
	 * @param mapFavourite
	 * @return
	 */
	public Integer createMapFavourite(MapFavourite mapFavourite);

	/**
	 * 更新地图收藏信息
	 * @param mapFavourite
	 */
	public void updateMapFavourite(MapFavourite mapFavourite);

	/**
	 * 根据编号删除地图收藏信息
	 * @param mapFavouriteID
	 */
	public void deleteMapFavouriteByID(Integer mapFavouriteID);

	/**
	 * 根据编号获得地图收藏信息
	 * @param mapFavouriteID
	 * @return
	 */
	public MapFavourite getMapFavouriteByID(Integer mapFavouriteID);

	/**
	 * 获得地图收藏信息列表
	 * @return
	 */
	public List<MapFavourite> getMapFavouriteList();

	/**
	 * 根据玩家编号获得地图收藏信息列表
	 * @param playerID
	 * @return
	 */
	public List<MapFavourite> getMapPagingFavouriteListByPlayerID(Integer playerID, Integer start, Integer offset);
	
	/**
	 * 根据玩家编号获得其地图收藏条数
	 * @param playerID
	 * @return
	 */
	public Integer getMapFavouriteNumByPlayerID(Integer playerID);

	/**
	 * 根据玩家编号和坐标获得地图收藏信息
	 * @param playerID
	 * @param posX
	 * @param posY
	 * @return
	 */
	MapFavourite getMapFavouriteByPosXAndPosYAndPlayerID(Integer playerID, Integer posX, Integer posY);
}
