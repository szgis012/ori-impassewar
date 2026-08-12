package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityExtDAO;
import com.war.domain.CityExt;

public class CityExtDAOTest {

	private static ICityExtDAO cityExtDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityExtDAO = (ICityExtDAO)SpringService.getApplicationContext().getBean("cityExtDAO");
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
		Integer techArmyAttack = 1;
		Integer techArmyDefense = 1;
		Integer techArmySpeed = 1;
		Integer techArmyRange = 1;
		Integer techTruckAttack = 1;
		Integer techTruckDefense = 1;
		Integer techTruckSpeed = 1;
		Integer techTruckRange = 1;
		Integer techAirplaneAttack = 1;
		Integer techAirplaneDefense = 1;
		Integer techAirplaneSpeed = 1;
		Integer techAirplaneRange = 1;

		CityExt cityExt = new CityExt();
		
		cityExt.setCityID(cityID);
		cityExt.setTechArmyAttack(techArmyAttack);
		cityExt.setTechArmyDefense(techArmyDefense);
		cityExt.setTechArmySpeed(techArmySpeed);
		cityExt.setTechArmyRange(techArmyRange);
		cityExt.setTechTruckAttack(techTruckAttack);
		cityExt.setTechTruckDefense(techTruckDefense);
		cityExt.setTechTruckSpeed(techTruckSpeed);
		cityExt.setTechTruckRange(techTruckRange);
		cityExt.setTechAirplaneAttack(techAirplaneAttack);
		cityExt.setTechAirplaneDefense(techAirplaneDefense);
		cityExt.setTechAirplaneSpeed(techAirplaneSpeed);
		cityExt.setTechAirplaneRange(techAirplaneRange);

		//测试创建
		cityExtDAO.createCityExt(cityExt);
		//assertNotNull(cityExtID);

		//测试通过编号获得对象
		CityExt destCityExt = cityExtDAO.getCityExtByID(cityID);
		assertNotNull(destCityExt);
		assertEquals(cityID,destCityExt.getCityID());
		assertEquals(techArmyAttack,destCityExt.getTechArmyAttack());
		assertEquals(techArmyDefense,destCityExt.getTechArmyDefense());
		assertEquals(techArmySpeed,destCityExt.getTechArmySpeed());
		assertEquals(techArmyRange,destCityExt.getTechArmyRange());
		assertEquals(techTruckAttack,destCityExt.getTechTruckAttack());
		assertEquals(techTruckDefense,destCityExt.getTechTruckDefense());
		assertEquals(techTruckSpeed,destCityExt.getTechTruckSpeed());
		assertEquals(techTruckRange,destCityExt.getTechTruckRange());
		assertEquals(techAirplaneAttack,destCityExt.getTechAirplaneAttack());
		assertEquals(techAirplaneDefense,destCityExt.getTechAirplaneDefense());
		assertEquals(techAirplaneSpeed,destCityExt.getTechAirplaneSpeed());
		assertEquals(techAirplaneRange,destCityExt.getTechAirplaneRange());
		
		//测试获得列表
		List<CityExt> cityExtList = cityExtDAO.getCityExtList();
		assertFalse(cityExtList.isEmpty());

		//测试更新
		techArmyAttack = 10;
		techArmyDefense = 10;
		techArmySpeed = 10;
		techArmyRange = 10;
		techTruckAttack = 10;
		techTruckDefense = 10;
		techTruckSpeed = 10;
		techTruckRange = 10;
		techAirplaneAttack = 10;
		techAirplaneDefense = 10;
		techAirplaneSpeed = 10;
		techAirplaneRange = 10;
		destCityExt.setCityID(cityID);
		destCityExt.setTechArmyAttack(techArmyAttack);
		destCityExt.setTechArmyDefense(techArmyDefense);
		destCityExt.setTechArmySpeed(techArmySpeed);
		destCityExt.setTechArmyRange(techArmyRange);
		destCityExt.setTechTruckAttack(techTruckAttack);
		destCityExt.setTechTruckDefense(techTruckDefense);
		destCityExt.setTechTruckSpeed(techTruckSpeed);
		destCityExt.setTechTruckRange(techTruckRange);
		destCityExt.setTechAirplaneAttack(techAirplaneAttack);
		destCityExt.setTechAirplaneDefense(techAirplaneDefense);
		destCityExt.setTechAirplaneSpeed(techAirplaneSpeed);
		destCityExt.setTechAirplaneRange(techAirplaneRange);
		cityExtDAO.updateCityExt(destCityExt);
		CityExt updatedCityExt = cityExtDAO.getCityExtByID(cityID);
		assertNotNull(updatedCityExt);
		assertEquals(cityID,updatedCityExt.getCityID());
		assertEquals(techArmyAttack,updatedCityExt.getTechArmyAttack());
		assertEquals(techArmyDefense,updatedCityExt.getTechArmyDefense());
		assertEquals(techArmySpeed,updatedCityExt.getTechArmySpeed());
		assertEquals(techArmyRange,updatedCityExt.getTechArmyRange());
		assertEquals(techTruckAttack,updatedCityExt.getTechTruckAttack());
		assertEquals(techTruckDefense,updatedCityExt.getTechTruckDefense());
		assertEquals(techTruckSpeed,updatedCityExt.getTechTruckSpeed());
		assertEquals(techTruckRange,updatedCityExt.getTechTruckRange());
		assertEquals(techAirplaneAttack,updatedCityExt.getTechAirplaneAttack());
		assertEquals(techAirplaneDefense,updatedCityExt.getTechAirplaneDefense());
		assertEquals(techAirplaneSpeed,updatedCityExt.getTechAirplaneSpeed());
		assertEquals(techAirplaneRange,updatedCityExt.getTechAirplaneRange());

		//测试删除
		cityExtDAO.deleteCityExtByID(cityID);
		assertNull(cityExtDAO.getCityExtByID(cityID));

	}

}