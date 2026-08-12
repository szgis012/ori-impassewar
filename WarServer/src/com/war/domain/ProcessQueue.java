package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class ProcessQueue implements Serializable {

	private static final long serialVersionUID = -4934245839069305531L;
	
	/** 进程队列编号 */
	private Integer processQueueID;
	/** 城市编号 */
	private Integer cityID;
	/** 目标编号 */
	private Integer targetID;
	/** 类型(1.建筑建造/升级 2.拆除建筑 3.科技升级4.军团科技研究 5.指挥官训练) */
	private Integer type;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date finishTime;

	public Integer getProcessQueueID() {
		return processQueueID;
	}

	public void setProcessQueueID(Integer processQueueID) {
		this.processQueueID = processQueueID;
	}
	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getTargetID() {
		return targetID;
	}

	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}