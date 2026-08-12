package com.war.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.war.domain.Battle;
import com.war.domain.BattleLog;
import com.war.domain.BattleWait;

public interface IBattleService {

	/**
	 * 添加战斗
	 * @param battle
	 * @return
	 */
	public Integer addBattle(Battle battle);
	
	/**
	 * 更新战斗信息
	 * @param battleID 战斗编号
	 * @param round 回合
	 * @param preRoundFinishTime 上一回合结束时间
	 */
	public void updateBattle(Integer battleID,Integer round);
	
	/**
	 * 军队移动
	 * @param json
	 */
	public void armyMove(JSONObject json);
	
	/**
	 * 军队防御
	 * @param json
	 */
	public void armyDefense(JSONObject json);
	
	/**
	 * 军队攻击
	 * @param json
	 * @return
	 */
	public JSONObject armyAttack(JSONObject json);
	
	/**
	 * 军队攻击城市防御
	 * @param json
	 * @return
	 */
	public JSONObject armyAttackCityDefense(JSONObject json);
	
	/**
	 * 城市防御攻击军队
	 * @param json
	 * @return
	 */
	public JSONObject cityDefenseAttackArmy(JSONObject json);
	
	/**
	 * 释放技能
	 * @param json
	 * @return
	 */
	public JSONObject castSkill(JSONObject json);
	
	/**
	 * 军队撤退
	 * @param json
	 * @return
	 */
	public JSONObject militaryRetreat(JSONObject json);
	
	/**
	 * 自动战斗
	 * @param json
	 * @return
	 */
	public JSONObject autoBattle(JSONObject json);
	
	/**
	 * 开门、关门
	 * @param json
	 * @return
	 */
	public JSONObject openAndCloseTheGate(JSONObject json);
	
	/**
	 * 回合结束
	 * @param battleID
	 */
	public void roundFinished(Integer battleID, Integer operator);
	
	/**
	 * 战斗结束
	 * @param battleID
	 * @param winner 胜利方编号(0.战斗时间结束(超过30回合) 1.进攻方 2.防守方 3.进攻方逃跑)
	 */
	public void battleFinished(Integer battleID,Integer winner);
	
	/**
	 * 初始化战斗信息
	 * @param battleID
	 * @return 战斗信息存在范围战斗信息，否则返回null
	 */
	public Battle initBattleInfo(Integer battleID);
	
	/**
	 * 获得战斗信息
	 * @param battleID
	 */
	public Battle getBattleInfo(Integer battleID);
	
	/**
	 * 获得城市攻击战斗列表
	 * @param cityID
	 * @return
	 */
	public List<Battle> getCityAttackBattleList(Integer cityID);
	
	/**
	 * 获得城市防守战斗列表
	 * @param cityID
	 * @return
	 */
	public List<Battle> getCityDefenseBattleList(Integer cityID);
	
	/**
	 * 获得城市战斗列表
	 * @param cityID
	 * @return
	 */
	public List<Battle> getCityBattleList(Integer cityID);
	
	/**
	 * 获得回合结束战斗列表
	 * @param roundTime
	 * @return
	 */
	public List<Battle> getRoundFinishedBattleList(Integer roundTime);
	
	/**
	 * 获得战斗Map
	 * @return
	 */
	public Map<Integer,Battle> getBattleMap();
	
	/**
	 * 获得战斗列表
	 * @return
	 */
	public List<Battle> getBattleList();

	/**
	 * 根据玩家编号获得他的战斗日志列表
	 * @param playerID
	 * @return
	 */
	public List<BattleLog> getBattleLogListByPlayerID(Integer playerID);
	
	/**
	 * 根据编号获得战斗日志
	 * @param battleLogID
	 * @return
	 */
	public BattleLog getBattleLogByID(Integer battleLogID);
	
	/**
	 * 结束战斗间歇等待
	 */
	public void finishBattleIntervalWait(Integer battleWaitID);
	
	/**
	 * 获得已完成等待的战斗等待信息列表
	 */
	public List<BattleWait> getIntervalFinishedBattleWaitList();
	
	/**
	 * 根据玩家编号获得其参与进攻的战斗日志数目
	 * @param playerID
	 * @return
	 */
	public Integer getPlayerAttackBattleLogNum(Integer playerID);

	/**
	 * 根据攻击目标等级和时间以及攻击方玩家编号获得其日志数目
	 * @param playerID
	 * @param level
	 * @param time
	 * @return
	 */
	public Integer getBattleLogNumForAttackTask(Integer playerID, Integer level, Date time);

	/**
	 * 获得玩家战斗日志的分页列表
	 * @param playerID
	 * @param page
	 * @return
	 */
	public List<BattleLog> getPlayerBattleLogPagingList(Integer playerID, Integer page);

	/**
	 * 获得玩家战斗日志总数
	 * @param playerID
	 * @return
	 */
	public Integer getPlayerBattleLogNum(Integer playerID);

	
}
