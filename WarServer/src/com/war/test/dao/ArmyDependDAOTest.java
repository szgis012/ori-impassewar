package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IArmyDependDAO;
import com.war.domain.ArmyDepend;

public class ArmyDependDAOTest {

	private static IArmyDependDAO armyDependDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		armyDependDAO = (IArmyDependDAO)SpringService.getApplicationContext().getBean("armyDependDAO");
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

		Integer armyID = 1;
		Integer ordnanceID = 1;
		Integer num = 1;

		ArmyDepend armyDepend = new ArmyDepend();
		
		armyDepend.setArmyID(armyID);
		armyDepend.setOrdnanceID(ordnanceID);
		armyDepend.setNum(num);

		//测试创建
		Integer armyDependID = armyDependDAO.createArmyDepend(armyDepend);
		assertNotNull(armyDependID);
		
		assertNotNull(armyDependDAO.getArmyDepend(armyID, ordnanceID));

		//测试通过编号获得对象
		ArmyDepend destArmyDepend = armyDependDAO.getArmyDependByID(armyDependID);
		assertNotNull(destArmyDepend);
		assertEquals(armyDependID,destArmyDepend.getArmyDependID());
		assertEquals(armyID,destArmyDepend.getArmyID());
		assertEquals(ordnanceID,destArmyDepend.getOrdnanceID());
		assertEquals(num,destArmyDepend.getNum());
		
		//测试获得列表
		List<ArmyDepend> armyDependList = armyDependDAO.getArmyDependList();
		assertFalse(armyDependList.isEmpty());
		
		armyDependList = armyDependDAO.getArmyDependList(armyID);
		assertFalse(armyDependList.isEmpty());

		//测试更新
//		armyDependID = 10;
//		armyID = 10;
//		ordnanceID = 10;
		num = 10;
		destArmyDepend.setArmyDependID(armyDependID);
		destArmyDepend.setArmyID(armyID);
		destArmyDepend.setOrdnanceID(ordnanceID);
		destArmyDepend.setNum(num);
		armyDependDAO.updateArmyDepend(destArmyDepend);
		ArmyDepend updatedArmyDepend = armyDependDAO.getArmyDependByID(armyDependID);
		assertNotNull(updatedArmyDepend);
		assertEquals(armyDependID,updatedArmyDepend.getArmyDependID());
		assertEquals(armyID,updatedArmyDepend.getArmyID());
		assertEquals(ordnanceID,updatedArmyDepend.getOrdnanceID());
		assertEquals(num,updatedArmyDepend.getNum());

		//测试删除
		armyDependDAO.deleteArmyDependByID(armyDependID);
		assertNull(armyDependDAO.getArmyDependByID(armyDependID));

	}

}