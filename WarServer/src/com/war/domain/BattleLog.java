package com.war.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BattleLog implements Serializable {

	private static final long serialVersionUID = -2097389569913902495L;

	/** 战斗日志编号 */
	private Integer battleLogID;
	/** 地图名称 */
	private String mapName;
	/** X坐标 */
	private Integer posX;
	/** Y坐标 */
	private Integer posY;
	/** 野怪等级(攻城战则为NULL)  */
	private Integer level;
	/** 进攻方玩家编号 */
	private Integer attackerPlayerID;
	/** 防守方玩家编号 */
	private Integer defenderPlayerID;
	/** 
	 * 进攻方军队信息 
	 * {militaryName: x(野怪的时候要包括等级), heroName: y, heroLevel: z, heroHead: u }
	 */
	private String attackerMilitaryInfo;
	/** 防守方军队信息 */
	private String defenderMilitaryInfo;
	/** 
	 * 进攻方士兵信息 
	 * [{"id":1, "dispatchNum":1000, "deadNum": 1000, "woundedNum": 40}, {...}, ...]
	 */
	private String attackerArmyInfo;
	/** 防守方士兵信息 */
	private String defenderArmyInfo;
	/** 
	 * 城防信息 
	 * [{id: 1, joinNum: 100, destoryNum: 100}, {...}, {}]
	 */
	private String cityDefenceInfo;
	/** 进攻方经验 */
	private Integer attackerExp;
	/** 防守方经验 */
	private Integer defenderExp;
	/** 进攻方声望 */
	private Integer attackerRenown;
	/** 防守方声望 */
	private Integer defenderRenown;
	/** 
	 * 获得资源
	 * {wood: 100, steel: 100, oil: 100, food: 100, money: 100} 
	 */
	private String attainedResource;
	/** 
	 * 获得装备
	 * [{id: 1, num: 1}] 
	 */
	private String attainedEquipment;
	/** 
	 * 获得宝物 
	 * [{id: 1, num: 1}] 
	 */
	private String attainedTreasure;
	/** 
	 * 备注
	 * 例：由于我方战斗胜利，成功降低敌方城市 1 点治安值。
	 */
	private String remark;
	/** 战斗结果(0.战斗时间结束 1.进攻方胜利 2.防守方胜利 3.进攻方逃跑) */
	private Integer result;
	/** 持续回合 */
	private Integer durativeRound;
	/** 开始时间 */
	private Date startTime;
	/** 结束时间 */
	private Date endTime;
	/** 战斗类型(1.掠夺战 2.攻城战) */
	private Integer type;
	/**
	 * 进攻方士兵列表
	 * Map(Key->Value): "army" -> 士兵对象, "dispatchNum" -> 派遣数量, "deadNum" -> 阵亡数量, "woundedNum" -> 伤兵数量
	 */
	private List<Map<String, Object>> attackArmyList;
	/**
	 * 进攻方士兵列表
	 * Map(Key->Value): "army" -> 士兵对象, "dispatchNum" -> 派遣数量, "deadNum" -> 阵亡数量, "woundedNum" -> 伤兵数量
	 */
	private List<Map<String, Object>> defenderArmyList;
	/**
	 * 城防信息列表
	 * Map(Key->Value): "defense" --> 城防建筑对象, "joinNum" -> 加入战斗数量, "destoryNum" -> 损失数量
	 */
	private List<Map<String, Object>> cityDefenseList;
	/**
	 * 获得装备列表，Map(Key->Value): "equipment" -> 装备对象, "num" -> 获得装备数量
	 */
	private List<Map<String, Object>> attainedEquipmentList;
	/**
	 * 获得宝物列表，Map(Key->Value): treasure -> 宝物对象, num -> 获得宝物数量
	 */
	private List<Map<String, Object>> attainedTreasureList;
	

	public Integer getBattleLogID() {
		return battleLogID;
	}

	public void setBattleLogID(Integer battleLogID) {
		this.battleLogID = battleLogID;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAttackerPlayerID() {
		return attackerPlayerID;
	}

	public void setAttackerPlayerID(Integer attackerPlayerID) {
		this.attackerPlayerID = attackerPlayerID;
	}

	public Integer getDefenderPlayerID() {
		return defenderPlayerID;
	}

	public void setDefenderPlayerID(Integer defenderPlayerID) {
		this.defenderPlayerID = defenderPlayerID;
	}

	public String getAttackerMilitaryInfo() {
		return attackerMilitaryInfo;
	}

	public void setAttackerMilitaryInfo(String attackerMilitaryInfo) {
		this.attackerMilitaryInfo = attackerMilitaryInfo;
	}

	public String getDefenderMilitaryInfo() {
		return defenderMilitaryInfo;
	}

	public void setDefenderMilitaryInfo(String defenderMilitaryInfo) {
		this.defenderMilitaryInfo = defenderMilitaryInfo;
	}

	public String getAttackerArmyInfo() {
		return attackerArmyInfo;
	}

	public void setAttackerArmyInfo(String attackerArmyInfo) {
		this.attackerArmyInfo = attackerArmyInfo;
	}

	public String getDefenderArmyInfo() {
		return defenderArmyInfo;
	}

	public void setDefenderArmyInfo(String defenderArmyInfo) {
		this.defenderArmyInfo = defenderArmyInfo;
	}

	public String getCityDefenceInfo() {
		return cityDefenceInfo;
	}

	public void setCityDefenceInfo(String cityDefenceInfo) {
		this.cityDefenceInfo = cityDefenceInfo;
	}

	public Integer getAttackerExp() {
		return attackerExp;
	}

	public void setAttackerExp(Integer attackerExp) {
		this.attackerExp = attackerExp;
	}

	public Integer getDefenderExp() {
		return defenderExp;
	}

	public void setDefenderExp(Integer defenderExp) {
		this.defenderExp = defenderExp;
	}

	public Integer getAttackerRenown() {
		return attackerRenown;
	}

	public void setAttackerRenown(Integer attackerRenown) {
		this.attackerRenown = attackerRenown;
	}

	public Integer getDefenderRenown() {
		return defenderRenown;
	}

	public void setDefenderRenown(Integer defenderRenown) {
		this.defenderRenown = defenderRenown;
	}

	public String getAttainedResource() {
		return attainedResource;
	}

	public void setAttainedResource(String attainedResource) {
		this.attainedResource = attainedResource;
	}

	public String getAttainedEquipment() {
		return attainedEquipment;
	}

	public void setAttainedEquipment(String attainedEquipment) {
		this.attainedEquipment = attainedEquipment;
	}

	public String getAttainedTreasure() {
		return attainedTreasure;
	}

	public void setAttainedTreasure(String attainedTreasure) {
		this.attainedTreasure = attainedTreasure;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getDurativeRound() {
		return durativeRound;
	}

	public void setDurativeRound(Integer durativeRound) {
		this.durativeRound = durativeRound;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Map<String, Object>> getAttackArmyList() {
		return attackArmyList;
	}

	public void setAttackArmyList(List<Map<String, Object>> attackArmyList) {
		this.attackArmyList = attackArmyList;
	}

	public List<Map<String, Object>> getDefenderArmyList() {
		return defenderArmyList;
	}

	public void setDefenderArmyList(List<Map<String, Object>> defenderArmyList) {
		this.defenderArmyList = defenderArmyList;
	}

	public List<Map<String, Object>> getCityDefenseList() {
		return cityDefenseList;
	}

	public void setCityDefenseList(List<Map<String, Object>> cityDefenseList) {
		this.cityDefenseList = cityDefenseList;
	}
	
	public List<Map<String, Object>> getAttainedEquipmentList() {
		return attainedEquipmentList;
	}

	public void setAttainedEquipmentList(
			List<Map<String, Object>> attainedEquipmentList) {
		this.attainedEquipmentList = attainedEquipmentList;
	}

	public List<Map<String, Object>> getAttainedTreasureList() {
		return attainedTreasureList;
	}

	public void setAttainedTreasureList(
			List<Map<String, Object>> attainedTreasureList) {
		this.attainedTreasureList = attainedTreasureList;
	}

}
