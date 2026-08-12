package com.war.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 招募市民，士兵或者生产武器等使用的进程表
 *
 * @author ghleed
 * @version 1.0
 */
public class ProductionQueue implements Serializable {
	
	private static final long serialVersionUID = -230253249458811506L;
	
	/** 队列编号 */
	private Integer productionQueueID;
	/** 城市编号 */
	private Integer cityID;
	/** 目标编号(城市对应关系表主键) */
	private Integer targetID;
	/** 类型(1.招募市民 ...) */
	private Integer type;
	/** 数量 */
	private Integer amount;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date finishTime;

	
	public Integer getProductionQueueID() {
		return productionQueueID;
	}

	public void setProductionQueueID(Integer productionQueueID) {
		this.productionQueueID = productionQueueID;
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
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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