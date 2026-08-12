package com.war.dao;


import java.util.List;

import com.war.domain.CityDefense;

public interface ICityDefenseDAO {

	public Integer createCityDefense(CityDefense cityDefense);

	public void updateCityDefense(CityDefense cityDefense);

	public void deleteCityDefenseByID(Integer cityDefenseID);

	public CityDefense getCityDefenseByID(Integer cityDefenseID);

	public List<CityDefense> getCityDefenseList();
	
	/**
	 * 获得城市特定防御的信息
	 * 
	 * @param cityID 城市编号
	 * @param defenseID 
	 * @return
	 */
	public CityDefense getCityDefense(Integer cityID,Integer defenseID);
	
	/**
	 * 获得城市所有防御的信息
	 * @param cityID
	 * @return
	 */
	public List<CityDefense> getCityDefenseList(Integer cityID);

}
