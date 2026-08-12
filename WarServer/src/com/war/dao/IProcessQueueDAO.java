package com.war.dao;

import java.util.Date;
import java.util.List;

import com.war.domain.ProcessQueue;

public interface IProcessQueueDAO {

	public Integer createProcessQueue(ProcessQueue processQueue);

	public void updateProcessQueue(ProcessQueue processQueue);

	public void deleteProcessQueueByID(Integer processQueueID);

	public ProcessQueue getProcessQueueByID(Integer processQueueID);

	public List<ProcessQueue> getProcessQueueListByCityID(Integer cityID);
	
	public List<ProcessQueue> getFinishedProcessQueueList();
	
	public List<ProcessQueue> getProcessQueueList();
	
	public Date getFinishTime(Integer cityID,Integer targetID, Integer type);

	/**
	 * 根据城市编号及类型获得进程队列
	 * @param cityID 城市编号
	 * @param type 类型
	 * @return
	 */
	public ProcessQueue getProcessQueueByCityIDAndType(Integer cityID,Integer type);
	
	/**
	 * 获得指定目标的进程信息
	 * @param cityID 城市编号
	 * @param targetID 目标编号
	 * @param type 类型
	 * @return
	 */
	public ProcessQueue getProcessQueue(Integer cityID,Integer targetID, Integer type);
	
	/**
	 * 根据城市编号已经进程类型获得进程队列列表
	 * 
	 * @param cityID  城市编号
	 * @param type ProcessTypeConstatnt定义
	 * @return
	 */
	public List<ProcessQueue> getProcessQueueList(Integer cityID, Integer type);
	
	/**
	 * 根据城市编号已经进程类型获得进程队列数目
	 * @param cityID
	 * @param type
	 * @return
	 */
	public Integer getProcessQueueNumByCityIDAndType(Integer cityID, Integer type);
}