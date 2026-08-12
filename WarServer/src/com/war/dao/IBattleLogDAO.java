package com.war.dao;

import java.util.Date;
import java.util.List;

import com.war.domain.BattleLog;

public interface IBattleLogDAO {

	/**
	 * 创建战斗日志
	 * @param battleLog
	 * @return
	 */
	public Integer createBattleLog(BattleLog battleLog);

	/**
	 * 更新战斗日志
	 * @param battleLog
	 */
	public void updateBattleLog(BattleLog battleLog);

	/**
	 * 删除战斗日志
	 * @param battleLogID
	 */
	public void deleteBattleLogByID(Integer battleLogID);

	/**
	 * 根据编号获得战斗日志
	 * @param battleLogID
	 * @return
	 */
	public BattleLog getBattleLogByID(Integer battleLogID);

	/**
	 * 获得战斗日志列表
	 * @return
	 */
	public List<BattleLog> getBattleLogList();

	/**
	 * 根据玩家编号获得他的战斗日志列表
	 * @param playerID
	 * @return
	 */
	public List<BattleLog> getBattleLogListByPlayerID(Integer playerID);
	
	/**
	 * 根据坐标查询战斗日志
	 * @param posX
	 * @param posY
	 * @return
	 */
	public List<BattleLog> getBattleLogListByPosXAndPosYOrderByStartTime(Integer posX, Integer posY);
	
	/**
	 * 根据进攻方玩家编号获得其战斗日志数量
	 * @param attackerPlayerID
	 * @return
	 */
	public Integer getBattleLogNumByAttackerPlayerID(Integer attackerPlayerID);
	
	/**
	 * 根据攻击目标等级和时间以及攻击方玩家编号获得其日志数目
	 * @param playerID
	 * @param level
	 * @param date
	 * @return
	 */
	public Integer getBattleLogNumForAttackTask(Integer playerID, Integer level, Date date);

	/**
	 * 根据玩家编号获得战斗日志分页列表信息
	 * @param playerID
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<BattleLog> getBattleLogPagingListByPlayerID(Integer playerID, Integer start, Integer offset);

	/**
	 * 根据玩家编号获得其战斗日志总数目
	 * @param playerID
	 * @return
	 */
	public Integer getBattleLogNumByPlayerID(Integer playerID);
}
