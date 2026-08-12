package com.war.dao;

import java.util.List;

import com.war.domain.CityRank;

public interface ICityRankDAO {

	/**
	 * 根据城市编号获得城市排名信息
	 * @param cityID
	 * @return
	 */
	public CityRank getCityRankByCityID(Integer cityID);
	
	
	
	/**
	 * 根据城市编号获得城市建筑点数排名
	 * @param cityID
	 * @return
	 */
	public Integer getCityConstructionPointRankByCityID(Integer cityID);
	
	/**
	 * 刷新城市建设点数排名
	 */
	public void refreshCityConstructionPointRank();
	
	/**
	 * 获得城市建设点数排名列表(分页)
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<CityRank> getCityConstructionPointRankPagingList(Integer start,Integer offset);
	
	
	
	/**
	 * 根据城市编号获得城市科技点数排名
	 * @param cityID
	 * @return
	 */
	public Integer getCityTechnologyPointRankByCityID(Integer cityID);
	
	/**
	 * 刷新城市科技点数排名
	 */
	public void refreshCityTechnologyPointRank();
	
	/**
	 * 获得城市科技点数排名列表(分页)
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<CityRank> getCityTechnologyPointRankPagingList(Integer start,Integer offset);
	
	
	
	/**
	 * 根据城市编号获得城市人口排名
	 * @param cityID
	 * @return
	 */
	public Integer getCityPopulationRankByCityID(Integer cityID);
	
	/**
	 * 刷新城市人口排名
	 */
	public void refreshCityPopulationRank();
	
	/**
	 * 获得城市人口排名列表(分页)
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<CityRank> getCityPopulationRankPagingList(Integer start,Integer offset);
	
}
