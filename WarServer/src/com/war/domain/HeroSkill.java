package com.war.domain;

import java.io.Serializable;

public class HeroSkill implements Serializable {

	private static final long serialVersionUID = -6862780593911715813L;
	
	/** 英雄技能编号 */
	private Integer heroSkillID;
	/** 城市英雄编号 */
	private Integer cityHeroID;
	/** 技能编号 */
	private Integer skillID;
	/** 等级 */
	private Integer level;
	/** 熟练度 */
	private Integer proficiency;
	/** 技能对象 */
	private Skill skill;
	/** 下一等级技能对象 */
	private Skill nextLevelSkill;
	
	
	public Skill getNextLevelSkill() {
		return nextLevelSkill;
	}

	public void setNextLevelSkill(Skill nextLevelSkill) {
		this.nextLevelSkill = nextLevelSkill;
	}

	public Integer getHeroSkillID() {
		return heroSkillID;
	}

	public void setHeroSkillID(Integer heroSkillID) {
		this.heroSkillID = heroSkillID;
	}
	
	public Integer getCityHeroID() {
		return cityHeroID;
	}

	public void setCityHeroID(Integer cityHeroID) {
		this.cityHeroID = cityHeroID;
	}
	
	public Integer getSkillID() {
		return skillID;
	}

	public void setSkillID(Integer skillID) {
		this.skillID = skillID;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getProficiency() {
		return proficiency;
	}

	public void setProficiency(Integer proficiency) {
		this.proficiency = proficiency;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
}