package com.war.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IMessageOutboxDAO;
import com.war.domain.MessageOutbox;

public class MessageOutboxDAOTest {

	private static IMessageOutboxDAO messageOutboxDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		messageOutboxDAO = (IMessageOutboxDAO)SpringService.getApplicationContext().getBean("messageOutboxDAO");
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

		Integer messageOutboxID = 1;
		Integer senderID = 1;
		String receiverName = "测试字符串";
		String title = "测试字符串";
		String content = "测试字符串";
		Date sendTime = new Date();

		MessageOutbox messageOutbox = new MessageOutbox();
		
		messageOutbox.setMessageOutboxID(messageOutboxID);
		messageOutbox.setSenderID(senderID);
		messageOutbox.setReceiverName(receiverName);
		messageOutbox.setTitle(title);
		messageOutbox.setContent(content);
		messageOutbox.setSendTime(sendTime);

		//测试创建
		messageOutboxID = messageOutboxDAO.createMessageOutbox(messageOutbox);
		assertNotNull(messageOutboxID);

		//测试通过编号获得对象
		MessageOutbox destMessageOutbox = messageOutboxDAO.getMessageOutboxByID(messageOutboxID);
		assertNotNull(destMessageOutbox);
		assertEquals(messageOutboxID,destMessageOutbox.getMessageOutboxID());
		assertEquals(senderID,destMessageOutbox.getSenderID());
		assertEquals(receiverName,destMessageOutbox.getReceiverName());
		assertEquals(title,destMessageOutbox.getTitle());
		assertEquals(content,destMessageOutbox.getContent());
		// assertEquals(sendTime,destMessageOutbox.getSendTime());

		//测试获得列表
		List<MessageOutbox> messageOutboxList = messageOutboxDAO.getMessageOutboxList();
		assertFalse(messageOutboxList.isEmpty());

		//测试更新
		messageOutboxID = 1;
		senderID = 10;
		receiverName = "字符串修改";
		title = "字符串修改";
		content = "字符串修改";
		destMessageOutbox.setMessageOutboxID(messageOutboxID);
		destMessageOutbox.setSenderID(senderID);
		destMessageOutbox.setReceiverName(receiverName);
		destMessageOutbox.setTitle(title);
		destMessageOutbox.setContent(content);
		destMessageOutbox.setSendTime(sendTime);
		messageOutboxDAO.updateMessageOutbox(destMessageOutbox);
		MessageOutbox updatedMessageOutbox = messageOutboxDAO.getMessageOutboxByID(messageOutboxID);
		assertNotNull(updatedMessageOutbox);
		assertEquals(messageOutboxID,updatedMessageOutbox.getMessageOutboxID());
		assertEquals(senderID,updatedMessageOutbox.getSenderID());
		assertEquals(receiverName,updatedMessageOutbox.getReceiverName());
		assertEquals(title,updatedMessageOutbox.getTitle());
		assertEquals(content,updatedMessageOutbox.getContent());
		// assertEquals(sendTime,updatedMessageOutbox.getSendTime());

		//测试删除
		messageOutboxDAO.deleteMessageOutboxByID(messageOutboxID);
		assertNull(messageOutboxDAO.getMessageOutboxByID(messageOutboxID));

	}

}