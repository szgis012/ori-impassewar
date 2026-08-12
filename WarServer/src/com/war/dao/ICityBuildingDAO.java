package com.war.dao;

import java.util.List;

import com.war.domain.CityBuilding;

public interface ICityBuildingDAO {

	/**
	 * 创建城市建筑
	 * @param cityBuilding
	 * @return
	 */
	public Integer createCityBuilding(CityBuilding cityBuilding);
	
	/**
	 * 更新城市建筑
	 * @param cityBuilding
	 */
	public void updateCityBuilding(CityBuilding cityBuilding);

	/**
	 * 删除城市建筑
	 * @param cityBuildingID
	 */
	public void deleteCityBuildingByID(Integer cityBuildingID);

	/**
	 * 根据城市编号及建筑编号获得城市建筑等级
	 * @param cityID
	 * @param buildingID
	 * @return
	 */
	public Integer getCityBuildingLevelByCityIDAndBuildingID(Integer cityID,Integer buildingID);
	
	/**
	 * 根据城市编号及建筑编号及位置获得城市建筑
	 * @param cityID
	 * @param buildingID
	 * @param position
	 * @return
	 */
	public CityBuilding getCityBuildingByCityIDAndBuildingIDAndPosition(Integer cityID,Integer buildingID,Integer position);
	
	/**
	 * 根据城市编号及建筑编号获得城市建筑
	 * @param cityID
	 * @param buildingID
	 * @return
	 */
	public CityBuilding getCityBuildingByCityIDAndBuildingID(Integer cityID,Integer buildingID);
	
	/**
	 * 根据编号获得城市建筑
	 * @param cityBuildingID
	 * @return
	 */
	public CityBuilding getCityBuildingByID(Integer cityBuildingID);

	/**
	 * 根据城市编号获得城市建筑列表
	 * @param cityID
	 * @return
	 */
	public List<CityBuilding> getCityBuildingListByCityID(Integer cityID);
	
	/**
	 * 获得指定位置的城市建筑
	 * @param cityID
	 * @param position 
	 * @return
	 */
	public CityBuilding getCityBuildingByPosition(Integer cityID,Integer position);

}