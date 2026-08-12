package com.war.dao;

import java.util.List;

import com.war.domain.CityMilitarySuccor;

public interface ICityMilitarySuccorDAO {

	/**
	 * 创建军队支援信息
	 * @param cityMilitarySuccor
	 * @return
	 */
	public Integer createCityMilitarySuccor(CityMilitarySuccor cityMilitarySuccor);

	/**
	 * 更新军队支援信息
	 * @param cityMilitarySuccor
	 */
	public void updateCityMilitarySuccor(CityMilitarySuccor cityMilitarySuccor);

	/**
	 * 根据编号更新战斗顺序
	 * @param battleOrder
	 * @param cityMilitarySuccorID
	 */
	public void updateBattleOrderByCityMilitarySuccorID(Integer battleOrder, Integer cityMilitarySuccorID);
	
	/**
	 * 根据编号删除军队支援信息
	 * @param cityMilitarySuccorID
	 */
	public void deleteCityMilitarySuccorByID(Integer cityMilitarySuccorID);

	/**
	 * 根据编号获得军队支援信息
	 * @param cityMilitarySuccorID
	 * @return
	 */
	public CityMilitarySuccor getCityMilitarySuccorByID(Integer cityMilitarySuccorID);

	/**
	 * 获得军队支援信息列表
	 * @return
	 */
	public List<CityMilitarySuccor> getCityMilitarySuccorList();

	/**
	 * 根据被支援城市编号获得其军队支援信息列表
	 * @param targetCityID
	 * @return
	 */
	public List<CityMilitarySuccor> getCityMilitarySuccorListByTargetCityID(Integer targetCityID);
	
	/**
	 * 根据被支援城市编号获得其被驻军并且非待命军队的列表
	 * @param targetCityID
	 * @return
	 */
	public List<CityMilitarySuccor> getCityMilitarySuccorActiveListByTargetCityIDOrderByBattleOrder(Integer targetCityID);
	
	/**
	 * 根据被支援城市编号获得其被驻军并且非待命军队的总数
	 * @param cityID
	 * @return
	 */
	public Integer getCityMilitarySuccorActiveNumByTargetCityID(Integer cityID);
	
	/**
	 * 根据城市军队编号获得编号
	 * @param cityMilitaryID
	 * @return
	 */
	public Integer getCityMilitarySuccorIDByCityMilitaryID(Integer cityMilitaryID);
	
	/**
	 * 根据被支援城市编号获得其被支援的军队数量
	 * @param cityID
	 * @return
	 */
	public Integer getCityMilitarySuccorNumByTargetCityID(Integer cityID);
	
	/**
	 * 根据城市编号更新其支援军队的顺序
	 * @param cityID
	 */
	public void refreshSuccorOrder(Integer cityID);
	
	/**
	 * 获得驻扎超过72个小时的援军信息列表
	 * @return
	 */
	public List<CityMilitarySuccor> getOverTimeCityMilitarySuccorList();

	/**
	 * 根据城市军队编号获得城市支援军队信息
	 * @param cityMilitaryID
	 * @return
	 */
	CityMilitarySuccor getCityMilitarySuccorByCityMilitaryID(Integer cityMilitaryID);
}
