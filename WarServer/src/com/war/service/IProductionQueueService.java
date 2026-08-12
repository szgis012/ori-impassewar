package com.war.service;

import java.util.List;

import com.war.domain.ProductionQueue;

public interface IProductionQueueService {
	/**
	 * 创建一个新的进程
	 * @param ProductionQueue
	 * @return
	 */
	public Integer createProductionQueue(ProductionQueue ProductionQueue);

	/**
	 * 更新进程信息
	 * @param ProductionQueue
	 */
	public void updateProductionQueue(ProductionQueue ProductionQueue);

	/**
	 * 删除指定编号的进程
	 * @param productionQueueID
	 */
	public void deleteProductionQueueByID(Integer productionQueueID);

	/**
	 * 获得指定编号的进程信息
	 * @param productionQueueID
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
	 * 获得指定条件的进程列表信息
	 * 
	 * @param cityID
	 * @param targetID 
	 * @param type ProductionQueueTypeConstant中定义
	 * @return List<ProductionQueue>
	 */
	public List<ProductionQueue> getProductionQueueList(Integer cityID,Integer targetID,Integer type);
	
	/**
	 * 减少军械制造花费时间
	 * @param productionQueueID
	 * @param reduceTime
	 */
	public void reduceOrdnanceProductCostTime(int cityID, int reduceSecond);
	
	/**
	 * 完成生产/征召
	 * @param productionQueueID
	 */
	public void finishProduction(int productionQueueID);

}
