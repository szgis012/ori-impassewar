package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class MessageOutbox implements Serializable {

	private static final long serialVersionUID = 325060902867620339L;
	
	/** 消息发件箱编号 */
	private Integer messageOutboxID;
	/** 发送玩家编号 */
	private Integer senderID;
	/** 接收玩家名称 */
	private String receiverName;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 发送时间 */
	private Date sendTime;

	public Integer getMessageOutboxID() {
		return messageOutboxID;
	}

	public void setMessageOutboxID(Integer messageOutboxID) {
		this.messageOutboxID = messageOutboxID;
	}

	public Integer getSenderID() {
		return senderID;
	}

	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
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

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
