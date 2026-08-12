package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IShbuildingDAO;
import com.war.domain.Shbuilding;

public class ShbuildingDAOTest {

	private static IShbuildingDAO shbuildingDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shbuildingDAO = (IShbuildingDAO)SpringService.getApplicationContext().getBean("shbuildingDAO");
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
		Integer isonlyone = 1;

		Shbuilding shbuilding = new Shbuilding();
		
		shbuilding.setName(name);
		shbuilding.setImage(image);
		shbuilding.setMaxLevel(maxLevel);
		shbuilding.setDescription(description);
		shbuilding.setIsonlyone(isonlyone);

		//测试创建
		Integer shbuildingID = shbuildingDAO.createShbuilding(shbuilding);
		assertNotNull(shbuildingID);

		//测试通过编号获得对象
		Shbuilding destShbuilding = shbuildingDAO.getShbuildingByID(shbuildingID);
		assertNotNull(destShbuilding);
		assertEquals(shbuildingID,destShbuilding.getShbuildingID());
		assertEquals(name,destShbuilding.getName());
		assertEquals(image,destShbuilding.getImage());
		assertEquals(maxLevel,destShbuilding.getMaxLevel());
		assertEquals(description,destShbuilding.getDescription());
		assertEquals(isonlyone,destShbuilding.getIsonlyone());
		
		//测试获得列表
		List<Shbuilding> shbuildingList = shbuildingDAO.getShbuildingList();
		assertFalse(shbuildingList.isEmpty());

		//测试更新
		name = "字符串修改";
		image = "字符串修改";
		maxLevel = 10;
		description = "字符串修改";
		isonlyone = 10;
		destShbuilding.setShbuildingID(shbuildingID);
		destShbuilding.setName(name);
		destShbuilding.setImage(image);
		destShbuilding.setMaxLevel(maxLevel);
		destShbuilding.setDescription(description);
		destShbuilding.setIsonlyone(isonlyone);
		shbuildingDAO.updateShbuilding(destShbuilding);
		Shbuilding updatedShbuilding = shbuildingDAO.getShbuildingByID(shbuildingID);
		assertNotNull(updatedShbuilding);
		assertEquals(shbuildingID,updatedShbuilding.getShbuildingID());
		assertEquals(name,updatedShbuilding.getName());
		assertEquals(image,updatedShbuilding.getImage());
		assertEquals(maxLevel,updatedShbuilding.getMaxLevel());
		assertEquals(description,updatedShbuilding.getDescription());
		assertEquals(isonlyone,updatedShbuilding.getIsonlyone());

		//测试删除
		shbuildingDAO.deleteShbuildingByID(shbuildingID);
		assertNull(shbuildingDAO.getShbuildingByID(shbuildingID));

	}

}