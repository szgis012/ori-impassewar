package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class CityExt implements Serializable {

	private static final long serialVersionUID = 4602607787846859682L;
	
	/** 城市编号 */
	private Integer cityID;
	/** 科技影响士兵攻击 */
	private Integer techArmyAttack;
	/** 科技影响士兵防御 */
	private Integer techArmyDefense;
	/** 科技影响士兵速度 */
	private Integer techArmySpeed;
	/** 科技影响士兵攻击范围 */
	private Integer techArmyRange;
	/** 科技影响车辆攻击 */
	private Integer techTruckAttack;
	/** 科技影响车辆防御 */
	private Integer techTruckDefense;
	/** 科技影响车辆速度 */
	private Integer techTruckSpeed;
	/** 科技影响车辆攻击范围 */
	private Integer techTruckRange;
	/** 科技影响飞机攻击 */
	private Integer techAirplaneAttack;
	/** 科技影响飞机防御 */
	private Integer techAirplaneDefense;
	/** 科技影响飞机速度 */
	private Integer techAirplaneSpeed;
	/** 科技影响飞机攻击范围 */
	private Integer techAirplaneRange;
	/** 科技增加负重 */
	private Integer techCarryAdd;
	/** 科技增加城市防御攻击 */
	private Integer techDefenseAttackAdd;
	/** 科技伤兵概率 */
	private Integer techWoundedArmyRate;
	/** 科技保护资源比例 */
	private Integer techProtectResourcePercent;
	/** 最后治理时间 */
	private Date lastManageTime;

	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getTechArmyAttack() {
		return techArmyAttack;
	}

	public void setTechArmyAttack(Integer techArmyAttack) {
		this.techArmyAttack = techArmyAttack;
	}
	
	public Integer getTechArmyDefense() {
		return techArmyDefense;
	}

	public void setTechArmyDefense(Integer techArmyDefense) {
		this.techArmyDefense = techArmyDefense;
	}
	
	public Integer getTechArmySpeed() {
		return techArmySpeed;
	}

	public void setTechArmySpeed(Integer techArmySpeed) {
		this.techArmySpeed = techArmySpeed;
	}
	
	public Integer getTechArmyRange() {
		return techArmyRange;
	}

	public void setTechArmyRange(Integer techArmyRange) {
		this.techArmyRange = techArmyRange;
	}
	
	public Integer getTechTruckAttack() {
		return techTruckAttack;
	}

	public void setTechTruckAttack(Integer techTruckAttack) {
		this.techTruckAttack = techTruckAttack;
	}
	
	public Integer getTechTruckDefense() {
		return techTruckDefense;
	}

	public void setTechTruckDefense(Integer techTruckDefense) {
		this.techTruckDefense = techTruckDefense;
	}
	
	public Integer getTechTruckSpeed() {
		return techTruckSpeed;
	}

	public void setTechTruckSpeed(Integer techTruckSpeed) {
		this.techTruckSpeed = techTruckSpeed;
	}
	
	public Integer getTechTruckRange() {
		return techTruckRange;
	}

	public void setTechTruckRange(Integer techTruckRange) {
		this.techTruckRange = techTruckRange;
	}
	
	public Integer getTechAirplaneAttack() {
		return techAirplaneAttack;
	}

	public void setTechAirplaneAttack(Integer techAirplaneAttack) {
		this.techAirplaneAttack = techAirplaneAttack;
	}
	
	public Integer getTechAirplaneDefense() {
		return techAirplaneDefense;
	}

	public void setTechAirplaneDefense(Integer techAirplaneDefense) {
		this.techAirplaneDefense = techAirplaneDefense;
	}
	
	public Integer getTechAirplaneSpeed() {
		return techAirplaneSpeed;
	}

	public void setTechAirplaneSpeed(Integer techAirplaneSpeed) {
		this.techAirplaneSpeed = techAirplaneSpeed;
	}
	
	public Integer getTechAirplaneRange() {
		return techAirplaneRange;
	}

	public void setTechAirplaneRange(Integer techAirplaneRange) {
		this.techAirplaneRange = techAirplaneRange;
	}
	
	public Integer getTechCarryAdd() {
		return techCarryAdd;
	}

	public void setTechCarryAdd(Integer techCarryAdd) {
		this.techCarryAdd = techCarryAdd;
	}
	
	public Integer getTechDefenseAttackAdd() {
		return techDefenseAttackAdd;
	}
	            
	public void setTechDefenseAttackAdd(Integer techDefenseAttackAdd) {
		this.techDefenseAttackAdd = techDefenseAttackAdd;
	}
	
	public Integer getTechWoundedArmyRate() {
		return techWoundedArmyRate;
	}

	public void setTechWoundedArmyRate(Integer techWoundedArmyRate) {
		this.techWoundedArmyRate = techWoundedArmyRate;
	}
	
	public Integer getTechProtectResourcePercent() {
		return techProtectResourcePercent;
	}

	public void setTechProtectResourcePercent(Integer techProtectResourcePercent) {
		this.techProtectResourcePercent = techProtectResourcePercent;
	}

	public Date getLastManageTime() {
		return lastManageTime;
	}

	public void setLastManageTime(Date lastManageTime) {
		this.lastManageTime = lastManageTime;
	}
}