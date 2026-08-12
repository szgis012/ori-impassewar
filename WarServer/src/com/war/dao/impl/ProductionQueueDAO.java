package com.war.dao.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IProductionQueueDAO;
import com.war.domain.ProductionQueue;

public class ProductionQueueDAO extends SqlMapClientDaoSupport implements IProductionQueueDAO{

	public Integer createProductionQueue(ProductionQueue productionQueue) {
		return (Integer)this.getSqlMapClientTemplate().insert("ProductionQueue.createProductionQueue", productionQueue);
	}
	
	public void updateFinishTimeByID(Integer productionQueueID, Date finishTime) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productionQueueID", productionQueueID);
		params.put("finishTime", finishTime);
		
		this.getSqlMapClientTemplate().update("ProductionQueue.updateFinishTimeByID", params);
	}
	
	public void updateProductionQueue(ProductionQueue productionQueue) {
		this.getSqlMapClientTemplate().update("ProductionQueue.updateProductionQueue", productionQueue);
	}
	
	public void deleteProductionQueueByID(Integer productionQueueID) {
		this.getSqlMapClientTemplate().delete("ProductionQueue.deleteProductionQueueByID", productionQueueID);
	}
	
	public ProductionQueue getProductionQueueByID(Integer productionQueueID) {
		return (ProductionQueue)this.getSqlMapClientTemplate().queryForObject("ProductionQueue.getProductionQueueByID", productionQueueID);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductionQueue> getProductionQueueList() {
		return this.getSqlMapClientTemplate().queryForList("ProductionQueue.getProductionQueueList");
	}

	@SuppressWarnings("unchecked")
	public List<ProductionQueue> getProductionQueueList(Integer cityID) {
		return this.getSqlMapClientTemplate().queryForList("ProductionQueue.getProductionQueueListByCityID",cityID);
	}

	@SuppressWarnings("unchecked")
	public List<ProductionQueue> getProductionQueueList(Integer cityID, Integer type) {
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		
		return this.getSqlMapClientTemplate().queryForList("ProductionQueue.getProductionQueueListByCityIDAndType",params);
	}

	@SuppressWarnings("unchecked")
	public List<ProductionQueue> getProductionQueueList(Integer cityID,Integer targetID,Integer type){
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		params.put("targetID", targetID);
		
		return  this.getSqlMapClientTemplate().queryForList("ProductionQueue.getProductionQueueByParams",params);
	}
	
	public ProductionQueue getProductionQueue(Integer cityID, Integer targetID, Integer type) {

		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("type", type);
		params.put("targetID", targetID);
		
		return (ProductionQueue) this.getSqlMapClientTemplate().queryForObject("ProductionQueue.getProductionQueueByParams",params);
	}

	@SuppressWarnings("unchecked")
	public List<ProductionQueue> getFinishedProductionQueueList() {
		return this.getSqlMapClientTemplate().queryForList("ProductionQueue.getFinishedProductionQueueList");
	}
	
	public void updateFinishTimeByCityID(Integer cityID, Integer reduceSecond) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("cityID", cityID);
		params.put("reduceSecond", reduceSecond);
		
		this.getSqlMapClientTemplate().update("ProductionQueue.updateFinishTimeByCityID", params);
	}

}