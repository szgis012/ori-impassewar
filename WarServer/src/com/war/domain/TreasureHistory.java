package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class TreasureHistory implements Serializable {

	private static final long serialVersionUID = -7442620464192253880L;
	
	/** 宝物历史编号 */
	private Integer treasureHistoryID;
	/** 玩家编号 */
	private Integer playerID;
	/** 宝物编号 */
	private Integer treasureID;
	/** 数量 */
	private Integer num;
	/** 类型(1.购买 2.使用) */
	private Integer type;
	/** 创建时间 */
	private Date createTime;

	
	public Integer getTreasureHistoryID() {
		return treasureHistoryID;
	}

	public void setTreasureHistoryID(Integer treasureHistoryID) {
		this.treasureHistoryID = treasureHistoryID;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public Integer getTreasureID() {
		return treasureID;
	}

	public void setTreasureID(Integer treasureID) {
		this.treasureID = treasureID;
	}
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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