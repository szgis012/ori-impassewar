package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ITreasureQueueDAO;
import com.war.domain.TreasureQueue;

public class TreasureQueueDAOTest {

	private static ITreasureQueueDAO treasureQueueDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		treasureQueueDAO = (ITreasureQueueDAO)SpringService.getApplicationContext().getBean("treasureQueueDAO");
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

		Integer cityID = 1;
		Integer category = 1;
		Integer type = 1;

		TreasureQueue treasureQueue = new TreasureQueue();
		
		treasureQueue.setTargetID(cityID);
		treasureQueue.setCategory(category);
		treasureQueue.setType(type);

		//测试创建
		Integer treasureQueueID = treasureQueueDAO.createTreasureQueue(treasureQueue);
		assertNotNull(treasureQueueID);

		//测试通过编号获得对象
		TreasureQueue destTreasureQueue = treasureQueueDAO.getTreasureQueueByID(treasureQueueID);
		assertNotNull(destTreasureQueue);
		assertEquals(treasureQueueID,destTreasureQueue.getTreasureQueueID());
		assertEquals(cityID,destTreasureQueue.getTargetID());
		assertEquals(category,destTreasureQueue.getCategory());
		assertEquals(type,destTreasureQueue.getType());
		
		//测试获得列表
		List<TreasureQueue> treasureQueueList = treasureQueueDAO.getTreasureQueueList();
		assertFalse(treasureQueueList.isEmpty());

		//测试更新
		cityID = 10;
		category = 10;
		type = 10;
		destTreasureQueue.setTreasureQueueID(treasureQueueID);
		destTreasureQueue.setTargetID(cityID);
		destTreasureQueue.setCategory(category);
		destTreasureQueue.setType(type);
		treasureQueueDAO.updateTreasureQueue(destTreasureQueue);
		TreasureQueue updatedTreasureQueue = treasureQueueDAO.getTreasureQueueByID(treasureQueueID);
		assertNotNull(updatedTreasureQueue);
		assertEquals(treasureQueueID,updatedTreasureQueue.getTreasureQueueID());
		assertEquals(cityID,updatedTreasureQueue.getTargetID());
		assertEquals(category,updatedTreasureQueue.getCategory());
		assertEquals(type,updatedTreasureQueue.getType());

		//测试删除
		treasureQueueDAO.deleteTreasureQueueByID(treasureQueueID);
		assertNull(treasureQueueDAO.getTreasureQueueByID(treasureQueueID));

	}

}