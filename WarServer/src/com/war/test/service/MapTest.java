package com.war.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.domain.Map;
import com.war.service.IMapService;

public class MapTest {

	private static IMapService mapService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapService = (IMapService)SpringService.getApplicationContext().getBean("mapService");
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
	public void testGetMapList() {
		List<Map> mapList = new ArrayList<Map>();
		
		for (int i=1;i<=20;i++) {
			Map map = new Map();
			map.setPosX(i);
			map.setPosY(i);
			mapList.add(map);
		}

		Date date1 = new Date();
		mapService.getMapListByMapPosXYList(mapList);
		System.out.println(System.currentTimeMillis()-date1.getTime());
		
	}
	
	@Test
	public void testGenerateGameMap() {
		Date date = new Date();
		mapService.generateGameMap();
		System.out.println(System.currentTimeMillis()-date.getTime());
	}
	
}
