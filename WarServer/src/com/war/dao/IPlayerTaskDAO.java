package com.war.dao;

import java.util.List;

import com.war.domain.PlayerTask;

/**
 * 玩家任务DAO接口
 *
 * @author TopTong
 * @version 1.0
 */
public interface IPlayerTaskDAO {
	
	/**
	 * 创建玩家任务
	 * @param playerTask
	 * @return
	 */
	public Integer createPlayerTask(PlayerTask playerTask);
	
	/**
	 * 根据玩家任务编号更新状态
	 * @param playerTaskID
	 * @param state
	 */
	public void updateStateByPlayerTaskID(Integer playerTaskID, Integer state);
	
	/**
	 * 根据玩家任务编号更新标示
	 * @param playerTaskID
	 * @param flag
	 */
	public void updateFlagByPlayerTaskID(Integer playerTaskID, Integer flag);
	
	/**
	 * 更新玩家任务
	 * @param playerTask
	 */
	public void updatePlayerTask(PlayerTask playerTask);
	
	/**
	 * 重置日常任务
	 */
	public void refreshDailyTask();
	
	/**
	 * 删除玩家任务
	 * @param playerTaskID
	 */
	public void deletePlayerTaskByID(Integer playerTaskID);

	/**
	 * 根据玩家编号和任务编号删除玩家任务
	 * @param playerID
	 * @param taskID
	 */
	public void deletePlayerTaskByPlayerIDAndTaskID(Integer playerID, Integer taskID);
	
	/**
	 * 根据编号获得玩家任务
	 * @param playerTaskID
	 * @return
	 */
	public PlayerTask getPlayerTaskByID(Integer playerTaskID);
	
	/**
	 * 根据玩家编号及任务编号获得玩家任务
	 * @param playerID
	 * @param taskID
	 * @return
	 */
	public PlayerTask getPlayerTaskByPlayerIDAndTaskID(Integer playerID, Integer taskID);

	/**
	 * 根据玩家编号获得玩家任务列表
	 * @param playerID
	 * @return
	 */
	public List<PlayerTask> getPlayerTaskListByPlayerID(Integer playerID);
	
	/**
	 * 根据玩家编号及任务类型获得玩家任务列表
	 * @param playerID
	 * @param taskType
	 * @return
	 */
	public List<PlayerTask> getPlayerTaskListByPlayerIDAndTaskTypeAndFlag(Integer playerID, Integer taskType, Integer flag);

}