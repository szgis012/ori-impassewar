package com.war.test.service;

import org.junit.Before;
import org.junit.BeforeClass;

import com.war.common.SpringService;
import com.war.service.IMapService;

public class MapServiceTest {
	private static IMapService mapService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapService = (IMapService)SpringService.getApplicationContext().getBean("mapService");
	}

	@Before
	public void setUp() throws Exception {
	}

	public void testGetBlankMapPos() {
	}
	
}

