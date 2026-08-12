package com.war.service;

import java.util.List;

import com.war.domain.MessageInbox;
import com.war.domain.MessageOutbox;

/**
 * 消息Service接口
 * 
 * @author TopTong
 * @version 1.0
 */

public interface IMessageService {

	/**
	 * 发送消息
	 * @param senderID
	 * @param receiverName
	 * @param title
	 * @param content
	 */
	public void sendMessage(Integer senderID, String receiverName, String title, String content);
	
	/**
	 * 发送消息
	 * @param message
	 * @return
	 */
	public Integer sendMessage(MessageOutbox messageOutbox);

	/**
	 * 根据收件箱消息编号删除消息
	 * @param messageInboxID
	 */
	public void deleteMessageInboxByID(Integer messageInboxID);
	
	/**
	 * 根据发件箱消息编号删除消息
	 * @param messageOutboxID
	 */
	public void deleteMessageOutboxByID(Integer messageOutboxID);

	/**
	 * 根据收件箱消息编号批量删除消息
	 * @param messageInboxIDs
	 */
	public void deleteMessagesInbox(Integer[] messageInboxIDs);
	
	/**
	 * 根据发件箱消息编号批量删除消息
	 * @param messageOutboxIDs
	 */
	public void deleteMessagesOutbox(Integer[] messageOutboxIDs);
	
	/**
	 * 获得玩家未读消息数量
	 * @param playerID
	 * @return
	 */
	public Integer getPlayerUnReadedMessageNum(Integer playerID);

	/**
	 * 根据编号获得收件箱消息
	 * @param messageInboxID
	 */
	public MessageInbox getMessageInboxByID(Integer messageInboxID);
	
	/**
	 * 根据编号获得发件箱消息
	 * @param messageOutboxID
	 * @return
	 */
	public MessageOutbox getMessageOutboxByID(Integer messageOutboxID);

	/**
	 * 读取消息
	 * @param messageID
	 */
	public void readMessage(Integer messageID);
	
	/**
	 * 获得收件箱消息数量
	 * @param receiverID
	 * @return
	 */
	public Integer getInboxMessageAmount(Integer playerID);
	
	/**
	 * 获得发件箱消息数量
	 * @param senderID
	 * @return
	 */
	public Integer getOutboxMessageAmount(Integer playerID);
	
	/**
	 * 获得收件箱消息列表(分页)
	 * @param senderID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<MessageInbox> getInboxMessagePagingList(Integer playerID, Integer start, Integer offset);

	/**
	 * 获得收件箱消息列表
	 * @param playerID 玩家编号
	 * @return
	 */
	public List<MessageInbox> getInboxMessageList(Integer playerID);
	
	/**
	 * 获得发件箱消息列表(分页)
	 * @param receiverID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<MessageOutbox> getOutboxMessagePagingList(Integer playerID,Integer start, Integer offset);

	/**
	 * 获得发件箱消息列表
	 * @param playerID 玩家编号
	 * @return
	 */
	public List<MessageOutbox> getOutboxMessageList(Integer playerID);
	
	/**
	 * 给军团内所有的成员发送消息
	 * @param guildID
	 */
	public void sendMessageToAllGuildMembers(Integer playerID, Integer guildID, String title, String content);
	
}
