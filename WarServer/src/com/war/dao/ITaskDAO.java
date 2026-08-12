package com.war.dao;


import java.util.List;

import com.war.domain.Task;

/**
 * 任务dao接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface ITaskDAO {
	/**
	 * 创建任务
	 * @param task
	 * @return
	 */
	public Integer createTask(Task task);

	/**
	 * 更新任务信息
	 * @param task
	 */
	public void updateTask(Task task);

	/**
	 * 根据编号删除任务
	 * @param taskID
	 */
	public void deleteTaskByID(Integer taskID);

	/**
	 * 获得指定任务编号的任务
	 * @param taskID
	 * @return
	 */
	public Task getTaskByID(Integer taskID);

	/**
	 * 得到所有任务列表
	 * @return
	 */
	public List<Task> getTaskList();
	
	/**
	 * 得到指定类型的所有任务列表
	 * @param type 任务类型
	 * @return
	 */
	public List<Task> getTaskList(Integer type);

}