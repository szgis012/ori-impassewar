package com.war.service.impl;

import java.util.Date;
import java.util.List;

import com.war.constant.TradeConstant;
import com.war.dao.IResTradeDAO;
import com.war.dao.IResTransportationDAO;
import com.war.dao.ITradeQueueDAO;
import com.war.domain.TradeQueue;
import com.war.service.ITradeQueueService;

public class TradeQueueService implements ITradeQueueService {

	private ITradeQueueDAO tradeQueueDAO;
	
	private IResTransportationDAO resTransportationDAO;
	
	private IResTradeDAO resTradeDAO;
	
	public Integer createTradeQueue(TradeQueue tradeQueue) {
		return tradeQueueDAO.createTradeQueue(tradeQueue);
	}

	public void reduceTradeCostTime(int queueID){
		
		TradeQueue tradeQueue = tradeQueueDAO.getTradeQueueByID(queueID);

		//时间减半
		Date arriveTime = new Date();
		arriveTime.setTime(System.currentTimeMillis() + (tradeQueue.getArriveTime().getTime()-System.currentTimeMillis())/2);
		tradeQueue.setArriveTime(arriveTime);
		
		tradeQueueDAO.updateTradeQueue(tradeQueue);
	}
	
	public void deleteTradeQueueByID(Integer tradeQueueID) {
		tradeQueueDAO.deleteTradeQueueByID(tradeQueueID);
	}

	public void updateTradeQueue(TradeQueue tradeQueue) {
		tradeQueueDAO.updateTradeQueue(tradeQueue);
	}
	
	public TradeQueue getTradeQueueByID(Integer tradeQueueID) {
		return tradeQueueDAO.getTradeQueueByID(tradeQueueID);
	}

	public List<TradeQueue> getTradeQueueListByCityID(Integer cityID) {
		List<TradeQueue> tradeQueueList = tradeQueueDAO.getTradeQueueListByCityID(cityID);
		
		for(int i=0;i<tradeQueueList.size();i++){
			switch(tradeQueueList.get(i).getType()){
				case TradeConstant.RESOURCE_TRANSPORTATION:
					tradeQueueList.get(i).setTargetObject(resTransportationDAO.getResTransportationByID(tradeQueueList.get(i).getTargetID()));
					break;
				case TradeConstant.RESOURCE_TRANSPORTATION_RETURN:
					tradeQueueList.get(i).setTargetObject(resTransportationDAO.getResTransportationByID(tradeQueueList.get(i).getTargetID()));
					break;
				case TradeConstant.TARDE_RETURN:
					tradeQueueList.get(i).setTargetObject(resTradeDAO.getResTradeByID(tradeQueueList.get(i).getTargetID()));
					break;
				default:
					break;
			}
		}
		//tradequeue
		return tradeQueueList;
	}
	
	public int getImportResourceTradeQueueNum(Integer targetCityID) {
		return tradeQueueDAO.getImportResourceTradeQueueNum(targetCityID);
	}
	
	public List<TradeQueue> getArrivedTradeQueueList() {
		return tradeQueueDAO.getArrivedTradeQueueList();
	}
	
	public List<TradeQueue> getTradeQueueList() {
		return tradeQueueDAO.getTradeQueueList();
	}

	
	public ITradeQueueDAO getTradeQueueDAO() {
		return tradeQueueDAO;
	}

	public void setTradeQueueDAO(ITradeQueueDAO tradeQueueDAO) {
		this.tradeQueueDAO = tradeQueueDAO;
	}

	public IResTransportationDAO getResTransportationDAO() {
		return resTransportationDAO;
	}

	public void setResTransportationDAO(IResTransportationDAO resTransportationDAO) {
		this.resTransportationDAO = resTransportationDAO;
	}

	public IResTradeDAO getResTradeDAO() {
		return resTradeDAO;
	}

	public void setResTradeDAO(IResTradeDAO resTradeDAO) {
		this.resTradeDAO = resTradeDAO;
	}

}
