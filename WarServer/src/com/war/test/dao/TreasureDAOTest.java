package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ITreasureDAO;
import com.war.domain.Treasure;


public class TreasureDAOTest {

	private static ITreasureDAO treasureDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		treasureDAO = (ITreasureDAO)SpringService.getApplicationContext().getBean("treasureDAO");
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

		Integer treasureID = 1;
		String name = "测试字符串";
		String description = "测试字符串";
		Integer type = 1;
		Integer cost = 1;
		String imgSrc = "测试字符串";
		String codeSrc = "测试字符串";

		Treasure treasure = new Treasure();
		
		treasure.setTreasureID(treasureID);
		treasure.setName(name);
		treasure.setDescription(description);
		treasure.setType(type);
		treasure.setCost(cost);
		treasure.setImgSrc(imgSrc);
		treasure.setCodeSrc(codeSrc);
		treasureDAO.createTreasure(treasure);
		//测试创建
		assertNotNull(treasureID);

		//测试通过编号获得对象
		Treasure destTreasure = treasureDAO.getTreasureByID(treasureID);
		assertNotNull(destTreasure);
		assertEquals(treasureID,destTreasure.getTreasureID());
		assertEquals(name,destTreasure.getName());
		assertEquals(description,destTreasure.getDescription());
		assertEquals(type,destTreasure.getType());
		assertEquals(cost,destTreasure.getCost());
		assertEquals(imgSrc,destTreasure.getImgSrc());
		assertEquals(codeSrc,destTreasure.getCodeSrc());
		
		//测试获得列表
		List<Treasure> treasureList = treasureDAO.getTreasureList();
		assertFalse(treasureList.isEmpty());

		//测试更新
		treasureID = 1;
		name = "字符串修改";
		description = "字符串修改";
		type = 10;
		cost = 10;
		imgSrc = "字符串修改";
		codeSrc = "字符串修改";
		destTreasure.setTreasureID(treasureID);
		destTreasure.setName(name);
		destTreasure.setDescription(description);
		destTreasure.setType(type);
		destTreasure.setCost(cost);
		destTreasure.setImgSrc(imgSrc);
		destTreasure.setCodeSrc(codeSrc);
		treasureDAO.updateTreasure(destTreasure);
		Treasure updatedTreasure = treasureDAO.getTreasureByID(treasureID);
		assertNotNull(updatedTreasure);
		assertEquals(treasureID,updatedTreasure.getTreasureID());
		assertEquals(name,updatedTreasure.getName());
		assertEquals(description,updatedTreasure.getDescription());
		assertEquals(type,updatedTreasure.getType());
		assertEquals(cost,updatedTreasure.getCost());
		assertEquals(imgSrc,updatedTreasure.getImgSrc());
		assertEquals(codeSrc,updatedTreasure.getCodeSrc());

		//测试删除
		treasureDAO.deleteTreasureByID(treasureID);
		assertNull(treasureDAO.getTreasureByID(treasureID));

	}
	
	@Test
	public void testGetTreasureListByState(){
		List<Treasure> treasureList = treasureDAO.getTreasureListByState(0);
		assertFalse(treasureList.isEmpty());
	}
	
	@Test
	public void testGetRecommendTreasureList(){
		List<Treasure> treasureList = treasureDAO.getRecommendTreasureList();
		assertTrue(treasureList.isEmpty());
	}

}