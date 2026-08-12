package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class Colonization implements Serializable {

	private static final long serialVersionUID = 5434429916846693229L;
	
	/** 殖民编号 */
	private Integer colonizationID;
	/** 城市编号 */
	private Integer cityID;
	/** 目标城市编号 */
	private Integer targetCityID;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 今日是否已经征收(0.未征收 1.已征收) */
	private Integer haveImposed;
	/** 目标城市信息 */
	private CityInfo targetCityInfo;

	
	public Integer getColonizationID() {
		return colonizationID;
	}

	public void setColonizationID(Integer colonizationID) {
		this.colonizationID = colonizationID;
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
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getHaveImposed() {
		return haveImposed;
	}

	public void setHaveImposed(Integer haveImposed) {
		this.haveImposed = haveImposed;
	}
	
	public CityInfo getTargetCityInfo() {
		return targetCityInfo;
	}

	public void setTargetCityInfo(CityInfo targetCityInfo) {
		this.targetCityInfo = targetCityInfo;
	}
	
}