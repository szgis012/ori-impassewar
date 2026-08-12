package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.City;
import com.war.domain.CityInfo;

public interface ICityDAO {

	/**
	 * 创建城市
	 * @param city
	 * @return
	 */
	public Integer createCity(City city);

	/**
	 * 根据城市编号更新城市人口上限
	 * @param cityID
	 * @param populationMax
	 */
	public void updatePopulationMaxByCityID(Integer cityID, Long populationMax);
	
	/**
	 * 根据城市编号更新城市状态
	 * @param cityID
	 * @param state
	 */
	public void updateStateByCityID(Integer cityID,Integer state);
	
	/**
	 * 批量增加城市治安
	 */
	public void batchAddCitySecurity();
	
	/**
	 * 更新城市
	 * @param city
	 */
	public void updateCity(City city);
	
	/**
	 * 根据城市编号删除城市
	 * @param cityID
	 */
	public void deleteCityByID(Integer cityID);

	/**
	 * 根据城市编号获得人口
	 * @param cityID
	 * @return
	 */
	public Map<String,Long> getPopulationByCityID(Integer cityID);
	
	/**
	 * 根据城市编号获得空闲商人数量
	 * @param cityID
	 * @return
	 */
	public Integer getBusinessFreeByCityID(Integer cityID);
	
	/**
	 * 根据城市编号获得玩家编号
	 * @param cityID
	 * @return
	 */
	public Integer getPlayerIDByCityID(Integer cityID);
	
	/**
	 * 根据玩家编号获得城市编号
	 * @param playerID  
	 * @return
	 */
	public Integer getCityIDByPlayerID(Integer playerID);
	
	/**
	 * 根据城市名称获得城市编号
	 * @param cityName
	 * @return
	 */
	public Integer getCityIDByCityName(String cityName);
	
	/**
	 * 根据城市编号获得城市名称
	 * @param cityID
	 * @return
	 */
	public String getCityNameByCityID(Integer cityID);
	
	/**
	 * 根据玩家编号获得城市
	 * @param playerID
	 * @return
	 */
	public City getCityByPlayerID(Integer playerID);
	
	/**
	 * 根据城市X坐标与Y坐标获得城市对象
	 * @param posX
	 * @param posY
	 * @return
	 */
	public City getCityByPosXAndPosY(Integer posX, Integer posY);
	
	/**
	 * 根据城市X坐标与Y坐标获得城市编号
	 * @param posX
	 * @param posY
	 * @return
	 */
	public Integer getCityIDByPosXAndPosY(Integer posX,Integer posY);
	
	/**
	 * 根据城市编号获得城市X坐标与Y坐标
	 * @param cityID
	 * @return
	 */
	public Map<String,Integer> getCityPosXAndPosYByCityID(Integer cityID);
	
	/**
	 * 根据城市编号获得城市的税率和治安值
	 * @param cityID
	 * @return
	 */
	public Map<String,Integer> getCityTaxAndSecurity(Integer cityID);
	
	/**
	 * 根据城市编号获得城市
	 * @param cityID
	 * @return
	 */
	public City getCityByID(Integer cityID);
	
	/**
	 * 根据城市编号获得城市信息
	 * @param cityID
	 * @return
	 */
	public CityInfo getCityInfoByCityID(Integer cityID);

	/**
	 * 获得城市列表
	 * @return
	 */
	public List<City> getCityList();
	
	/**
	 * 更新城市的部分字段信息
	 * 其中必须指定要更新的cityID和至少一个其他要更新的字段信息
	 * @param params key为要更新的字段 value为更新的值
	 */
	public void updateCity(java.util.Map<String, Object> params);
	
	/**
	 * 根据资源的变化更新治安值
	 * 当石油耗尽时，每小时减去城市治安值1
	 * 当食物耗尽时，每小时减去城市治安值2
	 * 当金钱耗尽时，每小时检出城市治安值3
	 */
	public void updateSecurityOfResourceEffect();
	
}