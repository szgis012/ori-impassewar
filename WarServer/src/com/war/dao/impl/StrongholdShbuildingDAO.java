package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IStrongholdShbuildingDAO;
import com.war.domain.StrongholdShbuilding;

/**
 * 要塞建筑关系DAO接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class StrongholdShbuildingDAO extends SqlMapClientDaoSupport implements IStrongholdShbuildingDAO {
	public Integer createStrongholdShbuilding(com.war.domain.StrongholdShbuilding shShbuilding) {
		return (Integer)this.getSqlMapClientTemplate().insert("StrongholdShbuilding.createStrongholdShbuilding", shShbuilding);
	}
	
	public void updateStrongholdShbuilding(StrongholdShbuilding shShbuilding) {
		this.getSqlMapClientTemplate().update("StrongholdShbuilding.updateStrongholdShbuilding", shShbuilding);
	}
	
	public void deleteStrongholdShbuildingByID(Integer shShbuildingID) {
		this.getSqlMapClientTemplate().delete("StrongholdShbuilding.deleteStrongholdShbuildingByID", shShbuildingID);
	}
	
	public StrongholdShbuilding getStrongholdShbuildingByID(Integer shShbuildingID) {
		return (StrongholdShbuilding)this.getSqlMapClientTemplate().queryForObject("StrongholdShbuilding.getStrongholdShbuildingByID", shShbuildingID);
	}
	
	@SuppressWarnings("unchecked")
	public List<StrongholdShbuilding> getStrongholdShbuildingList() {
		return this.getSqlMapClientTemplate().queryForList("StrongholdShbuilding.getStrongholdShbuildingList");
	}

	@SuppressWarnings("unchecked")
	public List<StrongholdShbuilding> getStrongholdBuildingListByStrongholdID(Integer strongholdID){
		return this.getSqlMapClientTemplate().queryForList("StrongholdShbuilding.getStrongholdBuildingListByStrongholdID",strongholdID);
	}
}
