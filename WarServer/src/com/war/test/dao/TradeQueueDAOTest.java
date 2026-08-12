package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ITradeQueueDAO;
import com.war.domain.TradeQueue;

public class TradeQueueDAOTest {

	private static ITradeQueueDAO tradeQueueDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tradeQueueDAO = (ITradeQueueDAO)SpringService.getApplicationContext().getBean("tradeQueueDAO");
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
		Integer targetCityID = 1;
		Integer targetID = 1;
		Integer type = 1;

		TradeQueue tradeQueue = new TradeQueue();
		
		tradeQueue.setCityID(cityID);
		tradeQueue.setTargetCityID(targetCityID);
		tradeQueue.setTargetID(targetID);
		tradeQueue.setType(type);

		//测试创建
		Integer tradeQueueID = tradeQueueDAO.createTradeQueue(tradeQueue);
		assertNotNull(tradeQueueID);

		//测试通过编号获得对象
		TradeQueue destTradeQueue = tradeQueueDAO.getTradeQueueByID(tradeQueueID);
		assertNotNull(destTradeQueue);
		assertEquals(tradeQueueID,destTradeQueue.getTradeQueueID());
		assertEquals(cityID,destTradeQueue.getCityID());
		assertEquals(targetCityID,destTradeQueue.getTargetCityID());
		assertEquals(targetID,destTradeQueue.getTargetID());
		assertEquals(type,destTradeQueue.getType());
		
		//测试获得列表
		List<TradeQueue> tradeQueueList = tradeQueueDAO.getTradeQueueList();
		assertFalse(tradeQueueList.isEmpty());

		//测试更新
		cityID = 10;
		targetCityID = 10;
		targetID = 10;
		type = 10;
		destTradeQueue.setCityID(cityID);
		destTradeQueue.setTargetCityID(targetCityID);
		destTradeQueue.setTargetID(targetID);
		destTradeQueue.setType(type);
		tradeQueueDAO.updateTradeQueue(destTradeQueue);
		TradeQueue updatedTradeQueue = tradeQueueDAO.getTradeQueueByID(tradeQueueID);
		assertNotNull(updatedTradeQueue);
		assertEquals(tradeQueueID,updatedTradeQueue.getTradeQueueID());
		assertEquals(cityID,updatedTradeQueue.getCityID());
		assertEquals(targetCityID,updatedTradeQueue.getTargetCityID());
		assertEquals(targetID,updatedTradeQueue.getTargetID());
		assertEquals(type,updatedTradeQueue.getType());

		//测试删除
		tradeQueueDAO.deleteTradeQueueByID(tradeQueueID);
		assertNull(tradeQueueDAO.getTradeQueueByID(tradeQueueID));

	}

}