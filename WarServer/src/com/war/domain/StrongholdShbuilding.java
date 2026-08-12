package com.war.domain;


import java.io.Serializable;

/**
 * 要塞中的建筑信息
 *
 * @author ghleed
 * @version 1.0
 */
public class StrongholdShbuilding implements Serializable {

	private static final long serialVersionUID = 4585643651326540490L;
	
	/** 编号 */
	private Integer shShbuildingID;
	/** 要塞编号 */
	private Integer strongholdID;
	/** 要塞建筑编号 */
	private Integer shbuildingID;
	/** 地图位置 */
	private Integer position;
	/** 建筑等级 */
	private Integer level;
	/** 状态(1.正常 2.建造中 3.拆除中) */
	private Integer state;
	/** 对应的建筑信息*/
	private Shbuilding building;

	public Integer getShShbuildingID() {
		return shShbuildingID;
	}

	public void setShShbuildingID(Integer shShbuildingID) {
		this.shShbuildingID = shShbuildingID;
	}
	public Integer getStrongholdID() {
		return strongholdID;
	}

	public void setStrongholdID(Integer strongholdID) {
		this.strongholdID = strongholdID;
	}
	public Integer getShbuildingID() {
		return shbuildingID;
	}

	public void setShbuildingID(Integer shbuildingID) {
		this.shbuildingID = shbuildingID;
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

	public Shbuilding getBuilding() {
		return building;
	}

	public void setBuilding(Shbuilding building) {
		this.building = building;
	}

}