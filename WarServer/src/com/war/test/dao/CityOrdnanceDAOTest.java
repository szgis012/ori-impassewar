package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityOrdnanceDAO;
import com.war.domain.CityOrdnance;

public class CityOrdnanceDAOTest {

	private static ICityOrdnanceDAO cityOrdnanceDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityOrdnanceDAO = (ICityOrdnanceDAO)SpringService.getApplicationContext().getBean("cityOrdnanceDAO");
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

		Integer ordnanceID = 1;
		Integer cityID = 1;
		Integer num = 1;

		CityOrdnance cityOrdnance = new CityOrdnance();
		
		cityOrdnance.setOrdnanceID(ordnanceID);
		cityOrdnance.setCityID(cityID);
		cityOrdnance.setNum(num);

		//测试创建
		Integer cityOrdnanceID = cityOrdnanceDAO.createCityOrdnance(cityOrdnance);
		assertNotNull(cityOrdnanceID);

		//测试通过编号获得对象
		CityOrdnance destCityOrdnance = cityOrdnanceDAO.getCityOrdnanceByID(cityOrdnanceID);
		assertNotNull(destCityOrdnance);
		assertEquals(cityOrdnanceID,destCityOrdnance.getCityOrdnanceID());
		assertEquals(ordnanceID,destCityOrdnance.getOrdnanceID());
		assertEquals(cityID,destCityOrdnance.getCityID());
		assertEquals(num,destCityOrdnance.getNum());
		
		assertNotNull(cityOrdnanceDAO.getCityOrdnance(cityID, ordnanceID));
		
		//测试获得列表
		List<CityOrdnance> cityOrdnanceList = cityOrdnanceDAO.getCityOrdnanceList();
		assertFalse(cityOrdnanceList.isEmpty());
		cityOrdnanceList = cityOrdnanceDAO.getCityOrdnanceList(cityID);
		assertFalse(cityOrdnanceList.isEmpty());

		//测试更新
//		cityOrdnanceID = 10;
//		ordnanceID = 10;
//		cityID = 10;
		num = 10;
		destCityOrdnance.setCityOrdnanceID(cityOrdnanceID);
		destCityOrdnance.setOrdnanceID(ordnanceID);
		destCityOrdnance.setCityID(cityID);
		destCityOrdnance.setNum(num);
		cityOrdnanceDAO.updateCityOrdnance(destCityOrdnance);
		CityOrdnance updatedCityOrdnance = cityOrdnanceDAO.getCityOrdnanceByID(cityOrdnanceID);
		assertNotNull(updatedCityOrdnance);
		assertEquals(cityOrdnanceID,updatedCityOrdnance.getCityOrdnanceID());
		assertEquals(ordnanceID,updatedCityOrdnance.getOrdnanceID());
		assertEquals(cityID,updatedCityOrdnance.getCityID());
		assertEquals(num,updatedCityOrdnance.getNum());

		//测试删除
		cityOrdnanceDAO.deleteCityOrdnanceByID(cityOrdnanceID);
		assertNull(cityOrdnanceDAO.getCityOrdnanceByID(cityOrdnanceID));

	}

}
