package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.DepoyQueue;

/**
 * 为MilitaryService服务的DAO
 *
 * @author ghleed
 * @version 1.0
 */
public interface IMilitaryDAO {
	 /**
	 * 获得城市军事行动信息列表
	 * @param cityID
	 * @return
	 */
	public List<Map<String, Object>> getMilitaryActionList(Integer cityID);
	
	/**
	 * 获得指定编号的出征队列详细信息
	 * @param depoyQueueID
	 * @return
	 */
	public Map<String, Object> getAttackDetail(Integer depoyQueueID);
	
	/**
	 * 根据城市编号获得城市军队防御信息列表
	 * @param cityID
	 * @return
	 */
	public List<DepoyQueue> getMilitaryDefenseList(Integer cityID);
	
}
