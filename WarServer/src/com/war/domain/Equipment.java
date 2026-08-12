package com.war.domain;

import java.io.Serializable;

public class Equipment implements Serializable {

	private static final long serialVersionUID = 3268433121769610391L;
	
	/** 装备编号 */
	private Integer equipmentID;
	/** 名称 */
	private String name;
	/** 图片 */
	private String image;
	/** 指挥 */
	private Integer command;
	/** 防护 */
	private Integer defense;
	/** 思维 */
	private Integer mind;
	/** 行政 */
	private Integer executivepower;
	/** 所需等级 */
	private Integer requiredLevel;
	/** 种类(1.肩章 2.帽子 3.衣服 4.鞋子 5.武器) */
	private Integer category;

	
	public Integer getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(Integer equipmentID) {
		this.equipmentID = equipmentID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
	
	public Integer getRequiredLevel() {
		return requiredLevel;
	}

	public void setRequiredLevel(Integer requiredLevel) {
		this.requiredLevel = requiredLevel;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

}