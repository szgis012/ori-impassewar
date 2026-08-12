package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IStrongholdShbuildingDAO;
import com.war.domain.StrongholdShbuilding;

public class StrongholdShbuildingDAOTest {

	private static IStrongholdShbuildingDAO shShbuildingDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shShbuildingDAO = (IStrongholdShbuildingDAO)SpringService.getApplicationContext().getBean("strongholdShbuildingDAO");
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

		Integer strongholdID = 1;
		Integer shbuildingID = 1;
		Integer position = 1;
		Integer level = 1;
		Integer state = 1;

		StrongholdShbuilding shShbuilding = new StrongholdShbuilding();
		
		shShbuilding.setStrongholdID(strongholdID);
		shShbuilding.setShbuildingID(shbuildingID);
		shShbuilding.setPosition(position);
		shShbuilding.setLevel(level);
		shShbuilding.setState(state);

		//测试创建
		Integer shShbuildingID = shShbuildingDAO.createStrongholdShbuilding(shShbuilding);
		assertNotNull(shShbuildingID);
		
		assertNotNull(shShbuildingDAO.getStrongholdBuildingListByStrongholdID(strongholdID));

		//测试通过编号获得对象
		StrongholdShbuilding destShShbuilding = shShbuildingDAO.getStrongholdShbuildingByID(shShbuildingID);
		assertNotNull(destShShbuilding);
		assertEquals(shShbuildingID,destShShbuilding.getShShbuildingID());
		assertEquals(strongholdID,destShShbuilding.getStrongholdID());
		assertEquals(shbuildingID,destShShbuilding.getShbuildingID());
		assertEquals(position,destShShbuilding.getPosition());
		assertEquals(level,destShShbuilding.getLevel());
		assertEquals(state,destShShbuilding.getState());
		
		//测试获得列表
		List<StrongholdShbuilding> shShbuildingList = shShbuildingDAO.getStrongholdShbuildingList();
		assertFalse(shShbuildingList.isEmpty());

		//测试更新
		strongholdID = 10;
		shbuildingID = 10;
		position = 10;
		level = 10;
		state = 10;
		destShShbuilding.setShShbuildingID(shShbuildingID);
		destShShbuilding.setStrongholdID(strongholdID);
		destShShbuilding.setShbuildingID(shbuildingID);
		destShShbuilding.setPosition(position);
		destShShbuilding.setLevel(level);
		destShShbuilding.setState(state);
		shShbuildingDAO.updateStrongholdShbuilding(destShShbuilding);
		StrongholdShbuilding updatedShShbuilding = shShbuildingDAO.getStrongholdShbuildingByID(shShbuildingID);
		assertNotNull(updatedShShbuilding);
		assertEquals(shShbuildingID,updatedShShbuilding.getShShbuildingID());
		assertEquals(strongholdID,updatedShShbuilding.getStrongholdID());
		assertEquals(shbuildingID,updatedShShbuilding.getShbuildingID());
		assertEquals(position,updatedShShbuilding.getPosition());
		assertEquals(level,updatedShShbuilding.getLevel());
		assertEquals(state,updatedShShbuilding.getState());

		//测试删除
		shShbuildingDAO.deleteStrongholdShbuildingByID(shShbuildingID);
		assertNull(shShbuildingDAO.getStrongholdShbuildingByID(shShbuildingID));

	}

}