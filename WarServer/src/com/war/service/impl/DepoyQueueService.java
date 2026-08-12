package com.war.service.impl;

import java.util.List;

import com.war.constant.DepoyTypeConstant;
import com.war.dao.IDepoyQueueDAO;
import com.war.domain.DepoyQueue;
import com.war.service.IDepoyQueueService;

/**
 * 出征队列Service实现
 * 
 * @author TopTong
 * @version 1.0
 */
public class DepoyQueueService implements IDepoyQueueService {
	
	private IDepoyQueueDAO depoyQueueDAO;
	
	public Integer createDepoyQueue(DepoyQueue depoyQueue) {
		return depoyQueueDAO.createDepoyQueue(depoyQueue);
	}

	public void updateDepoyQueue(DepoyQueue depoyQueue) {
		depoyQueueDAO.updateDepoyQueue(depoyQueue);
	}
	
	public void deleteDepoyQueueByID(Integer depoyQueueID) {
		depoyQueueDAO.deleteDepoyQueueByID(depoyQueueID);
	}

	public DepoyQueue getDepoyQueueByID(Integer depoyQueueID) {
		return depoyQueueDAO.getDepoyQueueByID(depoyQueueID);
	}

	public List<DepoyQueue> getCityDepoyQueueList(Integer cityID){
		return depoyQueueDAO.getDepoyQueueListByCityID(cityID);
	}
	
	public List<DepoyQueue> getCityAttackDepoyQueueList(Integer cityID){
		return depoyQueueDAO.getDepoyQueueListByCityIDAndType(cityID, DepoyTypeConstant.ATTACK);
	}
	
	public List<DepoyQueue> getCityDefenseDepoyQueueList(Integer cityID){
		return depoyQueueDAO.getDepoyQueueListWithMapIDByCityIDAndType(cityID, DepoyTypeConstant.ATTACK);
	}
	
	public Integer getCityAttackDepoyQueueNum(Integer cityID){
		return depoyQueueDAO.getDepoyQueueNumByCityIDAndType(cityID, DepoyTypeConstant.ATTACK);
	}
	
	public Integer getCityDefenseDepoyQueueNum(Integer cityID){
		return depoyQueueDAO.getDepoyQueueNumWithMapIDByCityIDAndType(cityID, DepoyTypeConstant.ATTACK);
	}
	
	public List<DepoyQueue> getCityDispatchDepoyQueueList(Integer cityID) {
		return depoyQueueDAO.getDepoyQueueListByCityIDAndType(cityID, DepoyTypeConstant.DISPATCH);
	}

	public List<DepoyQueue> getCitySuccorDepoyQueueList(Integer cityID) {
		return depoyQueueDAO.getDepoyQueueListWithMapIDByCityIDAndType(cityID, DepoyTypeConstant.DISPATCH);
	}
	
	public Integer getCityDispatchDepoyQueueNum(Integer cityID) {
		return depoyQueueDAO.getDepoyQueueNumByCityIDAndType(cityID, DepoyTypeConstant.DISPATCH);
	}

	public Integer getCitySuccorDepoyQueueNum(Integer cityID) {
		return depoyQueueDAO.getDepoyQueueNumWithMapIDByCityIDAndType(cityID, DepoyTypeConstant.DISPATCH);
	}

	public List<DepoyQueue> getDepoyQueueList() {
		return depoyQueueDAO.getDepoyQueueList();
	}
	
	public List<DepoyQueue> getFinishDepoyQueueList(){
		return depoyQueueDAO.getFinishDepoyQueueList();
	}
	
	public DepoyQueue getDepoyQueueByCityMilitaryID(Integer cityMilitaryID) {
		return depoyQueueDAO.getDepoyQueueByCityMilitaryID(cityMilitaryID);
	}

	public IDepoyQueueDAO getDepoyQueueDAO() {
		return depoyQueueDAO;
	}

	public void setDepoyQueueDAO(IDepoyQueueDAO depoyQueueDAO) {
		this.depoyQueueDAO = depoyQueueDAO;
	}

}
