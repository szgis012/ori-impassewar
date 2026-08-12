package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IResTransportationDAO;
import com.war.domain.ResTransportation;

public class ResTransportationDAOTest {

	private static IResTransportationDAO resTransportationDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		resTransportationDAO = (IResTransportationDAO)SpringService.getApplicationContext().getBean("resTransportationDAO");
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

		Long woodAmount = 1L;
		Long steelAmount = 1L;
		Long oilAmount = 1L;
		Long foodAmount = 1L;
		Long moneyAmount = 1L;

		ResTransportation resTransportation = new ResTransportation();
		
		resTransportation.setWoodAmount(woodAmount);
		resTransportation.setSteelAmount(steelAmount);
		resTransportation.setOilAmount(oilAmount);
		resTransportation.setFoodAmount(foodAmount);
		resTransportation.setMoneyAmount(moneyAmount);

		//测试创建
		Integer resTransportationID = resTransportationDAO.createResTransportation(resTransportation);
		assertNotNull(resTransportationID);

		//测试通过编号获得对象
		ResTransportation destResTransportation = resTransportationDAO.getResTransportationByID(resTransportationID);
		assertNotNull(destResTransportation);
		assertEquals(resTransportationID,destResTransportation.getResTransportationID());
		assertEquals(woodAmount,destResTransportation.getWoodAmount());
		assertEquals(steelAmount,destResTransportation.getSteelAmount());
		assertEquals(oilAmount,destResTransportation.getOilAmount());
		assertEquals(foodAmount,destResTransportation.getFoodAmount());
		assertEquals(moneyAmount,destResTransportation.getMoneyAmount());
		
		//测试获得列表
		List<ResTransportation> resTransportationList = resTransportationDAO.getResTransportationList();
		assertFalse(resTransportationList.isEmpty());

/*		//测试更新
		resTransportationID = 10;
		woodAmount = 10L;
		steelAmount = 10L;
		oilAmount = 10L;
		foodAmount = 10L;
		moneyAmount = 10L;
		destResTransportation.setResTransportationID(resTransportationID);
		destResTransportation.setWoodAmount(woodAmount);
		destResTransportation.setSteelAmount(steelAmount);
		destResTransportation.setOilAmount(oilAmount);
		destResTransportation.setFoodAmount(foodAmount);
		destResTransportation.setMoneyAmount(moneyAmount);
		resTransportationDAO.updateResTransportation(destResTransportation);
		ResTransportation updatedResTransportation = resTransportationDAO.getResTransportationByID(resTransportationID);
		assertNotNull(updatedResTransportation);
		assertEquals(resTransportationID,updatedResTransportation.getResTransportationID());
		assertEquals(woodAmount,updatedResTransportation.getWoodAmount());
		assertEquals(steelAmount,updatedResTransportation.getSteelAmount());
		assertEquals(oilAmount,updatedResTransportation.getOilAmount());
		assertEquals(foodAmount,updatedResTransportation.getFoodAmount());
		assertEquals(moneyAmount,updatedResTransportation.getMoneyAmount());*/

		//测试删除
		resTransportationDAO.deleteResTransportationByID(resTransportationID);
		assertNull(resTransportationDAO.getResTransportationByID(resTransportationID));

	}

}