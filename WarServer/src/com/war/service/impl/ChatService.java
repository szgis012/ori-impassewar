package com.war.service.impl;

import java.util.List;

import com.war.dao.IChatHistoryDAO;
import com.war.domain.ChatHistory;
import com.war.service.IChatService;

public class ChatService implements IChatService {

	IChatHistoryDAO chatHistoryDAO;
	
	public Integer addChatHistory(ChatHistory chatHistory) {
		return chatHistoryDAO.createChatHistory(chatHistory);
	}

	public void deleteChatHistoryByID(Integer chatHistoryID) {
		chatHistoryDAO.deleteChatHistoryByID(chatHistoryID);
	}

	public List<ChatHistory> getChatHistoryList() {
		return chatHistoryDAO.getChatHistoryList();
	}
	
	public IChatHistoryDAO getChatHistoryDAO() {
		return chatHistoryDAO;
	}

	public void setChatHistoryDAO(IChatHistoryDAO chatHistoryDAO) {
		this.chatHistoryDAO = chatHistoryDAO;
	}

	public List<ChatHistory> getChatHistoryList(String playerName, Integer chatType) {
		return chatHistoryDAO.getChatHistoryListByPlayerNameAndChatType(playerName, chatType);
	}

	public Integer getChatHistoryNum(String playerName, Integer chatType) {
		return chatHistoryDAO.getChatHistoryNumByPlayerNameAndChatType(playerName, chatType);
	}

	
}
