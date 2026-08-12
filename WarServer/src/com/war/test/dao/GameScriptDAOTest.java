package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IGameScriptDAO;

public class GameScriptDAOTest {
	
	private static IGameScriptDAO gameScriptDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameScriptDAO = (IGameScriptDAO)SpringService.getApplicationContext().getBean("gameScriptDAO");
	}

	@Test
	public void testGetBuildingMaxLevel() {
		assertTrue(gameScriptDAO.getBuildingMaxLevel(0, 0)==0);
	}

	@Test
	public void testGetBuildingNum() {
		assertTrue(gameScriptDAO.getBuildingNum(0, 0, 0)==0);
	}

	@Test
	public void testGetCityArmyNum() {
		assertTrue(gameScriptDAO.getCityArmyNum(0, 0)==0);
	}

	@Test
	public void testGetCityDefenseNum() {
		assertTrue(gameScriptDAO.getCityDefenseNum(0, 0)==0);
	}

	@Test
	public void testGetCityHeroNum() {
		assertTrue(gameScriptDAO.getCityHeroNum(0)==0);
	}

	@Test
	public void testGetCityMilitaryNum() {
		assertTrue(gameScriptDAO.getCityMilitaryNum(0)==0);
	}

	@Test
	public void testGetTechnologyLevel() {
		assertTrue(gameScriptDAO.getTechnologyLevel(0, 0)==0);
	}

}
