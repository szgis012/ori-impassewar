package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ITreasureHistoryDAO;
import com.war.domain.TreasureHistory;

public class TreasureHistoryDAOTest {

	private static ITreasureHistoryDAO treasureHistoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		treasureHistoryDAO = (ITreasureHistoryDAO)SpringService.getApplicationContext().getBean("treasureHistoryDAO");
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
		Integer type = 1;

		TreasureHistory treasureHistory = new TreasureHistory();
		
		treasureHistory.setPlayerID(playerID);
		treasureHistory.setTreasureID(treasureID);
		treasureHistory.setNum(num);
		treasureHistory.setType(type);

		//测试创建
		Integer treasureHistoryID = treasureHistoryDAO.createTreasureHistory(treasureHistory);
		assertNotNull(treasureHistoryID);

		//测试通过编号获得对象
		TreasureHistory destTreasureHistory = treasureHistoryDAO.getTreasureHistoryByID(treasureHistoryID);
		assertNotNull(destTreasureHistory);
		assertEquals(treasureHistoryID,destTreasureHistory.getTreasureHistoryID());
		assertEquals(playerID,destTreasureHistory.getPlayerID());
		assertEquals(treasureID,destTreasureHistory.getTreasureID());
		assertEquals(num,destTreasureHistory.getNum());
		assertEquals(type,destTreasureHistory.getType());
		
		//测试获得列表
		List<TreasureHistory> treasureHistoryList = treasureHistoryDAO.getTreasureHistoryList();
		assertFalse(treasureHistoryList.isEmpty());

		//测试更新
		playerID = 10;
		treasureID = 10;
		num = 10;
		type = 10;
		destTreasureHistory.setTreasureHistoryID(treasureHistoryID);
		destTreasureHistory.setPlayerID(playerID);
		destTreasureHistory.setTreasureID(treasureID);
		destTreasureHistory.setNum(num);
		destTreasureHistory.setType(type);
		treasureHistoryDAO.updateTreasureHistory(destTreasureHistory);
		TreasureHistory updatedTreasureHistory = treasureHistoryDAO.getTreasureHistoryByID(treasureHistoryID);
		assertNotNull(updatedTreasureHistory);
		assertEquals(treasureHistoryID,updatedTreasureHistory.getTreasureHistoryID());
		assertEquals(playerID,updatedTreasureHistory.getPlayerID());
		assertEquals(treasureID,updatedTreasureHistory.getTreasureID());
		assertEquals(num,updatedTreasureHistory.getNum());
		assertEquals(type,updatedTreasureHistory.getType());

		//测试删除
		treasureHistoryDAO.deleteTreasureHistoryByID(treasureHistoryID);
		assertNull(treasureHistoryDAO.getTreasureHistoryByID(treasureHistoryID));

	}

}