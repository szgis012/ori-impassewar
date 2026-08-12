package com.war.test.service;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.CacheService;
import com.war.common.SpringService;
import com.war.constant.CacheConstant;
import com.war.dao.IFriendDAO;
import com.war.dao.ITreasureDAO;
import com.war.domain.CityCandidacyHero;
import com.war.domain.Friend;
import com.war.service.IHeroService;
import com.war.service.ITreasureService;

public class ConnectionPollTest {

	private static IFriendDAO friendDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		friendDAO = (IFriendDAO)SpringService.getApplicationContext().getBean("friendDAO");
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
	public void testPerformance() {

		Date date = new Date();
		
		for (int i=0;i<500;i++) {
			Friend friend = new Friend();
			friend.setPlayerID(1);
			friend.setTargetPlayerID(1);
			friend.setState(1);
			friendDAO.createFriend(friend);
		}
	
		System.out.println(System.currentTimeMillis()-date.getTime());
		
	}
	
}
