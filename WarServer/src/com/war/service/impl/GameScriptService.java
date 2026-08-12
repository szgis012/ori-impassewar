package com.war.service.impl;

import java.util.Map;

import com.war.dao.IGameScriptDAO;
import com.war.service.IGameScriptService;

/**
 * 游戏脚本Service接口实现
 *
 * @author ghleed
 * @version 1.0
 */
public class GameScriptService implements IGameScriptService{
	
	private IGameScriptDAO gameScriptDAO ;
	
	public int getBuildingMaxLevel(int cityID, int buildingID) {
		return gameScriptDAO.getBuildingMaxLevel(cityID, buildingID);
	}

	public int getBuildingNum(int cityID, int buildingID, int level) {
		return gameScriptDAO.getBuildingNum(cityID, buildingID, level);
	}

	public int getBuildingNum(int cityID, int buildingID) {
		return gameScriptDAO.getBuildingNum(cityID, buildingID);
	}

	public int getCityArmyNum(int cityID, int armyID) {
		return gameScriptDAO.getCityArmyNum(cityID, armyID);
	}

	public int getCityDefenseNum(int cityID, int defenseID) {
		return gameScriptDAO.getCityDefenseNum(cityID, defenseID);
	}

	public int getCityHeroNum(int cityID) {
		return gameScriptDAO.getCityHeroNum(cityID);
	}

	public int getCityMilitaryNum(int cityID) {
		return gameScriptDAO.getCityMilitaryNum(cityID);
	}

	public int getTechnologyLevel(int cityID, int technologyID) {
		return gameScriptDAO.getTechnologyLevel(cityID, technologyID);
	}

	public int getCityOrdnanceNum(int cityID,int ordnanceID){
		return gameScriptDAO.getCityOrdnanceNum(cityID, ordnanceID);
	}
	
	public Map<String, Integer> getBuildingMaxLevelAndState(Integer buildingID, Integer cityID) {
		return gameScriptDAO.getBuildingMaxLevelAndStateByBuildingIDAndCityID(buildingID, cityID);
	}

	
	public IGameScriptDAO getGameScriptDAO() {
		return gameScriptDAO;
	}

	public void setGameScriptDAO(IGameScriptDAO gameScriptDAO) {
		this.gameScriptDAO = gameScriptDAO;
	}

}
