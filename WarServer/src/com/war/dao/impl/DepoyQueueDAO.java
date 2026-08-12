package com.war.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IDepoyQueueDAO;
import com.war.domain.DepoyQueue;

/**
 * 出兵队列dao实现
 *
 * @author ghleed
 * @version 1.0
 */
public class DepoyQueueDAO extends SqlMapClientDaoSupport implements IDepoyQueueDAO{

	public Integer createDepoyQueue(DepoyQueue depoyQueue) {
		return (Integer)this.getSqlMapClientTemplate().insert("DepoyQueue.createDepoyQueue", depoyQueue);
	}
	
	public void updateDepoyQueue(DepoyQueue depoyQueue) {
		this.getSqlMapClientTemplate().update("DepoyQueue.updateDepoyQueue", depoyQueue);
	}
	
	public void deleteDepoyQueueByID(Integer depoyQueueID) {
		this.getSqlMapClientTemplate().delete("DepoyQueue.deleteDepoyQueueByID", depoyQueueID);
	}
	
	public Integer getDepoyQueueNumByPosXAndPosY(Integer posX,Integer posY){
		
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("posX", posX);
		params.put("posY", posY);
		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("DepoyQueue.getDepoyQueueNumByPosXAndPosY", params);
	}
	
	public DepoyQueue getDepoyQueueByID(Integer depoyQueueID) {
		return (DepoyQueue)this.getSqlMapClientTemplate().queryForObject("DepoyQueue.getDepoyQueueByID", depoyQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getDepoyQueueListByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("DepoyQueue.getDepoyQueueListByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getDepoyQueueListWithMapIDByCityID(Integer cityID){
		return this.getSqlMapClientTemplate().queryForList("DepoyQueue.getDepoyQueueListWithMapIDByCityID",cityID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getDepoyQueueListByCityIDAndType(Integer cityID, Integer type) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("DepoyQueue.getDepoyQueueListByCityIDAndType", params);
	}

	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getDepoyQueueListWithMapIDByCityIDAndType(Integer cityID, Integer type) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("DepoyQueue.getDepoyQueueListWithMapIDByCityIDAndType", params);
	}

	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getDepoyQueueList() {
		return this.getSqlMapClientTemplate().queryForList("DepoyQueue.getDepoyQueueList");
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getFinishDepoyQueueList(){
		return this.getSqlMapClientTemplate().queryForList("DepoyQueue.getFinishDepoyQueueList");
	}

	public Integer getDepoyQueueNumByCityIDAndType(Integer cityID, Integer type) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("DepoyQueue.getDepoyQueueNumByCityIDAndType", params);
	}

	public Integer getDepoyQueueNumWithMapIDByCityIDAndType(Integer cityID, Integer type) {
		Map<String,Integer> params = new HashMap<String,Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("DepoyQueue.getDepoyQueueNumWithMapIDByCityIDAndType", params);
	}

	public DepoyQueue getDepoyQueueByCityMilitaryID(Integer cityMilitaryID) {
		return (DepoyQueue) this.getSqlMapClientTemplate().queryForObject("DepoyQueue.getDepoyQueueByCityMilitaryID", cityMilitaryID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DepoyQueue> getBeDepoyedQueueListByCityID(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("DepoyQueue.getBeDepoyedQueueListByCityID", cityID);
	}
}