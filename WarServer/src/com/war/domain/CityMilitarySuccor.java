package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class CityMilitarySuccor implements Serializable {

	private static final long serialVersionUID = 1931297507345809949L;

	/** 城市军队支援编号 */
	private Integer cityMilitarySuccorID;
	/** 城市编号 */
	private Integer cityID;
	/** 被支援城市编号 */
	private Integer targetCityID;
	/** 军队编号 */
	private Integer cityMilitaryID;
	/** 战斗顺序(0.不战斗) */
	private Integer battleOrder;
	/** 到达时间 */
	private Date arriveTime;
	/** 派遣城市信息 */
	private City dispatchCity;
	/** 派遣军队信息 */
	private CityMilitary dispatchMilitary;
	

	public Integer getCityMilitarySuccorID() {
		return cityMilitarySuccorID;
	}

	public void setCityMilitarySuccorID(Integer cityMilitarySuccorID) {
		this.cityMilitarySuccorID = cityMilitarySuccorID;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public Integer getTargetCityID() {
		return targetCityID;
	}

	public void setTargetCityID(Integer targetCityID) {
		this.targetCityID = targetCityID;
	}

	public Integer getCityMilitaryID() {
		return cityMilitaryID;
	}

	public void setCityMilitaryID(Integer cityMilitaryID) {
		this.cityMilitaryID = cityMilitaryID;
	}

	public Integer getBattleOrder() {
		return battleOrder;
	}

	public void setBattleOrder(Integer battleOrder) {
		this.battleOrder = battleOrder;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public City getDispatchCity() {
		return dispatchCity;
	}

	public void setDispatchCity(City dispatchCity) {
		this.dispatchCity = dispatchCity;
	}

	public CityMilitary getDispatchMilitary() {
		return dispatchMilitary;
	}

	public void setDispatchMilitary(CityMilitary dispatchMilitary) {
		this.dispatchMilitary = dispatchMilitary;
	}

}
