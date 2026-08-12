package com.war.service;

import java.util.List;

import com.war.domain.MapMonster;

public interface IMonsterService {

	/**
	 * 初始化怪物Map
	 * @return
	 */
	public java.util.Map<Integer, Object> initMonsterMap();
	
	/**
	 * 初始化怪物掉落Map
	 * @return
	 * key为怪物等级，value为对象
	 * key为表示掉落类别的字符串(treasureList,equipmentList)，value为对象
	 * key为表示属性的字符串(ID,minPercent,maxPercent)，value为值
	 */
	public java.util.Map<Integer,java.util.Map<String,List<java.util.Map<String, Integer>>>> initMonsterDropMap();

	/**
	 * 生成地图怪物列表
	 */
	public void generateMapMonsterList();
	
	/**
	 * 根据地图野怪编号获得地图野怪
	 * @param mapMonsterID
	 * @return
	 */
	public MapMonster getMapMonsterByID(Integer mapMonsterID);

}
