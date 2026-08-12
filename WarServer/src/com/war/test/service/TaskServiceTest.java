package com.war.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.domain.PlayerTask;
import com.war.service.ITaskService;

public class TaskServiceTest {
	static ITaskService taskService;
	static Integer playerID = 14;
	static Integer taskID = 1;
	
	private Integer playerTaskID;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		taskService = (ITaskService)SpringService.getApplicationContext().getBean("taskService");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		PlayerTask pt = new PlayerTask();
		pt.setPlayerID(playerID);
		pt.setTaskID(taskID);
		pt.setTaskType(1);
		pt.setState(0);
		pt.setFlag(0);
		playerTaskID = taskService.createPlayerTask(pt);
	}

	@After
	public void tearDown() throws Exception {
		taskService.deletePlayerTaskByID(playerTaskID);
	}
	
	@Test
	public void testGetPlayerTaskList() {
		List<PlayerTask> ptlist = taskService.getPlayerTaskList(playerID, 1);
		assertTrue(ptlist.size()>0);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetReward() {
		taskService.getReward(playerTaskID);
	}

	@Test
	public void testHasCompletedTask(){
		boolean flag = taskService.hasCompletedTask(playerID);
		System.out.println(flag);
	}
}
