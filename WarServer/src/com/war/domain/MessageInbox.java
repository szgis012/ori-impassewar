package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class MessageInbox implements Serializable {

	private static final long serialVersionUID = -7577049856738267688L;

	/** 消息收件箱编号 */
	private Integer messageInboxID;
	/** 发送玩家名称 */
	private String senderName;
	/** 接收玩家编号 */
	private Integer receiverID;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 已读标示(0.未读 1.已读) */
	private Integer readFlag;
	/** 接收时间 */
	private Date receiveTime;

	public Integer getMessageInboxID() {
		return messageInboxID;
	}

	public void setMessageInboxID(Integer messageInboxID) {
		this.messageInboxID = messageInboxID;
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

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

}
