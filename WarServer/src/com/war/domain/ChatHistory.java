package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class ChatHistory implements Serializable {

	private static final long serialVersionUID = 3184018571794441276L;
	
	/** 聊天历史信息编号 */
	private Integer chatHistoryID;
	/** 发送者名称 */
	private String playerName;
	/** 接收者名称 */
	private String receiverName;
	/** 内容 */
	private String content;
	/** 聊天类型(1.世界 2.军团 3.私聊) */
	private Integer chatType;
	/** 发送时间 */
	private Date sendTime;

	public Integer getChatHistoryID() {
		return chatHistoryID;
	}

	public void setChatHistoryID(Integer chatHistoryID) {
		this.chatHistoryID = chatHistoryID;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getChatType() {
		return chatType;
	}

	public void setChatType(Integer chatType) {
		this.chatType = chatType;
	}
	
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
}