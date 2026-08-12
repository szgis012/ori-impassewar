package com.war.service.building;

import com.war.domain.ProductionQueue;


/**
 * 兵工厂service
 *
 * @author ghleed
 * @version 1.0
 */
public interface IArmoryService {
	/**
	 * 生产指定军械,返回生产进程
	 * 
	 * @param cityID 城市编号
	 * @param ordnanceID 军械编号
	 * @param num 军械数量
	 */
	public ProductionQueue produceOrdnance(int cityID,int ordnanceID,int num);
	
	/**
	 * 拆卸指定数量的军械
	 * 
	 * @param cityOrdnanceID 城市军械编号
	 * @param num 军械数量
	 */
	public void backoutOrdnance(int cityOrdnanceID, int num);
	
	/**
	 * 完成军械生产时的处理函数
	 * @param pq
	 */
	public void finishProduceOrdnance(ProductionQueue pq);
	
	/**
	 * 客户端完成生产计算时调用该方法可以及时刷新信息
	 * @param productionProcessID
	 */
	public void clientProcessFinished(Integer productionProcessID);
	
	/**
	 * 取消军械生产
	 * @param productionProcessID
	 */
	public void cancelProduceOrdnance(Integer productionProcessID);
	
	/**
	 * 立即完成所有的军械生产进程(需要道具)
	 * @param cityID
	 */
	public void finishAllProduceQueue(Integer cityID);
}
