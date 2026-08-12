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
import com.war.dao.IBattleLogDAO;
import com.war.domain.BattleLog;

public class BattleLogDAOTest {

	private static IBattleLogDAO battleLogDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		battleLogDAO = (IBattleLogDAO)SpringService.getApplicationContext().getBean("battleLogDAO");
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
		String mapName = "测试字符串";
		Integer posX = 1;
		Integer posY = 1;
		Integer attackerPlayerID = 1;
		Integer defenderPlayerId = 1;
		String attackerMilitaryInfo = "测试字符串";
		String defenderMilitaryInfo = "测试字符串";
		String attackerArmyInfo = "测试字符串";
		String defenderArmyInfo = "测试字符串";
		String cityDefenceInfo = "测试字符串";
		Integer attackerExp = 1;
		Integer defenderExp = 1;
		Integer attackerRenown = 1;
		Integer defenderRenown = 1;
		String attainedResource = "测试字符串";
		String attainedEquipment = "测试字符串";
		String attainedTreasure = "测试字符串";
		String remark = "测试字符串";
		Integer result = 1;
		Integer durativeRound = 1;
		Date startTime = new Date();
		Date endTime = new Date();
		Integer type = 1;

		BattleLog battleLog = new BattleLog();
		
		battleLog.setBattleLogID(battleLogID);
		battleLog.setMapName(mapName);
		battleLog.setPosX(posX);
		battleLog.setPosY(posY);
		battleLog.setAttackerPlayerID(attackerPlayerID);
		battleLog.setDefenderPlayerID(defenderPlayerId);
		battleLog.setAttackerMilitaryInfo(attackerMilitaryInfo);
		battleLog.setDefenderMilitaryInfo(defenderMilitaryInfo);
		battleLog.setAttackerArmyInfo(attackerArmyInfo);
		battleLog.setDefenderArmyInfo(defenderArmyInfo);
		battleLog.setCityDefenceInfo(cityDefenceInfo);
		battleLog.setAttackerExp(attackerExp);
		battleLog.setDefenderExp(defenderExp);
		battleLog.setAttackerRenown(attackerRenown);
		battleLog.setDefenderRenown(defenderRenown);
		battleLog.setAttainedResource(attainedResource);
		battleLog.setAttainedEquipment(attainedEquipment);
		battleLog.setAttainedTreasure(attainedTreasure);
		battleLog.setRemark(remark);
		battleLog.setResult(result);
		battleLog.setDurativeRound(durativeRound);
		battleLog.setStartTime(startTime);
		battleLog.setEndTime(endTime);
		battleLog.setType(type);

		//测试创建
		battleLogID = battleLogDAO.createBattleLog(battleLog);
		assertNotNull(battleLogID);

		//测试通过编号获得对象
		BattleLog destBattleLog = battleLogDAO.getBattleLogByID(battleLogID);
		assertNotNull(destBattleLog);
		assertEquals(battleLogID,destBattleLog.getBattleLogID());
		assertEquals(mapName,destBattleLog.getMapName());
		assertEquals(posX,destBattleLog.getPosX());
		assertEquals(posY,destBattleLog.getPosY());
		assertEquals(attackerPlayerID,destBattleLog.getAttackerPlayerID());
		assertEquals(defenderPlayerId,destBattleLog.getDefenderPlayerID());
		assertEquals(attackerMilitaryInfo,destBattleLog.getAttackerMilitaryInfo());
		assertEquals(defenderMilitaryInfo,destBattleLog.getDefenderMilitaryInfo());
		assertEquals(attackerArmyInfo,destBattleLog.getAttackerArmyInfo());
		assertEquals(defenderArmyInfo,destBattleLog.getDefenderArmyInfo());
		assertEquals(cityDefenceInfo,destBattleLog.getCityDefenceInfo());
		assertEquals(attackerExp,destBattleLog.getAttackerExp());
		assertEquals(defenderExp,destBattleLog.getDefenderExp());
		assertEquals(attackerRenown,destBattleLog.getAttackerRenown());
		assertEquals(defenderRenown,destBattleLog.getDefenderRenown());
		assertEquals(attainedResource,destBattleLog.getAttainedResource());
		assertEquals(attainedEquipment,destBattleLog.getAttainedEquipment());
		assertEquals(attainedTreasure,destBattleLog.getAttainedTreasure());
		assertEquals(remark,destBattleLog.getRemark());
		assertEquals(result,destBattleLog.getResult());
		assertEquals(durativeRound,destBattleLog.getDurativeRound());
		// assertEquals(startTime,destBattleLog.getStartTime());
		// assertEquals(endTime,destBattleLog.getEndTime());
		assertEquals(type,destBattleLog.getType());

		//测试获得列表
		List<BattleLog> battleLogList = battleLogDAO.getBattleLogList();
		assertFalse(battleLogList.isEmpty());

		//测试更新
		battleLogID = 1;
		mapName = "字符串修改";
		posX = 10;
		posY = 10;
		attackerPlayerID = 10;
		defenderPlayerId = 10;
		attackerMilitaryInfo = "字符串修改";
		defenderMilitaryInfo = "字符串修改";
		attackerArmyInfo = "字符串修改";
		defenderArmyInfo = "字符串修改";
		cityDefenceInfo = "字符串修改";
		attackerExp = 10;
		defenderExp = 10;
		attackerRenown = 10;
		defenderRenown = 10;
		attainedResource = "字符串修改";
		attainedEquipment = "字符串修改";
		attainedTreasure = "字符串修改";
		remark = "字符串修改";
		result = 10;
		durativeRound = 10;
		type = 10;
		destBattleLog.setBattleLogID(battleLogID);
		destBattleLog.setMapName(mapName);
		destBattleLog.setPosX(posX);
		destBattleLog.setPosY(posY);
		destBattleLog.setAttackerPlayerID(attackerPlayerID);
		destBattleLog.setDefenderPlayerID(defenderPlayerId);
		destBattleLog.setAttackerMilitaryInfo(attackerMilitaryInfo);
		destBattleLog.setDefenderMilitaryInfo(defenderMilitaryInfo);
		destBattleLog.setAttackerArmyInfo(attackerArmyInfo);
		destBattleLog.setDefenderArmyInfo(defenderArmyInfo);
		destBattleLog.setCityDefenceInfo(cityDefenceInfo);
		destBattleLog.setAttackerExp(attackerExp);
		destBattleLog.setDefenderExp(defenderExp);
		destBattleLog.setAttackerRenown(attackerRenown);
		destBattleLog.setDefenderRenown(defenderRenown);
		destBattleLog.setAttainedResource(attainedResource);
		destBattleLog.setAttainedEquipment(attainedEquipment);
		destBattleLog.setAttainedTreasure(attainedTreasure);
		destBattleLog.setRemark(remark);
		destBattleLog.setResult(result);
		destBattleLog.setDurativeRound(durativeRound);
		destBattleLog.setStartTime(startTime);
		destBattleLog.setEndTime(endTime);
		destBattleLog.setType(type);
		battleLogDAO.updateBattleLog(destBattleLog);
		BattleLog updatedBattleLog = battleLogDAO.getBattleLogByID(battleLogID);
		assertNotNull(updatedBattleLog);
		assertEquals(battleLogID,updatedBattleLog.getBattleLogID());
		assertEquals(mapName,updatedBattleLog.getMapName());
		assertEquals(posX,updatedBattleLog.getPosX());
		assertEquals(posY,updatedBattleLog.getPosY());
		assertEquals(attackerPlayerID,updatedBattleLog.getAttackerPlayerID());
		assertEquals(defenderPlayerId,updatedBattleLog.getDefenderPlayerID());
		assertEquals(attackerMilitaryInfo,updatedBattleLog.getAttackerMilitaryInfo());
		assertEquals(defenderMilitaryInfo,updatedBattleLog.getDefenderMilitaryInfo());
		assertEquals(attackerArmyInfo,updatedBattleLog.getAttackerArmyInfo());
		assertEquals(defenderArmyInfo,updatedBattleLog.getDefenderArmyInfo());
		assertEquals(cityDefenceInfo,updatedBattleLog.getCityDefenceInfo());
		assertEquals(attackerExp,updatedBattleLog.getAttackerExp());
		assertEquals(defenderExp,updatedBattleLog.getDefenderExp());
		assertEquals(attackerRenown,updatedBattleLog.getAttackerRenown());
		assertEquals(defenderRenown,updatedBattleLog.getDefenderRenown());
		assertEquals(attainedResource,updatedBattleLog.getAttainedResource());
		assertEquals(attainedEquipment,updatedBattleLog.getAttainedEquipment());
		assertEquals(attainedTreasure,updatedBattleLog.getAttainedTreasure());
		assertEquals(remark,updatedBattleLog.getRemark());
		assertEquals(result,updatedBattleLog.getResult());
		assertEquals(durativeRound,updatedBattleLog.getDurativeRound());
		// assertEquals(startTime,updatedBattleLog.getStartTime());
		// assertEquals(endTime,updatedBattleLog.getEndTime());
		assertEquals(type,updatedBattleLog.getType());

		//测试删除
		battleLogDAO.deleteBattleLogByID(battleLogID);
		assertNull(battleLogDAO.getBattleLogByID(battleLogID));

	}

}