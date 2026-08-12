package com.war.dao;

import java.util.List;

import com.war.domain.BattleMilitary;
import com.war.domain.MapMonster;

public interface IMapMonsterDAO {

	/**
	 * 创建地图野怪
	 * @param mapMonster
	 * @return
	 */
	public Integer createMapMonster(MapMonster mapMonster);
	
	/**
	 * 批量创建地图野怪
	 * @param mapMonsterArray
	 * @return 新创建地图怪物编号数组
	 */
	public Integer[] createMapMonsterBatch(MapMonster[] mapMonsterArray);

	/**
	 * 根据战斗军队更新地图野怪士兵
	 * @param battleMilitary
	 */
	public void updateMapMonsterArmyByBattleMilitary(BattleMilitary battleMilitary);
	
	/**
	 * 更新地图野怪
	 * @param mapMonster
	 */
	public void updateMapMonster(MapMonster mapMonster);

	/**
	 * 根据编号删除地图野怪
	 * @param mapMonsterID
	 */
	public void deleteMapMonsterByID(Integer mapMonsterID);

	/**
	 * 删除目标地图野怪编号除外的地图野怪
	 * @param targetMapMonsterID
	 */
	public void deleteMapMonsterNotInTargetMapMonsterID(String targetMapMonsterIDStr);
	
	/**
	 * 删除无出征队列目标为当前野怪并且不在战斗中的野怪
	 */
	public void deleteNoDepoyQueueAndNotInBattleMapMonster();
	
	/**
	 * 根据编号获得地图野怪-战斗军队
	 * @param mapMonsterID
	 * @return
	 */
	public BattleMilitary getMapMonsterAsBattleMilitaryByID(Integer mapMonsterID);
	
	/**
	 * 根据编号获得地图野怪
	 * @param mapMonsterID
	 * @return
	 */
	public MapMonster getMapMonsterByID(Integer mapMonsterID);

	/**
	 * 获得地图野怪列表
	 * @return
	 */
	public List<MapMonster> getMapMonsterList();

}