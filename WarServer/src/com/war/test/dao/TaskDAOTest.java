package com.war.test.dao;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.war.common.SpringService;
import com.war.dao.ITaskDAO;
import com.war.domain.Task;

public class TaskDAOTest {

	private static ITaskDAO taskDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		taskDAO = (ITaskDAO)SpringService.getApplicationContext().getBean("taskDAO");
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

		Integer taskID = 1;
		String name = "测试字符串";
		String description = "测试字符串";
		String reward = "黄金10000";
		Integer type = 1;
		String triggerTaskList = "测试字符串";
		String codeSrc = "测试字符串";

		Task task = new Task();
		
		task.setTaskID(taskID);
		task.setName(name);
		task.setDescription(description);
		task.setType(type);
		task.setReward(reward);
		task.setTriggerTaskList(triggerTaskList);

		//测试创建
		taskID = taskDAO.createTask(task);
		assertNotNull(taskID);

		//测试通过编号获得对象
		Task destTask = taskDAO.getTaskByID(taskID);
		assertNotNull(destTask);
		assertEquals(taskID,destTask.getTaskID());
		assertEquals(name,destTask.getName());
		assertEquals(description,destTask.getDescription());
		assertEquals(type,destTask.getType());
		assertEquals(reward,destTask.getReward());
		assertEquals(triggerTaskList,destTask.getTriggerTaskList());
		
		//测试获得列表
		List<Task> taskList = taskDAO.getTaskList();
		assertFalse(taskList.isEmpty());
		
		taskList = taskDAO.getTaskList(type);
		assertFalse(taskList.isEmpty());
		
		//测试更新
		name = "字符串修改";
		description = "字符串修改";
		type = 10;
		reward = "白菜1000担";
		triggerTaskList = "字符串修改";
		codeSrc = "字符串修改";
		destTask.setTaskID(taskID);
		destTask.setName(name);
		destTask.setDescription(description);
		destTask.setType(type);
		destTask.setReward(reward);
		destTask.setTriggerTaskList(triggerTaskList);
		taskDAO.updateTask(destTask);
		Task updatedTask = taskDAO.getTaskByID(taskID);
		assertNotNull(updatedTask);
		assertEquals(taskID,updatedTask.getTaskID());
		assertEquals(name,updatedTask.getName());
		assertEquals(description,updatedTask.getDescription());
		assertEquals(type,updatedTask.getType());
		assertEquals(reward,updatedTask.getReward());
		assertEquals(triggerTaskList,updatedTask.getTriggerTaskList());

		//测试删除
		taskDAO.deleteTaskByID(taskID);
		assertNull(taskDAO.getTaskByID(taskID));

	}

}