package com.war.domain;

import java.io.Serializable;

public class BattleQueue implements Serializable {

	private static final long serialVersionUID = -4296049164760504529L;

	/** 战斗队列编号 */
	private Integer battleQueueID;
	/** 城市编号 */
	private Integer mapID;
	/** 城市军队编号 */
	private Integer cityMilitaryID;
	/** 顺序 */
	private Integer order;

	public Integer getBattleQueueID() {
		return battleQueueID;
	}

	public void setBattleQueueID(Integer battleQueueID) {
		this.battleQueueID = battleQueueID;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getCityMilitaryID() {
		return cityMilitaryID;
	}

	public void setCityMilitaryID(Integer cityMilitaryID) {
		this.cityMilitaryID = cityMilitaryID;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
