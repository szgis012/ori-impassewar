package com.war.service.impl;

import java.util.Date;
import java.util.List;

import com.war.common.DateService;
import com.war.dao.IOperationLogDAO;
import com.war.domain.OperationLog;
import com.war.service.IOperationLogService;

public class OperationLogService implements IOperationLogService {

	private IOperationLogDAO operationLogDAO;
	
	public Integer createOperationLog(OperationLog operationLog) {
		return operationLogDAO.createOperationLog(operationLog);
	}
	
	public Integer createOperationLog(Integer playerID, String operation, String target) {
		OperationLog operationLog = new OperationLog();
		operationLog.setPlayerID(playerID);
		operationLog.setOperation(operation);
		operationLog.setTarget(target);
		
		return this.createOperationLog(operationLog);
	}
	
	public Integer createOperationLog(Integer playerID, String operation) {
		return this.createOperationLog(playerID, operation, null);
	}

	public void deleteOperationLogByID(Integer operationLogID) {
		operationLogDAO.deleteOperationLogByID(operationLogID);
	}

	public OperationLog getOperationLogByID(Integer operationLogID) {
		return operationLogDAO.getOperationLogByID(operationLogID);
	}
	
	public void updateOperationLog(OperationLog operationLog) {
		operationLogDAO.updateOperationLog(operationLog);
	}

	public List<OperationLog> getOperationLogList() {
		return operationLogDAO.getOperationLogList();
	}
	
	public boolean hasPerformedOperation(Integer playerID, String operation) {
		return operationLogDAO.getOperationLogNumByPlayerIDAndOperation(playerID, operation) > 0 ? true : false;
	}
	
	public Integer getOperationLogNumForSpy(Integer playerID, String operation, Integer level, Date date) {
		return operationLogDAO.getOperationLogNumForSpy(playerID, operation, level.toString(), DateService.changeDateFormat(date, "yyyy-MM-dd"));
	}

	
	public IOperationLogDAO getOperationLogDAO() {
		return operationLogDAO;
	}

	public void setOperationLogDAO(IOperationLogDAO operationLogDAO) {
		this.operationLogDAO = operationLogDAO;
	}
	
}
