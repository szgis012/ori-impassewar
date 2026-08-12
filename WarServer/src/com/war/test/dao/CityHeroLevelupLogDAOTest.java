package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityHeroLevelupLogDAO;
import com.war.domain.CityHeroLevelupLog;

public class CityHeroLevelupLogDAOTest {

	private static ICityHeroLevelupLogDAO cityHeroLevelupLogDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityHeroLevelupLogDAO = (ICityHeroLevelupLogDAO)SpringService.getApplicationContext().getBean("cityHeroLevelupLogDAO");
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

		Integer cityHeroID = 1;
		Integer level = 1;
		Integer addCommand = 1;
		Integer addDefense = 1;
		Integer addMind = 1;
		Integer addExecutivepower = 1;

		CityHeroLevelupLog cityHeroLevelupLog = new CityHeroLevelupLog();
		
		cityHeroLevelupLog.setCityHeroID(cityHeroID);
		cityHeroLevelupLog.setLevel(level);
		cityHeroLevelupLog.setAddCommand(addCommand);
		cityHeroLevelupLog.setAddDefense(addDefense);
		cityHeroLevelupLog.setAddMind(addMind);
		cityHeroLevelupLog.setAddExecutivepower(addExecutivepower);

		//测试创建
		Integer cityHeroLevelupLogID = cityHeroLevelupLogDAO.createCityHeroLevelupLog(cityHeroLevelupLog);
		assertNotNull(cityHeroLevelupLogID);

		//测试通过编号获得对象
		CityHeroLevelupLog destCityHeroLevelupLog = cityHeroLevelupLogDAO.getCityHeroLevelupLogByID(cityHeroLevelupLogID);
		assertNotNull(destCityHeroLevelupLog);
		assertEquals(cityHeroLevelupLogID,destCityHeroLevelupLog.getCityHeroLevelupLogID());
		assertEquals(cityHeroID,destCityHeroLevelupLog.getCityHeroID());
		assertEquals(level,destCityHeroLevelupLog.getLevel());
		assertEquals(addCommand,destCityHeroLevelupLog.getAddCommand());
		assertEquals(addDefense,destCityHeroLevelupLog.getAddDefense());
		assertEquals(addMind,destCityHeroLevelupLog.getAddMind());
		assertEquals(addExecutivepower,destCityHeroLevelupLog.getAddExecutivepower());

		//测试获得列表
		List<CityHeroLevelupLog> cityHeroLevelupLogList = cityHeroLevelupLogDAO.getCityHeroLevelupLogList();
		assertFalse(cityHeroLevelupLogList.isEmpty());

		//测试更新
		cityHeroID = 10;
		level = 10;
		addCommand = 10;
		addDefense = 10;
		addMind = 10;
		addExecutivepower = 10;
		destCityHeroLevelupLog.setCityHeroLevelupLogID(cityHeroLevelupLogID);
		destCityHeroLevelupLog.setCityHeroID(cityHeroID);
		destCityHeroLevelupLog.setLevel(level);
		destCityHeroLevelupLog.setAddCommand(addCommand);
		destCityHeroLevelupLog.setAddDefense(addDefense);
		destCityHeroLevelupLog.setAddMind(addMind);
		destCityHeroLevelupLog.setAddExecutivepower(addExecutivepower);
		cityHeroLevelupLogDAO.updateCityHeroLevelupLog(destCityHeroLevelupLog);
		CityHeroLevelupLog updatedCityHeroLevelupLog = cityHeroLevelupLogDAO.getCityHeroLevelupLogByID(cityHeroLevelupLogID);
		assertNotNull(updatedCityHeroLevelupLog);
		assertEquals(cityHeroLevelupLogID,updatedCityHeroLevelupLog.getCityHeroLevelupLogID());
		assertEquals(cityHeroID,updatedCityHeroLevelupLog.getCityHeroID());
		assertEquals(level,updatedCityHeroLevelupLog.getLevel());
		assertEquals(addCommand,updatedCityHeroLevelupLog.getAddCommand());
		assertEquals(addDefense,updatedCityHeroLevelupLog.getAddDefense());
		assertEquals(addMind,updatedCityHeroLevelupLog.getAddMind());
		assertEquals(addExecutivepower,updatedCityHeroLevelupLog.getAddExecutivepower());

		//测试删除
		cityHeroLevelupLogDAO.deleteCityHeroLevelupLogByID(cityHeroLevelupLogID);
		assertNull(cityHeroLevelupLogDAO.getCityHeroLevelupLogByID(cityHeroLevelupLogID));

	}
}
