package com.war.domain;


import java.io.Serializable;


/**
 * 兵种依赖信息
 *
 * @author ghleed
 * @version 1.0
 */
public class ArmyDepend implements Serializable {

	private static final long serialVersionUID = -7149496106013187899L;
	
	/** 兵种依赖编号 */
	private Integer armyDependID;
	/** 兵种编号 */
	private Integer armyID;
	/** 军械编号 */
	private Integer ordnanceID;
	/** 数量 */
	private Integer num;

	public Integer getArmyDependID() {
		return armyDependID;
	}

	public void setArmyDependID(Integer armyDependID) {
		this.armyDependID = armyDependID;
	}
	public Integer getArmyID() {
		return armyID;
	}

	public void setArmyID(Integer armyID) {
		this.armyID = armyID;
	}
	public Integer getOrdnanceID() {
		return ordnanceID;
	}

	public void setOrdnanceID(Integer ordnanceID) {
		this.ordnanceID = ordnanceID;
	}
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}