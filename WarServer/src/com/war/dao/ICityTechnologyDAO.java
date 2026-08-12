package com.war.dao;

import java.util.List;

import com.war.domain.CityTechnology;

public interface ICityTechnologyDAO {

	/**
	 * 创建城市科技
	 * @param cityTechnology
	 * @return
	 */
	public Integer createCityTechnology(CityTechnology cityTechnology);

	/**
	 * 更新城市科技状态
	 * @param cityTechnologyID
	 * @param state
	 */
	public void updateCityTechnologyState(Integer cityTechnologyID,Integer state);
	
	/**
	 * 更新城市科技
	 * @param cityTechnology
	 */
	public void updateCityTechnology(CityTechnology cityTechnology);

	/**
	 * 根据城市科技编号删除城市科技
	 * @param cityTechnologyID
	 */
	public void deleteCityTechnologyByID(Integer cityTechnologyID);

	/**
	 * 根据城市科技编号获得城市科技
	 * @param cityTechnologyID
	 * @return
	 */
	public CityTechnology getCityTechnologyByID(Integer cityTechnologyID);

	/**
	 * 根据城市编号及科技状态获得城市科技
	 * @param cityID
	 * @param state
	 * @return
	 */
	public CityTechnology getCityTechnologyByCityIDAndState(Integer cityID,Integer state);
	
	/**
	 * 根据城市编号及科技编号获得城市科技等级
	 * @param cityID
	 * @param technologyID
	 * @return
	 */
	public Integer getCityTechnologyLevelByCityIDAndTechnologyID(Integer cityID,Integer technologyID);
	
	/**
	 * 根据城市编号及科技编号获得城市科技
	 * @param cityID
	 * @param technologyID
	 * @return
	 */
	public CityTechnology getCityTechnologyByCityIDAndTechnologyID(Integer cityID,Integer technologyID);
	
	/**
	 * 根据城市编号获得城市科技列表
	 * @param cityID
	 * @return
	 */
	public List<CityTechnology> getCityTechnologyListByCityID(Integer cityID);
	
	/**
	 * 获得城市科技列表
	 * @return
	 */
	public List<CityTechnology> getCityTechnologyList();

	/**
	 * 根据城市编号和城市科技等级获得城市科技数目
	 * @return
	 */
	public Integer getCityTechnologyNumByCityIDAndLevel(Integer cityID, Integer level);
}