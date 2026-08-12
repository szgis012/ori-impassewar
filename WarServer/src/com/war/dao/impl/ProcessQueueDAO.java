package com.war.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IProcessQueueDAO;
import com.war.domain.ProcessQueue;

public class ProcessQueueDAO extends SqlMapClientDaoSupport implements IProcessQueueDAO{

	public Integer createProcessQueue(ProcessQueue processQueue) {
		return (Integer)this.getSqlMapClientTemplate().insert("ProcessQueue.createProcessQueue", processQueue);
	}
	
	public void updateProcessQueue(ProcessQueue processQueue) {
		this.getSqlMapClientTemplate().update("ProcessQueue.updateProcessQueue", processQueue);
	}
	
	public void deleteProcessQueueByID(Integer processQueueID) {
		this.getSqlMapClientTemplate().delete("ProcessQueue.deleteProcessQueueByID", processQueueID);
	}
	
	public ProcessQueue getProcessQueueByID(Integer processQueueID) {
		return (ProcessQueue)this.getSqlMapClientTemplate().queryForObject("ProcessQueue.getProcessQueueByID", processQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessQueue> getProcessQueueListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("ProcessQueue.getProcessQueueListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessQueue> getFinishedProcessQueueList() {
		return this.getSqlMapClientTemplate().queryForList("ProcessQueue.getFinishedProcessQueueList");
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessQueue> getProcessQueueList() {
		return this.getSqlMapClientTemplate().queryForList("ProcessQueue.getProcessQueueList");
	}

	public Date getFinishTime(Integer cityID, Integer targetID, Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("targetID", targetID);
		params.put("type", type);
		
		return (Date)this.getSqlMapClientTemplate().queryForObject("ProcessQueue.getFinishTime", params);
	}

	public ProcessQueue getProcessQueueByCityIDAndType(Integer cityID,Integer type){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return (ProcessQueue)this.getSqlMapClientTemplate().queryForObject("ProcessQueue.getProcessQueueByCityIDAndType",params);
	}
	
	public ProcessQueue getProcessQueue(Integer cityID, Integer targetID,
			Integer type) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("targetID", targetID);
		params.put("type", type);
		
		return (ProcessQueue)this.getSqlMapClientTemplate().queryForObject("ProcessQueue.getProcessQueue", params);
	}

	@SuppressWarnings("unchecked")
	public List<ProcessQueue> getProcessQueueList(Integer cityID, Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("ProcessQueue.getProcessQueueListByCityIDAndType", params);
	}

	public Integer getProcessQueueNumByCityIDAndType(Integer cityID, Integer type) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("ProcessQueue.getProcessQueueNumByCityIDAndType", params);
	}

}