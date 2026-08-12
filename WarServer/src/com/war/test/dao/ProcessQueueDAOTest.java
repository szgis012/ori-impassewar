package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IProcessQueueDAO;
import com.war.domain.ProcessQueue;

public class ProcessQueueDAOTest {

	private static IProcessQueueDAO processQueueDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		processQueueDAO = (IProcessQueueDAO)SpringService.getApplicationContext().getBean("processQueueDAO");
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
		Integer targetID = 1;
		Integer type = 1;

		ProcessQueue processQueue = new ProcessQueue();
		
		processQueue.setCityID(cityID);
		processQueue.setTargetID(targetID);
		processQueue.setType(type);

		//测试创建
		Integer processQueueID = processQueueDAO.createProcessQueue(processQueue);
		assertNotNull(processQueueID);

		//测试通过编号获得对象
		ProcessQueue destProcessQueue = processQueueDAO.getProcessQueueByID(processQueueID);
		assertNotNull(destProcessQueue);
		assertEquals(processQueueID,destProcessQueue.getProcessQueueID());
		assertEquals(cityID,destProcessQueue.getCityID());
		assertEquals(targetID,destProcessQueue.getTargetID());
		assertEquals(type,destProcessQueue.getType());
		
		//测试获得列表
		List<ProcessQueue> processQueueList = processQueueDAO.getProcessQueueList();
		assertFalse(processQueueList.isEmpty());

		processQueueList = processQueueDAO.getProcessQueueList(cityID, type);
		assertTrue(processQueueList.size() > 0);
		
		//测试更新
		cityID = 10;
		targetID = 10;
		type = 10;
		destProcessQueue.setProcessQueueID(processQueueID);
		destProcessQueue.setCityID(cityID);
		destProcessQueue.setTargetID(targetID);
		destProcessQueue.setType(type);
		processQueueDAO.updateProcessQueue(destProcessQueue);
		ProcessQueue updatedProcessQueue = processQueueDAO.getProcessQueueByID(processQueueID);
		assertNotNull(updatedProcessQueue);
		assertEquals(processQueueID,updatedProcessQueue.getProcessQueueID());
		assertEquals(cityID,updatedProcessQueue.getCityID());
		assertEquals(targetID,updatedProcessQueue.getTargetID());
		assertEquals(type,updatedProcessQueue.getType());
		
		assertNotNull(processQueueDAO.getFinishTime(cityID, targetID, type));
		assertNotNull(processQueueDAO.getProcessQueue(cityID, targetID, type));

		//测试删除
		processQueueDAO.deleteProcessQueueByID(processQueueID);
		assertNull(processQueueDAO.getProcessQueueByID(processQueueID));

	}

}