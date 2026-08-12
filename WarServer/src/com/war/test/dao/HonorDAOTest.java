package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IHonorDAO;
import com.war.domain.Honor;

public class HonorDAOTest {

	private static IHonorDAO honorDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		honorDAO = (IHonorDAO)SpringService.getApplicationContext().getBean("honorDAO");
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

		String name = "测试";

		Honor honor = new Honor();
		
		honor.setName(name);

		//测试创建
		Integer honorID = honorDAO.createHonor(honor);
		assertNotNull(honorID);

		//测试通过编号获得对象
		Honor destHonor = honorDAO.getHonorByID(honorID);
		assertNotNull(destHonor);
		assertEquals(honorID,destHonor.getHonorID());
		assertEquals(name,destHonor.getName());
		
		//测试获得列表
		List<Honor> honorList = honorDAO.getHonorList();
		assertFalse(honorList.isEmpty());

		//测试更新
		name = "修改";
		destHonor.setHonorID(honorID);
		destHonor.setName(name);
		honorDAO.updateHonor(destHonor);
		Honor updatedHonor = honorDAO.getHonorByID(honorID);
		assertNotNull(updatedHonor);
		assertEquals(honorID,updatedHonor.getHonorID());
		assertEquals(name,updatedHonor.getName());

		//测试删除
		honorDAO.deleteHonorByID(honorID);
		assertNull(honorDAO.getHonorByID(honorID));

	}

}