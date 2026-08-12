package com.war.dao;

import java.util.List;

import com.war.domain.DepoyQueue;

/**
 * 出兵队列dao
 *
 * @author ghleed
 * @version 1.0
 */
public interface IDepoyQueueDAO {

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
	 * 根据X坐标及Y坐标获得出城队列数量
	 * @param posX
	 * @param posY
	 * @return
	 */
	public Integer getDepoyQueueNumByPosXAndPosY(Integer posX,Integer posY);
	
	/**
	 * 根据编号获得出征队列
	 * @param depoyQueueID
	 * @return
	 */
	public DepoyQueue getDepoyQueueByID(Integer depoyQueueID);

	/**
	 * 根据城市编号获得出征队列列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getDepoyQueueListByCityID(Integer cityID);
	
	/**
	 * 根据城市编号通过地图编号获得出征队列列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getDepoyQueueListWithMapIDByCityID(Integer cityID);
	
	/**
	 * 根据城市编号和类型获得出征队列列表
	 * @param cityID
	 * @param type
	 * @return
	 */
	public List<DepoyQueue> getDepoyQueueListByCityIDAndType(Integer cityID, Integer type);
	
	/**
	 * 根据城市编号和类型通过地图编号获得出征队列列表
	 * @param cityID
	 * @param type
	 * @return
	 */
	public List<DepoyQueue> getDepoyQueueListWithMapIDByCityIDAndType(Integer cityID, Integer type);
	
	/**
	 * 获得出征队列列表
	 * @return
	 */
	public List<DepoyQueue> getDepoyQueueList();
	
	/**
	 * 获得完成出征队列列表
	 * @return
	 */
	public List<DepoyQueue> getFinishDepoyQueueList();
	
	/**
	 * 根据城市编号和类型获得出征队列数目
	 * @param cityID
	 * @param type
	 * @return
	 */
	public Integer getDepoyQueueNumByCityIDAndType(Integer cityID, Integer type);
	
	/**
	 * 根据城市编号和类型通过地图编号获得出征队列数目
	 * @param cityID
	 * @param type
	 * @return
	 */
	public Integer getDepoyQueueNumWithMapIDByCityIDAndType(Integer cityID, Integer type);

	/**
	 * 根据城市军队编号获得其出征队列信息
	 * @param cityMilitaryID
	 * @return
	 */
	public DepoyQueue getDepoyQueueByCityMilitaryID(Integer cityMilitaryID);

	/**
	 * 获得别的城市对传入参数（cityID）所代表的城市进行（depoy）的信息
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getBeDepoyedQueueListByCityID(Integer cityID);
	
}