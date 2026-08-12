package com.war.domain;

import java.io.Serializable;

/**
 * 城市军队信息
 * 
 * @author ghleed
 * @version 1.0
 */
public class CityArmy implements Serializable {

	private static final long serialVersionUID = 5910286839289766589L;

	/** 城市兵力编号 */
	private Integer cityArmyID;
	/** 城市编号 */
	private Integer cityID;
	/** 兵种编号 */
	private Integer armyID;
	/** 兵的数量 */
	private Integer num;

	
	public Integer getCityArmyID() {
		return cityArmyID;
	}

	public void setCityArmyID(Integer cityArmyID) {
		this.cityArmyID = cityArmyID;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public Integer getArmyID() {
		return armyID;
	}

	public void setArmyID(Integer armyID) {
		this.armyID = armyID;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}