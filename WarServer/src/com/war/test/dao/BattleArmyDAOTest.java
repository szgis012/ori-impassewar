package com.war.test.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IBattleArmyDAO;
import com.war.domain.BattleArmy;

public class BattleArmyDAOTest {

	private static IBattleArmyDAO battleArmyDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		battleArmyDAO = (IBattleArmyDAO)SpringService.getApplicationContext().getBean("battleArmyDAO");
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

		Integer battleID = 1;
		Integer militaryID = 1;
		Integer armyIndex = 1;
		Integer armyID = 1;
		Integer amount = 1;
		Integer posX = 1;
		Integer posY = 1;
		Integer haveMoved = 1;
		Integer attackType = 1;

		BattleArmy battleArmy = new BattleArmy();
		
		battleArmy.setBattleID(battleID);
		battleArmy.setMilitaryID(militaryID);
		battleArmy.setArmyIndex(armyIndex);
		battleArmy.setArmyID(armyID);
		battleArmy.setAmount(amount);
		battleArmy.setPosX(posX);
		battleArmy.setPosY(posY);
		battleArmy.setHaveMoved(haveMoved);
		battleArmy.setAttackType(attackType);

		//测试创建
		Integer battleArmyID = battleArmyDAO.createBattleArmy(battleArmy);
		assertNotNull(battleArmyID);

		//测试通过编号获得对象
		BattleArmy destBattleArmy = battleArmyDAO.getBattleArmyByID(battleArmyID);
		assertNotNull(destBattleArmy);
		assertEquals(battleArmyID,destBattleArmy.getBattleArmyID());
		assertEquals(battleID,destBattleArmy.getBattleID());
		assertEquals(militaryID,destBattleArmy.getMilitaryID());
		assertEquals(armyIndex,destBattleArmy.getArmyIndex());
		assertEquals(armyID,destBattleArmy.getArmyID());
		assertEquals(amount,destBattleArmy.getAmount());
		assertEquals(posX,destBattleArmy.getPosX());
		assertEquals(posY,destBattleArmy.getPosY());
		assertEquals(haveMoved,destBattleArmy.getHaveMoved());
		assertEquals(attackType,destBattleArmy.getAttackType());
		
		//测试获得列表
		//List<BattleArmy> battleArmyList = battleArmyDAO.getBattleArmyList();
		//assertFalse(battleArmyList.isEmpty());

		//测试更新
		battleID = 10;
		militaryID = 10;
		armyIndex = 10;
		armyID = 10;
		amount = 10;
		posX = 10;
		posY = 10;
		haveMoved = 10;
		attackType = 10;
		destBattleArmy.setBattleArmyID(battleArmyID);
		destBattleArmy.setBattleID(battleID);
		destBattleArmy.setMilitaryID(militaryID);
		destBattleArmy.setArmyIndex(armyIndex);
		destBattleArmy.setArmyID(armyID);
		destBattleArmy.setAmount(amount);
		destBattleArmy.setPosX(posX);
		destBattleArmy.setPosY(posY);
		destBattleArmy.setHaveMoved(haveMoved);
		destBattleArmy.setAttackType(attackType);
		battleArmyDAO.updateBattleArmy(destBattleArmy);
		BattleArmy updatedBattleArmy = battleArmyDAO.getBattleArmyByID(battleArmyID);
		assertNotNull(updatedBattleArmy);
		assertEquals(battleArmyID,updatedBattleArmy.getBattleArmyID());
		assertEquals(battleID,updatedBattleArmy.getBattleID());
		assertEquals(militaryID,updatedBattleArmy.getMilitaryID());
		assertEquals(armyIndex,updatedBattleArmy.getArmyIndex());
		assertEquals(armyID,updatedBattleArmy.getArmyID());
		assertEquals(amount,updatedBattleArmy.getAmount());
		assertEquals(posX,updatedBattleArmy.getPosX());
		assertEquals(posY,updatedBattleArmy.getPosY());
		assertEquals(haveMoved,updatedBattleArmy.getHaveMoved());
		assertEquals(attackType,updatedBattleArmy.getAttackType());

		//测试删除
		battleArmyDAO.deleteBattleArmyByID(battleArmyID);
		assertNull(battleArmyDAO.getBattleArmyByID(battleArmyID));

	}

}