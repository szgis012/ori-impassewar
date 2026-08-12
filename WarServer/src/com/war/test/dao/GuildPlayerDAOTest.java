package com.war.test.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IGuildPlayerDAO;
import com.war.domain.GuildPlayer;

public class GuildPlayerDAOTest {

	private static IGuildPlayerDAO guildPlayerDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildPlayerDAO = (IGuildPlayerDAO)SpringService.getApplicationContext().getBean("guildPlayerDAO");
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
		Long contribution = 1L;
		String dutyName = "测试字符串";
		String permission = "测试字符串";

		GuildPlayer guildPlayer = new GuildPlayer();
		
		guildPlayer.setGuildID(guildID);
		guildPlayer.setPlayerID(playerID);
		guildPlayer.setContribution(contribution);
		guildPlayer.setDutyName(dutyName);
		guildPlayer.setPermission(permission);

		//测试创建
		guildPlayerDAO.createGuildPlayer(guildPlayer);

		/*//测试通过编号获得对象
		GuildPlayer destGuildPlayer = guildPlayerDAO.getGuildPlayerByID(guildPlayerID);
		assertNotNull(destGuildPlayer);
		assertEquals(guildID,destGuildPlayer.getGuildID());
		assertEquals(playerID,destGuildPlayer.getPlayerID());
		assertEquals(contribution,destGuildPlayer.getContribution());
		assertEquals(dutyName,destGuildPlayer.getDutyName());
		assertEquals(permission,destGuildPlayer.getPermission());
		
		//测试获得列表
		List<GuildPlayer> guildPlayerList = guildPlayerDAO.getGuildPlayerList();
		assertFalse(guildPlayerList.isEmpty());

		//测试更新
		guildID = 10;
		playerID = 10;
		contribution = 10L;
		dutyName = "字符串修改";
		permission = "字符串修改";
		destGuildPlayer.setGuildID(guildID);
		destGuildPlayer.setPlayerID(playerID);
		destGuildPlayer.setContribution(contribution);
		destGuildPlayer.setDutyName(dutyName);
		destGuildPlayer.setPermission(permission);
		guildPlayerDAO.updateGuildPlayer(destGuildPlayer);
		GuildPlayer updatedGuildPlayer = guildPlayerDAO.getGuildPlayerByID(guildPlayerID);
		assertNotNull(updatedGuildPlayer);
		assertEquals(guildID,updatedGuildPlayer.getGuildID());
		assertEquals(playerID,updatedGuildPlayer.getPlayerID());
		assertEquals(contribution,updatedGuildPlayer.getContribution());
		assertEquals(dutyName,updatedGuildPlayer.getDutyName());
		assertEquals(permission,updatedGuildPlayer.getPermission());*/

	}

}