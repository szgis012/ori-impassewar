package com.war.service.impl;

import java.util.List;

import com.war.dao.impl.MapDAO;
import com.war.dao.impl.SpyQueueDAO;
import com.war.domain.SpyQueue;
import com.war.service.ISpyQueueService;

/**
 * 侦察队列Service接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class SpyQueueService implements ISpyQueueService {
	
	private SpyQueueDAO spyQueueDAO;
	
	private MapDAO mapDAO;
	
	
	public Integer createSpyQueue(SpyQueue spyQueue) {
		return spyQueueDAO.createSpyQueue(spyQueue);
	}

	public void deleteSpyQueueByID(Integer spyQueueID) {
		spyQueueDAO.deleteSpyQueueByID(spyQueueID);
	}
	
	public void updateSpyQueue(SpyQueue spyQueue) {
		spyQueueDAO.updateSpyQueue(spyQueue);
	}

	public List<SpyQueue> getFinishSpyQueueList() {
		return spyQueueDAO.getFinishSpyQueueList();
	}

	public SpyQueue getSpyQueueByID(Integer spyQueueID) {
		SpyQueue spyQueue = spyQueueDAO.getSpyQueueByID(spyQueueID);
		if (spyQueue != null) {
			spyQueue.setMap(mapDAO.getMapByID(spyQueue.getMapID()));
		}
		return spyQueue;
	}

	public List<SpyQueue> getSpyQueueList() {
		List<SpyQueue> spyQueueList = spyQueueDAO.getSpyQueueList();
		for (SpyQueue spyQueue : spyQueueList) {
			spyQueue.setMap(mapDAO.getMapByID(spyQueue.getMapID()));
		}
		return spyQueueList;
	}
	
	public List<SpyQueue> getSpyQueueListByCityID(Integer cityID){
		List<SpyQueue> spyQueueList = spyQueueDAO.getSpyQueueListByCityID(cityID);
		for (SpyQueue spyQueue : spyQueueList) {
			spyQueue.setMap(mapDAO.getMapByID(spyQueue.getMapID()));
		}
		return spyQueueList;
	}

	
	public SpyQueueDAO getSpyQueueDAO() {
		return spyQueueDAO;
	}

	public void setSpyQueueDAO(SpyQueueDAO spyQueueDAO) {
		this.spyQueueDAO = spyQueueDAO;
	}

	public MapDAO getMapDAO() {
		return mapDAO;
	}

	public void setMapDAO(MapDAO mapDAO) {
		this.mapDAO = mapDAO;
	}

}
