package com.war.service;

import java.util.List;
import java.util.Map;

import com.war.domain.PlayerTreasure;
import com.war.domain.Treasure;
import com.war.domain.TreasureHistory;

/**
 * 宝物service
 * 
 * @author ghleed
 * @version 1.0
 */
public interface ITreasureService {
	
	/**
	 * 初始化宝物Map
	 * @return
	 */
	public Map<Integer, Treasure> initTreasuresMap();
	
	/**
	 * 初始化宝物按类别分类列表Map(key:类别 value:宝物列表)
	 * @return
	 */
	public Map<Integer, List<Treasure>> initTreasureListByCategoryMap();
	
	/**
	 * 初始化宝物按类型分类列表Map(key:类型 value:宝物列表)
	 * @return
	 */
	public Map<Integer, List<Treasure>> initTreasureListByTypeMap();
	
	/**
	 * 初始化推荐宝物列表
	 * @return
	 */
	public List<Treasure> initRecommendTreasureList();
	
	/**
	 * 使用一个宝物
	 * @param playerID 玩家编号
	 * @param treasureID 宝物编号
	 * @param params 给脚本传递的参数
	 */
	public Object useTreasure(int playerID, int treasureID,Object params);

	/**
	 * 创建宝物
	 * @param treasure 宝物信息
	 * @return
	 */
	public Integer createTreasure(Treasure treasure);

	/**
	 * 更新宝物信息
	 * @param treasure
	 */
	public void updateTreasure(Treasure treasure);

	/**
	 * 删除宝物
	 * @param treasureID 宝物编号
	 */
	public void deleteTreasureByID(Integer treasureID);

	/**
	 * 根据编号获取宝物信息
	 * @param treasureID 宝物编号
	 * @return
	 */
	public Treasure getTreasureByID(Integer treasureID);

	/**
	 * 根据宝物编号列表获得宝物列表
	 * @param treasureIDList
	 * @return
	 */
	public List<Treasure> getTreasureListByIDList(List<Integer> treasureIDList);
	
	/**
	 * 得到所有宝物列表
	 * @return
	 */
	public List<Treasure> getTreasureList();
	
	/**
	 * 获得指定分类下的所有宝物列表
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 * @return
	 */
	public List<Treasure> getTreasureListByCategory(Integer category); 
	
	/**
	 * 根据类型获得宝物列表
	 * @param type 宝物类型，TreasureTypeConstant中定义
	 * @return
	 */
	public List<Treasure> getTreasureListByType(Integer type);  
	
	/**
	 * 得到指定类型的宝物列表(包含玩家拥有该宝物数量的信息)
	 * (使用宝物时调用该方法返回所有可以使用的宝物列表)
	 * @param playerID 玩家编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 * @param type 宝物类型，TreasureTypeConstant中定义
	 * @return
	 */
	public List<Map<String,Object>> getTreasureMapListByType(Integer playerID,Integer category,Integer type);  
	
	/**
	 * 得到一项玩家和宝物的对应信息
	 * @param playerTreasure
	 * @return
	 */
	public PlayerTreasure getPlayerTreasureByID(Integer playerID,Integer treasureID);

	/**
	 * 得到玩家所有的宝物信息
	 * @param playerID 玩家编号
	 * @return
	 */
	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID);
	
	/**
	 * 得到玩家所有属于给定分类的宝物信息
	 * @param playerID 玩家编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 */
	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID,Integer category);
	
	/**
	 * 得到玩家所有属于给定分类的宝物信息(包含宝物的信息)
	 * (我的宝物界面显示时调用该接口)
	 * @param playerID 玩家编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 */
	public List<Map<String,Object>> getPlayerTreasureMapList(Integer playerID,Integer category);
	
	/**
	 * 得到玩家所有属于给定类型的宝物信息
	 * @param playerID 玩家编号
	 * @param category 宝物分类，TreasureCategoryConstant中定义
	 * @param type 宝物类型，TreasureTypeConstant中定义
	 */
	public List<PlayerTreasure> getPlayerTreasureList(Integer playerID,Integer category,Integer type);
   
	/**
	* 获得指定状态的宝物列表
	* @param state 宝物状态。TreasureStateConstant中定义
	* @return
	*/
	public List<Treasure> getTreasureListByState(Integer state);  
   
	/**
	* 获得推荐宝物列表
	* @return
	*/
	public List<Treasure> getRecommendTreasureList();
   
	/**
	 * 增加用户某个宝物的数量
	 * 处理包括两种情况：
	 * 1.如果用户还没有该宝物，那么就新增一条记录并且其数量为num
	 * 2.如果用户已经拥有该宝物的对应项，那么就在之前的数量+num
	 * @param playerID 玩家编号
	 * @param treasureID 宝物编号
	 * @param num 数量
	 */
	public void increasePlayerTreasure(Integer playerID,Integer treasureID,int num);

	/**
	 * 减少用户某个宝物的数量
	 * 处理包括两种情况：
	 * 1.如果用户还没有该宝物，或者减少的num比宝物的之前的数量要大则抛出运行时异常
	 * 2.否则就在之前的数量-num
	 * @param playerID 玩家编号
	 * @param treasureID 宝物编号
	 * @param num 数量
	 */
	public void decreasePlayerTreasure(Integer playerID,Integer treasureID,int num);

	  
	/**
	 * 删除玩家的所有的宝物信息
	 * @param playerID 玩家编号
	 */
	public void deletePlayerTreasure(Integer playerID);

	/**
	 * 购买宝物
	 * @param playerID 玩家编号
	 * @param treasureID 宝物编号
	 * @param num 宝物数量
	 * @param type 货币类型(1.金币购买 2.礼金购买)
	 */
	public void buyTreasure(Integer playerID, Integer treasureID, Integer num, Integer currencyType);
	
	/**
	 * 获得玩家使用宝物的历史记录
	 * @param playerID
	 * @param treasureID
	 * @param type
	 * @return
	 */
	public List<TreasureHistory> getTreasureHistoryList(Integer playerID, Integer treasureID, Integer type);

	/**
	 * 获得玩家在一天中使用某种宝物的数量
	 * @param playerID
	 * @param treasureID
	 * @param type
	 * @return
	 */
	public Integer getPlayerDailyTreasureHistoryNum(Integer playerID, Integer treasureID, Integer type);
   
}
