package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityTechnologyDAO;
import com.war.domain.CityTechnology;

public class CityTechnologyDAOTest {

	private static ICityTechnologyDAO cityTechnologyDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityTechnologyDAO = (ICityTechnologyDAO)SpringService.getApplicationContext().getBean("cityTechnologyDAO");
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
		Integer technologyID = 1;
		Integer level = 1;
		Integer state = 1;

		CityTechnology cityTechnology = new CityTechnology();
		
		cityTechnology.setCityID(cityID);
		cityTechnology.setTechnologyID(technologyID);
		cityTechnology.setLevel(level);
		cityTechnology.setState(state);

		//测试创建
		Integer cityTechnologyID = cityTechnologyDAO.createCityTechnology(cityTechnology);
		assertNotNull(cityTechnologyID);

		//测试通过编号获得对象
		CityTechnology destCityTechnology = cityTechnologyDAO.getCityTechnologyByID(cityTechnologyID);
		assertNotNull(destCityTechnology);
		assertEquals(cityTechnologyID,destCityTechnology.getCityTechnologyID());
		assertEquals(cityID,destCityTechnology.getCityID());
		assertEquals(technologyID,destCityTechnology.getTechnologyID());
		assertEquals(level,destCityTechnology.getLevel());
		assertEquals(state,destCityTechnology.getState());
		
		//测试获得列表
		List<CityTechnology> cityTechnologyList = cityTechnologyDAO.getCityTechnologyList();
		assertFalse(cityTechnologyList.isEmpty());

		//测试更新
		cityID = 10;
		technologyID = 10;
		level = 10;
		state = 10;
		destCityTechnology.setCityID(cityID);
		destCityTechnology.setTechnologyID(technologyID);
		destCityTechnology.setLevel(level);
		destCityTechnology.setState(state);
		cityTechnologyDAO.updateCityTechnology(destCityTechnology);
		CityTechnology updatedCityTechnology = cityTechnologyDAO.getCityTechnologyByID(cityTechnologyID);
		assertNotNull(updatedCityTechnology);
		assertEquals(cityTechnologyID,updatedCityTechnology.getCityTechnologyID());
		assertEquals(cityID,updatedCityTechnology.getCityID());
		assertEquals(technologyID,updatedCityTechnology.getTechnologyID());
		assertEquals(level,updatedCityTechnology.getLevel());
		assertEquals(state,updatedCityTechnology.getState());

		//测试删除
		cityTechnologyDAO.deleteCityTechnologyByID(cityTechnologyID);
		assertNull(cityTechnologyDAO.getCityTechnologyByID(cityTechnologyID));

	}

}