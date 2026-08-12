package com.war.dao;

import java.util.List;

import com.war.domain.PayHistory;

public interface IPayHistoryDAO {

	/**
	 * 创建充值历史
	 * @param payHistory
	 * @return
	 */
	public Integer createPayHistory(PayHistory payHistory);

	/**
	 * 更新充值历史
	 * @param payHistory
	 */
	public void updatePayHistory(PayHistory payHistory);

	/**
	 * 根据编号删除充值历史
	 * @param payHistoryID
	 */
	public void deletePayHistoryByID(Integer payHistoryID);

	/**
	 * 根据编号获得充值历史
	 * @param payHistoryID
	 * @return
	 */
	public PayHistory getPayHistoryByID(Integer payHistoryID);

	/**
	 * 获得充值历史列表
	 * @return
	 */
	public List<PayHistory> getPayHistoryList();

}