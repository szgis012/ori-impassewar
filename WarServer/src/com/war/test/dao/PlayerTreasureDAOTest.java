package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IPlayerTreasureDAO;
import com.war.domain.PlayerTreasure;


public class PlayerTreasureDAOTest {

	private static IPlayerTreasureDAO playerTreasureDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playerTreasureDAO = (IPlayerTreasureDAO)SpringService.getApplicationContext().getBean("playerTreasureDAO");
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

		Integer playerID = 1;
		Integer treasureID = 1;
		Integer num = 1;

		PlayerTreasure playerTreasure = new PlayerTreasure();
		
		playerTreasure.setPlayerID(playerID);
		playerTreasure.setTreasureID(treasureID);
		playerTreasure.setNum(num);

		//测试创建
		playerTreasureDAO.createPlayerTreasure(playerTreasure);

		//测试通过编号获得对象
		//PlayerTreasure destPlayerTreasure = playerTreasureDAO.getPlayerTreasureByID(playerTreasure);
		//assertNotNull(destPlayerTreasure);
		//assertEquals(playerID,destPlayerTreasure.getPlayerID());
		//assertEquals(treasureID,destPlayerTreasure.getTreasureID());
		//assertEquals(num,destPlayerTreasure.getNum());
		
		//测试获得列表
		List<PlayerTreasure> playerTreasureList = playerTreasureDAO.getPlayerTreasureList(playerID);
		assertFalse(playerTreasureList.isEmpty());

		//测试更新
		//playerID = 1;
		//treasureID = 1;
		//num = 10;
		//destPlayerTreasure.setPlayerID(playerID);
		//destPlayerTreasure.setTreasureID(treasureID);
		//destPlayerTreasure.setNum(num);
		//playerTreasureDAO.updatePlayerTreasure(destPlayerTreasure);
		//PlayerTreasure updatedPlayerTreasure = playerTreasureDAO.getPlayerTreasureByID(destPlayerTreasure);
		//assertNotNull(updatedPlayerTreasure);
		//assertEquals(playerID,updatedPlayerTreasure.getPlayerID());
		//assertEquals(treasureID,updatedPlayerTreasure.getTreasureID());
		//assertEquals(num,updatedPlayerTreasure.getNum());

		//测试删除
		//playerTreasureDAO.deletePlayerTreasureByID(destPlayerTreasure);
		//assertNull(playerTreasureDAO.getPlayerTreasureByID(destPlayerTreasure));

	}

}