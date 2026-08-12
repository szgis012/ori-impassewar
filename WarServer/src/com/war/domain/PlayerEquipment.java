package com.war.domain;

import java.io.Serializable;

public class PlayerEquipment implements Serializable {

	private static final long serialVersionUID = 8587037836636656854L;
	
	/** 玩家装备编号 */
	private Integer playerEquipmentID;
	/** 玩家编号 */
	private Integer playerID;
	/** 装备编号 */
	private Integer equipmentID;
	/** 装备对象 */
	private Equipment equipment;

	
	public Integer getPlayerEquipmentID() {
		return playerEquipmentID;
	}

	public void setPlayerEquipmentID(Integer playerEquipmentID) {
		this.playerEquipmentID = playerEquipmentID;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public Integer getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(Integer equipmentID) {
		this.equipmentID = equipmentID;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
}