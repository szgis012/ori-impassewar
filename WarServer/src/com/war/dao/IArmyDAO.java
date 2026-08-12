package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.Army;

/**
 * 兵种DAO接口
 * 
 * @author TopTong
 * @version 1.0
 */

public interface IArmyDAO {

	/**
	 * 创建军队
	 * @param army
	 * @return
	 */
	public Integer createArmy(Army army);

	/**
	 * 更新军队
	 * @param army
	 */
	public void updateArmy(Army army);

	/**
	 * 根据军队编号删除军队
	 * @param armyID
	 */
	public void deleteArmyByID(Integer armyID);

	/**
	 * 根据编号获得军队
	 * @param armyID
	 * @return
	 */
	public Army getArmyByID(Integer armyID);
	
	/**
	 * 获得军队列表
	 * @return
	 */
	public List<Army> getArmyList();

	/**
	 * 根据类型获得军队列表
	 * @param type
	 * @return
	 */
	public List<Army> getArmyListByType(Integer type);
	
}