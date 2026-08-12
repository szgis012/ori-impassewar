package com.war.domain;

import java.io.Serializable;

public class DataHistory implements Serializable {

	private static final long serialVersionUID = 4284572230409019936L;
	
	/** 数据历史编号(格式:YYYYMMDDHHMM) */
	private Long dataHistoryID;
	/** 在线玩家数量 */
	private Integer onlinePlayerNum;

	
	public Long getDataHistoryID() {
		return dataHistoryID;
	}

	public void setDataHistoryID(Long dataHistoryID) {
		this.dataHistoryID = dataHistoryID;
	}
	
	public Integer getOnlinePlayerNum() {
		return onlinePlayerNum;
	}

	public void setOnlinePlayerNum(Integer onlinePlayerNum) {
		this.onlinePlayerNum = onlinePlayerNum;
	}

}