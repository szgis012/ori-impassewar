package com.war.domain;

import java.io.Serializable;
import java.util.Map;

public class BattleArmy implements Serializable {

	private static final long serialVersionUID = 3096771414244923886L;
	
	/** 战斗士兵编号 */
	private Integer battleArmyID;
	/** 战斗编号 */
	private Integer battleID;
	/** 军队编号 */
	private Integer militaryID;
	/** 士兵势力(1.进攻方 2.防守方) */
	private Integer armyForce;
	/** 士兵索引 */
	private Integer armyIndex;
	/** 士兵编号 */
	private Integer armyID;
	/** 数量 */
	private Integer amount;
	/** X坐标 */
	private Integer posX;
	/** Y坐标 */
	private Integer posY;
	/** 是否移动 */
	private Integer haveMoved;
	/** 攻击类型(0.未操作 1.攻击 2.防御) */
	private Integer attackType;
	/** 士兵信息 */
	private Army army;
	/** 士兵受到的指挥官技能的持续效果 */
	private Map<Integer, Integer> skillMap;
	
	
	public Integer getBattleArmyID() {
		return battleArmyID;
	}

	public void setBattleArmyID(Integer battleArmyID) {
		this.battleArmyID = battleArmyID;
	}
	
	public Integer getBattleID() {
		return battleID;
	}

	public void setBattleID(Integer battleID) {
		this.battleID = battleID;
	}
	
	public Integer getMilitaryID() {
		return militaryID;
	}

	public void setMilitaryID(Integer militaryID) {
		this.militaryID = militaryID;
	}
	
	public Integer getArmyForce() {
		return armyForce;
	}

	public void setArmyForce(Integer armyForce) {
		this.armyForce = armyForce;
	}
	
	public Integer getArmyIndex() {
		return armyIndex;
	}

	public void setArmyIndex(Integer armyIndex) {
		this.armyIndex = armyIndex;
	}
	
	public Integer getArmyID() {
		return armyID;
	}

	public void setArmyID(Integer armyID) {
		this.armyID = armyID;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}
	
	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public Integer getHaveMoved() {
		return haveMoved;
	}

	public void setHaveMoved(Integer haveMoved) {
		this.haveMoved = haveMoved;
	}

	public Integer getAttackType() {
		return attackType;
	}

	public void setAttackType(Integer attackType) {
		this.attackType = attackType;
	}
	
	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		this.army = army;
	}

	public Map<Integer, Integer> getSkillMap() {
		return skillMap;
	}

	public void setSkillMap(Map<Integer, Integer> skillMap) {
		this.skillMap = skillMap;
	}

}