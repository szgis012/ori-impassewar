package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IChatHistoryDAO;
import com.war.domain.ChatHistory;

public class ChatHistoryDAO extends SqlMapClientDaoSupport implements IChatHistoryDAO{

	public Integer createChatHistory(ChatHistory chatHistory) {
		return (Integer)this.getSqlMapClientTemplate().insert("ChatHistory.createChatHistory", chatHistory);
	}
	
	public void deleteChatHistoryByID(Integer chatHistoryID) {
		this.getSqlMapClientTemplate().delete("ChatHistory.deleteChatHistoryByID", chatHistoryID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ChatHistory> getChatHistoryList() {
		return this.getSqlMapClientTemplate().queryForList("ChatHistory.getChatHistoryList");
	}
	
	@SuppressWarnings("unchecked")
	public List<ChatHistory> getChatHistoryListByPlayerNameAndChatType(String playerName, Integer chatType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerName", playerName);
		params.put("chatType", chatType);
		
		return this.getSqlMapClientTemplate().queryForList("ChatHistory.getChatHistoryListByPlayerNameAndChatType", params);
	}

	public Integer getChatHistoryNumByPlayerNameAndChatType(String playerName, Integer chatType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerName", playerName);
		params.put("chatType", chatType);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("ChatHistory.getChatHistoryNumByPlayerNameAndChatType", params);
	}

	
}