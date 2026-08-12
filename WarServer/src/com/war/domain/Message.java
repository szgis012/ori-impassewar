package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private static final long serialVersionUID = -6844010135027727544L;
	
	/** 消息编号 */
	private Integer messageID;
	/** 发送玩家编号 */
	private Integer senderID;
	/** 发送玩家名称 */
	private String senderName;
	/** 接收玩家编号 */
	private Integer receiverID;
	/** 接收玩家名称 */
	private String receiverName;
	/** 消息标题 */
	private String title;
	/** 消息内容 */
	private String content;
	/** 已读标示(0.未读 1.已读) */
	private Integer readFlag;
	/** 删除标志(1.正常 2.发件人已删除 3.收件人已删除) */
	private Integer deleteFlag;
	/** 消息发送时间 */
	private Date sendTime;

	
	public Integer getMessageID() {
		return messageID;
	}

	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}
	
	public Integer getSenderID() {
		return senderID;
	}

	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}
	
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public Integer getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(Integer receiverID) {
		this.receiverID = receiverID;
	}
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
	
	public Integer getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}