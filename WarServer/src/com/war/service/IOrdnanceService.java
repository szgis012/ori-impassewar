package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.CityOrdnance;
import com.war.domain.Ordnance;


/**
 * 军械service
 *
 * @author ghleed
 * @version 1.0
 */
public interface IOrdnanceService {
	
	/**
	 * 初始化军械Map
	 * @return
	 */
	public Map<Integer, Ordnance> initOrdnancesMap();
	
	/**
	 * 初始化自由联邦军械列表
	 * @return
	 */
	public List<Ordnance> initFreeUnionOrdnanceList();
	
	/**
	 * 初始化联合帝国军械列表
	 * @return
	 */
	public List<Ordnance> initUnionEmpireOrdnanceList();
	
	public Integer createOrdnance(Ordnance ordnance);

	public void updateOrdnance(Ordnance ordnance);

	public void deleteOrdnanceByID(Integer ordnanceID);

	public Ordnance getOrdnanceByID(Integer ordnanceID);

	public List<Ordnance> getOrdnanceList();
	
	/**
	 * 获得指定阵营的军械列表
	 * @param country ContryTypeConstant中定义
	 * @return
	 */
	public List<Ordnance> getOrdnanceListByCountry(int country);
	
	public Integer createCityOrdnance(CityOrdnance cityOrdnance);

	public void updateCityOrdnance(CityOrdnance cityOrdnance);

	public void deleteCityOrdnanceByID(Integer cityOrdnanceID);

	public CityOrdnance getCityOrdnanceByID(Integer cityOrdnanceID);

	public List<CityOrdnance> getCityOrdnanceList();
	
	public CityOrdnance getCityOrdnance(Integer cityID,Integer ordnanceID);
	
	public List<CityOrdnance> getCityOrdnanceList(Integer cityID);
}
