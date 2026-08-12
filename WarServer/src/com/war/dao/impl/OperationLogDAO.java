package com.war.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IOperationLogDAO;
import com.war.domain.OperationLog;

public class OperationLogDAO extends SqlMapClientDaoSupport implements IOperationLogDAO {

	public Integer createOperationLog(OperationLog operationLog) {
		return (Integer)this.getSqlMapClientTemplate().insert("OperationLog.createOperationLog", operationLog);
	}

	public void updateOperationLog(OperationLog operationLog) {
		this.getSqlMapClientTemplate().update("OperationLog.updateOperationLog", operationLog);
	}

	public void deleteOperationLogByID(Integer operationLogID) {
		this.getSqlMapClientTemplate().delete("OperationLog.deleteOperationLogByID", operationLogID);
	}

	public OperationLog getOperationLogByID(Integer operationLogID) {
		return (OperationLog)this.getSqlMapClientTemplate().queryForObject("OperationLog.getOperationLogByID", operationLogID);
	}
	
	@SuppressWarnings("unchecked")
	public List<OperationLog> getOperationLogList() {
		return this.getSqlMapClientTemplate().queryForList("OperationLog.getOperationLogList");
	}


	public Integer getOperationLogNumByPlayerIDAndOperation(Integer playerID, String operation) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("operation", operation);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("OperationLog.getOperationLogNumByPlayerIDAndOperation", params);
	}

	public Integer getOperationLogNumForSpy(Integer playerID, String operation, String target, Date time) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("playerID", playerID);
		params.put("operation", operation);
		params.put("target", target);
		params.put("time", time);
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject("OperationLog.getOperationLogNumForSpy", params);
	}


}
