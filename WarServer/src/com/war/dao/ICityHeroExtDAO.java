package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.CityHeroExt;

public interface ICityHeroExtDAO {
	
	/**
	 * 创建城市英雄扩展信息
	 * @param cityHeroExt
	 * @return
	 */
	public Integer createCityHeroExt(CityHeroExt cityHeroExt);

	/**
	 * 更新城市英雄扩展信息
	 * @param cityHeroExt
	 */
	public void updateCityHeroExt(CityHeroExt cityHeroExt);

	/**
	 * 更新城市信息扩展信息
	 * @param params
	 */
	public void updateCityHeroExtParams(Map<String, Object> params);
	
	/**
	 * 根据城市英雄编号更新装备提升的点数
	 * @param cityHeroID
	 * @param commandEquipmentAdd
	 * @param defenseEquipmentAdd
	 * @param mindEquipmentAdd
	 * @param executivepowerEquipmentAdd
	 */
	public void updateCityHeroExtEquipmentAddByID(Integer cityHeroID, Integer commandEquipmentAdd, Integer defenseEquipmentAdd, Integer mindEquipmentAdd, Integer executivepowerEquipmentAdd);
	
	/**
	 * 根据城市英雄编号更新其对军队的增益
	 * @param cityHeroID
	 * @param militaryAttackAdd
	 * @param militaryDefenseAdd
	 * @param militaryLifeAdd
	 */
	public void updateCityHeroMilitaryAddByID(Integer cityHeroID, Integer militaryAttackAdd, Integer militaryDefenseAdd, Integer militaryLifeAdd);
	
	/**
	 * 根据编号删除城市英雄扩展信息
	 * @param cityHeroExtID
	 */
	public void deleteCityHeroExtByID(Integer cityHeroID);

	/**
	 * 根据编号获得城市英雄扩展信息
	 * @param cityHeroExtID
	 * @return
	 */
	public CityHeroExt getCityHeroExtByID(Integer cityHeroID);

	/**
	 * 获得城市英雄扩展信息列表s
	 * @return
	 */
	public List<CityHeroExt> getCityHeroExtList();

	/**
	 * 根据玩家编号获得城市英雄扩展信息列表
	 * @param playerID
	 * @return
	 */
	public List<CityHeroExt> getCityHeroExtListBycityID(Integer cityID);


	/**
	 * 根据城市编号更新城市英雄扩展信息中的军团加成效果
	 * @param map 需要改变的军团加成效果参数
	 * @param cityID
	 */
	public void updateGuildAddByCityIDWithParams(Map<String, Integer> map, Integer cityID);
	
}
