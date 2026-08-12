package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.log4j.Logger;
import com.war.common.SpringService;
import com.war.dao.IDepoyQueueDAO;
import com.war.domain.DepoyQueue;

public class DepoyQueueDAOTest {

	private static IDepoyQueueDAO depoyQueueDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		depoyQueueDAO = (IDepoyQueueDAO)SpringService.getApplicationContext().getBean("depoyQueueDAO");
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

		Logger logger = Logger.getLogger(DepoyQueueDAOTest.class);
		Integer cityMilitaryID = 1;
		Integer cityID = 1;
		Integer mapID = 1;
		Integer type = 1;
		Long carryFood = 1L;
		Long carryWood = 1L;
		Long carryOil = 1L;
		Long carrySteel = 1L;
		Long carryMoney = 1L;
		
		Date finishTime = new Date();

		DepoyQueue depoyQueue = new DepoyQueue();
		
		depoyQueue.setCityMilitaryID(cityMilitaryID);
		depoyQueue.setCityID(cityID);
		depoyQueue.setMapID(mapID);
		depoyQueue.setType(type);
		JSONObject carry = new JSONObject();
		try {
			carry.put("wood", carryWood);
			carry.put("steel", carrySteel);
			carry.put("oil", carryOil);
			carry.put("food", carryFood);
			carry.put("money", carryMoney);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		depoyQueue.setRemark(carry.toString());
		depoyQueue.setFinishTime(finishTime);

		//测试创建
		Integer depoyQueueID = depoyQueueDAO.createDepoyQueue(depoyQueue);
		assertNotNull(depoyQueueID);

		//测试通过编号获得对象
		DepoyQueue destDepoyQueue = depoyQueueDAO.getDepoyQueueByID(depoyQueueID);
		assertNotNull(destDepoyQueue);
		assertEquals(depoyQueueID,destDepoyQueue.getDepoyQueueID());
		assertEquals(cityMilitaryID,destDepoyQueue.getCityMilitaryID());
		assertEquals(mapID,destDepoyQueue.getMapID());
		assertEquals(type,destDepoyQueue.getType());
		JSONObject destCarry = null;
		try {
			destCarry = new JSONObject(destDepoyQueue.getRemark());
			assertEquals(carryFood,destCarry.getLong("food"));
			assertEquals(carryWood,destCarry.getLong("wood"));
			assertEquals(carryOil,destCarry.getLong("oil"));
			assertEquals(carrySteel,destCarry.getLong("steel"));
			assertEquals(carryMoney,destCarry.getLong("money"));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}

//		assertEquals(finishTime,destDepoyQueue.getFinishTime());
		
		//测试获得列表
		List<DepoyQueue> depoyQueueList = depoyQueueDAO.getDepoyQueueList();
		assertFalse(depoyQueueList.isEmpty());
		assertFalse(depoyQueueDAO.getFinishDepoyQueueList().isEmpty());

		//测试更新
		cityMilitaryID = 10;
		mapID = 10;
		type = 10;
		carryFood = 10L;
		carryWood = 10L;
		carryOil = 10L;
		carrySteel = 10L;
		carryMoney = 10L;
		destDepoyQueue.setDepoyQueueID(depoyQueueID);
		destDepoyQueue.setCityMilitaryID(cityMilitaryID);
		destDepoyQueue.setMapID(mapID);
		destDepoyQueue.setType(type);
		try {
			destCarry.put("wood", carryWood);
			destCarry.put("steel", carrySteel);
			destCarry.put("oil", carryOil);
			destCarry.put("food", carryFood);
			destCarry.put("money", carryMoney);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
		destDepoyQueue.setRemark(destCarry.toString());
		destDepoyQueue.setFinishTime(finishTime);
		depoyQueueDAO.updateDepoyQueue(destDepoyQueue);
		DepoyQueue updatedDepoyQueue = depoyQueueDAO.getDepoyQueueByID(depoyQueueID);
		assertNotNull(updatedDepoyQueue);
		assertEquals(depoyQueueID,updatedDepoyQueue.getDepoyQueueID());
		assertEquals(cityMilitaryID,updatedDepoyQueue.getCityMilitaryID());
		assertEquals(mapID,updatedDepoyQueue.getMapID());
		assertEquals(type,updatedDepoyQueue.getType());
		try {
			JSONObject updateCarry = new JSONObject(updatedDepoyQueue.getRemark());
			assertEquals(carryFood,updateCarry.getLong("food"));
			assertEquals(carryWood,updateCarry.getLong("wood"));
			assertEquals(carryOil,updateCarry.getLong("oil"));
			assertEquals(carrySteel,updateCarry.getLong("steel"));
			assertEquals(carryMoney,updateCarry.getLong("money"));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
//		assertEquals(finishTime,updatedDepoyQueue.getFinishTime());

		//测试删除
		//depoyQueueDAO.deleteDepoyQueueByID(depoyQueueID);
		//assertNull(depoyQueueDAO.getDepoyQueueByID(depoyQueueID));

	}

}