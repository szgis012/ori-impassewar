package com.war.domain;

import java.io.Serializable;

public class CityHeroLevelupLog implements Serializable {

	private static final long serialVersionUID = 3258771810943521237L;
	
	/** 英雄升级日志编号 */
	private Integer cityHeroLevelupLogID;
	/** 城市英雄编号 */
	private Integer cityHeroID;
	/** 等级 */
	private Integer level;
	/** 增加指挥 */
	private Integer addCommand;
	/** 增加防护 */
	private Integer addDefense;
	/** 增加思维 */
	private Integer addMind;
	/** 增加执行力 */
	private Integer addExecutivepower;

	public Integer getCityHeroLevelupLogID() {
		return cityHeroLevelupLogID;
	}

	public void setCityHeroLevelupLogID(Integer cityHeroLevelupLogID) {
		this.cityHeroLevelupLogID = cityHeroLevelupLogID;
	}

	public Integer getCityHeroID() {
		return cityHeroID;
	}

	public void setCityHeroID(Integer cityHeroID) {
		this.cityHeroID = cityHeroID;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(Integer addCommand) {
		this.addCommand = addCommand;
	}

	public Integer getAddDefense() {
		return addDefense;
	}

	public void setAddDefense(Integer addDefense) {
		this.addDefense = addDefense;
	}

	public Integer getAddMind() {
		return addMind;
	}

	public void setAddMind(Integer addMind) {
		this.addMind = addMind;
	}

	public Integer getAddExecutivepower() {
		return addExecutivepower;
	}

	public void setAddExecutivepower(Integer addExecutivepower) {
		this.addExecutivepower = addExecutivepower;
	}


}
