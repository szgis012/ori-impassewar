package com.war.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 战斗军队
 * 包含掠夺战和攻城战两个不同种类战斗所对应不同的对象
 * 
 * @author TopTong
 * @version 1.0
 */
public class BattleMilitary implements Serializable {

	private static final long serialVersionUID = 5798199056355111304L;
	
	/** 玩家编号 */
	private Integer playerID;
	/** 城市军队编号 */
	private Integer cityMilitaryID;
	/** 名称 */
	private String name;
	/** 城市编号 */
	private Integer cityID;
	/** 城市英雄编号 */
	private Integer cityHeroID;
	/** 城市信息 */
	private CityInfo cityInfo;
	/** 城市英雄名称 */
	private CityHero cityHero;
	/** 指挥官是否已经释放技能 */
	private boolean haveCastedSkill;
	
	/** 地图怪物编号 */
	private Integer mapMonsterID;
	/** 怪物等级 */
	private Integer level;
	/** 指挥官名称 */
	private String cmderName;
	/** 指挥官头像 */
	private String cmderHead;
	/** 指挥官等级 */
	private Integer cmderLevel;	
	/** 指挥官指挥 */
	private Integer cmderCommand;
	/** 指挥官防御 */
	private Integer cmderDefense;
	
	/** 部队1 (格式：兵种编号:数量) */
	private String army1;
	/** 部队2 (格式：兵种编号:数量) */
	private String army2;
	/** 部队3 (格式：兵种编号:数量) */
	private String army3;
	/** 部队4 (格式：兵种编号:数量) */
	private String army4;
	/** 部队5 (格式：兵种编号:数量) */
	private String army5;
	/** 部队6 (格式：兵种编号:数量) */
	private String army6;
	/** 部队7 (格式：兵种编号:数量) */
	private String army7;
	/** 部队8 (格式：兵种编号:数量) */
	private String army8;
	/** 战斗士兵列表 */
	private List<BattleArmy> battleArmyList;
	
	/** 战斗类型(1.掠夺战 2.攻城战) */
	private Integer type;

	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public Integer getCityMilitaryID() {
		return cityMilitaryID;
	}

	public void setCityMilitaryID(Integer cityMilitaryID) {
		this.cityMilitaryID = cityMilitaryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public Integer getCityHeroID() {
		return cityHeroID;
	}

	public void setCityHeroID(Integer cityHeroID) {
		this.cityHeroID = cityHeroID;
	}

	public CityInfo getCityInfo() {
		return cityInfo;
	}

	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}

	public CityHero getCityHero() {
		return cityHero;
	}

	public void setCityHero(CityHero cityHero) {
		this.cityHero = cityHero;
	}
	
	public boolean getHaveCastedSkill() {
		return haveCastedSkill;
	}

	public void setHaveCastedSkill(boolean haveCastedSkill) {
		this.haveCastedSkill = haveCastedSkill;
	}

	public Integer getMapMonsterID() {
		return mapMonsterID;
	}

	public void setMapMonsterID(Integer mapMonsterID) {
		this.mapMonsterID = mapMonsterID;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCmderName() {
		return cmderName;
	}

	public void setCmderName(String cmderName) {
		this.cmderName = cmderName;
	}

	public String getCmderHead() {
		return cmderHead;
	}

	public void setCmderHead(String cmderHead) {
		this.cmderHead = cmderHead;
	}

	public Integer getCmderLevel() {
		return cmderLevel;
	}

	public void setCmderLevel(Integer cmderLevel) {
		this.cmderLevel = cmderLevel;
	}

	public Integer getCmderCommand() {
		return cmderCommand;
	}

	public void setCmderCommand(Integer cmderCommand) {
		this.cmderCommand = cmderCommand;
	}

	public Integer getCmderDefense() {
		return cmderDefense;
	}

	public void setCmderDefense(Integer cmderDefense) {
		this.cmderDefense = cmderDefense;
	}

	public String getArmy1() {
		return army1;
	}

	public void setArmy1(String army1) {
		this.army1 = army1;
	}

	public String getArmy2() {
		return army2;
	}

	public void setArmy2(String army2) {
		this.army2 = army2;
	}

	public String getArmy3() {
		return army3;
	}

	public void setArmy3(String army3) {
		this.army3 = army3;
	}

	public String getArmy4() {
		return army4;
	}

	public void setArmy4(String army4) {
		this.army4 = army4;
	}

	public String getArmy5() {
		return army5;
	}

	public void setArmy5(String army5) {
		this.army5 = army5;
	}

	public String getArmy6() {
		return army6;
	}

	public void setArmy6(String army6) {
		this.army6 = army6;
	}

	public String getArmy7() {
		return army7;
	}

	public void setArmy7(String army7) {
		this.army7 = army7;
	}

	public String getArmy8() {
		return army8;
	}

	public void setArmy8(String army8) {
		this.army8 = army8;
	}

	public List<BattleArmy> getBattleArmyList() {
		return battleArmyList;
	}

	public void setBattleArmyList(List<BattleArmy> battleArmyList) {
		this.battleArmyList = battleArmyList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
