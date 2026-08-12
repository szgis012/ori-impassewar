package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IMessageOutboxDAO;
import com.war.domain.MessageOutbox;

public class MessageOutboxDAO extends SqlMapClientDaoSupport implements IMessageOutboxDAO {

	public Integer createMessageOutbox(MessageOutbox messageOutbox) {
		return (Integer)this.getSqlMapClientTemplate().insert("MessageOutbox.createMessageOutbox", messageOutbox);
	}

	public void updateMessageOutbox(MessageOutbox messageOutbox) {
		this.getSqlMapClientTemplate().update("MessageOutbox.updateMessageOutbox", messageOutbox);
	}

	public void deleteMessageOutboxByID(Integer messageOutboxID) {
		this.getSqlMapClientTemplate().delete("MessageOutbox.deleteMessageOutboxByID", messageOutboxID);
	}

	public MessageOutbox getMessageOutboxByID(Integer messageOutboxID) {
		return (MessageOutbox)this.getSqlMapClientTemplate().queryForObject("MessageOutbox.getMessageOutboxByID", messageOutboxID);
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageOutbox> getMessageOutboxList() {
		return this.getSqlMapClientTemplate().queryForList("MessageOutbox.getMessageOutboxList");
	}

	public Integer getMessageOutboxAmountBySenderID(Integer senderID) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("MessageOutbox.getMessageOutboxAmountBySenderID", senderID);
	}

	@SuppressWarnings("unchecked")
	public List<MessageOutbox> getMessageOutboxListBySenderID(Integer senderID) {
		return this.getSqlMapClientTemplate().queryForList("MessageOutbox.getMessageOutboxListBySenderID", senderID);
	}

	@SuppressWarnings("unchecked")
	public List<MessageOutbox> getMessageOutboxPagingListBySenderID(Integer senderID, Integer start, Integer offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("senderID", senderID);
		params.put("start", start);
		params.put("offset", offset);
		
		return this.getSqlMapClientTemplate().queryForList("MessageOutbox.getMessageOutboxPagingListBySenderID", params);
	}

	public void sendGuildMessage(Integer guildID, Integer playerID, String title, String content) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("guildID", guildID);
		params.put("title", title);
		params.put("content", content);
		
		this.getSqlMapClientTemplate().insert("MessageOutbox.sendGuildMessage",params);
	}


}
