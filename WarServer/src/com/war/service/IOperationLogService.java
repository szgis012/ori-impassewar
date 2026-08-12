package com.war.service;

import java.util.Date;
import java.util.List;

import com.war.domain.OperationLog;

public interface IOperationLogService {

	/**
	 * 创建用户操作日志
	 * @param operationLog
	 * @return
	 */
	public Integer createOperationLog(OperationLog operationLog);

	/**
	 * 创建用户操作日志
	 * @param playerID
	 * @param operation
	 * @param target
	 * @return
	 */
	public Integer createOperationLog(Integer playerID, String operation, String target);
	
	/**
	 * 创建用户操作日志
	 * @param playerID
	 * @param operation
	 * @return
	 */
	public Integer createOperationLog(Integer playerID, String operation);
	
	/**
	 * 更新用户操作日志
	 * @param operationLog
	 */
	public void updateOperationLog(OperationLog operationLog);

	/**
	 * 根据编号删除用户操作日志
	 * @param operationLogID
	 */
	public void deleteOperationLogByID(Integer operationLogID);

	/**
	 * 根据编号获得用户操作日志
	 * @param operationLogID
	 * @return
	 */
	public OperationLog getOperationLogByID(Integer operationLogID);

	/**
	 * 获得用户操作日志列表
	 * @return
	 */
	public List<OperationLog> getOperationLogList();

	/**
	 * 根据用户编号和操作获得用户日志条数
	 * @return
	 */
	public boolean hasPerformedOperation(Integer playerID, String operation);
	
	/**
	 * 用于获得玩家对指定等级的目标进行侦查的操作记录数量
	 * @param playerID
	 * @param operation
	 * @param level 目标等级
	 * @param date 时间界限： 获得的记录都在此时间之后
	 * @return
	 */
	public Integer getOperationLogNumForSpy(Integer playerID, String operation, Integer level, Date date);
}
