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
import com.war.dao.IColonizationDAO;
import com.war.domain.Colonization;

public class ColonizationDAOTest {

	private static IColonizationDAO colonizationDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		colonizationDAO = (IColonizationDAO)SpringService.getApplicationContext().getBean("colonizationDAO");
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
		Date startTime = new Date();
		Date endTime = new Date();
		Integer haveImposed = 1;

		Colonization colonization = new Colonization();
		
		colonization.setCityID(cityID);
		colonization.setTargetCityID(targetCityID);
		colonization.setStartTime(startTime);
		colonization.setEndTime(endTime);
		colonization.setHaveImposed(haveImposed);

		//测试创建
		Integer colonizationID = colonizationDAO.createColonization(colonization);
		assertNotNull(colonizationID);

		//测试通过编号获得对象
		Colonization destColonization = colonizationDAO.getColonizationByID(colonizationID);
		assertNotNull(destColonization);
		assertEquals(colonizationID,destColonization.getColonizationID());
		assertEquals(cityID,destColonization.getCityID());
		assertEquals(targetCityID,destColonization.getTargetCityID());
		assertEquals(haveImposed,destColonization.getHaveImposed());
		
		//测试获得列表
		List<Colonization> colonizationList = colonizationDAO.getColonizationList();
		assertFalse(colonizationList.isEmpty());

		//测试更新
		cityID = 10;
		targetCityID = 10;
		haveImposed = 10;
		destColonization.setColonizationID(colonizationID);
		destColonization.setCityID(cityID);
		destColonization.setTargetCityID(targetCityID);
		destColonization.setHaveImposed(haveImposed);
		colonizationDAO.updateColonization(destColonization);
		Colonization updatedColonization = colonizationDAO.getColonizationByID(colonizationID);
		assertNotNull(updatedColonization);
		assertEquals(colonizationID,updatedColonization.getColonizationID());
		assertEquals(cityID,updatedColonization.getCityID());
		assertEquals(targetCityID,updatedColonization.getTargetCityID());
		assertEquals(haveImposed,updatedColonization.getHaveImposed());

		//测试删除
		colonizationDAO.deleteColonizationByID(colonizationID);
		assertNull(colonizationDAO.getColonizationByID(colonizationID));

	}

}