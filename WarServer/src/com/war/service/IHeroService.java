package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.City;
import com.war.domain.CityCandidacyHero;
import com.war.domain.CityHero;
import com.war.domain.CityHeroExt;
import com.war.domain.CityHeroLevelupLog;
import com.war.domain.CityResource;
import com.war.domain.HeroSkill;
import com.war.domain.ProcessQueue;
import com.war.domain.Skill;

public interface IHeroService {

	/**
	 * 初始化技能Map
	 * @return
	 */
	public Map<Integer, Map<Integer, Skill>> initSkillsMap();
	
	/**
	 * 清空城市候选英雄列表
	 */
	public void cleanCityCandidacyList();
	
	/**
	 * 刷新城市候选英雄列表
	 * @param cityID
	 */
	public void refreshCityCandidacyHeroList(Integer cityID);
	
	/**
	 * 增加城市英雄体力
	 * @param cityHeroID
	 * @param addStamina
	 */
	public void addCityHeroStamina(Integer cityHeroID, Integer addStamina);
	
	/**
	 * 更新城市英雄体力
	 * @param cityHeroID
	 * @param stamina
	 */
	public void updateCityHeroStamina(Integer cityHeroID, Integer stamina);
	
	/**
	 * 处理批量增加城市英雄体力
	 */
	public void handleBatchAddCityHeroStamina();
	
	/**
	 * 处理城市英雄逃跑（忠诚小于20）
	 */
	public void handleCityHeroRunAway();
	
	/**
	 * 增加城市英雄最大技能数量
	 * @param cityHeroID
	 * @param num
	 */
	public void addCityHeroMaxSkillNum(Integer cityHeroID, Integer num);
	
	/**
	 * 获取城市候选英雄列表
	 * @param cityID
	 * @return
	 */
	public List<CityCandidacyHero> getCityCandidacyHeroList(Integer cityID);
	
	/**
	 * 获得空闲城市英雄列表
	 * @param cityID
	 * @return
	 */
	public List<CityHero> getFreeCityHeroList(Integer cityID);
	
	/**
	 * 获得城市英雄列表
	 * @param cityID
	 * @return
	 */
	public List<CityHero> getCityHeroList(Integer cityID);
	
	/**
	 * 根据城市英雄编号获得城市英雄名称
	 * @param cityHeroID
	 * @return
	 */
	public String getCityHeroNameByCityHeroID(Integer cityHeroID);
	
	/**
	 * 招募英雄
	 * @param cityCandidacyHeroID
	 */
	public void recruitHero(Integer cityCandidacyHeroID);
	
	/**
	 * 解雇英雄
	 * @param cityHeroID
	 */
	public void dismissHero(Integer cityHeroID);
	
	/**
	 * 更改英雄状态
	 * @param cityHeroID
	 * @param state
	 */
	public void changeHeroState(Integer cityHeroID,Integer state);
	
	/**
	 * 英雄改名
	 * @param cityHeroID
	 * @param name
	 */
	public void heroRename(Integer cityHeroID,String name);
	
	/**
	 * 获得英雄未加点数
	 * @param cityHeroID
	 * @return
	 */
	public Integer getHeroUnsetPoint(Integer cityHeroID);
	
	/**
	 * 更换英雄装备
	 * @param cityHeroID
	 * @param playerEquipmentID
	 */
	public void changeHeroEquipment(Integer cityHeroID,Integer playerEquipmentID);
	
	/**
	 * 卸下英雄装备
	 * @param playerID
	 * @param cityHeroID
	 * @param category
	 */
	public void offloadHeroEquipment(Integer playerID,Integer cityHeroID,Integer category);
	
	/**
	 * 英雄加点
	 * @param cityHeroID
	 * @param commandAdded
	 * @param defenseAdded
	 * @param mindAdded
	 * @param executivepowerAdded
	 */
	public void addHeroPoint(Integer cityHeroID,Integer commandAdded,Integer defenseAdded,Integer mindAdded,Integer executivepowerAdded);
	
	/**
	 * 英雄加经验
	 * @param cityHeroID
	 * @param exp
	 */
	public void addHeroExp(Integer cityHeroID,Long exp);
	
	/**
	 * 英雄升级
	 * @param cityHeroID
	 */
	public Integer heroLevelUp(Integer cityHeroID);
	
	/**
	 * 指挥官完美升级
	 * @param cityHeroID
	 */
	public Integer heroPerfectLevelUp(Integer cityHeroID);
	
	/**
	 * 任命城市执政官
	 * @param cityHeroID
	 */
	public void setCityOfficer(Integer cityHeroID);
	
	/**
	 * 取消设置城市执政官
	 * @param cityHeroID
	 */
	public void cancelCityOfficer(Integer cityHeroID);
	
	/**
	 * 训练城市英雄提高英雄领导力
	 * @param cityHeroID
	 * @param addLeadership
	 */
	public void trainingCityHeroIncreaseLeadership(Integer cityHeroID, Integer hours);
	
	/**
	 * 取消训练城市英雄
	 * @param cityHeroID
	 */
	public void cancelTrainingCityHero(Integer processQueueID);
	
	/**
	 * 处理英雄训练完成事件
	 * @param processQueue
	 */
	public void cityHeroTrainingFinished(ProcessQueue processQueue);
	
	/**
	 * 更改城市英雄领导力
	 * @param cityHeroID
	 * @param Leadership
	 */
	public void updateCityHeroLeadership(Integer cityHeroID, Integer leadership);
	
	/**
	 * 增加城市英雄忠诚
	 * @param cityHeroID
	 * @param addLoyalty
	 */
	public void addCityHeroLoyalty(Integer cityHeroID, Integer addLoyalty);
	
	/**
	 * 更改英雄忠诚
	 * @param cityHeroID
	 * @param loyalty
	 */
	public void updateCityHeroLoyalty(Integer cityHeroID,Integer loyalty);  
	
	/**
	 * 学习技能
	 * @param cityHeroID
	 * @param skillID
	 */
	public void studySkill(Integer cityHeroID,Integer skillID);
	
	/**
	 * 遗忘技能
	 * @param cityHeroID
	 * @param heroSkillID
	 */
	public void forgetSkill(Integer cityHeroID,Integer heroSkillID);
	
	/**
	 * 升级技能
	 * @param cityHeroID
	 * @param heroSkillID
	 */
	public void levelUpSkill(Integer cityHeroID,Integer heroSkillID);
	
	/**
	 * 更新英雄技能熟练度
	 * @param heroSkillID
	 * @param proficiency
	 */
	public void updateHeroSkillProficiency(Integer heroSkillID,Integer proficiency);
	
	/**
	 * 获得英雄技能
	 * @param heroSkillID
	 * @return
	 */
	public HeroSkill getHeroSkill(Integer heroSkillID);
	
	/**
	 * 获得等级为1的技能列表
	 * @return
	 */
	public List<Skill> getLevel1SkillList();

	/**
	 * 获得城市英雄技能列表
	 * @param cityHeroID
	 * @return
	 */
	public List<HeroSkill> getHeroSkillList(Integer cityHeroID);
	
	/**
	 * 获得技能列表
	 * @return
	 */
	public List<Skill> getSkillList();
	
	/**
	 * 根据城市英雄编号获得城市英雄(附带装备信息)
	 * @param cityHeroID
	 * @return
	 */
	public CityHero getCityHero(Integer cityHeroID);
	
	/**
	 * 增加城市执政官经验
	 */
	public void addCityOfficerExp();
	
	/**
	 * 重置英雄点数
	 * @param playerID
	 * @param cityHeroID
	 * @param command
	 * @param defense
	 * @param mind
	 * @param executivepower
	 */
	public void resetHeroPoint(Integer playerID, Integer cityHeroID, Integer command, Integer defense,Integer mind, Integer executivepower);
	
	/**
	 * 获得城市英雄星级
	 * @param cityHeroID
	 * @return
	 */
	public Integer getCityHeroStar(Integer cityHeroID);
	
	/**
	 * 强化城市英雄星级
	 * @param playerID
	 * @param cityHeroID
	 * @param upgradeLuckTreasureID: (强运符)TreasureConstant中661~662, 0 代表不用道具
	 * @param stimulateBloodTreasureID: (血激符)TreasureConstant中663~664, 0 代表不用道具
	 */
	public boolean strengthenCityHeroStar(Integer playerID, Integer cityHeroID, Integer upgradeLuckTreasureID, Integer stimulateBloodTreasureID);
	
	/**
	 * 提升军魂
	 * @param cityHeroID
	 */
	public void addMilitarySpirit(Integer cityHeroID);
	
	/**
	 * 获得城市英雄扩展信息
	 * @param cityID
	 */
	public CityHeroExt getCityHeroExtByCityHeroID(Integer cityHeroID) ;
	
	/**
	 * 根据城市英雄编号更改统御
	 * @param cityHeroID
	 */
	public void updateReinByCityHeroID(Integer cityHeroID, Integer rein);
	
	/**
	 * 如果城市存在执政官返回true，否则返回false
	 * @param cityID
	 * @return
	 */
	public boolean existsCityOfficer(Integer cityID);
	
	/**
	 * 获得身上有装备的影响数量
	 * @param cityID
	 * @return
	 */
	public Integer getEquipedCityHeroNum(Integer cityID);

	/**
	 * 删除城市英雄升级日志
	 * @param cityHeroID
	 */
	public void deleteCityHeroLevelupLog(Integer cityHeroID);

	/**
	 * 删除城市英雄升级日志
	 * @param cityHeroID
	 * @param level 凡是大于等于level的记录都将被删除
	 */
	public void deleteCityHeroLevelupLog(Integer cityHeroID, Integer level);
	
	/**
	 * 删除城市英雄升级日志
	 * @param cityHeroLevelupLogID
	 */
	public void deleteCityHeroLevelupLogByID(Integer cityHeroLevelupLogID);

	/**
	 * 更新城市英雄升级日志
	 * @param cityHeroLevelupLog
	 */
	public void updateCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog);
	
	/**
	 * 添加城市英雄升级日志
	 * @param cityHeroLevelupLog
	 */
	public void createCityHeroLevelupLog(CityHeroLevelupLog cityHeroLevelupLog);

	/**
	 * 获得城市英雄升级日志
	 * @param cityHeroID
	 * @param level 得到等于level的日志信息
	 */
	public CityHeroLevelupLog getCityHeroLevelupLog(Integer cityHeroID, Integer level);

	/**
	 * 获得城市英雄的最大经验
	 * @param level 城市英雄当前等级
	 * @return
	 */
	public Long getCityHeroExpMax(Integer level);

	/**
	 * 更新城市英雄
	 * @param cityHero
	 */
	public void updateCityHero(CityHero cityHero);

	/**
	 * 获得城市英雄训练进程列表信息
	 * @param cityID
	 * @return
	 */
	public List<ProcessQueue> getHeroTrainingProcessQueueList(Integer cityID);

	/**
	 * 更新城市英雄的军队加成扩展信息
	 * @param cityHeroID
	 * @param militaryAttackAdd
	 * @param militaryDefenseAdd
	 * @param militaryLifeAdd
	 */
	public void updateCityHeroMilitaryAdd(Integer cityHeroID,Integer militaryAttackAdd, Integer militaryDefenseAdd,Integer militaryLifeAdd);

	/**
	 * 获得城市英雄基础统御值
	 * @param quality 只能是1-3范围内，否则会抛出GameException异常
	 * @param level 只能大于0，否则会抛出GameException异常
	 * @return
	 */
	public Integer getCityHeroBasicRein(Integer quality, Integer level);

	/**
	 * 更新城市英雄扩展信息
	 * @param cityHeroExt
	 */
	public void updateCityHeroExt(CityHeroExt cityHeroExt);

	public List<CityHero> getBugCityHeroList();
	
}
