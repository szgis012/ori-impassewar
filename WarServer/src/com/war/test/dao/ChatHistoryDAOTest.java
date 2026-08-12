package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IChatHistoryDAO;
import com.war.domain.ChatHistory;

public class ChatHistoryDAOTest {

	private static IChatHistoryDAO chatHistoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		chatHistoryDAO = (IChatHistoryDAO)SpringService.getApplicationContext().getBean("chatHistoryDAO");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCURD() {

		String playerName = "测试字符串";
		String receiverName = "测试字符串";
		String content = "测试字符串";
		Integer chatType = 1;
		Date sendTime = new Date();

		ChatHistory chatHistory = new ChatHistory();
		
		chatHistory.setPlayerName(playerName);
		chatHistory.setReceiverName(receiverName);
		chatHistory.setContent(content);
		chatHistory.setChatType(chatType);
		chatHistory.setSendTime(sendTime);

		//测试创建
		Integer chatHistoryID = chatHistoryDAO.createChatHistory(chatHistory);
		assertNotNull(chatHistoryID);

		//测试获得列表
		List<ChatHistory> chatHistoryList = chatHistoryDAO.getChatHistoryList();
		assertFalse(chatHistoryList.isEmpty());

		//测试删除
		//chatHistoryDAO.deleteChatHistoryByID(chatHistoryID);
	}

}