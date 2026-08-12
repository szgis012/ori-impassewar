package com.war.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Battle implements Serializable {

	private static final long serialVersionUID = 8477794076648482294L;
	
	/** 战斗编号 */
	private Integer battleID;
	/** 进攻方军队编号 */
	private Integer militaryAttackerID;
	/** 防守方军队编号 */
	private Integer militaryDefenderID;
	/** 进攻方经验 */
	private Long attackerExp;
	/** 防守方经验 */
	private Long defenderExp;
	/** 城市防御数量(格式:数量,数量,数量,..) */
	private String cityDefenseAmount;
	/** 城市防御是否攻击列表 */
	private Integer[] cityDefenseHaveAttackedArray;
	/** 战场X坐标 */
	private Integer stagePosX;
	/** 战场Y坐标 */
	private Integer stagePosY;
	/** 回合 */
	private Integer round;
	/** 开始时间 */
	private Date startTime;
	/** 上一回合结束时间 */
	private Date preRoundFinishTime;
	/** 障碍数组 */
	private int[][] barrierArray;
	/** 战斗类型(1.掠夺战 2.攻城战) */
	private Integer type;
	/** 城市防御列表 */
	private List<CityDefense> cityDefenseList;
	/** 城市防御数量数组 */
	private Integer[] cityDefenseAmountArray;
	/** 攻击方军队信息 */
	private BattleMilitary militaryAttacker;
	/** 防守方军队信息 */
	private BattleMilitary militaryDefender;
	/** 玩家战斗类型(1.进攻 2.防守) */
	private Integer battleType;
	/** 战斗详情列表 */
	private List<BattleDetail> battleDetailList;
	
	
	public List<BattleDetail> getBattleDetailList() {
		return battleDetailList;
	}

	public void setBattleDetailList(List<BattleDetail> battleDetailList) {
		this.battleDetailList = battleDetailList;
	}

	public Integer getBattleID() {
		return battleID;
	}

	public void setBattleID(Integer battleID) {
		this.battleID = battleID;
	}
	
	public Integer getMilitaryAttackerID() {
		return militaryAttackerID;
	}

	public void setMilitaryAttackerID(Integer militaryAttackerID) {
		this.militaryAttackerID = militaryAttackerID;
	}

	public Integer getMilitaryDefenderID() {
		return militaryDefenderID;
	}

	public void setMilitaryDefenderID(Integer militaryDefenderID) {
		this.militaryDefenderID = militaryDefenderID;
	}
	
	public Long getAttackerExp() {
		return attackerExp;
	}

	public void setAttackerExp(Long attackerExp) {
		this.attackerExp = attackerExp;
	}

	public Long getDefenderExp() {
		return defenderExp;
	}

	public void setDefenderExp(Long defenderExp) {
		this.defenderExp = defenderExp;
	}
	
	public String getCityDefenseAmount() {
		return cityDefenseAmount;
	}

	public void setCityDefenseAmount(String cityDefenseAmount) {
		this.cityDefenseAmount = cityDefenseAmount;
	}
	
	public Integer[] getCityDefenseHaveAttackedArray() {
		return cityDefenseHaveAttackedArray;
	}

	public void setCityDefenseHaveAttackedArray(Integer[] cityDefenseHaveAttackedArray) {
		this.cityDefenseHaveAttackedArray = cityDefenseHaveAttackedArray;
	}
	
	public Integer getStagePosX() {
		return stagePosX;
	}

	public void setStagePosX(Integer stagePosX) {
		this.stagePosX = stagePosX;
	}
	
	public Integer getStagePosY() {
		return stagePosY;
	}

	public void setStagePosY(Integer stagePosY) {
		this.stagePosY = stagePosY;
	}
	
	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getPreRoundFinishTime() {
		return preRoundFinishTime;
	}

	public void setPreRoundFinishTime(Date preRoundFinishTime) {
		this.preRoundFinishTime = preRoundFinishTime;
	}

	public int[][] getBarrierArray() {
		return barrierArray;
	}

	public void setBarrierArray(int[][] barrierArray) {
		this.barrierArray = barrierArray;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public List<CityDefense> getCityDefenseList() {
		return cityDefenseList;
	}

	public void setCityDefenseList(List<CityDefense> cityDefenseList) {
		this.cityDefenseList = cityDefenseList;
	}
	
	public BattleMilitary getMilitaryAttacker() {
		return militaryAttacker;
	}

	public void setMilitaryAttacker(BattleMilitary militaryAttacker) {
		this.militaryAttacker = militaryAttacker;
	}

	public BattleMilitary getMilitaryDefender() {
		return militaryDefender;
	}

	public void setMilitaryDefender(BattleMilitary militaryDefender) {
		this.militaryDefender = militaryDefender;
	}

	public void setCityDefenseAmountArray(Integer[] cityDefenseAmountArray) {
		this.cityDefenseAmountArray = cityDefenseAmountArray;
	}

	public Integer[] getCityDefenseAmountArray() {
		return cityDefenseAmountArray;
	}

	public Integer getBattleType() {
		return battleType;
	}

	public void setBattleType(Integer battleType) {
		this.battleType = battleType;
	}

}