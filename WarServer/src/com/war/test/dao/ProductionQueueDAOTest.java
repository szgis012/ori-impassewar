package com.war.test.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IProductionQueueDAO;
import com.war.domain.ProductionQueue;

public class ProductionQueueDAOTest {

	private static IProductionQueueDAO productionQueueDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		productionQueueDAO = (IProductionQueueDAO)SpringService.getApplicationContext().getBean("productionQueueDAO");
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
		Integer amount = 1;
		Date startTime = new Date();
		Date finishTime = new Date();

		ProductionQueue productionQueue = new ProductionQueue();
		
		productionQueue.setCityID(cityID);
		productionQueue.setTargetID(targetID);
		productionQueue.setType(type);
		productionQueue.setAmount(amount);
		productionQueue.setStartTime(startTime);
		productionQueue.setFinishTime(finishTime);

		Integer productionQueueID = productionQueueDAO.createProductionQueue(productionQueue);
		
		//测试创建
		assertNotNull(productionQueueID);

		//测试通过编号获得对象
		ProductionQueue destProductionQueue = productionQueueDAO.getProductionQueueByID(productionQueueID);
		assertNotNull(destProductionQueue);
		assertEquals(productionQueueID,destProductionQueue.getProductionQueueID());
		assertEquals(cityID,destProductionQueue.getCityID());
		assertEquals(targetID,destProductionQueue.getTargetID());
		assertEquals(type,destProductionQueue.getType());
		assertEquals(amount,destProductionQueue.getAmount());
//		assertEquals(startTime,destProductionQueue.getStartTime());
//		assertEquals(finishTime,destProductionQueue.getFinishTime());

		//测试获得列表
		List<ProductionQueue> productionQueueList = productionQueueDAO.getProductionQueueList();
		assertFalse(productionQueueList.isEmpty());

		//测试更新
		cityID = 10;
		targetID = 10;
		type = 10;
		amount = 10;
		destProductionQueue.setProductionQueueID(productionQueueID);
		destProductionQueue.setCityID(cityID);
		destProductionQueue.setTargetID(targetID);
		destProductionQueue.setType(type);
		destProductionQueue.setAmount(amount);
		destProductionQueue.setStartTime(startTime);
		destProductionQueue.setFinishTime(finishTime);
		productionQueueDAO.updateProductionQueue(destProductionQueue);
		ProductionQueue updatedProductionQueue = productionQueueDAO.getProductionQueueByID(productionQueueID);
		assertNotNull(updatedProductionQueue);
		assertEquals(productionQueueID,updatedProductionQueue.getProductionQueueID());
		assertEquals(cityID,updatedProductionQueue.getCityID());
		assertEquals(targetID,updatedProductionQueue.getTargetID());
		assertEquals(type,updatedProductionQueue.getType());
		assertEquals(amount,updatedProductionQueue.getAmount());
//		assertEquals(startTime,updatedProductionQueue.getStartTime());
//		assertEquals(finishTime,updatedProductionQueue.getFinishTime());

		//测试删除
		productionQueueDAO.deleteProductionQueueByID(productionQueueID);
		assertNull(productionQueueDAO.getProductionQueueByID(productionQueueID));

	}

}