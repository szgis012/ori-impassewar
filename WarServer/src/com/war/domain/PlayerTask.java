package com.war.domain;


import java.io.Serializable;

/**
 * 玩家任务信息
 *
 * @author ghleed
 * @version 1.0
 */
public class PlayerTask implements Serializable {

	private static final long serialVersionUID = -8307524295390772152L;
	
	/** 编号 */
	private Integer playerTaskID;
	/** 玩家编号 */
	private Integer playerID;
	/** 任务编号 */
	private Integer taskID;
	/** 任务类型 */
	private Integer taskType;
	/** 任务状态(0.未完成 1.已完成) */
	private Integer state;
	/** 标识(0.未提交 1.已提交) */
	private Integer flag;
	/** 任务*/
	private Task task;
	
	
	public Integer getPlayerTaskID() {
		return playerTaskID;
	}

	public void setPlayerTaskID(Integer playerTaskID) {
		this.playerTaskID = playerTaskID;
	}

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public Integer getTaskID() {
		return taskID;
	}

	public void setTaskID(Integer taskID) {
		this.taskID = taskID;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}