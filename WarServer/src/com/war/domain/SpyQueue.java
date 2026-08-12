package com.war.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 侦察队列
 *
 * @author TopTong
 * @version 1.0
 */
public class SpyQueue implements Serializable {

	private static final long serialVersionUID = 8396139291482267898L;
	
	/** 编号 */
	private Integer spyQueueID;
	/** 出发城市编号 */
	private Integer cityID;
	/** 地图编号 */
	private Integer mapID;
	/** 侦察兵的数量 */
	private Integer num;
	/** 侦察状态。SpyQueueStateConstant中定义*/
	private Integer state;
	/** 结束时间 */
	private Date finishTime;
	/** 地图对象 */
	private Map map; 

	public Integer getSpyQueueID() {
		return spyQueueID;
	}

	public void setSpyQueueID(Integer spyQueueID) {
		this.spyQueueID = spyQueueID;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}