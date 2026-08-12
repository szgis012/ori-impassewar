package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.orm.jpa.JpaAccessor;

import com.war.dao.IMessageInboxDAO;
import com.war.domain.MessageInbox;

public class MessageInboxDAO extends SqlMapClientDaoSupport implements IMessageInboxDAO {

	public Integer createMessageInbox(MessageInbox messageInbox) {
		return (Integer)this.getSqlMapClientTemplate().insert("MessageInbox.createMessageInbox", messageInbox);
	}

	public void updateMessageInbox(MessageInbox messageInbox) {
		this.getSqlMapClientTemplate().update("MessageInbox.updateMessageInbox", messageInbox);
	}
	
	public void updateMessageReadFlagByID(Integer messageInboxID, Integer readFlag) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("messageInboxID", messageInboxID);
		params.put("readFlag", readFlag);
		
		this.getSqlMapClientTemplate().update("MessageInbox.updateMessageReadFlagByID", params);
	}

	public void deleteMessageInboxByID(Integer messageInboxID) {
		this.getSqlMapClientTemplate().delete("MessageInbox.deleteMessageInboxByID", messageInboxID);
	}

	public MessageInbox getMessageInboxByID(Integer messageInboxID) {
		return (MessageInbox)this.getSqlMapClientTemplate().queryForObject("MessageInbox.getMessageInboxByID", messageInboxID);
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageInbox> getMessageInboxList() {
		return this.getSqlMapClientTemplate().queryForList("MessageInbox.getMessageInboxList");
	}

	public Integer getMessageInboxAmountByReceiverID(Integer receiverID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("MessageInbox.getMessageInboxAmountByReceiverID", receiverID);
	}

	@SuppressWarnings("unchecked")
	public List<MessageInbox> getMessageInboxListByReceiverID(Integer receiverID) {
		return this.getSqlMapClientTemplate().queryForList("MessageInbox.getMessageInboxListByReceiverID", receiverID);
	}

	public Integer getMessageInboxNumByReadFlagAndReceiverID(Integer readFlag, Integer receiverID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("readFlag", readFlag);
		params.put("receiverID", receiverID);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("MessageInbox.getMessageInboxNumByReadFlagAndReceiverID", params);
	}

	@SuppressWarnings("unchecked")
	public List<MessageInbox> getMessageInboxPagingListByReceiverID(Integer receiverID, Integer start, Integer offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("receiverID", receiverID);
		params.put("start", start);
		params.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("MessageInbox.getMessageInboxPagingListByReceiverID", params);
	}

	public Integer getMessageReadFlagByID(Integer messageInboxID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("MessageInbox.getMessageReadFlagByID", messageInboxID);
	}

}
