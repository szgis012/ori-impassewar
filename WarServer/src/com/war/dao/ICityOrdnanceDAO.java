package com.war.dao;

import java.util.List;

import com.war.domain.CityOrdnance;


/**
 * 城市的器械dao
 *
 * @author ghleed
 * @version 1.0
 */
public interface ICityOrdnanceDAO {

	public Integer createCityOrdnance(CityOrdnance cityOrdnance);

	public void updateCityOrdnance(CityOrdnance cityOrdnance);

	public void deleteCityOrdnanceByID(Integer cityOrdnanceID);

	public CityOrdnance getCityOrdnanceByID(Integer cityOrdnanceID);

	public List<CityOrdnance> getCityOrdnanceList();
	
	public CityOrdnance getCityOrdnance(Integer cityID,Integer ordnanceID);
	
	public List<CityOrdnance> getCityOrdnanceList(Integer cityID);

}