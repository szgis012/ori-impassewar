package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ISpyQueueDAO;
import com.war.domain.SpyQueue;

public class SpyQueueDAOTest {

	private static ISpyQueueDAO spyQueueDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spyQueueDAO = (ISpyQueueDAO)SpringService.getApplicationContext().getBean("spyQueueDAO");
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

		Integer fromCityID = 1;
		Integer toPosX = 1;
		Integer toPosY = 1;
		Integer num = 1;
		Integer state = 1;
		Date finishTime = new Date();

		SpyQueue spyQueue = new SpyQueue();
		
		spyQueue.setCityID(fromCityID);
		//spyQueue.setPosX(toPosX);
		//spyQueue.setPosY(toPosY);
		spyQueue.setNum(num);
		spyQueue.setState(state);
		spyQueue.setFinishTime(finishTime);

		//测试创建
		Integer spyQueueID = spyQueueDAO.createSpyQueue(spyQueue);
		assertNotNull(spyQueueID);

		//测试通过编号获得对象
		SpyQueue destSpyQueue = spyQueueDAO.getSpyQueueByID(spyQueueID);
		assertNotNull(destSpyQueue);
		assertEquals(spyQueueID,destSpyQueue.getSpyQueueID());
		assertEquals(fromCityID,destSpyQueue.getCityID());
		//assertEquals(toPosX,destSpyQueue.getPosX());
		//assertEquals(toPosY,destSpyQueue.getPosY());
		assertEquals(num,destSpyQueue.getNum());
		assertEquals(state,destSpyQueue.getState());
		
		//测试获得列表
		List<SpyQueue> spyQueueList = spyQueueDAO.getSpyQueueList();
		assertFalse(spyQueueList.isEmpty());

		spyQueueList = spyQueueDAO.getFinishSpyQueueList();
		assertFalse(spyQueueList.isEmpty());
		
		spyQueueList = spyQueueDAO.getSpyQueueListByCityID(fromCityID);
		assertFalse(spyQueueList.isEmpty());
		
		//测试更新
		fromCityID = 10;
		toPosX = 10;
		toPosY = 10;
		num = 10;
		state = 10;
		destSpyQueue.setSpyQueueID(spyQueueID);
		destSpyQueue.setCityID(fromCityID);
		//destSpyQueue.setPosX(toPosX);
		//destSpyQueue.setPosY(toPosY);
		destSpyQueue.setNum(num);
		destSpyQueue.setState(state);
		destSpyQueue.setFinishTime(finishTime);
		spyQueueDAO.updateSpyQueue(destSpyQueue);
		SpyQueue updatedSpyQueue = spyQueueDAO.getSpyQueueByID(spyQueueID);
		assertNotNull(updatedSpyQueue);
		assertEquals(spyQueueID,updatedSpyQueue.getSpyQueueID());
		assertEquals(fromCityID,updatedSpyQueue.getCityID());
		//assertEquals(toPosX,updatedSpyQueue.getPosX());
		//assertEquals(toPosY,updatedSpyQueue.getPosY());
		assertEquals(num,updatedSpyQueue.getNum());
		assertEquals(state,updatedSpyQueue.getState());

		//测试删除
		spyQueueDAO.deleteSpyQueueByID(spyQueueID);
		assertNull(spyQueueDAO.getSpyQueueByID(spyQueueID));

	}

}