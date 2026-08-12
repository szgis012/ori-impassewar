package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IOrdnanceDAO;
import com.war.domain.Ordnance;

public class OrdnanceDAOTest {

	private static IOrdnanceDAO ordnanceDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ordnanceDAO = (IOrdnanceDAO)SpringService.getApplicationContext().getBean("ordnanceDAO");
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
	public void testCURD() {

		Integer constraintDependID = 1;
		String name = "测试字符串";
		String image = "测试字符串";
		String description = "测试字符串";
		Integer type = 1;

		Ordnance ordnance = new Ordnance();
		
		ordnance.setConstraintDependID(constraintDependID);
		ordnance.setName(name);
		ordnance.setImage(image);
		ordnance.setDescription(description);
		ordnance.setType(type);

		//测试创建
		Integer ordnanceID = ordnanceDAO.createOrdnance(ordnance);
		assertNotNull(ordnanceID);

		//测试通过编号获得对象
		Ordnance destOrdnance = ordnanceDAO.getOrdnanceByID(ordnanceID);
		assertNotNull(destOrdnance);
		assertEquals(ordnanceID,destOrdnance.getOrdnanceID());
		assertEquals(constraintDependID,destOrdnance.getConstraintDependID());
		assertEquals(name,destOrdnance.getName());
		assertEquals(image,destOrdnance.getImage());
		assertEquals(description,destOrdnance.getDescription());
		assertEquals(type,destOrdnance.getType());
		
		//测试获得列表
		List<Ordnance> ordnanceList = ordnanceDAO.getOrdnanceList();
		assertFalse(ordnanceList.isEmpty());

		//测试更新
//		ordnanceID = 10;
//		constraintDependID = 10;
		name = "字符串修改";
		image = "字符串修改";
		description = "字符串修改";
		type = 2;
		destOrdnance.setOrdnanceID(ordnanceID);
		destOrdnance.setConstraintDependID(constraintDependID);
		destOrdnance.setName(name);
		destOrdnance.setImage(image);
		destOrdnance.setDescription(description);
		destOrdnance.setType(type);
		ordnanceDAO.updateOrdnance(destOrdnance);
		Ordnance updatedOrdnance = ordnanceDAO.getOrdnanceByID(ordnanceID);
		assertNotNull(updatedOrdnance);
		assertEquals(ordnanceID,updatedOrdnance.getOrdnanceID());
		assertEquals(constraintDependID,updatedOrdnance.getConstraintDependID());
		assertEquals(name,updatedOrdnance.getName());
		assertEquals(image,updatedOrdnance.getImage());
		assertEquals(description,updatedOrdnance.getDescription());
		assertEquals(type,updatedOrdnance.getType());

		//测试删除
		ordnanceDAO.deleteOrdnanceByID(ordnanceID);
		assertNull(ordnanceDAO.getOrdnanceByID(ordnanceID));

	}

}
