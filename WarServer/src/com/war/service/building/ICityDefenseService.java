package com.war.service.building;

import java.util.List;

import com.war.domain.CityDefense;
import com.war.domain.ProductionQueue;

public interface ICityDefenseService {
	/**
	 * 获得城市特定防御的信息
	 * 
	 * @param cityID 城市编号
	 * @param type 防御类型 CityDefenseTypeConstant中定义
	 * @return
	 */
	public CityDefense getCityDefense(Integer cityID,Integer type);
	
	/**
	 * 获得城市所有防御的信息
	 * @param cityID
	 * @return
	 */
	public List<CityDefense> getCityDefenseList(Integer cityID);
	
	/**
	 * 建造城市的防御
	 * @param cityID 城市编号
	 * @param type 防御类型
	 * @param num 数量
	 */
	public ProductionQueue buildCityDefense(Integer cityID,Integer type,Integer num);
	
	
	/** 完成城防建造时的处理函数
	 * @param pq
	 */
	public void finishBuildDefense(ProductionQueue pq);
	
	/**
	 * 客户端完成生产计算时调用该方法可以及时刷新信息
	 * @param productionQueueID
	 */
	public void clientProcessFinished(Integer productionQueueID);
	
	/**
	 * 取消城防生产
	 * @param productionQueueID
	 */
	public void cancelBuildDefense(Integer productionQueueID);
	
	/**
	 * 立即完成所有的城防建造进程(需要道具)
	 * @param cityID
	 */
	public void finishAllBuildProcess(Integer cityID);
	
	 /**
	 * 增加城市的防御(不需要建筑时间)
	 * @param cityID 城市编号
	 * @param type 防御类型
	 * @param num 数量
	 */
	public void addCityDefense(Integer cityID,Integer type,Integer num);
	
	/**
	 * 减少城市的防御
	 * @param cityID 城市编号
	 * @param type 防御类型
	 * @param num 数量
	 */
	public void minusCityDefense(Integer cityID,Integer type,Integer num);

}
