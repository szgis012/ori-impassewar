package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.PlayerTask;
import com.war.domain.Task;

public interface ITaskService {
	
	/**
	 * 初始化缓存Map
	 * @return
	 */
	public Map<Integer, Task> initTasksMap();
	
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
	 * 根据编号获得任务(缓存)
	 * @param taskID
	 * @return
	 */
	public Task getTaskByID(Integer taskID);

	/**
	 * 根据任务编号获得一项玩家任务信息
	 * @param playerID
	 * @param taskID
	 * @return
	 */
	public PlayerTask getPlayerTaskByTaskID(Integer playerID,Integer taskID);
	
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
	
	/**
	 * 领取奖励
	 * @param playerTaskID
	 */
	public void getReward(Integer playerTaskID);
	
	/**
	 * 根据玩家编号及任务类型获得玩家任务列表
	 * @param playerID 玩家编号
	 * @param taskType 任务类型
	 * @return
	 */
	public List<PlayerTask> getPlayerTaskList(Integer playerID,Integer taskType);
	
	/**
	 * 创建一项玩家任务信息
	 * @param playerTask
	 * @return
	 */
	public Integer createPlayerTask(PlayerTask playerTask);

	/**
	 * 删除一项玩家任务信息
	 * @param playerTaskID
	 */
	public void deletePlayerTaskByID(Integer playerTaskID);

	/**
	 * 根据编号获得一项玩家任务信息
	 * @param playerTaskID
	 * @return
	 */
	public PlayerTask getPlayerTaskByID(Integer playerTaskID);

	/**
	 * 获得玩家当前任务信息列表
	 * @param playerID
	 * @return
	 */
	public List<PlayerTask> getPlayerTaskList(Integer playerID);
	
	/**
	 * 判断玩家是否有已经完成的任务
	 * @param playerID
	 * @return true表示有已完成的任务，false表示没有
	 */
	public boolean hasCompletedTask(Integer playerID);
	
	/**
	 * 更新日常任务
	 */
	public void refreshDailyTask();

	/**
	 * 删除玩家任务
	 * @param playerID
	 * @param taskID
	 */
	public void deletePlayerTask(Integer playerID, Integer taskID);
}
