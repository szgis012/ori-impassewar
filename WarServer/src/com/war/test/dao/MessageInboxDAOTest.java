package com.war.test.dao;

import static org.junit.Assert.* ;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IMessageInboxDAO;
import com.war.domain.MessageInbox;

public class MessageInboxDAOTest {

	private static IMessageInboxDAO messageInboxDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		messageInboxDAO = (IMessageInboxDAO)SpringService.getApplicationContext().getBean("messageInboxDAO");
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

		Integer messageInboxID = 1;
		String senderName = "测试字符串";
		Integer receiverID = 1;
		String title = "测试字符串";
		String content = "测试字符串";
		Integer readFlag = 1;
		Date receiveTime = new Date();

		MessageInbox messageInbox = new MessageInbox();
		
		messageInbox.setMessageInboxID(messageInboxID);
		messageInbox.setSenderName(senderName);
		messageInbox.setReceiverID(receiverID);
		messageInbox.setTitle(title);
		messageInbox.setContent(content);
		messageInbox.setReadFlag(readFlag);
		messageInbox.setReceiveTime(receiveTime);

		//测试创建
		messageInboxID = messageInboxDAO.createMessageInbox(messageInbox);
		assertNotNull(messageInboxID);

		//测试通过编号获得对象
		MessageInbox destMessageInbox = messageInboxDAO.getMessageInboxByID(messageInboxID);
		assertNotNull(destMessageInbox);
		assertEquals(messageInboxID,destMessageInbox.getMessageInboxID());
		assertEquals(senderName,destMessageInbox.getSenderName());
		assertEquals(receiverID,destMessageInbox.getReceiverID());
		assertEquals(title,destMessageInbox.getTitle());
		assertEquals(content,destMessageInbox.getContent());
		assertEquals(readFlag,destMessageInbox.getReadFlag());
		// assertEquals(receiveTime,destMessageInbox.getReceiveTime());

		//测试获得列表
		List<MessageInbox> messageInboxList = messageInboxDAO.getMessageInboxList();
		assertFalse(messageInboxList.isEmpty());

		//测试更新
		messageInboxID = 1;
		senderName = "测试字符串";
		receiverID = 10;
		title = "字符串修改";
		content = "字符串修改";
		readFlag = 10;
		destMessageInbox.setMessageInboxID(messageInboxID);
		destMessageInbox.setSenderName(senderName);
		destMessageInbox.setReceiverID(receiverID);
		destMessageInbox.setTitle(title);
		destMessageInbox.setContent(content);
		destMessageInbox.setReadFlag(readFlag);
		destMessageInbox.setReceiveTime(receiveTime);
		messageInboxDAO.updateMessageInbox(destMessageInbox);
		MessageInbox updatedMessageInbox = messageInboxDAO.getMessageInboxByID(messageInboxID);
		assertNotNull(updatedMessageInbox);
		assertEquals(messageInboxID,updatedMessageInbox.getMessageInboxID());
		assertEquals(senderName,updatedMessageInbox.getSenderName());
		assertEquals(receiverID,updatedMessageInbox.getReceiverID());
		assertEquals(title,updatedMessageInbox.getTitle());
		assertEquals(content,updatedMessageInbox.getContent());
		assertEquals(readFlag,updatedMessageInbox.getReadFlag());
		// assertEquals(receiveTime,updatedMessageInbox.getReceiveTime());

		//测试删除
		messageInboxDAO.deleteMessageInboxByID(messageInboxID);
		assertNull(messageInboxDAO.getMessageInboxByID(messageInboxID));

	}

}
