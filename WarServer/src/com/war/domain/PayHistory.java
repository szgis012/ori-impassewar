package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class PayHistory implements Serializable {

	private static final long serialVersionUID = 3452884728572093163L;
	
	/** 充值历史编号 */
	private Integer payHistoryID;
	/** 玩家编号 */
	private Integer playerID;
	/** 数量 */
	private Integer amount;
	/** 充值时间 */
	private Date payTime;

	public Integer getPayHistoryID() {
		return payHistoryID;
	}

	public void setPayHistoryID(Integer payHistoryID) {
		this.payHistoryID = payHistoryID;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	

}