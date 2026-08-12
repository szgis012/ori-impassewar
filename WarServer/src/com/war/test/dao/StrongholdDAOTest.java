package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IStrongholdDAO;
import com.war.domain.Stronghold;

public class StrongholdDAOTest {

	private static IStrongholdDAO strongholdDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		strongholdDAO = (IStrongholdDAO)SpringService.getApplicationContext().getBean("strongholdDAO");
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

		Integer playerID = 1;
		Integer posX = 1;
		Integer posY = 1;
		String name = "测试字符串";
		Date createtime = new Date();

		Stronghold stronghold = new Stronghold();
		
		stronghold.setPlayerID(playerID);
		stronghold.setPosX(posX);
		stronghold.setPosY(posY);
		stronghold.setName(name);
		stronghold.setCreatetime(createtime);

		//测试创建
		Integer strongholdID = strongholdDAO.createStronghold(stronghold);
		assertNotNull(strongholdID);

		//测试通过编号获得对象
		Stronghold destStronghold = strongholdDAO.getStrongholdByID(strongholdID);
		assertNotNull(destStronghold);
		assertEquals(strongholdID,destStronghold.getStrongholdID());
		assertEquals(playerID,destStronghold.getPlayerID());
		assertEquals(posX,destStronghold.getPosX());
		assertEquals(posY,destStronghold.getPosY());
		assertEquals(name,destStronghold.getName());
		
		//测试获得列表
		List<Stronghold> strongholdList = strongholdDAO.getStrongholdList();
		assertFalse(strongholdList.isEmpty());

		//测试更新
		playerID = 10;
		posX = 10;
		posY = 10;
		name = "字符串修改";
		destStronghold.setStrongholdID(strongholdID);
		destStronghold.setPlayerID(playerID);
		destStronghold.setPosX(posX);
		destStronghold.setPosY(posY);
		destStronghold.setName(name);
		strongholdDAO.updateStronghold(destStronghold);
		Stronghold updatedStronghold = strongholdDAO.getStrongholdByID(strongholdID);
		assertNotNull(updatedStronghold);
		assertEquals(strongholdID,updatedStronghold.getStrongholdID());
		assertEquals(playerID,updatedStronghold.getPlayerID());
		assertEquals(posX,updatedStronghold.getPosX());
		assertEquals(posY,updatedStronghold.getPosY());
		assertEquals(name,updatedStronghold.getName());

		//测试删除
		strongholdDAO.deleteStrongholdByID(strongholdID);
		assertNull(strongholdDAO.getStrongholdByID(strongholdID));

	}

}