package com.war.domain;


import java.io.Serializable;
import java.util.Date;


/**
 * 宝物效果持续时间队列
 *
 * @author ghleed
 * @version 1.0
 */
public class TreasureQueue implements Serializable {
	
	private static final long serialVersionUID = 6428428441513400652L;
	
	/** 编号 */
	private Integer treasureQueueID;
	/** 目标编号 */
	private Integer targetID;
	/** 宝物类别，同宝物表定义 */
	private Integer category;
	/** 宝物类型，同宝物表定义 */
	private Integer type;
	/** 宝物效果结束时间 */
	private Date finishTime;

	public Integer getTreasureQueueID() {
		return treasureQueueID;
	}

	public void setTreasureQueueID(Integer treasureQueueID) {
		this.treasureQueueID = treasureQueueID;
	}
	
	public Integer getTargetID() {
		return targetID;
	}

	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
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
	

}