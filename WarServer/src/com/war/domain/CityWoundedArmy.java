package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class CityWoundedArmy implements Serializable {

	private static final long serialVersionUID = -90893995752840208L;
	
	/** 城市伤兵编号 */
	private Integer cityWoundedArmyID;
	/** 城市编号 */
	private Integer cityID;
	/** 兵种编号 */
	private Integer armyID;
	/** 数量 */
	private Integer num;
	/** 死亡时间 */
	private Date deathTime;
	/** 兵种 */
	private Army army;

	
	public Integer getCityWoundedArmyID() {
		return cityWoundedArmyID;
	}

	public void setCityWoundedArmyID(Integer cityWoundedArmyID) {
		this.cityWoundedArmyID = cityWoundedArmyID;
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

	public Date getDeathTime() {
		return deathTime;
	}

	public void setDeathTime(Date deathTime) {
		this.deathTime = deathTime;
	}

	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		this.army = army;
	}

}