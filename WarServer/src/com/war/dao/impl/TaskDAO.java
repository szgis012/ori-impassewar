package com.war.dao.impl;


import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ITaskDAO;
import com.war.domain.Task;

/**
 * 任务dao实现
 *
 * @author ghleed
 * @version 1.0
 */
public class TaskDAO extends SqlMapClientDaoSupport implements ITaskDAO{

	public Integer createTask(Task task) {
		return (Integer)this.getSqlMapClientTemplate().insert("Task.createTask", task);
	}
	
	public void updateTask(Task task) {
		this.getSqlMapClientTemplate().update("Task.updateTask", task);
	}
	
	public void deleteTaskByID(Integer taskID) {
		this.getSqlMapClientTemplate().delete("Task.deleteTaskByID", taskID);
	}
	
	public Task getTaskByID(Integer taskID) {
		return (Task)this.getSqlMapClientTemplate().queryForObject("Task.getTaskByID", taskID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> getTaskList() {
		return this.getSqlMapClientTemplate().queryForList("Task.getTaskList");
	}

	@SuppressWarnings("unchecked")
	public List<Task> getTaskList(Integer type) {
		return this.getSqlMapClientTemplate().queryForList("Task.getTaskListByType",type);
	}

}