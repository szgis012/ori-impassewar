package com.war.test.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.IPlayerTaskDAO;
import com.war.domain.PlayerTask;

public class PlayerTaskDAOTest {

	private static IPlayerTaskDAO playerTaskDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playerTaskDAO = (IPlayerTaskDAO)SpringService.getApplicationContext().getBean("playerTaskDAO");
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

		Integer playerID = 3;
		Integer taskID = 1001;
		Integer taskType = 1;
		Integer state = 1;
		Integer flag = 1;

		PlayerTask playerTask = new PlayerTask();
		
		playerTask.setPlayerID(playerID);
		playerTask.setTaskID(taskID);
		playerTask.setTaskType(taskType);
		playerTask.setState(state);
		playerTask.setFlag(flag);

		//测试创建
		Integer playerTaskID = playerTaskDAO.createPlayerTask(playerTask);
		assertNotNull(playerTaskID);

		//测试通过编号获得对象
		PlayerTask destPlayerTask = playerTaskDAO.getPlayerTaskByID(playerTaskID);
		assertNotNull(destPlayerTask);
		assertEquals(playerTaskID,destPlayerTask.getPlayerTaskID());
		assertEquals(playerID,destPlayerTask.getPlayerID());
		assertEquals(taskID,destPlayerTask.getTaskID());
		assertEquals(taskType,destPlayerTask.getTaskType());
		assertEquals(state,destPlayerTask.getState());
		assertEquals(flag,destPlayerTask.getFlag());

		//测试更新
		playerID = 3;
		taskID = 1002;
		taskType = 10;
		state = 10;
		flag = 10;
		destPlayerTask.setPlayerTaskID(playerTaskID);
		destPlayerTask.setPlayerID(playerID);
		destPlayerTask.setTaskID(taskID);
		destPlayerTask.setTaskType(taskType);
		destPlayerTask.setState(state);
		destPlayerTask.setFlag(flag);
		playerTaskDAO.updatePlayerTask(destPlayerTask);
		PlayerTask updatedPlayerTask = playerTaskDAO.getPlayerTaskByID(playerTaskID);
		assertNotNull(updatedPlayerTask);
		assertEquals(playerTaskID,updatedPlayerTask.getPlayerTaskID());
		assertEquals(playerID,updatedPlayerTask.getPlayerID());
		assertEquals(taskID,updatedPlayerTask.getTaskID());
		assertEquals(taskType,updatedPlayerTask.getTaskType());
		assertEquals(state,updatedPlayerTask.getState());
		assertEquals(flag,updatedPlayerTask.getFlag());

		//测试删除
		playerTaskDAO.deletePlayerTaskByID(playerTaskID);
		assertNull(playerTaskDAO.getPlayerTaskByID(playerTaskID));



	}

}
