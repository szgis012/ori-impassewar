package com.war.dao;

import java.util.List;
import java.util.Map;

import com.war.domain.BattleArmy;

public interface IBattleArmyDAO {

	/**
	 * 创建战斗士兵
	 * @param battleArmy
	 * @return
	 */
	public Integer createBattleArmy(BattleArmy battleArmy);

	/**
	 * 根据参数更新战斗士兵
	 * @param params
	 */
	public void updateBattleArmyByParams(Map<String,Object> params);
	
	/**
	 * 批量根据参数更新战斗士兵
	 * @param paramsList
	 */
	public void updateBattleArmyByParamsBatch(List<Map<String,Object>> paramsList);
	
	/**
	 * 更新战斗士兵
	 * @param battleArmy
	 */
	public void updateBattleArmy(BattleArmy battleArmy);

	/**
	 * 根据编号删除战斗士兵
	 * @param battleArmyID
	 */
	public void deleteBattleArmyByID(Integer battleArmyID);
	
	/**
	 * 根据战斗编号删除战斗士兵
	 * @param battleID
	 */
	public void deleteBattleArmyByBattleID(Integer battleID);

	/**
	 * 根据战斗编号及士兵势力及士兵索引获得战斗士兵
	 * @param battleID
	 * @param armyForce
	 * @param armyIndex
	 * @return
	 */
	public BattleArmy getBattleArmyByBattleIDAndArmyForceAndArmyIndex(Integer battleID,Integer armyForce,Integer armyIndex);
	
	/**
	 * 根据编号获得战斗士兵
	 * @param battleArmyID
	 * @return
	 */
	public BattleArmy getBattleArmyByID(Integer battleArmyID);

	/**
	 * 根据战斗编号及士兵势力获得战斗士兵列表
	 * @param battleID
	 * @param armyForce
	 * @return
	 */
	public List<BattleArmy> getBattleArmyListByBattleIDAndArmyForce(Integer battleID,Integer armyForce);
	
	/**
	 * 根据战斗编号获得战斗士兵列表
	 * @return
	 */
	public List<BattleArmy> getBattleArmyListByBattleID(Integer battleID);

}