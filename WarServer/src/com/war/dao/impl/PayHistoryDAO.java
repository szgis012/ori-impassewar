package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IPayHistoryDAO;
import com.war.domain.PayHistory;

public class PayHistoryDAO extends SqlMapClientDaoSupport implements IPayHistoryDAO{

	public Integer createPayHistory(PayHistory payHistory) {
		return (Integer)this.getSqlMapClientTemplate().insert("PayHistory.createPayHistory", payHistory);
	}
	
	public void updatePayHistory(PayHistory payHistory) {
		this.getSqlMapClientTemplate().update("PayHistory.updatePayHistory", payHistory);
	}
	
	public void deletePayHistoryByID(Integer payHistoryID) {
		this.getSqlMapClientTemplate().delete("PayHistory.deletePayHistoryByID", payHistoryID);
	}
	
	public PayHistory getPayHistoryByID(Integer payHistoryID) {
		return (PayHistory)this.getSqlMapClientTemplate().queryForObject("PayHistory.getPayHistoryByID", payHistoryID);
	}
	
	@SuppressWarnings("unchecked")
	public List<PayHistory> getPayHistoryList() {
		return this.getSqlMapClientTemplate().queryForList("PayHistory.getPayHistoryList");
	}

}