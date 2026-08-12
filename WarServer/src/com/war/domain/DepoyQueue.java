package com.war.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 出兵队列
 *
 * @author ghleed
 * @version 1.0
 */
public class DepoyQueue implements Serializable {

	private static final long serialVersionUID = 51929611060266643L;
	
	/** 队列编号 */
	private Integer depoyQueueID;
	/** 城市编号 */
	private Integer cityID;
	/** 出征部队编号 */
	private Integer cityMilitaryID;
	/** 目标地点 */
	private Integer mapID;
	/** 类型 (参看DepoyTypeConstant: 1侦查 2.攻击 3.派遣 9.返回) */
	private Integer type;
	/** 备注信息(JSON字符串) */
	private String remark;
	/** 到达目的地的时间 */
	private Date finishTime;
	/** 城市军队对象 */
	private CityMilitary cityMilitary;
	/** 地图对象 */
	private Map map;

	public Integer getDepoyQueueID() {
		return depoyQueueID;
	}

	public void setDepoyQueueID(Integer depoyQueueID) {
		this.depoyQueueID = depoyQueueID;
	}
	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getCityMilitaryID() {
		return cityMilitaryID;
	}

	public void setCityMilitaryID(Integer cityMilitaryID) {
		this.cityMilitaryID = cityMilitaryID;
	}
	
	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public CityMilitary getCityMilitary() {
		return cityMilitary;
	}

	public void setCityMilitary(CityMilitary cityMilitary) {
		this.cityMilitary = cityMilitary;
	}
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}