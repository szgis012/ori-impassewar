package com.war.test.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.service.IMonsterService;

public class MapMonsterServiceTest {

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
	public void testGenerateMapMonsterList(){
		monsterService.generateMapMonsterList();
	}
	
}