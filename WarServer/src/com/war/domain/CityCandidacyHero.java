package com.war.domain;

import java.io.Serializable;

public class CityCandidacyHero implements Serializable {

	private static final long serialVersionUID = -4976347188910174269L;
	
	/** 城市候选英雄编号 */
	private Integer cityCandidacyHeroID;
	/** 城市编号 */
	private Integer cityID;
	/** 名称 */
	private String name;
	/** 性别(1.男 2.女) */
	private Integer gender;
	/** 头像 */
	private String head;
	/** 等级 */
	private Integer level;
	/** 指挥 */
	private Integer command;
	/** 防护 */
	private Integer defense;
	/** 思维 */
	private Integer mind;
	/** 行政 */
	private Integer executivepower;
	/** 军魂 */
	private Integer militarySpirit;
	/** 状态(1.正常 2.被招募) */
	private Integer state;

	
	public Integer getCityCandidacyHeroID() {
		return cityCandidacyHeroID;
	}

	public void setCityCandidacyHeroID(Integer cityCandidacyHeroID) {
		this.cityCandidacyHeroID = cityCandidacyHeroID;
	}
	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getCommand() {
		return command;
	}

	public void setCommand(Integer command) {
		this.command = command;
	}
	
	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	
	public Integer getMind() {
		return mind;
	}

	public void setMind(Integer mind) {
		this.mind = mind;
	}
	
	public Integer getExecutivepower() {
		return executivepower;
	}

	public void setExecutivepower(Integer executivepower) {
		this.executivepower = executivepower;
	}
	
	public Integer getMilitarySpirit() {
		return militarySpirit;
	}

	public void setMilitarySpirit(Integer militarySpirit) {
		this.militarySpirit = militarySpirit;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}