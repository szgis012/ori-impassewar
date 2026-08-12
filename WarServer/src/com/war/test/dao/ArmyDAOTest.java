package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IArmyDAO;
import com.war.domain.Army;

public class ArmyDAOTest {

	private static IArmyDAO armyDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		armyDAO = (IArmyDAO)SpringService.getApplicationContext().getBean("armyDAO");
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
		String image = "测试字符串";
		String description = "测试字符串";
		Integer life = 1;
		Integer attack = 1;
		Integer defense = 1;
		Integer range = 1;
		Integer speed = 1;
		Integer carry = 1;
		Integer costFood = 1;
		Integer costMoney = 1;
		Integer population = 1;
		Integer attackType = 1;
		Integer defenseType = 1;
		Integer type = 1;

		Army army = new Army();
		
		army.setName(name);
		army.setImage(image);
		army.setDescription(description);
		army.setLife(life);
		army.setAttack(attack);
		army.setDefense(defense);
		army.setRange(range);
		army.setSpeed(speed);
		army.setCarry(carry);
		army.setCostFood(costFood);
		army.setCostMoney(costMoney);
		army.setPopulation(population);
		army.setAttackType(attackType);
		army.setDefenseType(defenseType);
		army.setType(type);

		//测试创建
		Integer armyID = armyDAO.createArmy(army);
		assertNotNull(armyID);

		//测试通过编号获得对象
		Army destArmy = armyDAO.getArmyByID(armyID);
		assertNotNull(destArmy);
		assertEquals(armyID,destArmy.getArmyID());
		assertEquals(name,destArmy.getName());
		assertEquals(image,destArmy.getImage());
		assertEquals(description,destArmy.getDescription());
		assertEquals(life,destArmy.getLife());
		assertEquals(attack,destArmy.getAttack());
		assertEquals(defense,destArmy.getDefense());
		assertEquals(range,destArmy.getRange());
		assertEquals(speed,destArmy.getSpeed());
		assertEquals(carry,destArmy.getCarry());
		assertEquals(costFood,destArmy.getCostFood());
		assertEquals(costMoney,destArmy.getCostMoney());
		assertEquals(population,destArmy.getPopulation());
		assertEquals(attackType,destArmy.getAttackType());
		assertEquals(defenseType,destArmy.getDefenseType());
		assertEquals(type,destArmy.getType());
		
		//测试获得列表
		List<Army> armyList = armyDAO.getArmyList();
		assertFalse(armyList.isEmpty());
		
		armyList = armyDAO.getArmyList();
		assertFalse(armyList.isEmpty());

		//测试更新
		name = "字符串修改";
		image = "字符串修改";
		description = "字符串修改";
		life = 10;
		attack = 10;
		defense = 10;
		range = 10;
		speed = 10;
		carry = 10;
		costFood = 10;
		costMoney = 10;
		population = 10;
		attackType = 10;
		defenseType = 10;
		type = 10;
		destArmy.setArmyID(armyID);
		destArmy.setName(name);
		destArmy.setImage(image);
		destArmy.setDescription(description);
		destArmy.setLife(life);
		destArmy.setAttack(attack);
		destArmy.setDefense(defense);
		destArmy.setRange(range);
		destArmy.setSpeed(speed);
		destArmy.setCarry(carry);
		destArmy.setCostFood(costFood);
		destArmy.setCostMoney(costMoney);
		destArmy.setPopulation(population);
		destArmy.setAttackType(attackType);
		destArmy.setDefenseType(defenseType);
		destArmy.setType(type);
		armyDAO.updateArmy(destArmy);
		Army updatedArmy = armyDAO.getArmyByID(armyID);
		assertNotNull(updatedArmy);
		assertEquals(armyID,updatedArmy.getArmyID());
		assertEquals(name,updatedArmy.getName());
		assertEquals(image,updatedArmy.getImage());
		assertEquals(description,updatedArmy.getDescription());
		assertEquals(life,updatedArmy.getLife());
		assertEquals(attack,updatedArmy.getAttack());
		assertEquals(defense,updatedArmy.getDefense());
		assertEquals(range,updatedArmy.getRange());
		assertEquals(speed,updatedArmy.getSpeed());
		assertEquals(carry,updatedArmy.getCarry());
		assertEquals(costFood,updatedArmy.getCostFood());
		assertEquals(costMoney,updatedArmy.getCostMoney());
		assertEquals(population,updatedArmy.getPopulation());
		assertEquals(attackType,updatedArmy.getAttackType());
		assertEquals(defenseType,updatedArmy.getDefenseType());
		assertEquals(type,updatedArmy.getType());

		//测试删除
		armyDAO.deleteArmyByID(armyID);
		assertNull(armyDAO.getArmyByID(armyID));
	}
	
	@Test
	public void testGetArmyNameByID(){
		//String name = armyDAO.getArmyNameByID(1);
		//assertNotNull(name);
		//System.err.println(name);
	}
	
	@Test
	public void testGetArmySpeed(){
		//assertNotNull(armyDAO.getArmyNameByID(1));
	}
	
	@Test
	public void testgGtArmyCost(){
		//Map<String, Integer> costs = armyDAO.getArmyCost(1);
		//assertNotNull(costs.get("costMoney"));
		//assertNotNull(costs.get("costFood"));
		//assertNotNull(costs.get("costOil"));
	}

}