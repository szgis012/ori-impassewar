package com.war.domain;

import java.io.Serializable;

public class GuildExt implements Serializable {

	private static final long serialVersionUID = 48821335394449832L;
	
	/** 军团编号 */
	private Integer guildID;
	/** 军队攻击加成 */
	private Integer militaryAttackAdd;
	/** 军队速度加成 */
	private Integer militarySpeedAdd;
	/** 军队攻击削弱 */
	private Integer militaryAttackMinus;
	/** 士兵生命加成 */
	private Integer armyLifeAdd;
	/** 士兵攻击削弱 */
	private Integer armyAttackMinus;
	/** 士兵范围削弱 */
	private Integer armyRangeMinus;
	/** 士兵速度削弱 */
	private Integer armySpeedMinus;
	/** 车辆生命加成 */
	private Integer truckLifeAdd;
	/** 车辆攻击削弱 */
	private Integer truckAttackMinus;
	/** 车辆范围削弱 */
	private Integer truckRangeMinus;
	/** 车辆速度削弱 */
	private Integer truckSpeedMinus;
	/** 飞机生命加成 */
	private Integer airplaneLifeAdd;
	/** 飞机攻击削弱 */
	private Integer airplaneAttackMinus;
	/** 飞机范围削弱 */
	private Integer airplaneRangeMinus;
	/** 飞机速度削弱 */
	private Integer airplaneSpeedMinus;

	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}

	public Integer getMilitaryAttackAdd() {
		return militaryAttackAdd;
	}

	public void setMilitaryAttackAdd(Integer militaryAttackAdd) {
		this.militaryAttackAdd = militaryAttackAdd;
	}

	public Integer getMilitarySpeedAdd() {
		return militarySpeedAdd;
	}

	public void setMilitarySpeedAdd(Integer militarySpeedAdd) {
		this.militarySpeedAdd = militarySpeedAdd;
	}

	public Integer getMilitaryAttackMinus() {
		return militaryAttackMinus;
	}

	public void setMilitaryAttackMinus(Integer militaryAttackMinus) {
		this.militaryAttackMinus = militaryAttackMinus;
	}

	public Integer getArmyLifeAdd() {
		return armyLifeAdd;
	}

	public void setArmyLifeAdd(Integer armyLifeAdd) {
		this.armyLifeAdd = armyLifeAdd;
	}

	public Integer getArmyAttackMinus() {
		return armyAttackMinus;
	}

	public void setArmyAttackMinus(Integer armyAttackMinus) {
		this.armyAttackMinus = armyAttackMinus;
	}

	public Integer getArmyRangeMinus() {
		return armyRangeMinus;
	}

	public void setArmyRangeMinus(Integer armyRangeMinus) {
		this.armyRangeMinus = armyRangeMinus;
	}

	public Integer getArmySpeedMinus() {
		return armySpeedMinus;
	}

	public void setArmySpeedMinus(Integer armySpeedMinus) {
		this.armySpeedMinus = armySpeedMinus;
	}

	public Integer getTruckLifeAdd() {
		return truckLifeAdd;
	}

	public void setTruckLifeAdd(Integer truckLifeAdd) {
		this.truckLifeAdd = truckLifeAdd;
	}

	public Integer getTruckAttackMinus() {
		return truckAttackMinus;
	}

	public void setTruckAttackMinus(Integer truckAttackMinus) {
		this.truckAttackMinus = truckAttackMinus;
	}

	public Integer getTruckRangeMinus() {
		return truckRangeMinus;
	}

	public void setTruckRangeMinus(Integer truckRangeMinus) {
		this.truckRangeMinus = truckRangeMinus;
	}

	public Integer getTruckSpeedMinus() {
		return truckSpeedMinus;
	}

	public void setTruckSpeedMinus(Integer truckSpeedMinus) {
		this.truckSpeedMinus = truckSpeedMinus;
	}

	public Integer getAirplaneLifeAdd() {
		return airplaneLifeAdd;
	}

	public void setAirplaneLifeAdd(Integer airplaneLifeAdd) {
		this.airplaneLifeAdd = airplaneLifeAdd;
	}

	public Integer getAirplaneAttackMinus() {
		return airplaneAttackMinus;
	}

	public void setAirplaneAttackMinus(Integer airplaneAttackMinus) {
		this.airplaneAttackMinus = airplaneAttackMinus;
	}

	public Integer getAirplaneRangeMinus() {
		return airplaneRangeMinus;
	}

	public void setAirplaneRangeMinus(Integer airplaneRangeMinus) {
		this.airplaneRangeMinus = airplaneRangeMinus;
	}

	public Integer getAirplaneSpeedMinus() {
		return airplaneSpeedMinus;
	}

	public void setAirplaneSpeedMinus(Integer airplaneSpeedMinus) {
		this.airplaneSpeedMinus = airplaneSpeedMinus;
	}


	
}
