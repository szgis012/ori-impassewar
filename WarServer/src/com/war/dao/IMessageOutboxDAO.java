package com.war.dao;

import java.util.List;

import com.war.domain.MessageOutbox;

public interface IMessageOutboxDAO {
	
	/**
	 * 创建发件箱消息
	 * @param messageOutbox
	 * @return
	 */
	public Integer createMessageOutbox(MessageOutbox messageOutbox);

	/**
	 * 更新发件箱消息
	 * @param messageOutbox
	 */
	public void updateMessageOutbox(MessageOutbox messageOutbox);

	/**
	 * 根据编号删除发件箱消息
	 * @param messageOutboxID
	 */
	public void deleteMessageOutboxByID(Integer messageOutboxID);

	/**
	 * 根据编号获得发件箱消息
	 * @param messageOutboxID
	 * @return
	 */
	public MessageOutbox getMessageOutboxByID(Integer messageOutboxID);

	/**
	 * 获得发件箱消息列表
	 * @return
	 */
	public List<MessageOutbox> getMessageOutboxList();
	
	/**
	 * 根据发件人编号获得其发件箱中的消息总数
	 * @param senderID
	 * @return
	 */
	public Integer getMessageOutboxAmountBySenderID(Integer senderID);
	
	/**
	 * 根据发件人编号获得其发件箱中的消息列表（分页）
	 * @param senderID
	 * @return
	 */
	public List<MessageOutbox> getMessageOutboxPagingListBySenderID(Integer senderID, Integer start, Integer offset);
	
	/**
	 * 根据发件人编号获得其发件箱中的消息列表
	 * @param senderID
	 * @return
	 */
	public List<MessageOutbox> getMessageOutboxListBySenderID(Integer senderID);
	
	/**
	 * 给军团内所有的成员发送消息
	 * @param playerID
	 * @param guildID
	 * @param title
	 * @param content
	 */
	public void sendGuildMessage(Integer guildID, Integer playerID, String title, String content);
}
