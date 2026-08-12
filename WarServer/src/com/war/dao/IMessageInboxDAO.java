package com.war.dao;

import java.util.List;

import com.war.domain.MessageInbox;

public interface IMessageInboxDAO {
	
	/**
	 * 创建收件箱消息
	 * @param messageInbox
	 * @return
	 */
	public Integer createMessageInbox(MessageInbox messageInbox);

	/**
	 * 更新收件箱消息
	 * @param messageInbox
	 */
	public void updateMessageInbox(MessageInbox messageInbox);
	
	/**
	 * 根据编号改变消息读取状态
	 * @param messageInboxID
	 */
	public void updateMessageReadFlagByID(Integer messageInboxID, Integer readFlag);

	/**
	 * 根据编号删除收件箱消息
	 * @param messageInboxID
	 */
	public void deleteMessageInboxByID(Integer messageInboxID);

	/**
	 * 根据编号获得收件箱消息
	 * @param messageInboxID
	 * @return
	 */
	public MessageInbox getMessageInboxByID(Integer messageInboxID);

	/**
	 * 获得收件箱消息列表
	 * @return
	 */
	public List<MessageInbox> getMessageInboxList();
	
	/**
	 * 根据收件箱消息的读取标识和收件人编号获得收件箱消息列表
	 * @return
	 */
	public Integer getMessageInboxNumByReadFlagAndReceiverID(Integer readFlag, Integer receiverID);
	
	/**
	 * 根据编号获得收件箱消息的读取标识
	 * @return
	 */
	public Integer getMessageReadFlagByID(Integer messageInboxID);
	
	/**
	 * 根据收件人编号获得其总的收件箱数目
	 * @return
	 */
	public Integer getMessageInboxAmountByReceiverID(Integer receiverID);
	
	/**
	 * 根据收件人编号获得收件箱消息列表(分页)
	 * @return
	 */
	public List<MessageInbox> getMessageInboxPagingListByReceiverID(Integer receiverID, Integer start, Integer offset);
	
	/**
	 * 根据收件人编号获得收件箱消息列表
	 * @return
	 */
	public List<MessageInbox> getMessageInboxListByReceiverID(Integer receiverID);
}
