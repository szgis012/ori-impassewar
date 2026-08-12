package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IPlayerDAO;
import com.war.domain.Player;

public class PlayerDAOTest {

	private static IPlayerDAO playerDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playerDAO = (IPlayerDAO)SpringService.getApplicationContext().getBean("playerDAO");
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

		Integer userID = 1;
		String name = "测试字符串";
		Integer honorID = 1;
		Integer guildID = 1;
		Integer country = 1;
		Long renown = 1L;
		Long attackPoint = 1L;
		Long defensePoint = 1L;
		Integer rank = 1;

		Player player = new Player();
		
		//player.setUserID(userID);
		player.setName(name);
		player.setHonorID(honorID);
		player.setGuildID(guildID);
		player.setCountry(country);
		player.setRenown(renown);
		player.setAttackPoint(attackPoint);
		player.setDefensePoint(defensePoint);
		player.setRank(rank);

		//测试创建
		Integer playerID = playerDAO.createPlayer(player);
		assertNotNull(playerID);

		//测试通过编号获得对象
		Player destPlayer = playerDAO.getPlayerByID(playerID);
		assertNotNull(destPlayer);
		assertEquals(playerID,destPlayer.getPlayerID());
		//assertEquals(userID,destPlayer.getUserID());
		assertEquals(name,destPlayer.getName());
		assertEquals(honorID,destPlayer.getHonorID());
		assertEquals(guildID,destPlayer.getGuildID());
		assertEquals(country,destPlayer.getCountry());
		assertEquals(renown,destPlayer.getRenown());
		assertEquals(attackPoint,destPlayer.getAttackPoint());
		assertEquals(defensePoint,destPlayer.getDefensePoint());
		assertEquals(rank,destPlayer.getRank());
		
		//测试获得列表
		List<Player> playerList = playerDAO.getPlayerList();
		assertFalse(playerList.isEmpty());

		//测试更新
		userID = 10;
		name = "字符串修改";
		honorID = 10;
		guildID = 10;
		country = 10;
		renown = 10L;
		attackPoint = 10L;
		defensePoint = 10L;
		rank = 10;
		destPlayer.setPlayerID(playerID);
		//destPlayer.setUserID(userID);
		destPlayer.setName(name);
		destPlayer.setHonorID(honorID);
		destPlayer.setGuildID(guildID);
		destPlayer.setCountry(country);
		destPlayer.setRenown(renown);
		destPlayer.setAttackPoint(attackPoint);
		destPlayer.setDefensePoint(defensePoint);
		destPlayer.setRank(rank);
		playerDAO.updatePlayer(destPlayer);
		Player updatedPlayer = playerDAO.getPlayerByID(playerID);
		assertNotNull(updatedPlayer);
		assertEquals(playerID,updatedPlayer.getPlayerID());
		//assertEquals(userID,updatedPlayer.getUserID());
		//assertEquals(name,updatedPlayer.getName());
		assertEquals(honorID,updatedPlayer.getHonorID());
		assertEquals(guildID,updatedPlayer.getGuildID());
		assertEquals(country,updatedPlayer.getCountry());
		assertEquals(renown,updatedPlayer.getRenown());
		assertEquals(attackPoint,updatedPlayer.getAttackPoint());
		assertEquals(defensePoint,updatedPlayer.getDefensePoint());
		assertEquals(rank,updatedPlayer.getRank());

		//测试删除
		playerDAO.deletePlayerByID(playerID);
		assertNull(playerDAO.getPlayerByID(playerID));

	}
	
	@Test
	public void testUpdateLastLoginInfo(){
		Integer playerID = 4;
		
		Player player = playerDAO.getPlayerByID(playerID);
		Integer loginNum = player.getLoginNum();

		playerDAO.updateLastLoginInfo(playerID);
		
		player = playerDAO.getPlayerByID(playerID);
		
		assertEquals(loginNum+1,player.getLoginNum() );
		
	}
	
	
	@Test
	public void testGetFinshedFreshmanProtectList(){
		assertFalse(playerDAO.getFinshedFreshmanProtectList(2).isEmpty());
	}
	
	@Test
	public void testRenown(){
		Integer playerID = 14;
		playerDAO.updateRenown(playerID, 1000L);
		
		Player player = playerDAO.getPlayerByID(playerID);
		assertEquals(player.getRenown(), 1000);
		
	}

}