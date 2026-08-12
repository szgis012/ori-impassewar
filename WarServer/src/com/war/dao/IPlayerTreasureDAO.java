package com.war.dao;


import java.util.List;
import java.util.Map;

import com.war.domain.PlayerTreasure;

/**
 *  玩家宝物DAO接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface IPlayerTreasureDAO {
	
	/**
	 * 创建一项玩家和宝物的对应信息
	 * @param playerTreasure
	 */
	public void createPlayerTreasure(PlayerTreasure playerTreasure);

	/**
	 * 更新一项玩家和宝物的对应信息
	 * @param playerTreasure
	 */
	public void updatePlayerTreasure(PlayerTreasure playerTreasure);

	/**
	 * 根据玩家编号及宝物编号删除玩家宝物
	 * @param playerID
	 * @param treasureID
	 */
	public void deletePlayerTreasureByPlayerIDAndTreasureID(Integer playerID, Integer treasureID);
	
	/**
	 * 删除玩家的所有的宝物信息
	 * @param playerID 玩家编号
	 */
	public void deletePlayerTreasure(Integer playerID);

	/**
	 * 根据玩家编号及宝物编号获得玩家宝物
	 * @param playerID
	 * @param treasureID
	 * @return
	 */
	public PlayerTreasure getPlayerTreasureByPlayerIDAndTreasureID(Integer playerID, Integer treasureID);

	/**
	 * 得到玩家所有的宝物信息
	 * @param playerID 玩家编号
	 * @return
	 */
	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID);
	
	/**
	 * 获得玩家某分类的宝物信息列表
	 *@param playerID 玩家编号
	 *@param category 宝物分类，TreasureCategoryConstant中定义
	 * @return
	 */
	public List<PlayerTreasure> getPlayerTreasureListByCategory(Integer playerID,Integer category);
	
	/**
	 * 获得玩家某类型的宝物信息列表
	 * @param playerID 玩家编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 * @param type 宝物类型，TreasureTypeConstant中定义
	 * @return
	 */
	public List<PlayerTreasure> getPlayerTreasureListByType(Integer playerID,Integer category,Integer type);
	
	/**
	 * 得到玩家所有属于给定分类的宝物信息(包含宝物的信息)
	 * @param playerID 玩家编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 */
	public List<Map<String,Object>> getPlayerTreasureMapList(Integer playerID,Integer category);

	/**
	 * 得到指定类型的宝物列表(包含玩家拥有该宝物数量的信息)
	 * @param playerID 玩家编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 * @param type 宝物类型，TreasureTypeConstant中定义
	 * @return
	 */
	public List<Map<String,Object>> getTreasureMapListByType(Integer playerID,Integer category,Integer type);  
}