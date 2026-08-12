package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.ISystemNoticeDAO;
import com.war.domain.SystemNotice;

/**
 * 系统公告DAO实现
 * @author TopTong
 *
 */
public class SystemNoticeDAO extends SqlMapClientDaoSupport implements ISystemNoticeDAO{

	public Integer createSystemNotice(SystemNotice systemNotice) {
		return (Integer)this.getSqlMapClientTemplate().insert("SystemNotice.createSystemNotice", systemNotice);
	}

	public void updateSystemNotice(SystemNotice systemNotice) {
		this.getSqlMapClientTemplate().update("SystemNotice.updateSystemNotice", systemNotice);
	}

	public void deleteSystemNoticeByID(Integer systemNoticeID) {
		this.getSqlMapClientTemplate().delete("SystemNotice.deleteSystemNoticeByID", systemNoticeID);
	}

	public SystemNotice getSystemNoticeByID(Integer systemNoticeID) {
		return (SystemNotice)this.getSqlMapClientTemplate().queryForObject("SystemNotice.getSystemNoticeByID", systemNoticeID);
	}
	
	@SuppressWarnings("unchecked")
	public List<SystemNotice> getSystemNoticeList() {
		return this.getSqlMapClientTemplate().queryForList("SystemNotice.getSystemNoticeList");
	}

}