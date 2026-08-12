package com.war.domain;


import java.io.Serializable;

/**
 * 城市防御信息
 *
 * @author ghleed
 * @version 1.0
 */
public class CityDefense implements Serializable {
	
	private static final long serialVersionUID = 5674872058936569393L;
	
	/** 城市防御编号 */
	private Integer cityDefenseID;
	/** 城市编号 */
	private Integer cityID;
	/** 城防编号 */
	private Integer defenseID;
	/** 数量 */
	private Integer num;
	
	/** 城市防御 */
	private Defense defense;

	public Integer getCityDefenseID() {
		return cityDefenseID;
	}

	public void setCityDefenseID(Integer cityDefenseID) {
		this.cityDefenseID = cityDefenseID;
	}
	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getDefenseID() {
		return defenseID;
	}

	public void setDefenseID(Integer defenseID) {
		this.defenseID = defenseID;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Defense getDefense() {
		return defense;
	}

	public void setDefense(Defense defense) {
		this.defense = defense;
	}

}