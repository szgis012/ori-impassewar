package com.war.test.dao;


import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.war.common.SpringService;
import com.war.dao.IMilitaryDAO;

public class MilitaryDAOTest {

	private static IMilitaryDAO militaryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		militaryDAO = (IMilitaryDAO) SpringService.getApplicationContext().getBean("militaryDAO");
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
	public void testGetMilitaryActionList(){
		List<Map<String,Object>> list = militaryDAO.getMilitaryActionList(4);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testGetAttackDetail(){
		Map<String,Object> detail = militaryDAO.getAttackDetail(3);
		assertNotNull(detail);
	}
}