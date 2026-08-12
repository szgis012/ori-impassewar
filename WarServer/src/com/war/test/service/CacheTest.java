package com.war.test.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.war.common.CacheService;
import com.war.common.SpringService;
import com.war.constant.CacheConstant;
import com.war.dao.ITreasureDAO;
import com.war.domain.CityCandidacyHero;
import com.war.service.IHeroService;
import com.war.service.ITreasureService;

public class CacheTest {

	private static ITreasureDAO treasureDAO;
	private static ITreasureService treasureService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		treasureDAO = (ITreasureDAO)SpringService.getApplicationContext().getBean("treasureDAO");
		treasureService = (ITreasureService)SpringService.getApplicationContext().getBean("treasureService");
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
	public void testGetBean() {
		ApplicationContext ctx = null;
		try {
			ctx = SpringService.getApplicationContext();
		} catch (BeansException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i=0;i<5;i++) {
			try {
				System.out.println(ctx.getBean("treasureService"));
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	//@Test
	public void testPerformance() {
		CacheService.putToCache(CacheConstant.TREASURE_LIST_BY_CATEOGRY_MAP, treasureService.initTreasureListByCategoryMap());
		
		Date date1 = new Date();
		for (int i=0;i<1000;i++) {
			treasureDAO.getTreasureListByCategory(1);
		}
		System.out.println(System.currentTimeMillis()-date1.getTime());
		
		Date date2 = new Date();
		for (int i=0;i<1000;i++) {
			treasureService.getTreasureListByCategory(1);
		}
		System.out.println(System.currentTimeMillis()-date2.getTime());
	}
	
	@Test
	public void testForPerformance() {
		Date date = new Date();
		String a = "aaaaaaa";
		String b = "bbbbbbb";
		for (int i=0;i<1000000;i++) {
			if (a.equals(b)) {
				System.out.println("c");
			}
		}
		System.out.println(System.currentTimeMillis()-date.getTime());
	}
	
}
