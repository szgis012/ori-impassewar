package com.war.dao;

import java.util.List;

import com.war.domain.CityHeroLevelupLog;

public interface ICityHeroLevelupLogDAO {

	/**
	 * 创建指挥官升级日志
	 * @param cityHeroLevelupLog
	 * @return
	 */
	public Integer createCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog);

	/**
	 * 更新指挥官升级日志
	 * @param cityHeroLevelupLog
	 */
	public void updateCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog);

	/**
	 * 根据编号删除指挥官升级日志
	 * @param cityHeroLevelupLogID
	 */
	public void deleteCityHeroLevelupLogByID(Integer cityHeroLevelupLogID);
	
	/**
	 * 根据指挥官编号删除指挥官升级日志
	 * @param cityHeroID
	 */
	public void deleteCityHeroLevelupLogByCityHeroID(Integer cityHeroID);
	
	/**
	 * 根据指挥官编号以及等级(>=)删除指挥官升级日志
	 * @param cityHeroID
	 * @param level
	 */
	public void deleteCityHeroLevelupLogByCityHeroIDAndLevel(Integer cityHeroID, Integer level);

	/**
	 * 根据编号获得指挥官升级日志
	 * @param cityHeroLevelupLogID
	 * @return
	 */
	public CityHeroLevelupLog getCityHeroLevelupLogByID(Integer cityHeroLevelupLogID);

	/**
	 * 获得指挥官升级日志列表
	 * @return
	 */
	public List<CityHeroLevelupLog> getCityHeroLevelupLogList();

	/**
	 * 根据指挥官编号以及等级(==)删除获得指挥官升级日志
	 * @param cityHeroID
	 * @param level
	 * @return
	 */
	public CityHeroLevelupLog getCityHeroLevelupLogByCityHeroIDAndLevel(Integer cityHeroID, Integer level);

}
