package com.war.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IBattleWaitDAO;
import com.war.domain.BattleWait;

public class BattleWaitDAOTest {

	private static IBattleWaitDAO battleWaitDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		battleWaitDAO = (IBattleWaitDAO)SpringService.getApplicationContext().getBean("battleWaitDAO");
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

		Integer battleWaitID = 1;
		Integer mapID = 1;
		Integer attackerCityMilitaryID = 1;
		Integer defenderCityMilitaryID = 1;
		Date startTime = new Date();

		BattleWait battleWait = new BattleWait();
		
		battleWait.setBattleWaitID(battleWaitID);
		battleWait.setMapID(mapID);
		battleWait.setAttackerCityMilitaryID(attackerCityMilitaryID);
		battleWait.setDefenderCityMilitaryID(defenderCityMilitaryID);
		battleWait.setStartTime(startTime);

		//测试创建
		battleWaitID = battleWaitDAO.createBattleWait(battleWait);
		assertNotNull(battleWaitID);

		//测试通过编号获得对象
		BattleWait destBattleWait = battleWaitDAO.getBattleWaitByID(battleWaitID);
		assertNotNull(destBattleWait);
		assertEquals(battleWaitID,destBattleWait.getBattleWaitID());
		assertEquals(mapID,destBattleWait.getMapID());
		assertEquals(attackerCityMilitaryID,destBattleWait.getAttackerCityMilitaryID());
		assertEquals(defenderCityMilitaryID,destBattleWait.getDefenderCityMilitaryID());
		//assertEquals(startTime,destBattleWait.getStartTime());

		//测试获得列表
		List<BattleWait> battleWaitList = battleWaitDAO.getBattleWaitList();
		assertFalse(battleWaitList.isEmpty());

		//测试更新
		battleWaitID = 1;
		mapID = 10;
		attackerCityMilitaryID = 10;
		defenderCityMilitaryID = 10;
		destBattleWait.setBattleWaitID(battleWaitID);
		destBattleWait.setMapID(mapID);
		destBattleWait.setAttackerCityMilitaryID(attackerCityMilitaryID);
		destBattleWait.setDefenderCityMilitaryID(defenderCityMilitaryID);
		destBattleWait.setStartTime(startTime);
		battleWaitDAO.updateBattleWait(destBattleWait);
		BattleWait updatedBattleWait = battleWaitDAO.getBattleWaitByID(battleWaitID);
		assertNotNull(updatedBattleWait);
		assertEquals(battleWaitID,updatedBattleWait.getBattleWaitID());
		assertEquals(mapID,updatedBattleWait.getMapID());
		assertEquals(attackerCityMilitaryID,updatedBattleWait.getAttackerCityMilitaryID());
		assertEquals(defenderCityMilitaryID,updatedBattleWait.getDefenderCityMilitaryID());
		// assertEquals(startTime,updatedBattleWait.getStartTime());

		//测试删除
		battleWaitDAO.deleteBattleWaitByID(battleWaitID);
		assertNull(battleWaitDAO.getBattleWaitByID(battleWaitID));

	}

}