package com.war.domain;

import java.io.Serializable;

public class Skill implements Serializable {

	private static final long serialVersionUID = -2370288365961193865L;
	
	/** 技能编号 */
	private Integer skillID;
	/** 名称 */
	private String name;
	/** 等级 */
	private Integer level;
	/** 最高等级 */
	private Integer maxLevel;
	/** 图片 */
	private String image;
	/** 描述 */
	private String description;
	/** 影响生命 */
	private Integer life;
	/** 影响攻击 */
	private Integer attack;
	/** 影响防御 */
	private Integer defense;
	/** 影响速度 */
	private Integer speed;
	/** 影响攻击范围 */
	private Integer range;
	/** 持续回合 */
	private Integer lastRound;
	/** 消耗体力 */
	private Integer costStamina;
	/** 学习等级 */
	private Integer studyLevel;
	/** 学习需要金钱 */
	private Integer studyMoney;
	/** 学习需要熟练度 */
	private Integer studyProficiency;

	
	public Integer getSkillID() {
		return skillID;
	}

	public void setSkillID(Integer skillID) {
		this.skillID = skillID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}
	
	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	
	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	
	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}
	
	public Integer getLastRound() {
		return lastRound;
	}

	public void setLastRound(Integer lastRound) {
		this.lastRound = lastRound;
	}

	public Integer getCostStamina() {
		return costStamina;
	}

	public void setCostStamina(Integer costStamina) {
		this.costStamina = costStamina;
	}
	
	public Integer getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(Integer studyLevel) {
		this.studyLevel = studyLevel;
	}
	
	public Integer getStudyMoney() {
		return studyMoney;
	}

	public void setStudyMoney(Integer studyMoney) {
		this.studyMoney = studyMoney;
	}
	
	public Integer getStudyProficiency() {
		return studyProficiency;
	}

	public void setStudyProficiency(Integer studyProficiency) {
		this.studyProficiency = studyProficiency;
	}

}