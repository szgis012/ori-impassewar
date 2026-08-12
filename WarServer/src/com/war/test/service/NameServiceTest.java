package com.war.test.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.service.INameService;


public class NameServiceTest {

	private static INameService nameService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nameService = (INameService)SpringService.getApplicationContext().getBean("nameService");
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
	
	public void testGetName(){
		System.out.println(nameService.generateName());
	}
	
	@Test
	public void testGetNameArray(){
		String[] nameArray = nameService.generateNameArray(5);
		for(int i=0;i<nameArray.length;i++){
			System.out.println(nameArray[i]);
		}
		//System.out.println(nameService.generateNameArray(5));
	}
	
}
