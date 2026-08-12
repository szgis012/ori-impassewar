package com.war.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.domain.Player;
import com.war.domain.PlayerTreasure;
import com.war.exception.GameException;
import com.war.service.IPlayerService;
import com.war.service.ITreasureService;

public class TreasureServiceTest {
	static ITreasureService treasureService;
	static Integer playerID = 1;
	static Integer treasureID = 1;
	
	private static IPlayerService playerService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		treasureService = (ITreasureService)SpringService.getApplicationContext().getBean("treasureService");
		playerService = (IPlayerService) SpringService.getApplicationContext().getBean("playerService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		treasureService.increasePlayerTreasure(playerID, treasureID, 1);
	}

	@After
	public void tearDown() throws Exception {
		treasureService.deletePlayerTreasure(playerID);
	}

	// @Test
	public void testUseTreasure() {
		treasureService.useTreasure(playerID, treasureID,null);
		
		try{
			treasureService.useTreasure(playerID, treasureID,null);
		}catch(GameException ex){
			assertEquals("宝物数量不足",ex.getMessage());
		}
	}

	// @Test
	public void testDecreasePlayerTreasure() {
		try{
			treasureService.decreasePlayerTreasure(playerID, treasureID, 2);
		}catch(GameException ex){
			assertEquals("宝物数量不足",ex.getMessage());
		}
		
		treasureService.decreasePlayerTreasure(playerID, treasureID, 1);
		PlayerTreasure pt = treasureService.getPlayerTreasureByID(playerID, treasureID);
		assertNull(pt);
	}

	// @Test
	public void testIncreasePlayerTreasure() {
		Integer tid = 2;
		treasureService.increasePlayerTreasure(playerID, tid, 10);
		PlayerTreasure pt = treasureService.getPlayerTreasureByID(playerID, tid);
		assertEquals(10,pt.getNum());
		treasureService.increasePlayerTreasure(playerID, tid, 10);
		pt = treasureService.getPlayerTreasureByID(playerID, tid);
		assertEquals(20,pt.getNum());
	}
	
	@Test
	public void testRewardTreasure() {
		List<Player> playerList = playerService.getPlayerList();
		System.out.println(playerList.size());
		for (Player player : playerList) {
			treasureService.increasePlayerTreasure(player.getPlayerID(), 114, 10);
		}
	}

}
