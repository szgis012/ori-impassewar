package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IMessageDAO;
import com.war.domain.Message;

public class MessageDAOTest {

	private static IMessageDAO messageDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		messageDAO = (IMessageDAO)SpringService.getApplicationContext().getBean("messageDAO");
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
	
	// @Test
	public void testCURD() {

		Integer senderID = 1;
		Integer receiverID = 1;
		String title = "测试字符串";
		String content = "测试字符串";
		Integer readFlag = 1;
		Integer deleteFlag = 1;

		Message message = new Message();
		
		message.setSenderID(senderID);
		message.setReceiverID(receiverID);
		message.setTitle(title);
		message.setContent(content);
		message.setReadFlag(readFlag);
		message.setDeleteFlag(deleteFlag);

		//测试创建
		Integer messageID = messageDAO.createMessage(message);
		assertNotNull(messageID);

		//测试通过编号获得对象
		Message destMessage = messageDAO.getMessageByID(messageID);
		assertNotNull(destMessage);
		assertEquals(messageID,destMessage.getMessageID());
		assertEquals(senderID,destMessage.getSenderID());
		assertEquals(receiverID,destMessage.getReceiverID());
		assertEquals(title,destMessage.getTitle());
		assertEquals(content,destMessage.getContent());
		assertEquals(readFlag,destMessage.getReadFlag());
		assertEquals(deleteFlag,destMessage.getDeleteFlag());
		
		//测试获得列表
		//List<Message> messageList = messageDAO.getMessageList();
		//assertFalse(messageList.isEmpty());

		//测试更新
		/*messageID = 10;
		senderID = 10;
		receiverID = 10;
		title = "字符串修改";
		content = "字符串修改";
		readFlag = 10;
		deleteFlag = 10;
		destMessage.setMessageID(messageID);
		destMessage.setSenderID(senderID);
		destMessage.setReceiverID(receiverID);
		destMessage.setTitle(title);
		destMessage.setContent(content);
		destMessage.setReadFlag(readFlag);
		destMessage.setDeleteFlag(deleteFlag);
		destMessage.setSendTime(sendTime);
		messageDAO.updateMessage(destMessage);
		Message updatedMessage = messageDAO.getMessageByID(messageID);
		assertNotNull(updatedMessage);
		assertEquals(messageID,updatedMessage.getMessageID());
		assertEquals(senderID,updatedMessage.getSenderID());
		assertEquals(receiverID,updatedMessage.getReceiverID());
		assertEquals(title,updatedMessage.getTitle());
		assertEquals(content,updatedMessage.getContent());
		assertEquals(readFlag,updatedMessage.getReadFlag());
		assertEquals(deleteFlag,updatedMessage.getDeleteFlag());
		assertEquals(sendTime,updatedMessage.getSendTime());*/

		//测试删除
		messageDAO.deleteMessageByID(messageID);
		assertNull(messageDAO.getMessageByID(messageID));

	}

	@Test
	public void testSendGuildMessage() {
		messageDAO.sendGuildMessage(2, 8, "测试标题（南瓜）", "测试内容（南瓜）");
	}
}