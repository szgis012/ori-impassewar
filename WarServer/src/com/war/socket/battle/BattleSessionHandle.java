package com.war.socket.battle;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;

public class BattleSessionHandle extends IoHandlerAdapter {

	@Override
	public void sessionCreated(IoSession session) throws Exception {
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		session.close();
		BattleSocketService.removeBattleSession(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		JSONObject json = new JSONObject(message.toString());
		
		int battleID = json.getInt("battleID");
		int operator = json.getInt("operator");
		
		int type = json.getInt("type");
		
		@SuppressWarnings("unused")
		int armyNO;
		
		switch(type){
			case 1:
				//移动
				BattleSocketService.armyMove(json);
				break;
			case 2:
				//攻击
				BattleSocketService.armyAttack(json);
				break;
			case 3:
				//防御
				BattleSocketService.armyDefense(json);
				break;
			case 6:
				//攻击城防
				BattleSocketService.armyAttackCityDefense(json);
				break;
			case 7:
				//城防攻击
				BattleSocketService.cityDefenseAttackArmy(json);
				break;
			case 8:
				//释放技能
				BattleSocketService.castSkill(json);
				break;
			case 11:
				//结束回合
				BattleSocketService.finishRound(json);
				break;
			case 12:
				//全军撤退
				BattleSocketService.militaryRetreat(json);
				break;
			case 15:
				//离开战场
				BattleSocketService.removeBattleSession(session);
				break;
			case 20:
				//自动战斗
				BattleSocketService.autoBattle(json);
				break;
			case 21:
				//野怪自动战斗(客户端调用)
				BattleSocketService.mapMonsterAutoBattle(json);
				break;
			case 30:
				//初始化
				BattleSocketService.addBattleSession(battleID, operator, session);
				break;
			case 88:
				// 开门、关门
				BattleSocketService.openAndCloseTheGate(json);
				break;
			default:
				break;
		}
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close();
		BattleSocketService.removeBattleSession(session);
	}
	
}
