package com.war.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IReferenceAccountDAO;
import com.war.domain.ReferenceAccount;

/**
 * 兵种DAO实现
 * 
 * @author TopTong
 * @version 1.0
 */

public class ReferenceAccountDAO extends SqlMapClientDaoSupport implements IReferenceAccountDAO{

	public Integer createReferenceAccount(ReferenceAccount referenceAccount) {
		return (Integer)this.getSqlMapClientTemplate().insert("ReferenceAccount.createReferenceAccount", referenceAccount);
	}

	public void deleteReferenceAccountByID(Integer id) {
		this.getSqlMapClientTemplate().delete("ReferenceAccount.deleteReferenceAccountByID", id);
	}

	public ReferenceAccount getReferenceAccountByID(Integer id) {
		 return (ReferenceAccount)this.getSqlMapClientTemplate().queryForObject("ReferenceAccount.getReferenceAccountByID", id);
	}

	public void updateReferenceAccount(ReferenceAccount referenceAccount) {
		this.getSqlMapClientTemplate().update("ReferenceAccount.updateReferenceAccount", referenceAccount);
	}
	
	public ReferenceAccount getReferenceAccountByAccountName(String accountName) {
		return (ReferenceAccount)this.getSqlMapClientTemplate().queryForObject("ReferenceAccount.getReferenceAccountByAccountName", accountName);
	}
}