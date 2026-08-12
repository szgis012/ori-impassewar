package com.war.test.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IDeclareWarDAO;
import com.war.domain.DeclareWar;

public class DeclareWarDAOTest {

	private static IDeclareWarDAO declareWarDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		declareWarDAO = (IDeclareWarDAO)SpringService.getApplicationContext().getBean("declareWarDAO");
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
		Integer targetPlayerID = 1;
		Date startTime = new Date();
		Date finishTime = new Date();

		DeclareWar declareWar = new DeclareWar();
		
		declareWar.setPlayerID(playerID);
		declareWar.setTargetPlayerID(targetPlayerID);
		declareWar.setStartTime(startTime);
		declareWar.setFinishTime(finishTime);

		//测试创建
		Integer declareWarID = declareWarDAO.createDeclareWar(declareWar);
		assertNotNull(declareWarID);

		//测试通过编号获得对象
		DeclareWar destDeclareWar = declareWar;
		
		//测试更新
		declareWarID = 10;
		playerID = 10;
		targetPlayerID = 10;
		destDeclareWar.setDeclareWarID(declareWarID);
		destDeclareWar.setPlayerID(playerID);
		destDeclareWar.setTargetPlayerID(targetPlayerID);
		destDeclareWar.setStartTime(startTime);
		destDeclareWar.setFinishTime(finishTime);
		declareWarDAO.updateDeclareWar(destDeclareWar);

	}

}