package com.war.test.dao;

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
import com.war.dao.IBattleDetailDAO;
import com.war.domain.BattleDetail;

public class BattleDetailDAOTest {

	private static IBattleDetailDAO battleDetailDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		battleDetailDAO = (IBattleDetailDAO)SpringService.getApplicationContext().getBean("battleDetailDAO");
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

		Integer battleLogID = 1;
		Integer round = 1;
		String attackerOperation = "测试字符串";
		String defenderOperation = "测试字符串";
		Integer state = 1;

		BattleDetail battleDetail = new BattleDetail();
		
		battleDetail.setBattleLogID(battleLogID);
		battleDetail.setRound(round);
		battleDetail.setAttackerOperation(attackerOperation);
		battleDetail.setDefenderOperation(defenderOperation);
		battleDetail.setState(state);

		//测试创建
		battleDetailDAO.createBattleDetail(battleDetail);

		//测试通过编号获得对象
		BattleDetail destBattleDetail = battleDetailDAO.getBattleDetailByID(1,1);
		assertNotNull(destBattleDetail);
		assertEquals(battleLogID,destBattleDetail.getBattleLogID());
		assertEquals(round,destBattleDetail.getRound());
		assertEquals(attackerOperation,destBattleDetail.getAttackerOperation());
		assertEquals(defenderOperation,destBattleDetail.getDefenderOperation());
		assertEquals(state,destBattleDetail.getState());

		//测试获得列表
		List<BattleDetail> battleDetailList = battleDetailDAO.getBattleDetailList();
		assertFalse(battleDetailList.isEmpty());

		//测试更新
		battleLogID = 1;
		round = 1;
		attackerOperation = "字符串修改";
		defenderOperation = "字符串修改";
		state = 10;
		destBattleDetail.setBattleLogID(battleLogID);
		destBattleDetail.setRound(round);
		destBattleDetail.setAttackerOperation(attackerOperation);
		destBattleDetail.setDefenderOperation(defenderOperation);
		destBattleDetail.setState(state);
		battleDetailDAO.updateBattleDetail(destBattleDetail);
		BattleDetail updatedBattleDetail = battleDetailDAO.getBattleDetailByID(1,1);
		assertNotNull(updatedBattleDetail);
		assertEquals(battleLogID,updatedBattleDetail.getBattleLogID());
		assertEquals(round,updatedBattleDetail.getRound());
		assertEquals(attackerOperation,updatedBattleDetail.getAttackerOperation());
		assertEquals(defenderOperation,updatedBattleDetail.getDefenderOperation());
		assertEquals(state,updatedBattleDetail.getState());

		//测试删除
		battleDetailDAO.deleteBattleDetailByID(1,1);
		assertNull(battleDetailDAO.getBattleDetailByID(1,1));

	}

}
