package com.war.service;

import java.util.List;

import com.war.domain.Colonization;

public interface IColonizationService {

	/**
	 * 添加殖民
	 * @param cityID
	 * @param targetCityID
	 */
	public void addColonization(Integer cityID, Integer targetCityID);
	
	/**
	 * 取消殖民
	 * @param colonizationID
	 */
	public void cancelColonization(Integer colonizationID);
	
	/**
	 * 获得城市是否可殖民
	 * @param cityID
	 * @return
	 */
	public boolean canCityColonize(Integer cityID);
	
	/**
	 * 获得城市是否已经殖民
	 * @param cityID
	 * @param targetCityID
	 * @return
	 */
	public boolean haveColonized(Integer cityID, Integer targetCityID);
	
	/**
	 * 获得城市殖民数量
	 * @param cityID
	 * @return
	 */
	public Integer getCityColonizationNum(Integer cityID);
	
	/**
	 * 获得城市殖民列表
	 * @param cityID
	 * @return
	 */
	public List<Colonization> getCityColonizationList(Integer cityID);
	
	/**
	 * 征收物资
	 * @param colonizationID
	 * @param type(1.资源 2.军械)
	 */
	public void impose(Integer colonizationID,Integer type);
	
	/**
	 * 重置殖民是否已经征收
	 */
	public void resetColonizationHaveImpose();
	
	/**
	 * 处理已结束殖民
	 */
	public void handleFinishedColonization();
	
}
