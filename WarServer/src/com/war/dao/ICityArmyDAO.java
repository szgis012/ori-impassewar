package com.war.dao;

import java.util.List;

import com.war.domain.CityArmy;

/**
 * 城市兵力DAO接口
 * 
 * @author TopTong
 * @version 1.0
 */
public interface ICityArmyDAO {

	/**
	 * 创建城市兵力
	 * @param cityArmy
	 * @return
	 */
	public Integer createCityArmy(CityArmy cityArmy);

	/**
	 * 更新城市兵力
	 * @param cityArmy
	 */
	public void updateCityArmy(CityArmy cityArmy);

	/**
	 * 根据编号删除城市兵力
	 * @param cityArmyID
	 */
	public void deleteCityArmyByID(Integer cityArmyID);

	/**
	 * 根据编号获得城市兵力
	 * @param cityArmyID
	 * @return
	 */
	public CityArmy getCityArmyByID(Integer cityArmyID);
	
	/**
	 * 获得城市兵力列表
	 * @return
	 */
	public List<CityArmy> getCityArmyList();
	
	/**
	 * 根据城市编号获得城市兵力列表
	 * @param cityID
	 * @return
	 */
	public List<CityArmy> getCityArmyListByCityID(Integer cityID);
	
	/**
	 * 根据城市编号及军队编号获得城市军队信息
	 * @param cityID
	 * @param armyID
	 * @return
	 */
	public CityArmy getCityArmyByCityIDAndArmyID(Integer cityID,Integer armyID);
	
	/**
	 * 批量更新城市士兵数量
	 * @param cityID
	 * @param armyIDs
	 * @param nums
	 */
	public void batchUpdateCityArmyNumByCityIDAndArmyIDs(int cityID, int[] armyIDs,int[] nums);
	
	/**
	 * 根据城市编号和士兵编号删除城市士兵
	 * @param cityID
	 * @param armyID
	 */
	public void deleteCityArmyByCityIDAndArmyID(Integer cityID, Integer armyID);

}