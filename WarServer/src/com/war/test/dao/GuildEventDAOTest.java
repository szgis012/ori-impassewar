package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IGuildEventDAO;
import com.war.domain.GuildEvent;

public class GuildEventDAOTest {

	private static IGuildEventDAO guildEventDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildEventDAO = (IGuildEventDAO)SpringService.getApplicationContext().getBean("guildEventDAO");
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

		Integer guildID = 1;
		String description = "测试字符串";

		GuildEvent guildEvent = new GuildEvent();
		
		guildEvent.setGuildID(guildID);
		guildEvent.setDescription(description);

		//测试创建
		Integer guildEventID = guildEventDAO.createGuildEvent(guildEvent);
		assertNotNull(guildEventID);

		/*//测试通过编号获得对象
		GuildEvent destGuildEvent = guildEventDAO.getGuildEventByID(guildEventID);
		assertNotNull(destGuildEvent);
		assertEquals(guildEventID,destGuildEvent.getGuildEventID());
		assertEquals(guildID,destGuildEvent.getGuildID());
		assertEquals(description,destGuildEvent.getDescription());
		
		//测试获得列表
		List<GuildEvent> guildEventList = guildEventDAO.getGuildEventList();
		assertFalse(guildEventList.isEmpty());

		//测试更新
		guildID = 10;
		description = "字符串修改";
		destGuildEvent.setGuildEventID(guildEventID);
		destGuildEvent.setGuildID(guildID);
		destGuildEvent.setDescription(description);
		guildEventDAO.updateGuildEvent(destGuildEvent);
		GuildEvent updatedGuildEvent = guildEventDAO.getGuildEventByID(guildEventID);
		assertNotNull(updatedGuildEvent);
		assertEquals(guildEventID,updatedGuildEvent.getGuildEventID());
		assertEquals(guildID,updatedGuildEvent.getGuildID());
		assertEquals(description,updatedGuildEvent.getDescription());

		//测试删除
		guildEventDAO.deleteGuildEventByID(guildEventID);
		assertNull(guildEventDAO.getGuildEventByID(guildEventID));*/

	}

}