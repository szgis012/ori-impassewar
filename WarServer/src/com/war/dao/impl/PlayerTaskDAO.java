package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IPlayerTaskDAO;
import com.war.domain.PlayerTask;

/**
 * 玩家任务DAO实现
 *
 * @author TopTong
 * @version 1.0
 */
public class PlayerTaskDAO extends SqlMapClientDaoSupport implements IPlayerTaskDAO{

	public Integer createPlayerTask(PlayerTask playerTask) {
		return (Integer)this.getSqlMapClientTemplate().insert("PlayerTask.createPlayerTask", playerTask);
	}
	
	public void updateStateByPlayerTaskID(Integer playerTaskID, Integer state) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerTaskID", playerTaskID);
		params.put("state", state);
		
		this.getSqlMapClientTemplate().update("PlayerTask.updateStateByPlayerTaskID", params);
	}
	
	public void updateFlagByPlayerTaskID(Integer playerTaskID, Integer flag) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerTaskID", playerTaskID);
		params.put("flag", flag);
		
		this.getSqlMapClientTemplate().update("PlayerTask.updateFlagByPlayerTaskID", params);
	}
	
	public void updatePlayerTask(PlayerTask playerTask) {
		this.getSqlMapClientTemplate().update("PlayerTask.updatePlayerTask", playerTask);
	}

	public void refreshDailyTask(){
		this.getSqlMapClientTemplate().update("PlayerTask.refreshDailyTask");
	}
	
	public void deletePlayerTaskByID(Integer playerTaskID) {
		this.getSqlMapClientTemplate().delete("PlayerTask.deletePlayerTaskByID", playerTaskID);
	}
	
	public void deletePlayerTaskByPlayerIDAndTaskID(Integer playerID, Integer taskID) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("taskID", taskID);
		
		this.getSqlMapClientTemplate().delete("PlayerTask.deletePlayerTaskByPlayerIDAndTaskID", params);
	}
	
	public PlayerTask getPlayerTaskByID(Integer playerTaskID) {
		return (PlayerTask)this.getSqlMapClientTemplate().queryForObject("PlayerTask.getPlayerTaskByID", playerTaskID);
	}
	
	public PlayerTask getPlayerTaskByPlayerIDAndTaskID(Integer playerID,Integer taskID){
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("taskID", taskID);
		
		return (PlayerTask)this.getSqlMapClientTemplate().queryForObject("PlayerTask.getPlayerTaskByPlayerIDAndTaskID", params);
	}

	@SuppressWarnings("unchecked")
	public List<PlayerTask> getPlayerTaskListByPlayerID(Integer playerID) {
		return this.getSqlMapClientTemplate().queryForList("PlayerTask.getPlayerTaskListByPlayerID", playerID);
	}

	@SuppressWarnings("unchecked")
	public List<PlayerTask> getPlayerTaskListByPlayerIDAndTaskTypeAndFlag(Integer playerID, Integer taskType, Integer flag) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("playerID", playerID);
		params.put("taskType", taskType);
		params.put("flag", flag);
		
		return this.getSqlMapClientTemplate().queryForList("PlayerTask.getPlayerTaskListByPlayerIDAndTaskTypeAndFlag", params);
	}

}
