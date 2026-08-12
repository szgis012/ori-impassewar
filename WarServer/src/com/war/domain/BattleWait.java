package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class BattleWait implements Serializable {

	private static final long serialVersionUID = 1455233336179177387L;

	/** 战斗等待编号 */
	private Integer battleWaitID;
	/** 地图编号 */
	private Integer mapID;
	/** 进攻方城市军队编号 */
	private Integer attackerCityMilitaryID;
	/** 防守方城市军队编号 */
	private Integer defenderCityMilitaryID;
	/** 开始时间 */
	private Date startTime;

	public Integer getBattleWaitID() {
		return battleWaitID;
	}

	public void setBattleWaitID(Integer battleWaitID) {
		this.battleWaitID = battleWaitID;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getAttackerCityMilitaryID() {
		return attackerCityMilitaryID;
	}

	public void setAttackerCityMilitaryID(Integer attackerCityMilitaryID) {
		this.attackerCityMilitaryID = attackerCityMilitaryID;
	}

	public Integer getDefenderCityMilitaryID() {
		return defenderCityMilitaryID;
	}

	public void setDefenderCityMilitaryID(Integer defenderCityMilitaryID) {
		this.defenderCityMilitaryID = defenderCityMilitaryID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
