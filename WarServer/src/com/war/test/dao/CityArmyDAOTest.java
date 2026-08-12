package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityArmyDAO;
import com.war.domain.CityArmy;

public class CityArmyDAOTest {

	private static ICityArmyDAO cityArmyDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityArmyDAO = (ICityArmyDAO)SpringService.getApplicationContext().getBean("cityArmyDAO");
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
		Integer armyID = 1;
		Integer num = 1;

		CityArmy cityArmy = new CityArmy();
		
		cityArmy.setCityID(cityID);
		cityArmy.setArmyID(armyID);
		cityArmy.setNum(num);

		//测试创建
		Integer cityArmyID = cityArmyDAO.createCityArmy(cityArmy);
		assertNotNull(cityArmyID);

		//测试通过编号获得对象
		CityArmy destCityArmy = cityArmyDAO.getCityArmyByID(cityArmyID);
		assertNotNull(destCityArmy);
		assertEquals(cityArmyID,destCityArmy.getCityArmyID());
		assertEquals(cityID,destCityArmy.getCityID());
		assertEquals(armyID,destCityArmy.getArmyID());
		assertEquals(num,destCityArmy.getNum());
		
		//测试更新
		num = 10;
		destCityArmy.setCityArmyID(cityArmyID);
		destCityArmy.setCityID(cityID);
		destCityArmy.setArmyID(armyID);
		destCityArmy.setNum(num);
		cityArmyDAO.updateCityArmy(destCityArmy);
		CityArmy updatedCityArmy = cityArmyDAO.getCityArmyByID(cityArmyID);
		assertNotNull(updatedCityArmy);
		assertEquals(cityArmyID,updatedCityArmy.getCityArmyID());
		assertEquals(cityID,updatedCityArmy.getCityID());
		assertEquals(armyID,updatedCityArmy.getArmyID());
		assertEquals(num,updatedCityArmy.getNum());

		//测试删除
		cityArmyDAO.deleteCityArmyByID(cityArmyID);
		assertNull(cityArmyDAO.getCityArmyByID(cityArmyID));

	}

}