package com.war.dao;

import java.util.List;

import com.war.domain.BattleDetail;

public interface IBattleDetailDAO {

	/**
	 * 创建战场详情信息
	 * @param battleDetail
	 */
	public void createBattleDetail(BattleDetail battleDetail);
	
	/**
	 * 更新战场详情信息
	 * @param battleDetail
	 */
	public void updateBattleDetail(BattleDetail battleDetail);
	
	/**
	 * 根据编号删除战场详情信息
	 * @param battleLogID
	 * @param round
	 */
	public void deleteBattleDetailByID(Integer battleLogID, Integer round);
	
	/**
	 * 根据编号获得战场详情信息
	 * @param battleLogID
	 * @param round
	 * @return
	 */
	public BattleDetail getBattleDetailByID(Integer battleLogID, Integer round);
	
	/**
	 * 获得战场详情信息列表
	 * @return
	 */
	public List<BattleDetail> getBattleDetailList();
	
	/**
	 * 根据战斗日志编号获得战场详情列表
	 * @param battleLogID
	 * @return
	 */
	public List<BattleDetail> getBattleDetailListByBattleLogID(Integer battleLogID);
}
