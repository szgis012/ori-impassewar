package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityCandidacyHeroDAO;
import com.war.domain.CityCandidacyHero;

public class CityCandidacyHeroDAOTest {

	private static ICityCandidacyHeroDAO cityCandidacyHeroDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityCandidacyHeroDAO = (ICityCandidacyHeroDAO)SpringService.getApplicationContext().getBean("cityCandidacyHeroDAO");
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
		String name = "测试字符串";
		String head = "测试字符串";
		Integer level = 1;
		Integer command = 1;
		Integer defense = 1;
		Integer mind = 1;
		Integer executivepower = 1;
		Integer state = 1;

		CityCandidacyHero cityCandidacyHero = new CityCandidacyHero();
		
		cityCandidacyHero.setCityID(cityID);
		cityCandidacyHero.setName(name);
		cityCandidacyHero.setHead(head);
		cityCandidacyHero.setLevel(level);
		cityCandidacyHero.setCommand(command);
		cityCandidacyHero.setDefense(defense);
		cityCandidacyHero.setMind(mind);
		cityCandidacyHero.setExecutivepower(executivepower);
		cityCandidacyHero.setState(state);

		//测试创建
		Integer cityCandidacyHeroID = cityCandidacyHeroDAO.createCityCandidacyHero(cityCandidacyHero);
		assertNotNull(cityCandidacyHeroID);

		//测试通过编号获得对象
		CityCandidacyHero destCityCandidacyHero = cityCandidacyHeroDAO.getCityCandidacyHeroByID(cityCandidacyHeroID);
		assertNotNull(destCityCandidacyHero);
		assertEquals(cityCandidacyHeroID,destCityCandidacyHero.getCityCandidacyHeroID());
		assertEquals(cityID,destCityCandidacyHero.getCityID());
		assertEquals(name,destCityCandidacyHero.getName());
		assertEquals(head,destCityCandidacyHero.getHead());
		assertEquals(level,destCityCandidacyHero.getLevel());
		assertEquals(command,destCityCandidacyHero.getCommand());
		assertEquals(defense,destCityCandidacyHero.getDefense());
		assertEquals(mind,destCityCandidacyHero.getMind());
		assertEquals(executivepower,destCityCandidacyHero.getExecutivepower());
		assertEquals(state,destCityCandidacyHero.getState());
		
		//测试获得列表
		List<CityCandidacyHero> cityCandidacyHeroList = cityCandidacyHeroDAO.getCityCandidacyHeroList();
		assertFalse(cityCandidacyHeroList.isEmpty());

		//测试更新
		cityID = 10;
		name = "字符串修改";
		head = "字符串修改";
		level = 10;
		command = 10;
		defense = 10;
		mind = 10;
		executivepower = 10;
		state = 10;
		destCityCandidacyHero.setCityCandidacyHeroID(cityCandidacyHeroID);
		destCityCandidacyHero.setCityID(cityID);
		destCityCandidacyHero.setName(name);
		destCityCandidacyHero.setHead(head);
		destCityCandidacyHero.setLevel(level);
		destCityCandidacyHero.setCommand(command);
		destCityCandidacyHero.setDefense(defense);
		destCityCandidacyHero.setMind(mind);
		destCityCandidacyHero.setExecutivepower(executivepower);
		destCityCandidacyHero.setState(state);
		cityCandidacyHeroDAO.updateCityCandidacyHero(destCityCandidacyHero);
		CityCandidacyHero updatedCityCandidacyHero = cityCandidacyHeroDAO.getCityCandidacyHeroByID(cityCandidacyHeroID);
		assertNotNull(updatedCityCandidacyHero);
		assertEquals(cityCandidacyHeroID,updatedCityCandidacyHero.getCityCandidacyHeroID());
		assertEquals(cityID,updatedCityCandidacyHero.getCityID());
		assertEquals(name,updatedCityCandidacyHero.getName());
		assertEquals(head,updatedCityCandidacyHero.getHead());
		assertEquals(level,updatedCityCandidacyHero.getLevel());
		assertEquals(command,updatedCityCandidacyHero.getCommand());
		assertEquals(defense,updatedCityCandidacyHero.getDefense());
		assertEquals(mind,updatedCityCandidacyHero.getMind());
		assertEquals(executivepower,updatedCityCandidacyHero.getExecutivepower());
		assertEquals(state,updatedCityCandidacyHero.getState());

		//测试删除
		cityCandidacyHeroDAO.deleteCityCandidacyHeroByID(cityCandidacyHeroID);
		assertNull(cityCandidacyHeroDAO.getCityCandidacyHeroByID(cityCandidacyHeroID));

	}

}