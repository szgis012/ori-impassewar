package com.war.test.dao;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityDefenseDAO;
import com.war.domain.CityDefense;

public class CityDefenseDAOTest {

	private static ICityDefenseDAO cityDefenseDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityDefenseDAO = (ICityDefenseDAO)SpringService.getApplicationContext().getBean("cityDefenseDAO");
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

		Integer cityDefenseID = 1;
		Integer cityID = 2;
		Integer defenseID = 17;
		Integer num = 1;

		CityDefense cityDefense = new CityDefense();
		
		cityDefense.setCityDefenseID(cityDefenseID);
		cityDefense.setCityID(cityID);
		cityDefense.setDefenseID(defenseID);
		cityDefense.setNum(num);

		//测试创建
		cityDefenseID = cityDefenseDAO.createCityDefense(cityDefense);
		assertNotNull(cityDefenseID);

		//测试通过编号获得对象
		CityDefense destCityDefense = cityDefenseDAO.getCityDefenseByID(cityDefenseID);
		assertNotNull(destCityDefense);
		assertEquals(cityDefenseID,destCityDefense.getCityDefenseID());
		assertEquals(cityID,destCityDefense.getCityID());
		assertEquals(defenseID,destCityDefense.getDefenseID());
		assertEquals(num,destCityDefense.getNum());

		//测试获得列表
		List<CityDefense> cityDefenseList = cityDefenseDAO.getCityDefenseList();
		assertFalse(cityDefenseList.isEmpty());

		//测试更新
		cityID = 2;
		defenseID = 18;
		num = 10;
		destCityDefense.setCityDefenseID(cityDefenseID);
		destCityDefense.setCityID(cityID);
		destCityDefense.setDefenseID(defenseID);
		destCityDefense.setNum(num);
		cityDefenseDAO.updateCityDefense(destCityDefense);
		CityDefense updatedCityDefense = cityDefenseDAO.getCityDefenseByID(cityDefenseID);
		assertNotNull(updatedCityDefense);
		assertEquals(cityDefenseID,updatedCityDefense.getCityDefenseID());
		assertEquals(cityID,updatedCityDefense.getCityID());
		assertEquals(defenseID,updatedCityDefense.getDefenseID());
		assertEquals(num,updatedCityDefense.getNum());

		//测试删除
		cityDefenseDAO.deleteCityDefenseByID(cityDefenseID);
		assertNull(cityDefenseDAO.getCityDefenseByID(cityDefenseID));

	}
}