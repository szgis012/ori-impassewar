package com.war.dao;

import java.util.List;

import com.war.domain.ResTransportation;

public interface IResTransportationDAO {

	/**
	 * 创建资源交易
	 * @param resTransportation
	 * @return
	 */
	public Integer createResTransportation(ResTransportation resTransportation);

	/**
	 * 根据资源交易编号删除资源交易
	 * @param resTransportationID
	 */
	public void deleteResTransportationByID(Integer resTransportationID);

	/**
	 * 根据资源交易编号获得资源交易
	 * @param resTransportationID
	 * @return
	 */
	public ResTransportation getResTransportationByID(Integer resTransportationID);

	/**
	 * 获得资源交易列表
	 * @return
	 */
	public List<ResTransportation> getResTransportationList();

}