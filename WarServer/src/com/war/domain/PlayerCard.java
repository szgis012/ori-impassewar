package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class PlayerCard implements Serializable {

	private static final long serialVersionUID = -5409910874899432925L;
	
	/** 玩家编号 */
	private Integer playerID;
	/** 类型 */
	private Integer type;
	/** 创建时间 */
	private Date createTime;

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}