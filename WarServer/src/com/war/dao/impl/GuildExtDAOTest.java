package com.war.dao.impl;

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
import com.war.dao.IGuildExtDAO;
import com.war.domain.GuildExt;

public class GuildExtDAOTest {

	private static IGuildExtDAO guildExtDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		guildExtDAO = (IGuildExtDAO)SpringService.getApplicationContext().getBean("guildExtDAO");
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

		Integer guildID = 1;
		Integer militaryAttackAdd = 1;
		Integer militarySpeedAdd = 1;
		Integer militaryAttackMinus = 1;
		Integer armyLifeAdd = 1;
		Integer armyAttackMinus = 1;
		Integer armyRangeMinus = 1;
		Integer armySpeedMinus = 1;
		Integer truckLifeAdd = 1;
		Integer truckAttackMinus = 1;
		Integer truckRangeMinus = 1;
		Integer truckSpeedMinus = 1;
		Integer airplaneLifeAdd = 1;
		Integer airplaneAttackMinus = 1;
		Integer airplaneRangeMinus = 1;
		Integer airplaneSpeedMinus = 1;

		GuildExt guildExt = new GuildExt();
		
		guildExt.setGuildID(guildID);
		guildExt.setMilitaryAttackAdd(militaryAttackAdd);
		guildExt.setMilitarySpeedAdd(militarySpeedAdd);
		guildExt.setMilitaryAttackMinus(militaryAttackMinus);
		guildExt.setArmyLifeAdd(armyLifeAdd);
		guildExt.setArmyAttackMinus(armyAttackMinus);
		guildExt.setArmyRangeMinus(armyRangeMinus);
		guildExt.setArmySpeedMinus(armySpeedMinus);
		guildExt.setTruckLifeAdd(truckLifeAdd);
		guildExt.setTruckAttackMinus(truckAttackMinus);
		guildExt.setTruckRangeMinus(truckRangeMinus);
		guildExt.setTruckSpeedMinus(truckSpeedMinus);
		guildExt.setAirplaneLifeAdd(airplaneLifeAdd);
		guildExt.setAirplaneAttackMinus(airplaneAttackMinus);
		guildExt.setAirplaneRangeMinus(airplaneRangeMinus);
		guildExt.setAirplaneSpeedMinus(airplaneSpeedMinus);


		//测试创建
		guildExtDAO.createGuildExt(guildExt);
		//assertNotNull(guildExtID);

		//测试通过编号获得对象
		GuildExt destGuildExt = guildExtDAO.getGuildExtByID(1);
		assertNotNull(destGuildExt);
		assertEquals(guildID,destGuildExt.getGuildID());
		assertEquals(militaryAttackAdd,destGuildExt.getMilitaryAttackAdd());
		assertEquals(militarySpeedAdd,destGuildExt.getMilitarySpeedAdd());
		assertEquals(militaryAttackMinus,destGuildExt.getMilitaryAttackMinus());
		assertEquals(armyLifeAdd,destGuildExt.getArmyLifeAdd());
		assertEquals(armyAttackMinus,destGuildExt.getArmyAttackMinus());
		assertEquals(armyRangeMinus,destGuildExt.getArmyRangeMinus());
		assertEquals(armySpeedMinus,destGuildExt.getArmySpeedMinus());
		assertEquals(truckLifeAdd,destGuildExt.getTruckLifeAdd());
		assertEquals(truckAttackMinus,destGuildExt.getTruckAttackMinus());
		assertEquals(truckRangeMinus,destGuildExt.getTruckRangeMinus());
		assertEquals(truckSpeedMinus,destGuildExt.getTruckSpeedMinus());
		assertEquals(airplaneLifeAdd,destGuildExt.getAirplaneLifeAdd());
		assertEquals(airplaneAttackMinus,destGuildExt.getAirplaneAttackMinus());
		assertEquals(airplaneRangeMinus,destGuildExt.getAirplaneRangeMinus());
		assertEquals(airplaneSpeedMinus,destGuildExt.getAirplaneSpeedMinus());


		//测试获得列表
		List<GuildExt> guildExtList = guildExtDAO.getGuildExtList();
		assertFalse(guildExtList.isEmpty());

		//测试更新
		militaryAttackAdd = 10;
		militarySpeedAdd = 10;
		militaryAttackMinus = 10;
		armyLifeAdd = 10;
		armyAttackMinus = 10;
		armyRangeMinus = 10;
		armySpeedMinus = 10;
		truckLifeAdd = 10;
		truckAttackMinus = 10;
		truckRangeMinus = 10;
		truckSpeedMinus = 10;
		airplaneLifeAdd = 10;
		airplaneAttackMinus = 10;
		airplaneRangeMinus = 10;
		airplaneSpeedMinus = 10;

		
		destGuildExt.setMilitaryAttackAdd(militaryAttackAdd);
		destGuildExt.setMilitarySpeedAdd(militarySpeedAdd);
		destGuildExt.setMilitaryAttackMinus(militaryAttackMinus);
		destGuildExt.setArmyLifeAdd(armyLifeAdd);
		destGuildExt.setArmyAttackMinus(armyAttackMinus);
		destGuildExt.setArmyRangeMinus(armyRangeMinus);
		destGuildExt.setArmySpeedMinus(armySpeedMinus);
		destGuildExt.setTruckLifeAdd(truckLifeAdd);
		destGuildExt.setTruckAttackMinus(truckAttackMinus);
		destGuildExt.setTruckRangeMinus(truckRangeMinus);
		destGuildExt.setTruckSpeedMinus(truckSpeedMinus);
		destGuildExt.setAirplaneLifeAdd(airplaneLifeAdd);
		destGuildExt.setAirplaneAttackMinus(airplaneAttackMinus);
		destGuildExt.setAirplaneRangeMinus(airplaneRangeMinus);
		destGuildExt.setAirplaneSpeedMinus(airplaneSpeedMinus);
		guildExtDAO.updateGuildExt(destGuildExt);
		GuildExt updatedGuildExt = guildExtDAO.getGuildExtByID(1);
		assertNotNull(updatedGuildExt);
		assertEquals(guildID,updatedGuildExt.getGuildID());
		assertEquals(militaryAttackAdd,updatedGuildExt.getMilitaryAttackAdd());
		assertEquals(militarySpeedAdd,updatedGuildExt.getMilitarySpeedAdd());
		assertEquals(militaryAttackMinus,updatedGuildExt.getMilitaryAttackMinus());
		assertEquals(armyLifeAdd,updatedGuildExt.getArmyLifeAdd());
		assertEquals(armyAttackMinus,updatedGuildExt.getArmyAttackMinus());
		assertEquals(armyRangeMinus,updatedGuildExt.getArmyRangeMinus());
		assertEquals(armySpeedMinus,updatedGuildExt.getArmySpeedMinus());
		assertEquals(truckLifeAdd,updatedGuildExt.getTruckLifeAdd());
		assertEquals(truckAttackMinus,updatedGuildExt.getTruckAttackMinus());
		assertEquals(truckRangeMinus,updatedGuildExt.getTruckRangeMinus());
		assertEquals(truckSpeedMinus,updatedGuildExt.getTruckSpeedMinus());
		assertEquals(airplaneLifeAdd,updatedGuildExt.getAirplaneLifeAdd());
		assertEquals(airplaneAttackMinus,updatedGuildExt.getAirplaneAttackMinus());
		assertEquals(airplaneRangeMinus,updatedGuildExt.getAirplaneRangeMinus());
		assertEquals(airplaneSpeedMinus,updatedGuildExt.getAirplaneSpeedMinus());


		//测试删除
		guildExtDAO.deleteGuildExtByID(1);
		assertNull(guildExtDAO.getGuildExtByID(1));

	}


}
