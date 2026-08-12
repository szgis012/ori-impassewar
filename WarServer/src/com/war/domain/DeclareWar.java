package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class DeclareWar implements Serializable {

	private static final long serialVersionUID = 6017435042869713032L;
	
	/** 宣战编号 */
	private Integer declareWarID;
	/** 玩家编号 */
	private Integer playerID;
	/** 目标玩家编号 */
	private Integer targetPlayerID;
	/** 战争开始时间 */
	private Date startTime;
	/** 战争结束时间 */
	private Date finishTime;

	public Integer getDeclareWarID() {
		return declareWarID;
	}

	public void setDeclareWarID(Integer declareWarID) {
		this.declareWarID = declareWarID;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public Integer getTargetPlayerID() {
		return targetPlayerID;
	}

	public void setTargetPlayerID(Integer targetPlayerID) {
		this.targetPlayerID = targetPlayerID;
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