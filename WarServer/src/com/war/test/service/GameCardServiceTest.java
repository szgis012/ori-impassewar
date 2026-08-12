package com.war.test.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.service.IGameCardService;

public class GameCardServiceTest {

	private static IGameCardService gameCardService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameCardService = (IGameCardService)SpringService.getApplicationContext().getBean("gameCardService");
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
	public void testGenerateGameCard(){
		gameCardService.generateGameCard(1, 20000);
	}
	
}