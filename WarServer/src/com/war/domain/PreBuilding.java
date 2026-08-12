package com.war.domain;

/**
 * 前置建筑对象
 * 
 * @author TopTong
 * @version 1.0
 */

public class PreBuilding {

	/** 建筑编号 */
	private Integer buildingID;
	/** 建筑名称 */
	private String buildingName;
	/** 等级 */
	private Integer level;
	
	
	public Integer getBuildingID() {
		return buildingID;
	}
	public void setBuildingID(Integer buildingID) {
		this.buildingID = buildingID;
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
