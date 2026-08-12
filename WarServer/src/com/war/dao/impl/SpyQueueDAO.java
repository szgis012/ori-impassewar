package com.war.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ISpyQueueDAO;
import com.war.domain.SpyQueue;

/**
 * 侦察队列DAO接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class SpyQueueDAO extends SqlMapClientDaoSupport implements ISpyQueueDAO{

	public Integer createSpyQueue(SpyQueue spyQueue) {
		return (Integer)this.getSqlMapClientTemplate().insert("SpyQueue.createSpyQueue", spyQueue);
	}
	
	public void updateSpyQueue(SpyQueue spyQueue) {
		this.getSqlMapClientTemplate().update("SpyQueue.updateSpyQueue", spyQueue);
	}
	
	public void deleteSpyQueueByID(Integer spyQueueID) {
		this.getSqlMapClientTemplate().delete("SpyQueue.deleteSpyQueueByID", spyQueueID);
	}
	
	public SpyQueue getSpyQueueByID(Integer spyQueueID) {
		return (SpyQueue)this.getSqlMapClientTemplate().queryForObject("SpyQueue.getSpyQueueByID", spyQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpyQueue> getSpyQueueList() {
		return this.getSqlMapClientTemplate().queryForList("SpyQueue.getSpyQueueList");
	}
	
	@SuppressWarnings("unchecked")
	public List<SpyQueue> getSpyQueueListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("SpyQueue.getSpyQueueListByCityID",cityID);
	}

	@SuppressWarnings("unchecked")
	public List<SpyQueue> getFinishSpyQueueList(){
		return this.getSqlMapClientTemplate().queryForList("SpyQueue.getFinishSpyQueueList");
	}
}
