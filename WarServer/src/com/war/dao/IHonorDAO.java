package com.war.dao;

import java.util.List;

import com.war.domain.Honor;

public interface IHonorDAO {

	/**
	 * 创建军衔
	 * @param honor
	 * @return
	 */
	public Integer createHonor(Honor honor);

	/**
	 * 更新军衔
	 * @param honor
	 */
	public void updateHonor(Honor honor);

	/**
	 * 根据编号删除军衔
	 * @param honorID
	 */
	public void deleteHonorByID(Integer honorID);

	/**
	 * 根据编号获得军衔
	 * @param honorID
	 * @return
	 */
	public Honor getHonorByID(Integer honorID);

	/**
	 * 获得军衔列表
	 * @return
	 */
	public List<Honor> getHonorList();

}