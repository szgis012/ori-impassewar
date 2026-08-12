package com.war.domain;

import java.io.Serializable;

public class CityBuilding implements Serializable {

	private static final long serialVersionUID = 4307215722622538061L;
	
	/** 城市建筑编号 */
	private Integer cityBuildingID;
	/** 城市编号 */
	private Integer cityID;
	/** 建筑编号 */
	private Integer buildingID;
	/** 地图位置 */
	private Integer position;
	/** 建筑等级 */
	private Integer level;
	/** 状态(1.正常 2.建造中 3.拆除中) */
	private Integer state;
	/** 建筑*/
	private Building building;
	/** 建筑排程(建造中 , 拆除中有效)*/
	private ProcessQueue processQueue;

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Integer getCityBuildingID() {
		return cityBuildingID;
	}

	public void setCityBuildingID(Integer cityBuildingID) {
		this.cityBuildingID = cityBuildingID;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(Integer buildingID) {
		this.buildingID = buildingID;
	}
	
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public ProcessQueue getProcessQueue() {
		return processQueue;
	}

	public void setProcessQueue(ProcessQueue processQueue) {
		this.processQueue = processQueue;
	}

}