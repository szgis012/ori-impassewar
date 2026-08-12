package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.CityTechnology;
import com.war.domain.ProcessQueue;
import com.war.domain.Technology;

public interface ITechnologyService {

	/**
	 * 初始化科技Map(缓存)
	 * @return
	 */
	public Map<Integer,Technology> initTechnologiesMap();
	
	/**
	 * 初始化科技Map附带约束依赖对象(缓存)
	 * @return
	 */
	public Map<Integer,Map<Integer,Technology>> initTechnologiesMapWithConstraintDepend();
	
	/**
	 * 根据科技类型初始化科技列表并将列表封装为Map<Integer,List<Technology>>
	 * @return key:科技类型 value:当前科技类型列表
	 */
	public Map<Integer,List<Technology>> initTechnologiesListByType();
	
	/**
	 * 根据科技编号及等级获得科技编号
	 * @param technologyID
	 * @param level
	 * @return
	 */
	public Technology getTechnologyByIDAndLevel(Integer technologyID,Integer level);
	
	/**
	 * 研究科技
	 * @param cityID
	 * @param technologyID
	 */
	public void researchTechnology(Integer cityID,Integer technologyID);
	
	/**
	 * 取消研究科技
	 * @param cityID
	 */
	public void cancelResearchTechnology(Integer cityID);
	
	/**
	 * 根据类型获得科技列表
	 * @param type
	 * @return
	 */
	public List<Technology> getTechnologyListByType(Integer type);
	
	/**
	 * 研究完成(Quatrz调用)
	 * @param processQueue
	 */
	public void researchFinished(ProcessQueue processQueue);
	
	/**
	 * 获得正在研究科技
	 * @param cityID
	 * @return researchingTechnology(研究中科技) processQueue(研究中科技进程)
	 */
	public Map<String,Object> getResearchingTechnology(Integer cityID);
	
	/**
	 * 创建科技
	 * @param technology
	 * @return
	 */
	public Integer createTechnology(Technology technology);
	
	/**
	 * 根据科技编号删除科技
	 * @param technologyID
	 */
	public void deleteTechnologyByID(Integer technologyID);

	/**
	 * 获得城市科技Map
	 * @param cityID
	 * @return
	 */
	public Map<Integer,CityTechnology> getCityTechnologyMap(Integer cityID);
	
	/**
	 * 根据类型获得城市科技列表
	 * @param cityID
	 * @param type
	 * @return
	 */
	public List<CityTechnology> getCityTechnologyListByType(Integer cityID,Integer type);
	
	/**
	 * 客户端科技研究进程完成
	 * @param processQueueID
	 */
	public void clientProcessFinished(Integer processQueueID);
	
	/**
	 * 获得科技列表
	 * @return
	 */
	public List<Technology> getTechnologyList();
	
	/**
	 * 根据城市编号及科技编号获得城市科技
	 * @param cityID
	 * @param technologyID
	 * @return
	 */
	public CityTechnology getCityTechnologyByCityIDAndTechnologyID(Integer cityID,Integer technologyID);
	
	/**
	 * 获得城市科技中在LEVEL等级之内的科技数目
	 * @param cityID
	 * @param level
	 * @return
	 */
	public Integer getCityTechnologyNumWithLevel(Integer cityID, Integer level);

	/**
	 * 获得城市建筑等级
	 * @param cityID
	 * @param technologyID
	 * @return
	 */
	public Integer getCityTechnologyLevel(Integer cityID, Integer technologyID);
	
}
