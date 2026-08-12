package com.war.dao;

import java.util.List;

import com.war.domain.BattleMilitary;
import com.war.domain.CityMilitary;

public interface ICityMilitaryDAO {

	/**
	 * 创建城市军队
	 * @param cityMilitary
	 * @return
	 */
	public Integer createCityMilitary(CityMilitary cityMilitary);

	/**
	 * 根据城市军队编号更新名称
	 * @param cityMilitaryID
	 * @param name
	 */
	public void updateNameByCityMilitaryID(Integer cityMilitaryID,String name);
	
	/**
	 * 根据城市军队编号更新城市英雄编号
	 * @param cityMilitaryID
	 * @param cityHeroID
	 */
	public void updateCityHeroIDByCityMilitaryID(Integer cityMilitaryID,Integer cityHeroID);
	
	/**
	 * 根据战斗军队更新城市军队士兵
	 * @param battleMilitary
	 */
	public void updateCityMilitaryArmyByBattleMilitary(BattleMilitary battleMilitary);
	
	/**
	 * 更新城市军队
	 * @param cityMilitary
	 */
	public void updateCityMilitary(CityMilitary cityMilitary);

	/**
	 * 根据城市军队编号更新其资源消耗值
	 * @param cityMilitaryID
	 * @param costOil
	 * @param costFood
	 * @param costMoney
	 */
	public void updateCityMilitaryConsume(Integer cityMilitaryID, Integer costOil, Integer costFood, Integer costMoney);
	
	/**
	 * 根据城市军队编号删除城市军队
	 * @param cityMilitaryID
	 */
	public void deleteCityMilitaryByID(Integer cityMilitaryID);

	/**
	 * 根据城市英雄编号获得城市军队编号
	 * @param cityHeroID
	 * @return
	 */
	public Integer getCityMilitaryIDByCityHeroID(Integer cityHeroID);
	
	/**
	 * 根据城市军队编号获得城市军队-战斗军队
	 * @param cityMilitaryID
	 * @return
	 */
	public BattleMilitary getCityMilitaryAsBattleMilitaryByID(Integer cityMilitaryID);
	
	/**
	 * 根据城市军队编号获得城市军队
	 * @param cityMilitaryID
	 * @return
	 */
	public CityMilitary getCityMilitaryByID(Integer cityMilitaryID);

	/**
	 * 根据城市编号获得城市军队列表
	 * @return
	 */
	public List<CityMilitary> getCityMilitaryListByCityID(Integer cityID);
	
	/**
	 * 更改城市军队的状态
	 * @param cityMilitaryID 军队编号
	 * @param state 军队状态（CityMilitaryStateConstant中定义） 
	 */
	public void updateCityMilitaryState(Integer cityMilitaryID, Integer state);
	
	/**
	 * 如果城市留守部队返回true，否则返回false
	 * @param cityID
	 * @return
	 */
	public boolean existsStayMilitary(Integer cityID);

	/**
	 * 根据城市英雄编删除城市军队
	 * @param cityHeroID
	 */
	public void deleteCityMilitaryByCityHeroID(Integer cityHeroID);

	/**
	 * 根据城市编号和状态获得城市军队的数量
	 * @param cityID
	 * @return
	 */
	public Integer getNotNormalStateCityMilitaryNumByCityID(Integer cityID);

}