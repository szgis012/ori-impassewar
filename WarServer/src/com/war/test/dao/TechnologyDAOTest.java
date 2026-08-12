package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ITechnologyDAO;
import com.war.domain.Technology;

public class TechnologyDAOTest {

	private static ITechnologyDAO technologyDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		technologyDAO = (ITechnologyDAO)SpringService.getApplicationContext().getBean("technologyDAO");
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

		String name = "测试字符串";
		String image = "测试字符串";
		Integer maxLevel = 1;
		String description = "测试字符串";

		Technology technology = new Technology();
		
		technology.setName(name);
		technology.setImage(image);
		technology.setMaxLevel(maxLevel);
		technology.setDescription(description);

		//测试创建
		Integer technologyID = technologyDAO.createTechnology(technology);
		assertNotNull(technologyID);

		//测试通过编号获得对象
		Technology destTechnology = technologyDAO.getTechnologyByID(technologyID);
		assertNotNull(destTechnology);
		assertEquals(technologyID,destTechnology.getTechnologyID());
		assertEquals(name,destTechnology.getName());
		assertEquals(image,destTechnology.getImage());
		assertEquals(maxLevel,destTechnology.getMaxLevel());
		assertEquals(description,destTechnology.getDescription());
		
		//测试获得列表
		List<Technology> technologyList = technologyDAO.getTechnologyList();
		assertFalse(technologyList.isEmpty());

		//测试更新
		name = "字符串修改";
		image = "字符串修改";
		maxLevel = 10;
		description = "字符串修改";
		destTechnology.setName(name);
		destTechnology.setImage(image);
		destTechnology.setMaxLevel(maxLevel);
		destTechnology.setDescription(description);
		technologyDAO.updateTechnology(destTechnology);
		Technology updatedTechnology = technologyDAO.getTechnologyByID(technologyID);
		assertNotNull(updatedTechnology);
		assertEquals(technologyID,updatedTechnology.getTechnologyID());
		assertEquals(name,updatedTechnology.getName());
		assertEquals(image,updatedTechnology.getImage());
		assertEquals(maxLevel,updatedTechnology.getMaxLevel());
		assertEquals(description,updatedTechnology.getDescription());

		//测试删除
		technologyDAO.deleteTechnologyByID(technologyID);
		assertNull(technologyDAO.getTechnologyByID(technologyID));

	}

}