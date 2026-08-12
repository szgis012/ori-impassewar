package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.Defense;


public interface IDefenseService {

	/**
	 * 初始化城防列表
	 * @return
	 */
	public List<Defense> initDefenseList();
	
	/**
	 * 初始化城防Map
	 * @return
	 */
	public Map<Integer, Defense> initDefenseMap();
	
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

	/**
	 * 获得防御对象的克隆：　缓存中防御对象的浅拷贝
	 * @param defenseID
	 * @return
	 */
	public Defense getClonedDefenseByID(Integer defenseID);
	
}
