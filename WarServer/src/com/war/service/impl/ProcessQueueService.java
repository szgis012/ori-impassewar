package com.war.service.impl;

import java.util.Date;
import java.util.List;

import com.war.dao.IProcessQueueDAO;
import com.war.domain.ProcessQueue;
import com.war.service.IProcessQueueService;

public class ProcessQueueService implements IProcessQueueService {

	private IProcessQueueDAO processQueueDAO;
	
	
	public Integer addProcessQueue(ProcessQueue processQueue) {
		return processQueueDAO.createProcessQueue(processQueue);
	}

	public void deleteProcessQueueByID(Integer processQueueID) {
		processQueueDAO.deleteProcessQueueByID(processQueueID);
	}
	
	public void updateProcessQueue(ProcessQueue processQueue) {
		processQueueDAO.updateProcessQueue(processQueue);
	}

	public ProcessQueue getProcessQueueByID(Integer processQueueID) {
		return processQueueDAO.getProcessQueueByID(processQueueID);
	}

	public List<ProcessQueue> getCityIDProcessQueueList(Integer cityID) {
		return processQueueDAO.getProcessQueueListByCityID(cityID);
	}
	
	public List<ProcessQueue> getFinishedProcessQueueList() {
		return processQueueDAO.getFinishedProcessQueueList();
	}
	
	public List<ProcessQueue> getProcessQueueList() {
		return processQueueDAO.getProcessQueueList();
	}

	public void reduceBuildCostTime(int queueID,int reduceTime){
		ProcessQueue pq = processQueueDAO.getProcessQueueByID(queueID);
		
		if (pq!=null) {
			Date ft = pq.getFinishTime();
			ft.setTime(ft.getTime() - reduceTime * 1000);
			processQueueDAO.updateProcessQueue(pq);
		}
		
	}
	
	public void reduceTechResearchCostTime(int queueID, int reduceTime) {
		ProcessQueue pq = processQueueDAO.getProcessQueueByID(queueID);
		
		if (pq!=null) {
			Date ft = pq.getFinishTime();
			ft.setTime(ft.getTime() - reduceTime * 1000);
			processQueueDAO.updateProcessQueue(pq);
		}
		
	}
	
	public void reduceGuildTechResearchCostTime(int queueID, int reduceTime) {
		ProcessQueue pq = processQueueDAO.getProcessQueueByID(queueID);
		
		if (pq!=null) {
			Date ft = pq.getFinishTime();
			ft.setTime(ft.getTime() - reduceTime * 1000);
			processQueueDAO.updateProcessQueue(pq);
		}
		
	}
	
	public IProcessQueueDAO getProcessQueueDAO() {
		return processQueueDAO;
	}

	public void setProcessQueueDAO(IProcessQueueDAO processQueueDAO) {
		this.processQueueDAO = processQueueDAO;
	}

	public ProcessQueue getProcessQueue(Integer cityID, Integer type){
		return processQueueDAO.getProcessQueueByCityIDAndType(cityID, type);
	}
	
	public Date getFinishTime(Integer cityID, Integer targetID, Integer type) {
		return processQueueDAO.getFinishTime(cityID, targetID, type);
	}
	
	public ProcessQueue getProcessQueue(Integer cityID,Integer targetID, Integer type){
		return processQueueDAO.getProcessQueue(cityID, targetID, type);
	}

	public List<ProcessQueue> getProcessQueueList(Integer cityID, Integer type) {
		return processQueueDAO.getProcessQueueList(cityID, type);
	}

	public Integer getProcessQueueNumByCityIDAndType(Integer cityID, Integer type) {
		return processQueueDAO.getProcessQueueNumByCityIDAndType(cityID, type);
	}
	
}
