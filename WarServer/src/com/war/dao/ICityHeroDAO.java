package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.CityHero;

public interface ICityHeroDAO {

	/**
	 * 创建城市英雄
	 * @param cityHero
	 * @return
	 */
	public Integer createCityHero(CityHero cityHero);

	/**
	 * 根据城市英雄编号更新名称
	 * @param cityHeroID
	 * @param name
	 */
	public void updateNameByCityHeroID(Integer cityHeroID,String name);
	
	/**
	 * 根据城市英雄编号更新体力
	 * @param cityHeroID
	 * @param stamina
	 */
	public void updateStaminaByCityHeroID(Integer cityHeroID, Integer stamina);
	
	/**
	 * 根据城市英雄编号增加城市英雄经验
	 * @param cityHeroID
	 * @param exp
	 */
	public void addExpByCityHeroID(Integer cityHeroID,Long exp);
	
	/**
	 * 批量增加城市英雄经验
	 */
	public void addCityHeroExpBatch();
	
	/**
	 * 批量增加城市英雄体力
	 */
	public void batchAddCityHeroStamina();
	
	/**
	 * 根据城市英雄编号更新最大技能数量
	 * @param cityHeroID
	 * @param maxSkillNum
	 */
	public void updateMaxSkillNumByCityHeroID(Integer cityHeroID, Integer maxSkillNum);
	
	/**
	 * 根据城市英雄编号更新城市英雄点数
	 * @param cityHeroID
	 * @param command
	 * @param defense
	 * @param mind
	 * @param executivepower
	 * @param unsetPoint
	 */
	public void updatePointByCityHeroID(Integer cityHeroID,Integer command,Integer defense,Integer mind,Integer executivepower,Integer unsetPoint);
	
	/**
	 * 根据城市英雄编号更新状态
	 * @param cityHeroID
	 * @param state
	 */
	public void updateStateByCityHeroID(Integer cityHeroID,Integer state);
	
	/**
	 * 根据城市英雄编号更新军魄
	 * @param cityHeroID
	 * @param militarySoul
	 */
	public void updateMilitarySoulByCityHeroID(Integer cityHeroID,Integer militarySoul);
	
	/**
	 * 根据城市英雄编号更新品质
	 * @param cityHeroID
	 * @param quality
	 */
	public void updateQualityByCityHeroID(Integer cityHeroID,Integer quality);
	
	/**
	 * 根据城市英雄编号更新星级
	 * @param cityHeroID
	 * @param star
	 */
	public void updateStarByCityHeroID(Integer cityHeroID,Integer star);
	
	/**
	 * 根据城市英雄编号更新统御
	 * @param cityHeroID
	 * @param star
	 */
	public void updateReinByCityHeroID(Integer cityHeroID,Integer rein);
	
	/**
	 * 根据城市英雄编号更新已经增加的军魂点数
	 * @param cityHeroID
	 * @param addedMilitarySpirit
	 */
	public void updateAddedMilitarySpiritByCityHeroID(Integer cityHeroID,Integer addedMilitarySpirit);
	
	/**
	 * 根据城市英雄编号更新军魂点
	 * @param cityHeroID
	 * @param militarySpirit
	 */
	public void updateMilitarySpiritByCityHeroID(Integer cityHeroID,Integer militarySpirit);
	
	/**
	 * 根据城市英雄编号更新领导力
	 * @param cityHeroID
	 * @param leadership
	 */
	public void updateLeadershipByCityHeroID(Integer cityHeroID, Integer leadership);
	
	/**
	 * 根据城市英雄编号更新忠诚
	 * @param cityHeroID
	 * @param loylalty
	 */
	public void updateLoyaltyByCityHeroID(Integer cityHeroID,Integer loyalty);
	
	/**
	 * 根据城市英雄编号更新体力上限
	 * @param cityHeroID
	 * @param staminaMax
	 */
	public void updateStaminaMaxByCityHeroID(Integer cityHeroID,Integer staminaMax);
	
	/**
	 * 更新英雄装备
	 * @param params
	 */
	public void updateHeroEquipmentByCityHeroID(Map<String,Integer> params);
	
	/**
	 * 更新城市英雄
	 * @param cityHero
	 */
	public void updateCityHero(CityHero cityHero);

	/**
	 * 根据编号删除城市英雄
	 * @param cityHeroID
	 */
	public void deleteCityHeroByID(Integer cityHeroID);

	/**
	 * 根据城市英雄编号获得城市英雄名称
	 * @param cityHeroID
	 * @return
	 */
	public String getCityHeroNameByCityHeroID(Integer cityHeroID);
	
	/**
	 * 根据城市英雄编号获得未加点数
	 * @param cityHeroID
	 * @return
	 */
	public Integer getUnsetPointByCityHeroID(Integer cityHeroID);
	
	/**
	 * 根据城市编号获得城市英雄数量
	 * @param cityID
	 * @return
	 */
	public Integer getCityHeroNumByCityID(Integer cityID);
	
	/**
	 * 根据编号获得城市英雄
	 * @param cityHeroID
	 * @return
	 */
	public CityHero getCityHeroByID(Integer cityHeroID);
	
	/**
	 * 根据城市编号及城市英雄状态获得城市英雄列表
	 * @param cityID
	 * @param state
	 * @return
	 */
	public List<CityHero> getCityHeroListByCityIDAndState(Integer cityID,Integer state);
	
	/**
	 * 根据城市编号获得城市英雄列表
	 * @param cityID
	 * @return
	 */
	public List<CityHero> getCityHeroListByCityID(Integer cityID);
	
	/**
	 * 根据状态获得城市英雄列表
	 * @param state
	 * @return
	 */
	public List<CityHero> getCityHeroListByState(Integer state);
	
	/**
	 * 获得城市英雄列表
	 * @return
	 */
	public List<CityHero> getCityHeroList();
	
	/**
	 * 如果城市存在执政官返回true，否则返回false
	 * @param cityID
	 * @return
	 */
	public boolean existsCityOfficer(Integer cityID);
	
	/**
	 * 根据城市编号获得其中身上带有装备的英雄
	 * @param cityID
	 * @return
	 */
	public Integer getEquipedCityHeroNumByCityID(Integer cityID);

	/**
	 * 根据城市编号增加城市英雄的统御点数
	 * @param cityID
	 * @param point 要增加百分比
	 */
	public void addReinByCityIDWithMultiple(Integer cityID, Integer percent);

	public List<CityHero> getBugCityHeroList();

}