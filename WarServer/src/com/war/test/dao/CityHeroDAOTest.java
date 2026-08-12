package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityHeroDAO;
import com.war.domain.CityHero;

public class CityHeroDAOTest {

	private static ICityHeroDAO cityHeroDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityHeroDAO = (ICityHeroDAO)SpringService.getApplicationContext().getBean("cityHeroDAO");
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
		String name = "测试字符";
		String head = "测试字符串";
		Integer level = 1;
		Long exp = 1L;
		Integer command = 1;
		Integer defense = 1;
		Integer mind = 1;
		Integer executivepower = 1;
		Integer unsetPoint = 1;
		Integer loyalty = 1;
		Integer equipmentEpaulet = 1;
		Integer equipmentCap = 1;
		Integer equipmentClothes = 1;
		Integer equipmentShoe = 1;
		Integer equipmentWeapon = 1;
		Integer state = 1;

		CityHero cityHero = new CityHero();
		
		cityHero.setCityID(cityID);
		cityHero.setName(name);
		cityHero.setHead(head);
		cityHero.setLevel(level);
		cityHero.setExp(exp);
		cityHero.setCommand(command);
		cityHero.setDefense(defense);
		cityHero.setMind(mind);
		cityHero.setExecutivepower(executivepower);
		cityHero.setUnsetPoint(unsetPoint);
		cityHero.setLoyalty(loyalty);
		cityHero.setEquipmentEpaulet(equipmentEpaulet);
		cityHero.setEquipmentCap(equipmentCap);
		cityHero.setEquipmentClothes(equipmentClothes);
		cityHero.setEquipmentShoe(equipmentShoe);
		cityHero.setEquipmentWeapon(equipmentWeapon);
		cityHero.setState(state);

		//测试创建
		Integer cityHeroID = cityHeroDAO.createCityHero(cityHero);
		assertNotNull(cityHeroID);

		//测试通过编号获得对象
		CityHero destCityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		assertNotNull(destCityHero);
		assertEquals(cityHeroID,destCityHero.getCityHeroID());
		assertEquals(cityID,destCityHero.getCityID());
		assertEquals(name,destCityHero.getName());
		assertEquals(head,destCityHero.getHead());
		assertEquals(level,destCityHero.getLevel());
		assertEquals(exp,destCityHero.getExp());
		assertEquals(command,destCityHero.getCommand());
		assertEquals(defense,destCityHero.getDefense());
		assertEquals(mind,destCityHero.getMind());
		assertEquals(executivepower,destCityHero.getExecutivepower());
		assertEquals(unsetPoint,destCityHero.getUnsetPoint());
		assertEquals(loyalty,destCityHero.getLoyalty());
		assertEquals(equipmentEpaulet,destCityHero.getEquipmentEpaulet());
		assertEquals(equipmentCap,destCityHero.getEquipmentCap());
		assertEquals(equipmentClothes,destCityHero.getEquipmentClothes());
		assertEquals(equipmentShoe,destCityHero.getEquipmentShoe());
		assertEquals(equipmentWeapon,destCityHero.getEquipmentWeapon());
		assertEquals(state,destCityHero.getState());
		
		//测试获得列表
		List<CityHero> cityHeroList = cityHeroDAO.getCityHeroList();
		assertFalse(cityHeroList.isEmpty());

		//测试更新
		cityID = 10;
		name = "字符串修";
		head = "字符串修改";
		level = 10;
		exp = 10L;
		command = 10;
		defense = 10;
		mind = 10;
		executivepower = 10;
		unsetPoint = 10;
		loyalty = 10;
		equipmentEpaulet = 10;
		equipmentCap = 10;
		equipmentClothes = 10;
		equipmentShoe = 10;
		equipmentWeapon = 10;
		state = 10;
		destCityHero.setCityHeroID(cityHeroID);
		destCityHero.setCityID(cityID);
		destCityHero.setName(name);
		destCityHero.setHead(head);
		destCityHero.setLevel(level);
		destCityHero.setExp(exp);
		destCityHero.setCommand(command);
		destCityHero.setDefense(defense);
		destCityHero.setMind(mind);
		destCityHero.setExecutivepower(executivepower);
		destCityHero.setUnsetPoint(unsetPoint);
		destCityHero.setLoyalty(loyalty);
		destCityHero.setEquipmentEpaulet(equipmentEpaulet);
		destCityHero.setEquipmentCap(equipmentCap);
		destCityHero.setEquipmentClothes(equipmentClothes);
		destCityHero.setEquipmentShoe(equipmentShoe);
		destCityHero.setEquipmentWeapon(equipmentWeapon);
		destCityHero.setState(state);
		cityHeroDAO.updateCityHero(destCityHero);
		CityHero updatedCityHero = cityHeroDAO.getCityHeroByID(cityHeroID);
		assertNotNull(updatedCityHero);
		assertEquals(cityHeroID,updatedCityHero.getCityHeroID());
		assertEquals(cityID,updatedCityHero.getCityID());
		assertEquals(name,updatedCityHero.getName());
		assertEquals(head,updatedCityHero.getHead());
		assertEquals(level,updatedCityHero.getLevel());
		assertEquals(exp,updatedCityHero.getExp());
		assertEquals(command,updatedCityHero.getCommand());
		assertEquals(defense,updatedCityHero.getDefense());
		assertEquals(mind,updatedCityHero.getMind());
		assertEquals(executivepower,updatedCityHero.getExecutivepower());
		assertEquals(unsetPoint,updatedCityHero.getUnsetPoint());
		assertEquals(loyalty,updatedCityHero.getLoyalty());
		assertEquals(equipmentEpaulet,updatedCityHero.getEquipmentEpaulet());
		assertEquals(equipmentCap,updatedCityHero.getEquipmentCap());
		assertEquals(equipmentClothes,updatedCityHero.getEquipmentClothes());
		assertEquals(equipmentShoe,updatedCityHero.getEquipmentShoe());
		assertEquals(equipmentWeapon,updatedCityHero.getEquipmentWeapon());
		assertEquals(state,updatedCityHero.getState());

		//测试删除
		cityHeroDAO.deleteCityHeroByID(cityHeroID);
		assertNull(cityHeroDAO.getCityHeroByID(cityHeroID));

	}
	
	@Test
	public void testExsitsCityOfficer(){
		assertFalse(cityHeroDAO.existsCityOfficer(4));
	}

}