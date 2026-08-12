package com.war.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.dao.IMessageInboxDAO;
import com.war.dao.IMessageOutboxDAO;
import com.war.dao.IPlayerDAO;
import com.war.domain.MessageInbox;
import com.war.domain.MessageOutbox;
import com.war.exception.GameException;
import com.war.service.IMessageService;
import com.war.socket.game.GameSocketService;

public class MessageService implements IMessageService {

	private IMessageInboxDAO messageInboxDAO;
	
	private IMessageOutboxDAO messageOutboxDAO;
	
	private IPlayerDAO playerDAO;
	
	private static Logger logger = Logger.getLogger(MessageService.class);
	
	public void sendMessage(Integer senderID, String receiverName, String title, String content) {
		
		Integer receiverID = playerDAO.getPlayerIDByPlayerName(receiverName);
		
		if(receiverID==null){
			throw new GameException("玩家 " + receiverName + " 不存在。");
		}
		
		// 收件箱信息
		MessageInbox messageInbox = new MessageInbox();
		messageInbox.setReceiverID(receiverID);
		if (senderID==0) {
			messageInbox.setSenderName("系统");
		} else {
			messageInbox.setSenderName(this.getPlayerNameByPlayerID(senderID));
		}
		messageInbox.setTitle(title);
		messageInbox.setContent(content);
		messageInbox.setReadFlag(0);
		messageInboxDAO.createMessageInbox(messageInbox);           //发送到信箱
		
		if (senderID!=0) {                                          //添加到已发送
			// 发件箱信息
			MessageOutbox messageOutbox = new MessageOutbox();
			messageOutbox.setSenderID(senderID);
			messageOutbox.setReceiverName(receiverName);
			messageOutbox.setTitle(title);
			messageOutbox.setContent(content);
			messageOutboxDAO.createMessageOutbox(messageOutbox);
		}
		
		JSONObject json = new JSONObject();
		try {
			json.put("type", 4);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		//向客户端push新消息信息
		GameSocketService.sendDataToClient(receiverID, json);
	}
	
	public Integer sendMessage(MessageOutbox messageOutbox) {
		
		Integer playerID = playerDAO.getPlayerIDByPlayerName(messageOutbox.getReceiverName());
		
		if(playerID==null){
			throw new GameException("玩家 " + messageOutbox.getReceiverName() + " 不存在。");
		}
		
		// 收件箱信息
		MessageInbox messageInbox = new MessageInbox();
		messageInbox.setReceiverID(playerID);
		messageInbox.setSenderName(this.getPlayerNameByPlayerID(messageOutbox.getSenderID()));
		messageInbox.setTitle(messageOutbox.getTitle());
		messageInbox.setContent(messageOutbox.getContent());
		
		Integer messageInboxID = messageInboxDAO.createMessageInbox(messageInbox);
		// 发件箱信息
		messageOutboxDAO.createMessageOutbox(messageOutbox);
		
		JSONObject json = new JSONObject();
		try {
			json.put("type", 4);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		
		//向客户端push新消息信息
		GameSocketService.sendDataToClient(playerID, json);
		
		return messageInboxID;
	}
	
	public void deleteMessageInboxByID(Integer messageInboxID) {
		messageInboxDAO.deleteMessageInboxByID(messageInboxID);
	}

	public void deleteMessageOutboxByID(Integer messageOutboxID) {
		messageOutboxDAO.deleteMessageOutboxByID(messageOutboxID);
	}
	
	public void deleteMessagesInbox(Integer[] messageInboxIDs) {
		for (Integer messageInboxID : messageInboxIDs) {
			this.deleteMessageInboxByID(messageInboxID);
		}
	}

	public void deleteMessagesOutbox(Integer[] messageOutboxIDs) {
		for (Integer messageOutboxID : messageOutboxIDs) {
			this.deleteMessageOutboxByID(messageOutboxID);
		}
	}
	
	public Integer getPlayerUnReadedMessageNum(Integer playerID){
		return messageInboxDAO.getMessageInboxNumByReadFlagAndReceiverID(0, playerID);
	}

	public MessageInbox getMessageInboxByID(Integer messageInboxID) {
		return messageInboxDAO.getMessageInboxByID(messageInboxID);
	}

	public MessageOutbox getMessageOutboxByID(Integer messageOutboxID) {
		return messageOutboxDAO.getMessageOutboxByID(messageOutboxID);
	}
	
	public void readMessage(Integer messageInboxID){
		messageInboxDAO.updateMessageReadFlagByID(messageInboxID, 1);
	}
	
	public Integer getInboxMessageAmount(Integer playerID) {
		return messageInboxDAO.getMessageInboxAmountByReceiverID(playerID);
	}
	
	public Integer getOutboxMessageAmount(Integer playerID) {
		return messageOutboxDAO.getMessageOutboxAmountBySenderID(playerID);
	}
	
	public List<MessageInbox> getInboxMessagePagingList(Integer playerID, Integer start, Integer offset) {
		
		List<MessageInbox> messageList = messageInboxDAO.getMessageInboxPagingListByReceiverID(playerID, start, offset);
		return messageList;
	}
	
	public List<MessageInbox> getInboxMessageList(Integer playerID) {
		return messageInboxDAO.getMessageInboxListByReceiverID(playerID);
	}
	
	public List<MessageOutbox> getOutboxMessagePagingList(Integer playerID,Integer start, Integer offset) {
		
		List<MessageOutbox> messageList = messageOutboxDAO.getMessageOutboxPagingListBySenderID(playerID, start, offset);
		return messageList;
	}
	
	public List<MessageOutbox> getOutboxMessageList(Integer playerID) {
		return messageOutboxDAO.getMessageOutboxListBySenderID(playerID);
	}
	
	public void sendMessageToAllGuildMembers(Integer playerID, Integer guildID, String title, String content) {
		// TODO JiaHL: 检查道具、扣除道具、权限判断交给前端
		messageOutboxDAO.sendGuildMessage(guildID, playerID, title, content);
	}
	
	@SuppressWarnings("unchecked")
	private String getPlayerNameByPlayerID(Integer playerID) {
		return ((Map<Integer, String>)CacheService.getFromCache(CacheConstant.PLAYERID_PLAYERNAME_MAP)).get(playerID);
	}
	

	public IMessageInboxDAO getMessageInboxDAO() {
		return messageInboxDAO;
	}

	public void setMessageInboxDAO(IMessageInboxDAO messageInboxDAO) {
		this.messageInboxDAO = messageInboxDAO;
	}

	public IMessageOutboxDAO getMessageOutboxDAO() {
		return messageOutboxDAO;
	}

	public void setMessageOutboxDAO(IMessageOutboxDAO messageOutboxDAO) {
		this.messageOutboxDAO = messageOutboxDAO;
	}

	public IPlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(IPlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}


}
