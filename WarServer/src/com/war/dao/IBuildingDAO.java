package com.war.dao;

import java.util.List;

import com.war.domain.Building;

public interface IBuildingDAO {

	public Integer createBuilding(Building building);

	public void updateBuilding(Building building);

	public void deleteBuildingByID(Integer buildingID);

	public Building getBuildingByID(Integer buildingID);

	public List<Building> getBuildingList();
	
	public String getBuildingName(Integer buildingID);

}