package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IDefenseDAO;
import com.war.domain.Defense;

public class DefenseDAOTest {

	private static IDefenseDAO defenseDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		defenseDAO = (IDefenseDAO)SpringService.getApplicationContext().getBean("defenseDAO");
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

		Integer defenseID = 2;
		String name = "测试字符串";
		String image = "测试字符串";
		String description = "测试字符串";

		Defense defense = new Defense();
		
		defense.setDefenseID(defenseID);
		defense.setName(name);
		defense.setImage(image);
		defense.setDescription(description);

		//测试创建
		defenseDAO.createDefense(defense);
		assertNotNull(defenseID);

		//测试通过编号获得对象
		Defense destDefense = defenseDAO.getDefenseByID(defenseID);
		assertNotNull(destDefense);
		assertEquals(defenseID,destDefense.getDefenseID());
		assertEquals(name,destDefense.getName());
		assertEquals(image,destDefense.getImage());
		assertEquals(description,destDefense.getDescription());

		//测试获得列表
		List<Defense> defenseList = defenseDAO.getDefenseList();
		assertFalse(defenseList.isEmpty());

		//测试更新
		name = "字符串修改";
		image = "字符串修改";
		description = "字符串修改";
		destDefense.setDefenseID(defenseID);
		destDefense.setName(name);
		destDefense.setImage(image);
		destDefense.setDescription(description);
		defenseDAO.updateDefense(destDefense);
		Defense updatedDefense = defenseDAO.getDefenseByID(defenseID);
		assertNotNull(updatedDefense);
		assertEquals(defenseID,updatedDefense.getDefenseID());
		assertEquals(name,updatedDefense.getName());
		assertEquals(image,updatedDefense.getImage());
		assertEquals(description,updatedDefense.getDescription());

		//测试删除
		defenseDAO.deleteDefenseByID(defenseID);
		assertNull(defenseDAO.getDefenseByID(defenseID));

	}

}