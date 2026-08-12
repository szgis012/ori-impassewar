package com.war.service.impl;

import java.util.Calendar;

import com.war.dao.IDataHistoryDAO;
import com.war.domain.DataHistory;
import com.war.service.IDataHistoryService;
import com.war.socket.game.GameSocketService;

public class DataHistoryService implements IDataHistoryService {

	private IDataHistoryDAO dataHistoryDAO;
	
	public Long generateID(Calendar calendar){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(calendar.get(Calendar.YEAR));
		
		if(calendar.get(Calendar.MONTH)+1<10){
			stringBuffer.append("0");
			
		}
		stringBuffer.append(calendar.get(Calendar.MONTH)+1);
		
		if(calendar.get(Calendar.DATE)<10){
			stringBuffer.append("0");
		}
		stringBuffer.append(calendar.get(Calendar.DATE));
		
		if(calendar.get(Calendar.HOUR_OF_DAY)<10){
			stringBuffer.append("0");
		}
		stringBuffer.append(calendar.get(Calendar.HOUR_OF_DAY));
		
		return new Long(stringBuffer.toString());
	}
	
	public void saveDataHistory(){
		
		DataHistory dataHistory = new DataHistory();
		
		dataHistory.setDataHistoryID(this.generateID(Calendar.getInstance()));

		dataHistory.setOnlinePlayerNum(GameSocketService.getSessionNum());
		dataHistoryDAO.createDataHistory(dataHistory);
	}

	
	public IDataHistoryDAO getDataHistoryDAO() {
		return dataHistoryDAO;
	}

	public void setDataHistoryDAO(IDataHistoryDAO dataHistoryDAO) {
		this.dataHistoryDAO = dataHistoryDAO;
	}
	
}
