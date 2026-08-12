package com.war.test.service;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.CacheService;
import com.war.common.SpringService;
import com.war.constant.CacheConstant;
import com.war.service.IMonsterService;

public class MonsterServiceTest {

	private static IMonsterService monsterService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		monsterService = (IMonsterService)SpringService.getApplicationContext().getBean("monsterService");
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
	public void testGenerateMapMonsterList() {
		Date date = new Date();
		CacheService.putToCache(CacheConstant.MONSTER_MAP, monsterService.initMonsterMap());
		monsterService.generateMapMonsterList();
		System.out.println(System.currentTimeMillis()-date.getTime());
	}
	
}
