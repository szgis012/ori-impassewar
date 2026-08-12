package com.war.domain;

import java.io.Serializable;

/**
 * 军队兵力信息
 * 
 * @author TopTong
 * @version 1.0
 */
public class MilitaryArmy implements Serializable {

	private static final long serialVersionUID = 4870752868832320958L;
	
	/** 兵种编号 */
	private Integer armyID;
	/** 数量 */
	private Integer amount;
	/** 战场X坐标 */
	private Integer posX;
	/** 战场Y坐标 */
	private Integer posY;
	/** 是否移动 */
	private Boolean haveMoved;
	/** 攻击类型(0.未操作 1.攻击 2.防御) */
	private Integer attackType;
	/** 兵种信息 */
	private Army army;
	
	
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
	public Boolean getHaveMoved() {
		return haveMoved;
	}
	public void setHaveMoved(Boolean haveMoved) {
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
	
}
