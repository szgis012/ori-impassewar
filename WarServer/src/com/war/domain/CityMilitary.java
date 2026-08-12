package com.war.domain;

import java.io.Serializable;
import java.util.List;

public class CityMilitary implements Serializable {

	private static final long serialVersionUID = -7817509210300220973L;

	/** 城市军队编号 */
	private Integer cityMilitaryID;
	/** 名称 */
	private String name;
	/** 城市编号 */
	private Integer cityID;
	/** 城市信息 */
	private CityInfo cityInfo;
	/** 城市英雄编号 */
	private Integer cityHeroID;
	/** 城市英雄名称 */
	private CityHero cityHero;
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
	/** 石油消耗 */
	private Integer costOil;
	/** 食物消耗 */
	private Integer costFood;
	/** 金钱消耗 */
	private Integer costMoney;
	/** 部队状态 CityMilitaryStateConstant中定义 */
	private Integer state;

	
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

	public CityInfo getCityInfo() {
		return cityInfo;
	}

	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}

	public Integer getCityHeroID() {
		return cityHeroID;
	}

	public void setCityHeroID(Integer cityHeroID) {
		this.cityHeroID = cityHeroID;
	}

	public CityHero getCityHero() {
		return cityHero;
	}

	public void setCityHero(CityHero cityHero) {
		this.cityHero = cityHero;
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
	
	public Integer getCostOil() {
		return costOil;
	}

	public void setCostOil(Integer costOil) {
		this.costOil = costOil;
	}

	public Integer getCostFood() {
		return costFood;
	}

	public void setCostFood(Integer costFood) {
		this.costFood = costFood;
	}

	public Integer getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(Integer costMoney) {
		this.costMoney = costMoney;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}