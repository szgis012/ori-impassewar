package com.war.dao;

import java.util.Map;


/**
 * 游戏脚本DAO接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface IGameScriptDAO {
	
	/**
	 * 获得指定等级建筑的数量
	 * @param cityID 城市编号
	 * @param buildingID 建筑编号
	 * @param level 建筑等级
	 * @return 返回建筑的数量，如果没有该建筑返回0
	 */
	public int getBuildingNum(int cityID,int buildingID,int level);
	
	/**
	 * 获得指定等级建筑的所有数量(等级大于0的建筑数量)
	 * @param cityID 城市编号
	 * @param buildingID 建筑编号
	 * @return 返回建筑的数量，如果没有该建筑返回0
	 */
	public int getBuildingNum(int cityID,int buildingID);
	
	/**
	 * 获得建筑的最大等级
	 * @param cityID 城市编号
	 * @param buildingID 建筑编号
	 * @return 返回该建筑的最大等级，如果没有该建筑或者建筑正在建造中返回0
	 */
	public int getBuildingMaxLevel(int cityID,int buildingID);
	
	/**
	 * 获得城市某兵的数量
	 * @param cityID 城市编号
	 * @param armyID 兵种编号
	 * @return 返回城市里拥有的该兵数量，如果没有该兵返回0
	 */
	public int getCityArmyNum(int cityID,int armyID);
	
	/**
	 * 获得城市拥有的指挥官数量
	 * @param cityID 城市编号
	 * @return 返回指挥官的数量，如果没有任何指挥官返回0
	 */
	public int getCityHeroNum(int cityID);
	
	/**
	 * 获得城市已编制的军队数量
	 * @param cityID 城市编号
	 * @return 返回已编制军队数量，如果没有编制任何军队返回0
	 */
	public int getCityMilitaryNum(int cityID);
	
	/**
	 * 获得城防
	 * @param cityID 城市编号
	 * @param type 城防类型(CityDefenseTypeConstant类中定义)
	 * @return 返回相应城防的数量
	 */
	public int getCityDefenseNum(int cityID,int defenseID);
	
	/**
	 * 获得科技等级
	 * @param cityID 城市编号
	 * @param technologyID 科技编号
	 * @return 返回科技的等级，如果科技没有升级返回0
	 */
	public int getTechnologyLevel(int cityID,int technologyID);
	
	/**
	 * 获得城市的军械数量
	 * @param cityID 城市编号
	 * @param ordnanceID 军械编号
	 * @return 返回军械的数量，如果没有军械返回0
	 */
	public int getCityOrdnanceNum(int cityID,int ordnanceID);
	
	/**
	 * 根据建筑编号和城市编号获得指定城市中某一建筑的最大等级和状态
	 * @param buildingID
	 * @param cityID
	 * @return
	 */
	public Map<String, Integer> getBuildingMaxLevelAndStateByBuildingIDAndCityID(Integer buildingID, Integer cityID);
}
