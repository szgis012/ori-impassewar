package com.war.test.service;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.war.common.SpringService;
import com.war.service.IPlayerService;

public class PlayerServiceTest {

	private static IPlayerService playerService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playerService = (IPlayerService)SpringService.getApplicationContext().getBean("playerService");
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
	
	//@Test
	public void testRefreshFreshmanProtect(){
		playerService.refreshFreshmanProtect();
	}
	
	//@Test
	public void testCreatePlayer() {
		playerService.createPlayer("222228", "22222228", "22222228", 1, "212", 11);
	}
	

}