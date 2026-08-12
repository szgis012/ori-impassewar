package com.war.test.service;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.CacheService;
import com.war.common.SpringService;
import com.war.constant.CacheConstant;
import com.war.domain.BattleLog;
import com.war.service.IArmyService;
import com.war.service.IBattleService;

public class BattleServiceTest {

	private static IBattleService battleService;
	private static IArmyService armyService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		battleService = (IBattleService) SpringService.getApplicationContext().getBean("battleService");
		armyService = (IArmyService) SpringService.getApplicationContext().getBean("armyService");
		
		// 士兵缓存
		CacheService.putToCache(CacheConstant.ARMIES_MAP, armyService.initArmiesMap());
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
//	@Test
	public void testGetBattleLogListByPlayerID () {
		
		List<BattleLog> battleLogList = battleService.getBattleLogListByPlayerID(44);
		
		System.out.println(battleLogList.toString());
	}
	
	@Test
	public void testGetBattleLogByID() {
		BattleLog battleLog = battleService.getBattleLogByID(65);
		assertNotNull(battleLog);
	}
	
}
