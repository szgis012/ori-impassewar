package com.war.dao;

import java.sql.SQLException;
import java.util.List;

import com.war.domain.CityCandidacyHero;

public interface ICityCandidacyHeroDAO {

	/**
	 * 创建城市候选英雄
	 * @param cityCandidacyHero
	 * @return
	 */
	public Integer createCityCandidacyHero(CityCandidacyHero cityCandidacyHero);
	
	/**
	 * 创建多个城市候选英雄
	 * @param cityCandidacyHeroArray 城市候选英雄
	 * @throws SQLException
	 */
	public void createCityCandidacyHeroArray(CityCandidacyHero[] cityCandidacyHeroArray) throws SQLException;

	/**
	 * 更新城市候选英雄状态
	 * @param cityCandidacyHeroID
	 * @param state
	 */
	public void updateCityCandidacyHeroState(Integer cityCandidacyHeroID,Integer state);
	
	/**
	 * 更新城市候选英雄
	 * @param cityCandidacyHero
	 */
	public void updateCityCandidacyHero(CityCandidacyHero cityCandidacyHero);

	/**
	 * 根据编号删除城市候选英雄
	 * @param cityCandidacyHeroID
	 */
	public void deleteCityCandidacyHeroByID(Integer cityCandidacyHeroID);
	
	/**
	 * 根据城市编号删除城市候选英雄列表
	 * @param cityID
	 */
	public void deleteCityCandidacyHeroListByCityID(Integer cityID);
	
	/**
	 * 删除城市候选英雄列表
	 */
	public void deleteCityCandidacyHeroList();

	/**
	 * 获得城市候选英雄数量
	 * @param cityID
	 * @return
	 */
	public Integer getCityCandidacyHeroNum(Integer cityID);
	
	/**
	 * 根据编号获得城市候选英雄
	 * @param cityCandidacyHeroID
	 * @return
	 */
	public CityCandidacyHero getCityCandidacyHeroByID(Integer cityCandidacyHeroID);

	/**
	 * 根据城市编号获得城市候选英雄列表
	 * @param cityID
	 * @return
	 */
	public List<CityCandidacyHero> getCityCandidacyHeroListByCityID(Integer cityID);
	
	/**
	 * 获得城市候选英雄列表
	 * @return
	 */
	public List<CityCandidacyHero> getCityCandidacyHeroList();

}