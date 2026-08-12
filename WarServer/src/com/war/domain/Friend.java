package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class Friend implements Serializable {

	private static final long serialVersionUID = -8797453917827979194L;

	/** 好友编号 */
	private Integer friendID;
	/** 玩家编号 */
	private Integer playerID;
	/** 目标玩家编号 */
	private Integer targetPlayerID;
	/** 状态(0.邀请中 1.正常) */
	private Integer state;
	/** 创建时间 */
	private Date createTime;
	/** 目标玩家 */
	private Player targetPlayer;
	/** 目标玩家城市X坐标 */
	private Integer posX;
	/** 目标玩家城市Y坐标 */
	private Integer posY;

	public Integer getFriendID() {
		return friendID;
	}

	public void setFriendID(Integer friendID) {
		this.friendID = friendID;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Player getTargetPlayer() {
		return targetPlayer;
	}

	public void setTargetPlayer(Player targetPlayer) {
		this.targetPlayer = targetPlayer;
	}

	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

}
