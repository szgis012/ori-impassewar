package com.war.domain;

import java.io.Serializable;

public class BattleDetail implements Serializable {

	private static final long serialVersionUID = 5484078587443918465L;

	/** 战斗日志编号 */
	private Integer battleLogID;
	/** 回合 */
	private Integer round;
	/** 进攻方操作信息 */
	private String attackerOperation;
	/** 防守方操作信息 */
	private String defenderOperation;
	/** 状态(0.正常 1.进攻方胜利 2.防守方胜利 3.进攻方逃跑 4.进攻方超时) */
	private Integer state;

	public Integer getBattleLogID() {
		return battleLogID;
	}

	public void setBattleLogID(Integer battleLogID) {
		this.battleLogID = battleLogID;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public String getAttackerOperation() {
		return attackerOperation;
	}

	public void setAttackerOperation(String attackerOperation) {
		this.attackerOperation = attackerOperation;
	}

	public String getDefenderOperation() {
		return defenderOperation;
	}

	public void setDefenderOperation(String defenderOperation) {
		this.defenderOperation = defenderOperation;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
