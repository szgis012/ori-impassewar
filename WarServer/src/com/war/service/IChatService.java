package com.war.service;

import java.util.List;

import com.war.domain.ChatHistory;

public interface IChatService {

	/**
	 * 新增聊天历史记录
	 * @param chatHistory
	 * @return
	 */
	public Integer addChatHistory(ChatHistory chatHistory);

	/**
	 * 根据聊天历史记录编号删除聊天历史记录
	 * @param chatHistoryID
	 */
	public void deleteChatHistoryByID(Integer chatHistoryID);

	/**
	 * 获得聊天历史记录列表
	 * @return
	 */
	public List<ChatHistory> getChatHistoryList();
	
	
	/**
	 * 获得聊天历史记录列表
	 * @param playerName
	 * @param chatType
	 * @return
	 */
	public List<ChatHistory> getChatHistoryList(String playerName, Integer chatType);
	
	/**
	 * 获得聊天历史记录数目
	 * @param playerName
	 * @param chatType
	 * @return
	 */
	public Integer getChatHistoryNum(String playerName, Integer chatType);
}
