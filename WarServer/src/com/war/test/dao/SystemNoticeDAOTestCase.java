package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ISystemNoticeDAO;
import com.war.domain.SystemNotice;

public class SystemNoticeDAOTestCase {

	private static ISystemNoticeDAO systemNoticeDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		systemNoticeDAO = (ISystemNoticeDAO)SpringService.getApplicationContext().getBean("systemNoticeDAO");
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

		String content = "测试字符串";

		SystemNotice systemNotice = new SystemNotice();
		
		systemNotice.setContent(content);

		//测试创建
		Integer systemNoticeID = systemNoticeDAO.createSystemNotice(systemNotice);
		assertNotNull(systemNoticeID);

		//测试通过编号获得对象
		SystemNotice destSystemNotice = systemNoticeDAO.getSystemNoticeByID(systemNoticeID);
		assertNotNull(destSystemNotice);
		assertEquals(systemNoticeID,destSystemNotice.getSystemNoticeID());
		assertEquals(content,destSystemNotice.getContent());

		//测试获得列表
		List<SystemNotice> systemNoticeList = systemNoticeDAO.getSystemNoticeList();
		assertFalse(systemNoticeList.isEmpty());

		//测试更新
		content = "字符串修改";
		destSystemNotice.setSystemNoticeID(systemNoticeID);
		destSystemNotice.setContent(content);
		systemNoticeDAO.updateSystemNotice(destSystemNotice);
		SystemNotice updatedSystemNotice = systemNoticeDAO.getSystemNoticeByID(systemNoticeID);
		assertNotNull(updatedSystemNotice);
		assertEquals(systemNoticeID,updatedSystemNotice.getSystemNoticeID());
		assertEquals(content,updatedSystemNotice.getContent());

		//测试删除
		systemNoticeDAO.deleteSystemNoticeByID(systemNoticeID);
		assertNull(systemNoticeDAO.getSystemNoticeByID(systemNoticeID));

	}

}