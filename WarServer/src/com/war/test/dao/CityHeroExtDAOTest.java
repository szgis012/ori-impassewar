package com.war.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ICityHeroExtDAO;
import com.war.domain.CityHeroExt;

public class CityHeroExtDAOTest {

	private static ICityHeroExtDAO cityHeroExtDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cityHeroExtDAO = (ICityHeroExtDAO)SpringService.getApplicationContext().getBean("cityHeroExtDAO");
	}

	@Test
	public void testCreateCityHeroExt() {
	}

	@Test
	public void testUpdateCityHeroExt() {
	}

	@Test
	public void testDeleteCityHeroExtByID() {
	}

	@Test
	public void testGetCityHeroExtByID() {
	}

	@Test
	public void testGetCityHeroExtList() {
	}
	@Test
	public void testCURD() {

		Integer cityHeroID = 1;
		Integer commandEquipmentAdd = 1;
		Integer commandTreasureAdd = 1;
		Integer defenseEquipmentAdd = 1;
		Integer defenseTreasureAdd = 1;
		Integer mindEquipmentAdd = 1;
		Integer mindTreasureAdd = 1;
		Integer executivepowerEquipmentAdd = 1;
		Integer executivepowerTreasureAdd = 1;
		Integer reinGuildAdd = 1;
		Integer reinTreasureAdd = 1;
		Integer expGuildAdd = 1;
		Integer expTreasureAdd = 1;
		Integer militaryAttackAdd = 1;
		Integer militaryDefenseAdd = 1;
		Integer militaryLifeAdd = 1;

		CityHeroExt cityHeroExt = new CityHeroExt();
		
		cityHeroExt.setCityHeroID(cityHeroID);
		cityHeroExt.setCommandEquipmentAdd(commandEquipmentAdd);
		cityHeroExt.setCommandTreasureAdd(commandTreasureAdd);
		cityHeroExt.setDefenseEquipmentAdd(defenseEquipmentAdd);
		cityHeroExt.setDefenseTreasureAdd(defenseTreasureAdd);
		cityHeroExt.setMindEquipmentAdd(mindEquipmentAdd);
		cityHeroExt.setMindTreasureAdd(mindTreasureAdd);
		cityHeroExt.setExecutivepowerEquipmentAdd(executivepowerEquipmentAdd);
		cityHeroExt.setExecutivepowerTreasureAdd(executivepowerTreasureAdd);
		cityHeroExt.setReinGuildAdd(reinGuildAdd);
		cityHeroExt.setReinTreasureAdd(reinTreasureAdd);
		cityHeroExt.setExpGuildAdd(expGuildAdd);
		cityHeroExt.setExpTreasureAdd(expTreasureAdd);
		cityHeroExt.setMilitaryAttackAdd(militaryAttackAdd);
		cityHeroExt.setMilitaryDefenseAdd(militaryDefenseAdd);
		cityHeroExt.setMilitaryLifeAdd(militaryLifeAdd);

		//测试创建
		cityHeroExtDAO.createCityHeroExt(cityHeroExt);
		// assertNotNull(cityHeroExtID);

		//测试通过编号获得对象
		CityHeroExt destCityHeroExt = cityHeroExtDAO.getCityHeroExtByID(1);
		assertNotNull(destCityHeroExt);
		assertEquals(cityHeroID,destCityHeroExt.getCityHeroID());
		assertEquals(commandEquipmentAdd,destCityHeroExt.getCommandEquipmentAdd());
		assertEquals(commandTreasureAdd,destCityHeroExt.getCommandTreasureAdd());
		assertEquals(defenseEquipmentAdd,destCityHeroExt.getDefenseEquipmentAdd());
		assertEquals(defenseTreasureAdd,destCityHeroExt.getDefenseTreasureAdd());
		assertEquals(mindEquipmentAdd,destCityHeroExt.getMindEquipmentAdd());
		assertEquals(mindTreasureAdd,destCityHeroExt.getMindTreasureAdd());
		assertEquals(executivepowerEquipmentAdd,destCityHeroExt.getExecutivepowerEquipmentAdd());
		assertEquals(executivepowerTreasureAdd,destCityHeroExt.getExecutivepowerTreasureAdd());
		assertEquals(reinGuildAdd,destCityHeroExt.getReinGuildAdd());
		assertEquals(reinTreasureAdd,destCityHeroExt.getReinTreasureAdd());
		assertEquals(expGuildAdd,destCityHeroExt.getExpGuildAdd());
		assertEquals(expTreasureAdd,destCityHeroExt.getExpTreasureAdd());
		assertEquals(militaryAttackAdd,destCityHeroExt.getMilitaryAttackAdd());
		assertEquals(militaryDefenseAdd,destCityHeroExt.getMilitaryDefenseAdd());
		assertEquals(militaryLifeAdd,destCityHeroExt.getMilitaryLifeAdd());

		//测试获得列表
		List<CityHeroExt> cityHeroExtList = cityHeroExtDAO.getCityHeroExtList();
		assertFalse(cityHeroExtList.isEmpty());

		//测试更新
		commandEquipmentAdd = 10;
		commandTreasureAdd = 10;
		defenseEquipmentAdd = 10;
		defenseTreasureAdd = 10;
		mindEquipmentAdd = 10;
		mindTreasureAdd = 10;
		executivepowerEquipmentAdd = 10;
		executivepowerTreasureAdd = 10;
		reinGuildAdd = 10;
		reinTreasureAdd = 10;
		expGuildAdd = 10;
		expTreasureAdd = 10;
		militaryAttackAdd = 10;
		militaryDefenseAdd = 10;
		militaryLifeAdd = 10;
		destCityHeroExt.setCityHeroID(cityHeroID);
		destCityHeroExt.setCommandEquipmentAdd(commandEquipmentAdd);
		destCityHeroExt.setCommandTreasureAdd(commandTreasureAdd);
		destCityHeroExt.setDefenseEquipmentAdd(defenseEquipmentAdd);
		destCityHeroExt.setDefenseTreasureAdd(defenseTreasureAdd);
		destCityHeroExt.setMindEquipmentAdd(mindEquipmentAdd);
		destCityHeroExt.setMindTreasureAdd(mindTreasureAdd);
		destCityHeroExt.setExecutivepowerEquipmentAdd(executivepowerEquipmentAdd);
		destCityHeroExt.setExecutivepowerTreasureAdd(executivepowerTreasureAdd);
		destCityHeroExt.setReinGuildAdd(reinGuildAdd);
		destCityHeroExt.setReinTreasureAdd(reinTreasureAdd);
		destCityHeroExt.setExpGuildAdd(expGuildAdd);
		destCityHeroExt.setExpTreasureAdd(expTreasureAdd);
		destCityHeroExt.setMilitaryAttackAdd(militaryAttackAdd);
		destCityHeroExt.setMilitaryDefenseAdd(militaryDefenseAdd);
		destCityHeroExt.setMilitaryLifeAdd(militaryLifeAdd);
		cityHeroExtDAO.updateCityHeroExt(destCityHeroExt);
		CityHeroExt updatedCityHeroExt = cityHeroExtDAO.getCityHeroExtByID(1);
		assertNotNull(updatedCityHeroExt);
		assertEquals(cityHeroID,updatedCityHeroExt.getCityHeroID());
		assertEquals(commandEquipmentAdd,updatedCityHeroExt.getCommandEquipmentAdd());
		assertEquals(commandTreasureAdd,updatedCityHeroExt.getCommandTreasureAdd());
		assertEquals(defenseEquipmentAdd,updatedCityHeroExt.getDefenseEquipmentAdd());
		assertEquals(defenseTreasureAdd,updatedCityHeroExt.getDefenseTreasureAdd());
		assertEquals(mindEquipmentAdd,updatedCityHeroExt.getMindEquipmentAdd());
		assertEquals(mindTreasureAdd,updatedCityHeroExt.getMindTreasureAdd());
		assertEquals(executivepowerEquipmentAdd,updatedCityHeroExt.getExecutivepowerEquipmentAdd());
		assertEquals(executivepowerTreasureAdd,updatedCityHeroExt.getExecutivepowerTreasureAdd());
		assertEquals(reinGuildAdd,updatedCityHeroExt.getReinGuildAdd());
		assertEquals(reinTreasureAdd,updatedCityHeroExt.getReinTreasureAdd());
		assertEquals(expGuildAdd,updatedCityHeroExt.getExpGuildAdd());
		assertEquals(expTreasureAdd,updatedCityHeroExt.getExpTreasureAdd());
		assertEquals(militaryAttackAdd,updatedCityHeroExt.getMilitaryAttackAdd());
		assertEquals(militaryDefenseAdd,updatedCityHeroExt.getMilitaryDefenseAdd());
		assertEquals(militaryLifeAdd,updatedCityHeroExt.getMilitaryLifeAdd());

		//测试删除
		cityHeroExtDAO.deleteCityHeroExtByID(10);
		assertNull(cityHeroExtDAO.getCityHeroExtByID(10));

	}

}
