package com.war.dao;

import java.util.List;

import com.war.domain.CityWoundedArmy;

/**
 * 伤兵DAO接口
 * 
 * @author JiaHL
 * @version 1.0
 */
public interface ICityWoundedArmyDAO {

	/**
	 * 创建伤兵信息
	 * @param CityWoundedArmy
	 * @return Integer 伤兵编号
	 */
	public Integer createCityWoundedArmy(CityWoundedArmy cityWoundedArmy);

	
	/**
	 * 更新伤兵信息
	 * @param CityWoundedArmy
	 */
	public void updateCityWoundedArmy(CityWoundedArmy cityWoundedArmy);

	/**
	 * 根据编号删除伤兵信息
	 * @param CityWoundedArmyID
	 */
	public void deleteCityWoundedArmyByID(Integer cityWoundedArmyID);

	/**
	 * 根据城市编号获得城市伤兵列表
	 * @param cityID
	 * @return 城市伤兵列表
	 */
	public List<CityWoundedArmy> getCityWoundedArmyListByCityID(Integer cityID);
	
	/**
	 * 根据编号获得伤兵信息
	 * @param CityWoundedArmyID
	 * @return CityWoundedArmy
	 */
	public CityWoundedArmy getCityWoundedArmyByID(Integer cityWoundedArmyID);
	
	/**
	 * 获得到期后自动遣散的伤兵列表
	 * @return
	 */
	public List<CityWoundedArmy> getAutoDismissedCityWoundedArmyList();
	
	public List<CityWoundedArmy> getCityWoundedArmyList();
}
