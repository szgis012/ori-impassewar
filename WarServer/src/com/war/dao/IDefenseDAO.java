package com.war.dao;

import java.util.List;

import com.war.domain.Defense;

public interface IDefenseDAO {
	
	/**
	 * 创建防御
	 * @param defense
	 * @return
	 */
	public Integer createDefense(Defense defense);

	/**
	 * 更新防御
	 * @param defense
	 */
	public void updateDefense(Defense defense);

	/**
	 * 根据编号删除防御信息
	 * @param defenseID
	 */
	public void deleteDefenseByID(Integer defenseID);

	/**
	 * 根据编号获得防御信息
	 * @param defenseID
	 * @return
	 */
	public Defense getDefenseByID(Integer defenseID);

	/**
	 * 获得防御信息列表
	 * @return
	 */
	public List<Defense> getDefenseList();

	
}
