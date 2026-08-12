package com.war.socket.battle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.json.JSONException;
import org.json.JSONObject;

import com.war.common.SpringService;
import com.war.service.IBattleService;

public class BattleSocketService {

	private static IBattleService battleService = (IBattleService)SpringService.getBean("battleService");
	
	private static Map<Integer,Map<Integer,IoSession>> battleSessionMap = new HashMap<Integer,Map<Integer,IoSession>>();
	
	private static Logger logger = Logger.getLogger(BattleSocketService.class);
	
	/**
	 * 添加战斗Session
	 * @param battleID
	 * @param operator
	 * @param session
	 */
	public synchronized static void addBattleSession(Integer battleID,Integer operator,IoSession session) {
		
		Map<Integer,IoSession> sessionMap = battleSessionMap.get(battleID);
		
		if(sessionMap==null){
			sessionMap = new HashMap<Integer,IoSession>();
		}
		sessionMap.put(operator, session);
		
		battleSessionMap.put(battleID, sessionMap);
	}
	
	/**
	 * 删除战斗Session
	 * @param session
	 */
	public synchronized static void removeBattleSession(IoSession session) {
		
		Iterator<Entry<Integer, Map<Integer,IoSession>>> iterator = battleSessionMap.entrySet().iterator();
		
		Entry<Integer,Map<Integer,IoSession>> currentBattleEntry = null;
		
		//是否存在对应的session
		boolean exsitsSession = false;
		int currentBattleID = 0;
		
		while(iterator.hasNext()){
			currentBattleEntry = iterator.next();
			
			if(currentBattleEntry.getValue().get(1)==session){
				currentBattleID = currentBattleEntry.getKey();
				return;
			}
			
			if(currentBattleEntry.getValue().get(2)==session){
				currentBattleID = currentBattleEntry.getKey();
				return;
			}
		}
		
		//如果Session存在则清除
		if(exsitsSession){
			battleSessionMap.get(currentBattleID).remove(session);
		}
		
	}
	
	/**
	 * 判断战斗Session是否存在
	 * @param battleID
	 * @param operator
	 * @return
	 */
	public static boolean isBattleSessionExist(Integer battleID,Integer operator){
		if ( battleSessionMap.get(battleID) != null && battleSessionMap.get(battleID).get(operator) != null) {
			return true;
		} else {
			return false; 
		}
	}
	
	public static void getBattleSession(Integer battle){
		
	}
	
	/**
	 * 发送信息至战斗另一方客户端
	 * @param battleID
	 * @param currentOperator
	 * @param data
	 */
	public static void sendDataToTheOtherClient(Integer battleID,Integer currentOperator,Object data){
		
		IoSession session = battleSessionMap.get(battleID).get(3-currentOperator);
		if(session!=null){
			session.write(data);
		}
		
	}
	
	/**
	 * 发送信息至战斗双方客户端
	 * @param battleID
	 * @param data
	 */
	public static void sendDataToClient(Integer battleID,Object data){
		
		if(battleID!=null){
			
			if(battleSessionMap.get(battleID) == null){
				return;
			}
			
			IoSession sessionAttacker = battleSessionMap.get(battleID).get(1);
			IoSession sessionDefender = battleSessionMap.get(battleID).get(2);
			
			if(sessionAttacker!=null){
				sessionAttacker.write(data);
			}
			
			if(sessionDefender!=null){
				sessionDefender.write(data);
			}
			
		}
		
	}
	
	/**
	 * 军队移动
	 * @param json
	 */
	public static void armyMove(JSONObject json){
		battleService.armyMove(json);
		try {
			sendDataToClient(json.getInt("battleID"),json);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 军队攻击
	 * @param json
	 */
	public static void armyAttack(JSONObject json){
		try {
			sendDataToClient(json.getInt("battleID"),battleService.armyAttack(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 军队攻击城市防御
	 * @param json
	 */
	public static void armyAttackCityDefense(JSONObject json){
		try {
			sendDataToClient(json.getInt("battleID"),battleService.armyAttackCityDefense(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 城市防御攻击军队
	 * @param json
	 */
	public static void cityDefenseAttackArmy(JSONObject json){
		try {
			sendDataToClient(json.getInt("battleID"),battleService.cityDefenseAttackArmy(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 军队防御
	 * @param json
	 */
	public static void armyDefense(JSONObject json){
		battleService.armyDefense(json);
		try {
			sendDataToClient(json.getInt("battleID"),json);
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 释放技能
	 * @param json
	 */
	public static void castSkill(JSONObject json){
		try {
			sendDataToClient(json.getInt("battleID"),battleService.castSkill(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	public static void militaryRetreat(JSONObject json){
		try {
			sendDataToClient(json.getInt("battleID"),battleService.militaryRetreat(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 自动战斗
	 * @param json
	 */
	public static void autoBattle(JSONObject json){
		try {
			sendDataToClient(json.getInt("battleID"),battleService.autoBattle(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 野怪自动战斗
	 * @param json
	 */
	public static void mapMonsterAutoBattle(JSONObject json){
		try {
			sendDataToClient(json.getInt("battleID"),battleService.autoBattle(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		}
	}
	
	/**
	 * 结束回合
	 * @param json
	 */
	public static void finishRound(JSONObject json){
		try {
			battleService.roundFinished(json.getInt("battleID"), json.getInt("operator"));
		} catch (JSONException e) {
			logger.error("异常：", e);
		} 
	}
	
	/**
	 * 开门、关门
	 * @param json
	 */
	public static void openAndCloseTheGate(JSONObject json) {
		try {
			sendDataToClient(json.getInt("battleID"),battleService.openAndCloseTheGate(json));
		} catch (JSONException e) {
			logger.error("异常：", e);
		} 
	}
	
}
