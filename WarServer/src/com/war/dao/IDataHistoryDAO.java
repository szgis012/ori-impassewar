package com.war.dao;

import java.util.List;

import com.war.domain.DataHistory;

/**
 * 数据历史DAO接口
 * 
 * @author TopTong
 * @version 1.0
 */
public interface IDataHistoryDAO {

	/**
	 * 创建数据历史
	 * @param dataHistory
	 * @return
	 */
	public Integer createDataHistory(DataHistory dataHistory);

	/**
	 *更新数据历史
	 * @param dataHistory
	 */
	public void updateDataHistory(DataHistory dataHistory);

	/**
	 * 根据编号删除数据历史
	 * @param dataHistoryID
	 */
	public void deleteDataHistoryByID(Long dataHistoryID);

	/**
	 * 根据编号获得数据历史
	 * @param dataHistoryID
	 * @return
	 */
	public DataHistory getDataHistoryByID(Long dataHistoryID);

	/**
	 * 获得数据历史列表
	 * @return
	 */
	public List<DataHistory> getDataHistoryList();

}