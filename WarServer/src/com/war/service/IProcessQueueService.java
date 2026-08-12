package com.war.service;

import java.util.Date;
import java.util.List;

import com.war.domain.ProcessQueue;

public interface IProcessQueueService {

	public Integer addProcessQueue(ProcessQueue processQueue);

	public void updateProcessQueue(ProcessQueue processQueue);

	public void deleteProcessQueueByID(Integer processQueueID);

	public ProcessQueue getProcessQueueByID(Integer processQueueID);

	public List<ProcessQueue> getCityIDProcessQueueList(Integer cityID);
	
	public List<ProcessQueue> getFinishedProcessQueueList();
	
	public List<ProcessQueue> getProcessQueueList();
	
	public Date getFinishTime(Integer cityID,Integer targetID, Integer type);
	
	/**
	 * 获得进程队列信息
	 * @param cityID
	 * @param type
	 * @return
	 */
	public ProcessQueue getProcessQueue(Integer cityID, Integer type);
	
	/**
	 * 获得指定目标的排程信息
	 * @param cityID 城市编号
	 * @param targetID 目标编号
	 * @param type 类型
	 * @return
	 */
	public ProcessQueue getProcessQueue(Integer cityID,Integer targetID, Integer type);
	
	/**
	 * 获得城市指定类型的队列
	 * 
	 * @param cityID  城市编号
	 * @param type ProcessTypeConstatnt定义
	 * @return
	 */
	public List<ProcessQueue> getProcessQueueList(Integer cityID, Integer type);

	/**
	 * 减少建筑建造，升级，拆除或者科技研究花费时间
	 * @param queueID 进程编号
	 * @param reduceTime 减少的时间，以秒为单位
	 */
	public void reduceBuildCostTime(int queueID,int reduceTime);
	
	/**
	 * 减少科技研究花费时间
	 * @param queueID
	 * @param reduceTime
	 */
	public void reduceTechResearchCostTime(int queueID, int reduceTime);
	
	/**
	 * 减少军团科技研究化肥时间
	 * @param queueID
	 * @param reduceTime
	 */
	public void reduceGuildTechResearchCostTime(int queueID, int reduceTime);
	
	/**
	 * 获得进程队列数目
	 * @param cityID
	 * @param type
	 * @return
	 */
	public Integer getProcessQueueNumByCityIDAndType(Integer cityID, Integer type);
	
}


