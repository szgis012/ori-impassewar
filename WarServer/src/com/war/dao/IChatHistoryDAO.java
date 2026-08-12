package com.war.dao;

import java.util.List;

import com.war.domain.ChatHistory;

public interface IChatHistoryDAO {

	/**
	 * 创建聊天历史记录
	 * @param chatHistory
	 * @return
	 */
	public Integer createChatHistory(ChatHistory chatHistory);

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
	 * 根据玩家名称和聊天类型获得其聊天历史记录信息列表
	 * @param playerName
	 * @param chatType
	 * @return
	 */
	public List<ChatHistory> getChatHistoryListByPlayerNameAndChatType(String playerName, Integer chatType);

	/**
	 * 根据玩家名称和聊天类型获得其聊天历史记录信息数目
	 * @param playerName
	 * @param chatType
	 * @return
	 */
	public Integer getChatHistoryNumByPlayerNameAndChatType(String playerName, Integer chatType);
}