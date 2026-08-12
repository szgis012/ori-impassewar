package com.war.dao;

import java.util.List;

import com.war.domain.Colonization;

public interface IColonizationDAO {

	/**
	 * 创建殖民
	 * @param colonization
	 * @return
	 */
	public Integer createColonization(Colonization colonization);

	/**
	 * 更新是否已经征收
	 * @param haveImposed
	 */
	public void updateHaveImposed(Integer haveImposed);
	
	/**
	 * 根据殖民编号更新是否已经征收
	 * @param colonizationID
	 * @param haveImposed
	 */
	public void updateHaveImposedByColonizationID(Integer colonizationID, Integer haveImposed);
	
	/**
	 * 更新殖民
	 * @param colonization
	 */
	public void updateColonization(Colonization colonization);

	/**
	 * 根据编号删除殖民
	 * @param colonizationID
	 */
	public void deleteColonizationByID(Integer colonizationID);

	/**
	 * 根据城市编号获得殖民数量
	 * @param cityID
	 * @return
	 */
	public Integer getColonizationNumByCityID(Integer cityID);
	
	/**
	 * 根据编号获得殖民
	 * @param colonizationID
	 * @return
	 */
	public Colonization getColonizationByID(Integer colonizationID);

	/**
	 * 根据城市编号及目标城市编号获得殖民
	 * @param cityID
	 * @param targetCityID
	 * @return
	 */
	public Colonization getColonizationByCityIDAndTargetCityID(Integer cityID, Integer targetCityID);
	
	/**
	 * 根据城市编号获得殖民列表
	 * @param cityID
	 * @return
	 */
	public List<Colonization> getColonizationListByCityID(Integer cityID);
	
	/**
	 * 获得已结束殖民列表
	 * @return
	 */
	public List<Colonization> getFinishedColonizationList();
	
	/**
	 * 获得殖民列表
	 * @return
	 */
	public List<Colonization> getColonizationList();

}