package com.war.domain;


import java.io.Serializable;

/**
 * 任务
 *
 * @author ghleed
 * @version 1.0
 */
public class Task implements Serializable {

	private static final long serialVersionUID = 6503684761123662994L;
	
	/** 任务编号 */
	private Integer taskID;
	/** 任务名称 */
	private String name;
	/** 任务描述 */
	private String description;
	/** 任务指南 */
	private String guide;
	/** 任务目标 */
	private String target;
	/** 任务类别 */
	private Integer type;
	/** 奖励信息*/
	private String reward;
	/** 触发的任务列表。以逗号(,)分割的任务编号列表。如1000,2000  */
	private String triggerTaskList;
	/** 任务目标脚本执行后的得到的任务目标信息 */
	private Object targetInfo;
	
	public Integer getTaskID() {
		return taskID;
	}

	public void setTaskID(Integer taskID) {
		this.taskID = taskID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTriggerTaskList() {
		return triggerTaskList;
	}

	public void setTriggerTaskList(String triggerTaskList) {
		this.triggerTaskList = triggerTaskList;
	}

	public Object getTargetInfo() {
		return targetInfo;
	}

	public void setTargetInfo(Object targetInfo) {
		this.targetInfo = targetInfo;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

}
