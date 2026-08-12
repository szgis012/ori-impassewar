package com.war.dao;


import java.util.List;

import com.war.domain.SpyQueue;

/**
 * 侦察队列DAO接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface ISpyQueueDAO {

	public Integer createSpyQueue(SpyQueue spyQueue);

	public void updateSpyQueue(SpyQueue spyQueue);

	public void deleteSpyQueueByID(Integer spyQueueID);

	public SpyQueue getSpyQueueByID(Integer spyQueueID);

	public List<SpyQueue> getSpyQueueList();
	
	/**
	 * 获得指定城市发起的所有侦察进程列表
	 * @param cityID 城市编号
	 * @return
	 */
	public List<SpyQueue> getSpyQueueListByCityID(Integer cityID);

	/**
	 * 获得已经完成的侦察进程列表
	 * @return
	 */
	public List<SpyQueue> getFinishSpyQueueList();
}
