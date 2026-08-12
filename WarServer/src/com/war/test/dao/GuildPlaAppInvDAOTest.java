package com.war.test.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IGuildPlaAppInvDAO;
import com.war.domain.GuildPlaAppInv;

public class GuildPlaAppInvDAOTest {

	private static IGuildPlaAppInvDAO guildPlaAppInvDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildPlaAppInvDAO = (IGuildPlaAppInvDAO)SpringService.getApplicationContext().getBean("guildPlaAppInvDAO");
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
		Integer playerID = 1;
		Integer type = 1;

		GuildPlaAppInv guildPlaAppInv = new GuildPlaAppInv();
		
		guildPlaAppInv.setGuildID(guildID);
		guildPlaAppInv.setPlayerID(playerID);
		guildPlaAppInv.setType(type);

		//测试创建
		guildPlaAppInvDAO.createGuildPlaAppInv(guildPlaAppInv);
		//assertNotNull(guildPlaAppInvID);

		/*//测试通过编号获得对象
		GuildPlaAppInv destGuildPlaAppInv = guildPlaAppInvDAO.getGuildPlaAppInvByID(guildPlaAppInvID);
		assertNotNull(destGuildPlaAppInv);
		assertEquals(guildID,destGuildPlaAppInv.getGuildID());
		assertEquals(playerID,destGuildPlaAppInv.getPlayerID());
		assertEquals(playerName,destGuildPlaAppInv.getPlayerName());
		assertEquals(type,destGuildPlaAppInv.getType());
		assertEquals(createTime,destGuildPlaAppInv.getCreateTime());
		
		//测试获得列表
		List<GuildPlaAppInv> guildPlaAppInvList = guildPlaAppInvDAO.getGuildPlaAppInvList();
		assertFalse(guildPlaAppInvList.isEmpty());

		//测试更新
		guildID = 10;
		playerID = 10;
		playerName = "字符串修改";
		type = 10;
		destGuildPlaAppInv.setGuildID(guildID);
		destGuildPlaAppInv.setPlayerID(playerID);
		destGuildPlaAppInv.setPlayerName(playerName);
		destGuildPlaAppInv.setType(type);
		destGuildPlaAppInv.setCreateTime(createTime);
		guildPlaAppInvDAO.updateGuildPlaAppInv(destGuildPlaAppInv);
		GuildPlaAppInv updatedGuildPlaAppInv = guildPlaAppInvDAO.getGuildPlaAppInvByID(guildPlaAppInvID);
		assertNotNull(updatedGuildPlaAppInv);
		assertEquals(guildID,updatedGuildPlaAppInv.getGuildID());
		assertEquals(playerID,updatedGuildPlaAppInv.getPlayerID());
		assertEquals(playerName,updatedGuildPlaAppInv.getPlayerName());
		assertEquals(type,updatedGuildPlaAppInv.getType());
		assertEquals(createTime,updatedGuildPlaAppInv.getCreateTime());

		//测试删除
		guildPlaAppInvDAO.deleteGuildPlaAppInvByID(guildPlaAppInvID);
		assertNull(guildPlaAppInvDAO.getGuildPlaAppInvByID(guildPlaAppInvID));*/
	}

}