package com.war.service;

import java.util.List;

import com.war.domain.DepoyQueue;

/**
 * 出征队列service
 *
 * @author ghleed
 * @version 1.0
 */
public interface IDepoyQueueService {
	
	/**
	 * 创建出征队列
	 * @param depoyQueue
	 * @return
	 */
	public Integer createDepoyQueue(DepoyQueue depoyQueue);

	/**
	 * 更新出征队列
	 * @param depoyQueue
	 */
	public void updateDepoyQueue(DepoyQueue depoyQueue);

	/**
	 * 根据编号删除出征队列
	 * @param depoyQueueID
	 */
	public void deleteDepoyQueueByID(Integer depoyQueueID);

	/**
	 * 根据编号获得出征队列
	 * @param depoyQueueID
	 * @return
	 */
	public DepoyQueue getDepoyQueueByID(Integer depoyQueueID);

	/**
	 * 获得城市出征队列列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getCityDepoyQueueList(Integer cityID);
	
	/**
	 * 获得城市进攻队列列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getCityAttackDepoyQueueList(Integer cityID);
	
	/**
	 * 获得城市防守队列列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getCityDefenseDepoyQueueList(Integer cityID);
	
	/**
	 * 获得城市进攻队列数目
	 * @param cityID
	 * @return
	 */
	public Integer getCityAttackDepoyQueueNum(Integer cityID);
	
	/**
	 * 获得城市防守队列数目
	 * @param cityID
	 * @return
	 */
	public Integer getCityDefenseDepoyQueueNum(Integer cityID);
	
	/**
	 * 获得城市派遣队列列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getCityDispatchDepoyQueueList(Integer cityID);
	
	/**
	 * 获得城市被支援队列列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getCitySuccorDepoyQueueList(Integer cityID);
	
	/**
	 * 获得城市派遣队列数目
	 * @param cityID
	 * @return
	 */
	public Integer getCityDispatchDepoyQueueNum(Integer cityID);

	/**
	 * 获得城市被支援队列数目
	 * @param cityID
	 * @return
	 */
	public Integer getCitySuccorDepoyQueueNum(Integer cityID);
	
	/**
	 * 获得出征队列列表
	 * @return
	 */
	public List<DepoyQueue> getDepoyQueueList();
	
	/**
	 * 获得完成出征的队列
	 * @return
	 */
	public List<DepoyQueue> getFinishDepoyQueueList();
	
	/**
	 * 获得出征队列列表
	 * @param cityMilitaryID
	 * @return
	 */
	public DepoyQueue getDepoyQueueByCityMilitaryID(Integer cityMilitaryID);
}
