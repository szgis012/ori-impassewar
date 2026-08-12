package com.war.dao;

import java.sql.SQLException;
import java.util.List;

import com.war.domain.Message;

/**
 * 消息DAO接口
 * 
 * @author TopTong
 * @version 1.0
 */

public interface IMessageDAO {

	public void createMessageBatch(Message[] messageArray);
	
	/**
	 * 创建消息
	 * @param message
	 * @return
	 */
	public Integer createMessage(Message message);

	/**
	 * 根据编号更新消息已读标示
	 * @param messageID
	 * @param deleteFlag
	 */
	public void updateMessageReadFlagByID(Integer messageID,Integer readFlag);
	
	/**
	 * 根据编号更新消息删除标示
	 * @param messageID
	 * @param deleteFlag
	 */
	public void updateMessageDeleteFlagByID(Integer messageID,Integer deleteFlag);
	
	/**
	 * 根据消息编号删除消息
	 * @param messageID
	 */
	public void deleteMessageByID(Integer messageID);

	/**
	 * 根据消息编号数组删除多个消息
	 * @param messageIDs 消息编号数组
	 * @throws SQLException
	 */
	public void deleteMessages(Integer[] messageIDs) throws SQLException;
	
	/**
	 * 根据已读标示及接受者编号获得消息数量
	 * @param receiverID
	 * @return
	 */
	public Integer getMessageNumByReadFlagAndReceiverID(Integer readFlag,Integer receiverID);
	
	/**
	 * 根据消息编号获得消息已读标示
	 * @param messageID
	 * @return
	 */
	public Integer getMessageReadFlagByID(Integer messageID);
	
	/**
	 * 根据消息编号获得消息删除标示
	 * @param messageID
	 * @return
	 */
	public Integer getMessageDeleteFlagByID(Integer messageID);
	
	/**
	 * 根据消息编号获得消息
	 * @param messageID
	 * @return
	 */
	public Message getMessageByID(Integer messageID);

	/**
	 * 根据发送者编号获得消息数量
	 * @param senderID
	 * @return
	 */
	public Integer getMessageAmountBySenderID(Integer senderID);
	
	/**
	 * 根据接收者编号获得消息数量
	 * @param receiverID
	 * @return
	 */
	public Integer getMessageAmountByReceiverID(Integer receiverID);
	
	/**
	 * 根据发送者编号获得消息列表(分页)
	 * @param senderID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<Message> getMessagePagingListBySenderID(Integer senderID,Integer start,Integer offset);
	
	/**
	 * 根据发送者编号获得消息列表
	 * @param senderID 发送者编号
	 * @return
	 */
	public List<Message> getMessageListBySenderID(Integer senderID);
	
	/**
	 * 根据接受者编号获得消息列表(分页)
	 * @param receiverID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<Message> getMessagePagingListByReceiverID(Integer receiverID,Integer start,Integer offset);
	
	/**
	 * 根据接受者编号获得消息列表
	 * @param receiverID 接受者编号
	 * @return
	 */
	public List<Message> getMessageListByReceiverID(Integer receiverID);

	/**
	 * 向军团全体成员发送信息
	 * @param guildID
	 * @param playerID
	 * @param title
	 * @param content
	 */
	public void sendGuildMessage(Integer guildID, Integer playerID, String title, String content);
}