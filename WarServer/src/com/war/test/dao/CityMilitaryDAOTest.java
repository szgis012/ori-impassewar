package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityMilitaryDAO;
import com.war.domain.CityMilitary;

public class CityMilitaryDAOTest {

	private static ICityMilitaryDAO cityMilitaryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityMilitaryDAO = (ICityMilitaryDAO)SpringService.getApplicationContext().getBean("cityMilitaryDAO");
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

		String name = "测试字符串";
		Integer cityID = 1;
		Integer cityHeroID = 1;
		String army1 = "测试字符串";
		String army2 = "测试字符串";
		String army3 = "测试字符串";
		String army4 = "测试字符串";
		String army5 = "测试字符串";
		String army6 = "测试字符串";
		String army7 = "测试字符串";
		String army8 = "测试字符串";

		CityMilitary cityMilitary = new CityMilitary();
		
		cityMilitary.setName(name);
		cityMilitary.setCityID(cityID);
		cityMilitary.setCityHeroID(cityHeroID);
		cityMilitary.setArmy1(army1);
		cityMilitary.setArmy2(army2);
		cityMilitary.setArmy3(army3);
		cityMilitary.setArmy4(army4);
		cityMilitary.setArmy5(army5);
		cityMilitary.setArmy6(army6);
		cityMilitary.setArmy7(army7);
		cityMilitary.setArmy8(army8);

		//测试创建
		Integer cityMilitaryID = cityMilitaryDAO.createCityMilitary(cityMilitary);
		assertNotNull(cityMilitaryID);

		//测试通过编号获得对象
		CityMilitary destCityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		assertNotNull(destCityMilitary);
		assertEquals(cityMilitaryID,destCityMilitary.getCityMilitaryID());
		assertEquals(name,destCityMilitary.getName());
		assertEquals(cityID,destCityMilitary.getCityID());
		assertEquals(cityHeroID,destCityMilitary.getCityHeroID());
		assertEquals(army1,destCityMilitary.getArmy1());
		assertEquals(army2,destCityMilitary.getArmy2());
		assertEquals(army3,destCityMilitary.getArmy3());
		assertEquals(army4,destCityMilitary.getArmy4());
		assertEquals(army5,destCityMilitary.getArmy5());
		assertEquals(army6,destCityMilitary.getArmy6());
		assertEquals(army7,destCityMilitary.getArmy7());
		assertEquals(army8,destCityMilitary.getArmy8());
		
		//测试获得列表
		//List<CityMilitary> cityMilitaryList = cityMilitaryDAO.getCityMilitaryList();
		//assertFalse(cityMilitaryList.isEmpty());

		//测试更新
		name = "字符串修改";
		cityID = 10;
		cityHeroID = 10;
		army1 = "字符串修改";
		army2 = "字符串修改";
		army3 = "字符串修改";
		army4 = "字符串修改";
		army5 = "字符串修改";
		army6 = "字符串修改";
		army7 = "字符串修改";
		army8 = "字符串修改";
		destCityMilitary.setName(name);
		destCityMilitary.setCityID(cityID);
		destCityMilitary.setCityHeroID(cityHeroID);
		destCityMilitary.setArmy1(army1);
		destCityMilitary.setArmy2(army2);
		destCityMilitary.setArmy3(army3);
		destCityMilitary.setArmy4(army4);
		destCityMilitary.setArmy5(army5);
		destCityMilitary.setArmy6(army6);
		destCityMilitary.setArmy7(army7);
		destCityMilitary.setArmy8(army8);
		cityMilitaryDAO.updateCityMilitary(destCityMilitary);
		CityMilitary updatedCityMilitary = cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID);
		assertNotNull(updatedCityMilitary);
		assertEquals(cityMilitaryID,updatedCityMilitary.getCityMilitaryID());
		assertEquals(name,updatedCityMilitary.getName());
		assertEquals(cityID,updatedCityMilitary.getCityID());
		assertEquals(cityHeroID,updatedCityMilitary.getCityHeroID());
		assertEquals(army1,updatedCityMilitary.getArmy1());
		assertEquals(army2,updatedCityMilitary.getArmy2());
		assertEquals(army3,updatedCityMilitary.getArmy3());
		assertEquals(army4,updatedCityMilitary.getArmy4());
		assertEquals(army5,updatedCityMilitary.getArmy5());
		assertEquals(army6,updatedCityMilitary.getArmy6());
		assertEquals(army7,updatedCityMilitary.getArmy7());
		assertEquals(army8,updatedCityMilitary.getArmy8());

		//测试删除
		cityMilitaryDAO.deleteCityMilitaryByID(cityMilitaryID);
		assertNull(cityMilitaryDAO.getCityMilitaryByID(cityMilitaryID));

	}
	
	@Test
	public void testExistsStayMilitary(){
		assertFalse(cityMilitaryDAO.existsStayMilitary(4));
	}

}