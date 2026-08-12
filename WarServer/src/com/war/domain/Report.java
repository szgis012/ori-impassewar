package com.war.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 报告信息
 *
 * @author ghleed
 * @version 1.0
 */
public class Report implements Serializable {

	private static final long serialVersionUID = 7902683168028302250L;
	
	/** 报告编号 */
	private Integer reportID;
	/** 玩家编号，标识报告所属的玩家 */
	private Integer playerID;
	/** 标题 */
	private String title;
	/** 报告内容 */
	private String content;
	/** 报告类型 */
	private Integer type;
	/** 已读标志：0 未读 1 已读 */
	private Integer readFlag;
	/** 保存标志：0  未保存 1 已保存 */
	private Integer saveFlag;
	/** 接收报告的时间 */
	private Date receiveTime;

	public Integer getReportID() {
		return reportID;
	}

	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}
	
	public Integer getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(Integer saveFlag) {
		this.saveFlag = saveFlag;
	}
	
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	

}