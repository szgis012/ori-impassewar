package com.war.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.war.dao.IBuildingDAO;
import com.war.domain.Building;

public class BuildingDAO extends SqlMapClientDaoSupport implements IBuildingDAO{

	public Integer createBuilding(Building building) {
		return (Integer)this.getSqlMapClientTemplate().insert("Building.createBuilding", building);
	}
	
	public void updateBuilding(Building building) {
		this.getSqlMapClientTemplate().update("Building.updateBuilding", building);
	}
	
	public void deleteBuildingByID(Integer buildingID) {
		this.getSqlMapClientTemplate().delete("Building.deleteBuildingByID", buildingID);
	}
	
	public Building getBuildingByID(Integer buildingID) {
		return (Building)this.getSqlMapClientTemplate().queryForObject("Building.getBuildingByID", buildingID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Building> getBuildingList() {
		return this.getSqlMapClientTemplate().queryForList("Building.getBuildingList");
	}

	public String getBuildingName(Integer buildingID) {
		return (String)this.getSqlMapClientTemplate().queryForObject("Building.getBuildingName", buildingID);
	}

}