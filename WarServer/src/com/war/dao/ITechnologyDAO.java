package com.war.dao;

import java.util.List;

import com.war.domain.Technology;

public interface ITechnologyDAO {

	/**
	 * 创建科技
	 * @param technology
	 * @return
	 */
	public Integer createTechnology(Technology technology);

	/**
	 * 更新科技
	 * @param technology
	 */
	public void updateTechnology(Technology technology);

	/**
	 * 根据科技编号删除科技
	 * @param technologyID
	 */
	public void deleteTechnologyByID(Integer technologyID);

	/**
	 * 根据编号获得科技
	 * @param technologyID
	 * @return
	 */
	public Technology getTechnologyByID(Integer technologyID);

	/**
	 * 根据科技类型获得科技列表
	 * @param type
	 * @return
	 */
	public List<Technology> getTechnologyListByType(Integer type);
	
	/**
	 * 获得科技列表
	 * @return
	 */
	public List<Technology> getTechnologyList();

}