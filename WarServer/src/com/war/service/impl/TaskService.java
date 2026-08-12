package com.war.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.war.common.CacheService;
import com.war.constant.CacheConstant;
import com.war.constant.SuffixConstant;
import com.war.constant.TaskConstant;
import com.war.dao.IPlayerTaskDAO;
import com.war.dao.ITaskDAO;
import com.war.domain.PlayerTask;
import com.war.domain.Task;
import com.war.exception.GameException;
import com.war.script.IGameScriptContextFactory;
import com.war.script.IGameScriptEngine;
import com.war.service.ITaskService;

public class TaskService implements ITaskService {
	
	private ITaskDAO taskDAO;
	
	private IPlayerTaskDAO playerTaskDAO;
	
	private IGameScriptEngine gameScriptEngine;
	
	private IGameScriptContextFactory gameScriptContextFactory;
	
	private DataSourceTransactionManager transactionManager;
	
	private static final String PRE_SCRIPT_PATH = "/script/task/";
	
	private static Logger logger = Logger.getLogger(TaskService.class);
	
	Lock lockGetPlayerTaskList = new ReentrantLock();
	
	Lock lockGetReward = new ReentrantLock();
	
	//初始化任务信息    
	public Map<Integer, Task> initTasksMap() {
		Map<Integer, Task> tasksMap = new HashMap<Integer, Task>();
		List<Task> taskList = taskDAO.getTaskList();
		for (int i=0;i<taskList.size();i++) {
			tasksMap.put(taskList.get(i).getTaskID(), taskList.get(i));
		}
		return tasksMap;
	}
	
	public Integer createTask(Task task) {
		return taskDAO.createTask(task);
	}

	public void deleteTaskByID(Integer taskID) {
		taskDAO.deleteTaskByID(taskID);

	}
	
	
	 public PlayerTask getPlayerTaskByTaskID(Integer playerID,Integer taskID){
		 return playerTaskDAO.getPlayerTaskByPlayerIDAndTaskID(playerID, taskID);
	 }

	 
	public List<PlayerTask> getPlayerTaskList(Integer playerID, Integer taskType) {
		try {
			lockGetPlayerTaskList.lock();
			
			List<PlayerTask> playerTaskList = playerTaskDAO.getPlayerTaskListByPlayerIDAndTaskTypeAndFlag(playerID, taskType, TaskConstant.INCOMPLETED_DAILY_TASK_FLAG);
			
			if(playerTaskList.size() > 0) {
				Task task = null;
				Integer taskState = null;
				
				// 处理每一个任务信息
				for (PlayerTask playerTask : playerTaskList) {
					task = this.getTaskByID(playerTask.getTaskID());
					playerTask.setTask(task);
					
					// 获得任务目标
					taskState = this.readTask(task, playerID);
					
					if (taskState==1 && playerTask.getState()==0) {
						// 更新任务状态为已完成
						playerTaskDAO.updateStateByPlayerTaskID(playerTask.getPlayerTaskID(), taskState);
					}
					
					playerTask.setState(taskState);
				}
			}
			return playerTaskList;
			
		} finally {
			lockGetPlayerTaskList.unlock();
		}
	}
	
	//读取任务信息,返回任务完成标志
	private Integer readTask(Task task,Integer playerID){
		//Script.task  .gy 脚本       
		String fileName = task.getTaskID()+SuffixConstant.TASK_SCRIPT_SUFFIX;
		//执行脚本
		try {
			//获得任务检查脚本执行的上下文       检查玩家任务完成情况
			gameScriptEngine.setContext(gameScriptContextFactory.getContext(playerID, 2, null));
			System.out.println(PRE_SCRIPT_PATH+fileName);
			//任务脚本返回信息
			String result = (String)gameScriptEngine.executeScript(PRE_SCRIPT_PATH+fileName);
			//任务目标完成信息
			task.setTargetInfo(this.getTargetText(result));
			//解析任务完成标志
			return this.getTaskFlag(result);
		}catch(Exception e){
			logger.error("异常：", e);
			logger.error("playerID:" + playerID);
		}
		
		return 0;
	}
	
	/**
	 * 解析任务目标文本
	 * @param result 任务脚本的返回结果 
	 * @return
	 */
	private String getTargetText(String result){
		return result.substring(1);
	}
	
	/**
	 * 解析任务完成标志
	 * @param result 任务脚本的返回结果
	 * @return
	 */
	private Integer getTaskFlag(String result){
		return Integer.valueOf(result.substring(0, 1));
	}
	
	public boolean hasCompletedTask(Integer playerID){
		List<PlayerTask> ptlist = playerTaskDAO.getPlayerTaskListByPlayerID(playerID);
		
		if(ptlist.size()>0){
			Task task;
			
			//处理每一个任务信息
			for (PlayerTask pt : ptlist){
				task = this.getTaskByID(pt.getTaskID());
				pt.setTask(task);
				pt.setState(this.readTask(task,playerID));
				//如果任务已完成就返回
				if(pt.getState() == 1)
					return true;
			}
		}
		
		return false;
	}


	//提交任务 领取任务奖励
	public void getReward(Integer playerTaskID) {
		
		//DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		//TransactionStatus status = null;
		
		try {
			lockGetReward.lock();
			
			//status = transactionManager.getTransaction(td);
			
			PlayerTask playerTask = playerTaskDAO.getPlayerTaskByID(playerTaskID);
			
			//只有任务已完成才允许领取奖励
			if (playerTask==null) {
				throw new GameException("您未领取或已完成该任务。");
			}
			
			if (!playerTask.getState().equals(1)) {
				throw new GameException("您还未完成该任务。");
			}
		
			Task task = this.getTaskByID(playerTask.getTaskID());
	
			String fileName = task.getTaskID()+SuffixConstant.TASK_SCRIPT_SUFFIX;
	
			//执行脚本
			//获得领取任务奖励脚本执行的上下文
			gameScriptEngine.setContext(gameScriptContextFactory.getContext(playerTask.getPlayerID(), 1, null));
			gameScriptEngine.executeScript(PRE_SCRIPT_PATH + fileName);

			switch (task.getType()) {
				case TaskConstant.BEGINNER:
				case TaskConstant.DEVELOPMENT:
					// 普通任务
					playerTaskDAO.deletePlayerTaskByID(playerTaskID);
					// 添加触发任务
					this.addTriggerTask(playerTask.getPlayerID(), task.getTriggerTaskList());
					break;
				case TaskConstant.DAILY:
					// 日常任务
					playerTaskDAO.updateFlagByPlayerTaskID(playerTaskID, 1);
					break;
				case TaskConstant.CYCLE:
					// 循环任务
					playerTaskDAO.updateStateByPlayerTaskID(playerTaskID, 0);
					break;
			}
			
			//transactionManager.commit(status);
		} catch (RuntimeException re) {
			//transactionManager.rollback(status);
			throw new GameException(re.getMessage().substring(re.getMessage().indexOf(" ")+1,re.getMessage().length()));
		} catch (Exception e) {
			//transactionManager.rollback(status);
			logger.error("异常：", e);
			throw new GameException("领取奖励失败，请稍后再试。");
		} finally {
			lockGetReward.unlock();
		}
	}
	
	/**
	 * 添加触发的任务
	 * @param playerID 玩家编号
	 * @param taskList 已逗号(,)分割的任务TaskID列表
	 */
	private void addTriggerTask(Integer playerID, String triggerTaskList){
		if (triggerTaskList==null || triggerTaskList.length()==0)
			return;
		
		String[] taskIDs = triggerTaskList.split(",");
		PlayerTask playerTask = null;
		
		Task task = null;
		// 创建触发任务
		for(int i=0; i<taskIDs.length; i++){
			
			playerTask = playerTaskDAO.getPlayerTaskByPlayerIDAndTaskID(playerID, Integer.valueOf(taskIDs[i]));
			if (playerTask != null)
				continue;
			
			task = this.getTaskByID(Integer.valueOf(taskIDs[i]));
			
			playerTask = new PlayerTask();
			playerTask.setPlayerID(playerID);
			playerTask.setTaskID(task.getTaskID());
			playerTask.setTaskType(task.getType());
			playerTask.setState(0);
			playerTask.setFlag(0);
			
			playerTaskDAO.createPlayerTask(playerTask);
		}
	}

	public void refreshDailyTask(){
		playerTaskDAO.refreshDailyTask();
	}
	
	@SuppressWarnings("unchecked")
	public Task getTaskByID(Integer taskID) {
		return ((Map<Integer, Task>)CacheService.getFromCache(CacheConstant.TASKS_MAP)).get(taskID);
	}

	public List<Task> getTaskList() {
		return taskDAO.getTaskList();
	}

	public List<Task> getTaskList(Integer type) {
		return taskDAO.getTaskList(type);
	}

	public void updateTask(Task task) {
		taskDAO.updateTask(task);
	}
	
	public void deletePlayerTaskByID(Integer playerTaskID) {
		playerTaskDAO.deletePlayerTaskByID(playerTaskID);
	}

	public PlayerTask getPlayerTaskByID(Integer playerTaskID) {
		return playerTaskDAO.getPlayerTaskByID(playerTaskID);
	}

	public List<PlayerTask> getPlayerTaskList(Integer playerID) {
		return playerTaskDAO.getPlayerTaskListByPlayerID(playerID);
	}
	
	public void deletePlayerTask(Integer playerID, Integer taskID) {
		playerTaskDAO.deletePlayerTaskByPlayerIDAndTaskID(playerID, taskID);
	}


	public ITaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(ITaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public IPlayerTaskDAO getPlayerTaskDAO() {
		return playerTaskDAO;
	}

	public void setPlayerTaskDAO(IPlayerTaskDAO playerTaskDAO) {
		this.playerTaskDAO = playerTaskDAO;
	}

	public IGameScriptEngine getGameScriptEngine() {
		return gameScriptEngine;
	}

	public void setGameScriptEngine(IGameScriptEngine gameScriptEngine) {
		this.gameScriptEngine = gameScriptEngine;
	}

	public IGameScriptContextFactory getGameScriptContextFactory() {
		return gameScriptContextFactory;
	}

	public void setGameScriptContextFactory(
			IGameScriptContextFactory gameScriptContextFactory) {
		this.gameScriptContextFactory = gameScriptContextFactory;
	}

	public Integer createPlayerTask(PlayerTask playerTask) {
		return playerTaskDAO.createPlayerTask(playerTask);
	}

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
