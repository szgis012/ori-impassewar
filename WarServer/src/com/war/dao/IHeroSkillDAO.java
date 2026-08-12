package com.war.dao;

import java.util.List;

import com.war.domain.HeroSkill;

/**
 * 英雄技能DAO接口
 * 
 * @author TopTong
 * @version 1.0
 */
public interface IHeroSkillDAO {

	/**
	 * 创建英雄技能
	 * @param heroSkill
	 * @return
	 */
	public Integer createHeroSkill(HeroSkill heroSkill);

	/**
	 * 更新英雄技能熟练度
	 * @param heroSkillID
	 * @param proficiency
	 */
	public void updateHeroSkillProficiency(Integer heroSkillID,Integer proficiency);
	
	/**
	 * 更新英雄技能
	 * @param heroSkill
	 */
	public void updateHeroSkill(HeroSkill heroSkill);

	/**
	 * 根据编号删除英雄技能
	 * @param heroSkillID
	 */
	public void deleteHeroSkillByID(Integer heroSkillID);
	
	/**
	 * 根据城市英雄编号删除技能英雄列表
	 * @param cityHeroID
	 */
	public void deleteHeroSkillListByCityHeroID(Integer cityHeroID);

	/**
	 * 根据编号获得英雄技能
	 * @param heroSkillID
	 * @return
	 */
	public HeroSkill getHeroSkillByID(Integer heroSkillID);

	/**
	 * 根据城市英雄编号及技能编号获得英雄技能
	 * @param cityHeroID
	 * @param skillID
	 * @return
	 */
	public HeroSkill getHeroSkillByCityHeroIDAndSkillID(Integer cityHeroID, Integer skillID);
	
	/**
	 * 根据城市英雄编号获得英雄技能列表
	 * @param cityHeroID
	 * @return
	 */
	public List<HeroSkill> getHeroSkillListByCityHeroID(Integer cityHeroID);
	
	/**
	 * 获得英雄技能列表
	 * @return
	 */
	public List<HeroSkill> getHeroSkillList();

}