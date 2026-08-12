package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IMapMonsterDAO;
import com.war.domain.MapMonster;

public class MapMonsterDAOTest {

	private static IMapMonsterDAO mapMonsterDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapMonsterDAO = (IMapMonsterDAO)SpringService.getApplicationContext().getBean("mapMonsterDAO");
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

		Integer level = 1;
		String cmderName = "测试字符串";
		String cmderHead = "测试字符串";
		Integer cmderLevel = 1;
		Integer cmderCommand = 1;
		Integer cmderDefense = 1;
		String army1 = "测试字符串";
		String army2 = "测试字符串";
		String army3 = "测试字符串";
		String army4 = "测试字符串";
		String army5 = "测试字符串";
		String army6 = "测试字符串";
		String army7 = "测试字符串";
		String army8 = "测试字符串";

		MapMonster mapMonster = new MapMonster();
		
		mapMonster.setLevel(level);
		mapMonster.setCmderName(cmderName);
		mapMonster.setCmderHead(cmderHead);
		mapMonster.setCmderLevel(cmderLevel);
		mapMonster.setCmderCommand(cmderCommand);
		mapMonster.setCmderDefense(cmderDefense);
		mapMonster.setArmy2(army2);
		mapMonster.setArmy3(army3);
		mapMonster.setArmy4(army4);
		mapMonster.setArmy5(army5);
		mapMonster.setArmy6(army6);
		mapMonster.setArmy7(army7);
		mapMonster.setArmy8(army8);

		//测试创建
		Integer mapMonsterID = mapMonsterDAO.createMapMonster(mapMonster);
		assertNotNull(mapMonsterID);

		//测试通过编号获得对象
		MapMonster destMapMonster = mapMonsterDAO.getMapMonsterByID(mapMonsterID);
		assertNotNull(destMapMonster);
		assertEquals(mapMonsterID,destMapMonster.getMapMonsterID());
		assertEquals(level,destMapMonster.getLevel());
		assertEquals(cmderName,destMapMonster.getCmderName());
		assertEquals(cmderHead,destMapMonster.getCmderHead());
		assertEquals(cmderLevel,destMapMonster.getCmderLevel());
		assertEquals(cmderCommand,destMapMonster.getCmderCommand());
		assertEquals(cmderDefense,destMapMonster.getCmderDefense());
		assertEquals(army1,destMapMonster.getArmy1());
		assertEquals(army2,destMapMonster.getArmy2());
		assertEquals(army3,destMapMonster.getArmy3());
		assertEquals(army4,destMapMonster.getArmy4());
		assertEquals(army5,destMapMonster.getArmy5());
		assertEquals(army6,destMapMonster.getArmy6());
		assertEquals(army7,destMapMonster.getArmy7());
		assertEquals(army8,destMapMonster.getArmy8());
		
		//测试获得列表
		List<MapMonster> mapMonsterList = mapMonsterDAO.getMapMonsterList();
		assertFalse(mapMonsterList.isEmpty());

		//测试更新
		level = 10;
		cmderName = "字符串修改";
		cmderHead = "字符串修改";
		cmderLevel = 10;
		cmderCommand = 10;
		cmderDefense = 10;
		army1 = "字符串修改";
		army2 = "字符串修改";
		army3 = "字符串修改";
		army4 = "字符串修改";
		army5 = "字符串修改";
		army6 = "字符串修改";
		army7 = "字符串修改";
		army8 = "字符串修改";
		destMapMonster.setMapMonsterID(mapMonsterID);
		destMapMonster.setLevel(level);
		destMapMonster.setCmderName(cmderName);
		destMapMonster.setCmderHead(cmderHead);
		destMapMonster.setCmderLevel(cmderLevel);
		destMapMonster.setCmderCommand(cmderCommand);
		destMapMonster.setCmderDefense(cmderDefense);
		destMapMonster.setArmy1(army1);
		destMapMonster.setArmy2(army2);
		destMapMonster.setArmy3(army3);
		destMapMonster.setArmy4(army4);
		destMapMonster.setArmy5(army5);
		destMapMonster.setArmy6(army6);
		destMapMonster.setArmy7(army7);
		destMapMonster.setArmy8(army8);
		mapMonsterDAO.updateMapMonster(destMapMonster);
		MapMonster updatedMapMonster = mapMonsterDAO.getMapMonsterByID(mapMonsterID);
		assertNotNull(updatedMapMonster);
		assertEquals(mapMonsterID,updatedMapMonster.getMapMonsterID());
		assertEquals(level,updatedMapMonster.getLevel());
		assertEquals(cmderName,updatedMapMonster.getCmderName());
		assertEquals(cmderHead,updatedMapMonster.getCmderHead());
		assertEquals(cmderLevel,updatedMapMonster.getCmderLevel());
		assertEquals(cmderCommand,updatedMapMonster.getCmderCommand());
		assertEquals(cmderDefense,updatedMapMonster.getCmderDefense());
		assertEquals(army1,updatedMapMonster.getArmy1());
		assertEquals(army2,updatedMapMonster.getArmy2());
		assertEquals(army3,updatedMapMonster.getArmy3());
		assertEquals(army4,updatedMapMonster.getArmy4());
		assertEquals(army5,updatedMapMonster.getArmy5());
		assertEquals(army6,updatedMapMonster.getArmy6());
		assertEquals(army7,updatedMapMonster.getArmy7());
		assertEquals(army8,updatedMapMonster.getArmy8());

		//测试删除
		mapMonsterDAO.deleteMapMonsterByID(mapMonsterID);
		assertNull(mapMonsterDAO.getMapMonsterByID(mapMonsterID));

	}

}