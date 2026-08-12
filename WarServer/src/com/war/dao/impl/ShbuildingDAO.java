package com.war.dao.impl;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IShbuildingDAO;
import com.war.domain.Shbuilding;

/**
 * 要塞建筑DAO接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class ShbuildingDAO extends SqlMapClientDaoSupport implements IShbuildingDAO{

	public Integer createShbuilding(Shbuilding shbuilding) {
		return (Integer)this.getSqlMapClientTemplate().insert("Shbuilding.createShbuilding", shbuilding);
	}
	
	public void updateShbuilding(Shbuilding shbuilding) {
		this.getSqlMapClientTemplate().update("Shbuilding.updateShbuilding", shbuilding);
	}
	
	public void deleteShbuildingByID(Integer shbuildingID) {
		this.getSqlMapClientTemplate().delete("Shbuilding.deleteShbuildingByID", shbuildingID);
	}
	
	public Shbuilding getShbuildingByID(Integer shbuildingID) {
		return (Shbuilding)this.getSqlMapClientTemplate().queryForObject("Shbuilding.getShbuildingByID", shbuildingID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Shbuilding> getShbuildingList() {
		return this.getSqlMapClientTemplate().queryForList("Shbuilding.getShbuildingList");
	}

}
