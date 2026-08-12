package com.war.dao;

import java.util.List;

import com.war.domain.Treasure;


/**
 * 宝物dao接口
 *
 * @author ghleed
 * @version 1.0
 */
public interface ITreasureDAO {

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
	 * 获得宝物类型列表
	 * @return
	 */
	public List<Integer> getTreasureTypeList();

	/**
	 * 根据编号获取宝物信息
	 * @param treasureID 宝物编号
	 * @return
	 */
	public Treasure getTreasureByID(Integer treasureID);

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
	 * 得到指定类型的宝物列表
	 * @param type 宝物类型
	 * @return
	 */
	public List<Treasure> getTreasureListByType(Integer type);  
	
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
}
