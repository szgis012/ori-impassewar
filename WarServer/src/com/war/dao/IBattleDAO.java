package com.war.dao;

import java.util.List;

import com.war.domain.Battle;

public interface IBattleDAO {

	/**
	 * 创建战斗
	 * @param battle
	 * @return
	 */
	public Integer createBattle(Battle battle);

	/**
	 * 更新战斗回合及上回合结束时间(默认为系统当前时间)
	 * @param battleID
	 * @param round
	 */
	public void updateBattleRoundAndPreRoundFinishTime(Integer battleID,Integer round);
	
	/**
	 * 更新战斗经验
	 * @param battleID
	 * @param attackerExp
	 * @param defenderExp
	 */
	public void updateBattleExp(Integer battleID,Long attackerExp,Long defenderExp);
	
	/**
	 * 更新战斗城市防御数量
	 * @param battleID
	 * @param cityDefenseAmount
	 */
	public void updateBattleCityDefenseAmount(Integer battleID,String cityDefenseAmount);
	
	/**
	 * 更新战斗
	 * @param battle
	 */
	public void updateBattle(Battle battle);

	/**
	 * 根据战斗编号删除战斗
	 * @param battleID
	 */
	public void deleteBattleByID(Integer battleID);

	/**
	 * 根据战斗编号获得战斗
	 * @param battleID
	 * @return
	 */
	public Battle getBattleByID(Integer battleID);

	/**
	 * 根据城市编号获得进攻战斗列表
	 * @param cityID
	 * @return
	 */
	public List<Battle> getAttackBattleListByCityID(Integer cityID);
	
	/**
	 * 根据城市编号获得防守战斗列表
	 * @param cityID
	 * @return
	 */
	public List<Battle> getDefenseBattleListByCityID(Integer cityID);
	
	/**
	 * 根据城市编号获得战斗列表
	 * @param cityID
	 * @return
	 */
	public List<Battle> getBattleListByCityID(Integer cityID);
	
	/**
	 * 获得回合结束战斗列表
	 * @param roundTime
	 * @return
	 */
	public List<Battle> getRoundFinishedBattleList(Integer roundTime);
	
	/**
	 * 获得战斗列表
	 * @return
	 */
	public List<Battle> getBattleList();
	

}