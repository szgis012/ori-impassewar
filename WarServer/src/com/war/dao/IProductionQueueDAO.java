package com.war.dao;

import java.util.Date;
import java.util.List;

import com.war.domain.ProductionQueue;

public interface IProductionQueueDAO {

	/**
	 * 创建一个新的进程
	 * @param ProductionQueue
	 * @return
	 */
	public Integer createProductionQueue(ProductionQueue productionQueue);

	/**
	 * 根据编号更新完成时间
	 * @param productionQueueID
	 * @param finishTime
	 */
	public void updateFinishTimeByID(Integer productionQueueID, Date finishTime);
	
	/**
	 * 更新进程信息
	 * @param ProductionQueue
	 */
	public void updateProductionQueue(ProductionQueue productionQueue);

	/**
	 * 删除指定编号的进程
	 * @param ProductionQueueID
	 */
	public void deleteProductionQueueByID(Integer productionQueueID);

	/**
	 * 获得指定编号的进程信息
	 * @param ProductionQueueID
	 * @return
	 */
	public ProductionQueue getProductionQueueByID(Integer productionQueueID);

	/**
	 * 获得所有进程列表
	 * @return
	 */
	public List<ProductionQueue> getProductionQueueList();

	/**
	 * 获得指定城市的进程列表
	 * @param cityID
	 * @return
	 */
	public List<ProductionQueue> getProductionQueueList(Integer cityID);
	
	/**
	 * 获得城市指定类型的进程列表
	 * @param cityID
	 * @param type ProductionQueueTypeConstant中定义
	 * @return
	 */
	public List<ProductionQueue> getProductionQueueList(Integer cityID,Integer type);
	
	/**
	 * 获得指定条件的进程列表信息
	 * 
	 * @param cityID
	 * @param targetID 
	 * @param type ProductionQueueTypeConstant中定义
	 * @return List<ProductionQueue>
	 */
	public List<ProductionQueue> getProductionQueueList(Integer cityID,Integer targetID,Integer type);
	
	/**
	 * 获得指定条件的进程信息
	 * 
	 * @param cityID
	 * @param targetID 
	 * @param type ProductionQueueTypeConstant中定义
	 * @return
	 */
	public ProductionQueue getProductionQueue(Integer cityID,Integer targetID,Integer type);
	
	/**
	 * 得到所有已完成的进程列表
	 * @return
	 */
	public List<ProductionQueue> getFinishedProductionQueueList();

	/**
	 * 根据城市编号更新其所有的产品生产队列结束时间
	 * @param cityID
	 * @param reduceSecond
	 */
	public void updateFinishTimeByCityID(Integer cityID, Integer reduceSecond);
}