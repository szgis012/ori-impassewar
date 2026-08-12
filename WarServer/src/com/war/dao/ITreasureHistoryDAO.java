package com.war.dao;

import java.util.List;

import com.war.domain.TreasureHistory;

public interface ITreasureHistoryDAO {

	/**
	 * 创建宝物历史
	 * @param treasureHistory
	 * @return
	 */
	public Integer createTreasureHistory(TreasureHistory treasureHistory);

	/**
	 * 更新宝物历史
	 * @param treasureHistory
	 */
	public void updateTreasureHistory(TreasureHistory treasureHistory);

	/**
	 * 根据编号删除宝物历史
	 * @param treasureHistoryID
	 */
	public void deleteTreasureHistoryByID(Integer treasureHistoryID);

	/**
	 * 根据编号获得宝物历史
	 * @param treasureHistoryID
	 * @return
	 */
	public TreasureHistory getTreasureHistoryByID(Integer treasureHistoryID);

	/**
	 * 获得宝物历史列表
	 * @return
	 */
	public List<TreasureHistory> getTreasureHistoryList();
	
	/**
	 * 根据玩家编号和类型获得宝物历史信息列表
	 * @param playerID
	 * @param type
	 * @return
	 */
	public List<TreasureHistory> getTreasureHistoryListByPlayerIDAndTreasureIDAndType(Integer playerID, Integer TreasureID, Integer type);

	/**
	 * 根据玩家编号和宝物编号以及类型获得每日使用特定宝物的数量
	 * @param playerID
	 * @param treasureID
	 * @param type
	 * @return
	 */
	public Integer getDailyTreasureHistoryNumByPlayerIDAndTreasureIDAndType(Integer playerID, Integer treasureID, Integer type);

}