package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class GuildRewardReceiveLog implements Serializable {

	private static final long serialVersionUID = -145479301867934925L;
	
	/** 玩家编号 */
	private Integer playerID;
	/** 领取时间 */
	private Date receiveTime;

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}


}
