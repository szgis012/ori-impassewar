package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IBattleQueueDAO;
import com.war.domain.BattleQueue;

public class BattleQueueDAOTest {

	private static IBattleQueueDAO battleQueueDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		battleQueueDAO = (IBattleQueueDAO)SpringService.getApplicationContext().getBean("battleQueueDAO");
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

//		Integer battleQueueID = 1;
		Integer mapID = 1;
		Integer cityMilitaryID = 1;
		Integer order = 1;

		BattleQueue battleQueue = new BattleQueue();
		
		battleQueue.setMapID(mapID);
		battleQueue.setCityMilitaryID(cityMilitaryID);
		battleQueue.setOrder(order);

		//测试创建
		Integer battleQueueID = battleQueueDAO.createBattleQueue(battleQueue);
		assertNotNull(battleQueueID);

		//测试通过编号获得对象
		BattleQueue destBattleQueue = battleQueueDAO.getBattleQueueByID(battleQueueID);
		assertNotNull(destBattleQueue);
		assertEquals(battleQueueID,destBattleQueue.getBattleQueueID());
		assertEquals(mapID,destBattleQueue.getMapID());
		assertEquals(cityMilitaryID,destBattleQueue.getCityMilitaryID());
		assertEquals(order,destBattleQueue.getOrder());

		//测试获得列表
		List<BattleQueue> battleQueueList = battleQueueDAO.getBattleQueueList();
		assertFalse(battleQueueList.isEmpty());

		//测试更新
//		battleQueueID = 1;
		mapID = 10;
		cityMilitaryID = 10;
		order = 10;
		destBattleQueue.setBattleQueueID(battleQueueID);
		destBattleQueue.setMapID(mapID);
		destBattleQueue.setCityMilitaryID(cityMilitaryID);
		destBattleQueue.setOrder(order);
		battleQueueDAO.updateBattleQueue(destBattleQueue);
		BattleQueue updatedBattleQueue = battleQueueDAO.getBattleQueueByID(battleQueueID);
		assertNotNull(updatedBattleQueue);
		assertEquals(battleQueueID,updatedBattleQueue.getBattleQueueID());
		assertEquals(mapID,updatedBattleQueue.getMapID());
		assertEquals(cityMilitaryID,updatedBattleQueue.getCityMilitaryID());
		assertEquals(order,updatedBattleQueue.getOrder());

		//测试删除
		battleQueueDAO.deleteBattleQueueByID(battleQueueID);
		assertNull(battleQueueDAO.getBattleQueueByID(battleQueueID));

	}

}