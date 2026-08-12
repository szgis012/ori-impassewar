package com.war.service.impl;

import java.util.Date;
import java.util.List;

import com.war.common.DateService;
import com.war.dao.IProductionQueueDAO;
import com.war.domain.ProductionQueue;
import com.war.service.IProductionQueueService;

public class ProductionQueueService implements IProductionQueueService {
	
	private IProductionQueueDAO productionQueueDAO;
	
	
	public Integer createProductionQueue(ProductionQueue productionQueue) {
		return productionQueueDAO.createProductionQueue(productionQueue);
	}

	public void deleteProductionQueueByID(Integer productionQueueID) {
		productionQueueDAO.deleteProductionQueueByID(productionQueueID);
	}

	public ProductionQueue getProductionQueue(Integer cityID, Integer targetID, Integer type) {
		return productionQueueDAO.getProductionQueue(cityID, targetID, type);
	}

	public ProductionQueue getProductionQueueByID(Integer productionQueueID) {
		return productionQueueDAO.getProductionQueueByID(productionQueueID);
	}

	public List<ProductionQueue> getProductionQueueList() {
		return productionQueueDAO.getProductionQueueList();
	}

	public List<ProductionQueue> getProductionQueueList(Integer cityID) {
		return productionQueueDAO.getProductionQueueList(cityID);
	}

	public List<ProductionQueue> getProductionQueueList(Integer cityID,Integer type) {
		return productionQueueDAO.getProductionQueueList(cityID, type);
	}

	public void updateProductionQueue(ProductionQueue productionQueue) {
		productionQueueDAO.updateProductionQueue(productionQueue);
	}
	
	public List<ProductionQueue> getProductionQueueList(Integer cityID,Integer targetID,Integer type){
		return productionQueueDAO.getProductionQueueList(cityID, targetID, type);
	}
	public List<ProductionQueue> getFinishedProductionQueueList(){
		return productionQueueDAO.getFinishedProductionQueueList();
	}
	
	public void reduceOrdnanceProductCostTime(int cityID, int reduceSecond) {
		productionQueueDAO.updateFinishTimeByCityID(cityID, reduceSecond);
	}
	
	public void finishProduction(int productionQueueID) {
		
		ProductionQueue productionQueue = productionQueueDAO.getProductionQueueByID(productionQueueID);
		
		if(productionQueue!=null) {
			productionQueue.setFinishTime(DateService.getCurrentUtilDate());
			productionQueueDAO.updateProductionQueue(productionQueue);
		}
		
	}
	
	
	public IProductionQueueDAO getProductionQueueDAO() {
		return productionQueueDAO;
	}

	public void setProductionQueueDAO(IProductionQueueDAO productionQueueDAO) {
		this.productionQueueDAO = productionQueueDAO;
	}
	

}
