package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IBattleDAO;
import com.war.domain.Battle;

public class BattleDAOTest {

	private static IBattleDAO battleDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		battleDAO = (IBattleDAO)SpringService.getApplicationContext().getBean("battleDAO");
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

		Integer militaryAttackerID = 1;
		Integer militaryDefenderID = 1;
		Integer stagePosX = 1;
		Integer stagePosY = 1;
		Integer round = 1;

		Battle battle = new Battle();
		
		battle.setMilitaryAttackerID(militaryAttackerID);
		battle.setMilitaryDefenderID(militaryDefenderID);
		battle.setStagePosX(stagePosX);
		battle.setStagePosY(stagePosY);
		battle.setRound(round);

		//测试创建
		Integer battleID = battleDAO.createBattle(battle);
		assertNotNull(battleID);

		//测试通过编号获得对象
		Battle destBattle = battleDAO.getBattleByID(battleID);
		assertNotNull(destBattle);
		assertEquals(battleID,destBattle.getBattleID());
		assertEquals(militaryAttackerID,destBattle.getMilitaryAttackerID());
		assertEquals(militaryDefenderID,destBattle.getMilitaryDefenderID());
		assertEquals(stagePosX,destBattle.getStagePosX());
		assertEquals(stagePosY,destBattle.getStagePosY());
		assertEquals(round,destBattle.getRound());
		
		//测试获得列表
		List<Battle> battleList = battleDAO.getBattleList();
		assertFalse(battleList.isEmpty());

		//测试更新
		militaryAttackerID = 10;
		militaryDefenderID = 10;
		stagePosX = 10;
		stagePosY = 10;
		round = 10;
		destBattle.setMilitaryAttackerID(militaryAttackerID);
		destBattle.setMilitaryDefenderID(militaryDefenderID);
		destBattle.setStagePosX(stagePosX);
		destBattle.setStagePosY(stagePosY);
		destBattle.setRound(round);
		battleDAO.updateBattle(destBattle);
		Battle updatedBattle = battleDAO.getBattleByID(battleID);
		assertNotNull(updatedBattle);
		assertEquals(battleID,updatedBattle.getBattleID());
		assertEquals(militaryAttackerID,updatedBattle.getMilitaryAttackerID());
		assertEquals(militaryDefenderID,updatedBattle.getMilitaryDefenderID());
		assertEquals(stagePosX,updatedBattle.getStagePosX());
		assertEquals(stagePosY,updatedBattle.getStagePosY());
		assertEquals(round,updatedBattle.getRound());
 
		//测试删除
		battleDAO.deleteBattleByID(battleID);
		assertNull(battleDAO.getBattleByID(battleID));

	}

}