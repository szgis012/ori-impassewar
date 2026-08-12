package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IDataHistoryDAO;
import com.war.domain.DataHistory;

/**
 * 数据历史DAO实现
 * 
 * @author TopTong
 * @version 1.0
 */
public class DataHistoryDAO extends SqlMapClientDaoSupport implements IDataHistoryDAO{

	public Integer createDataHistory(DataHistory dataHistory) {
		return (Integer)this.getSqlMapClientTemplate().insert("DataHistory.createDataHistory", dataHistory);
	}
	
	public void updateDataHistory(DataHistory dataHistory) {
		this.getSqlMapClientTemplate().update("DataHistory.updateDataHistory", dataHistory);
	}
	
	public void deleteDataHistoryByID(Long dataHistoryID) {
		this.getSqlMapClientTemplate().delete("DataHistory.deleteDataHistoryByID", dataHistoryID);
	}
	
	public DataHistory getDataHistoryByID(Long dataHistoryID) {
		return (DataHistory)this.getSqlMapClientTemplate().queryForObject("DataHistory.getDataHistoryByID", dataHistoryID);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataHistory> getDataHistoryList() {
		return this.getSqlMapClientTemplate().queryForList("DataHistory.getDataHistoryList");
	}

}