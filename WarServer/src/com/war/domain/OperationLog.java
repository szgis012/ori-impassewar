package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class OperationLog implements Serializable {

	private static final long serialVersionUID = 6654282322830963442L;

	/** 操作日志编号 */
	private Integer operationLogID;
	/** 玩家编号 */
	private Integer playerID;
	/** 操作 */
	private String operation;
	/** 目标 */
	private String target;
	/** 创建时间 */
	private Date createTime;

	public Integer getOperationLogID() {
		return operationLogID;
	}

	public void setOperationLogID(Integer operationLogID) {
		this.operationLogID = operationLogID;
	}

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
