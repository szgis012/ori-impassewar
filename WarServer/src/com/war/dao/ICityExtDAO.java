package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.CityExt;

public interface ICityExtDAO {

	/**
	 * 创建城市扩展信息
	 * @param cityExt
	 */
	public void createCityExt(CityExt cityExt);

	/**
	 * 根据参数更新城市扩展信息
	 * @param params
	 */
	public void updateCityExtParams(Map<String,Object> params);
	
	/**
	 * 更新城市扩展信息
	 * @param cityExt
	 */
	public void updateCityExt(CityExt cityExt);

	/**
	 * 根据编号删除城市扩展信息
	 * @param cityExtID
	 */
	public void deleteCityExtByID(Integer cityID);

	/**
	 * 根据编号获得城市扩展信息
	 * @param cityExtID
	 * @return
	 */
	public CityExt getCityExtByID(Integer cityID);

	/**
	 * 获得城市扩展信息列表
	 * @return
	 */
	public List<CityExt> getCityExtList();

}