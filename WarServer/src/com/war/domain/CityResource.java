package com.war.domain;

import java.io.Serializable;

/**
 * 城市资源
 * @author JiaHL
 *
 */
public class CityResource implements Serializable {
	
	private static final long serialVersionUID = 7674240433190969743L;
	
	/** 城市编号 */
	private Integer cityID;
	/** 资源数量上限 */
	private Long resourceNumMax;
	/** 木材数量 */
	private Long woodNum;
	/** 木材产量 */
	private Long woodOutput;
	/** 木材工人数量 */
	private Integer woodWorkerNum;
	/** 木材建筑加成 */
	private Integer woodBuildingAdd;
	/** 木材科技加成 */
	private Integer woodTechAdd;
	/** 木材野地加成 */
	private Integer woodFieldAdd;
	/** 木材执行官加成 */
	private Integer woodOfficerAdd;
	/** 木材军团加成 */
	private Integer woodGuildAdd;
	/** 木材宝物加成 */
	private Integer woodTreasureAdd;
	/** 钢铁数量 */
	private Long steelNum;
	/** 钢铁产量 */
	private Long steelOutput;
	/** 钢铁工人数量 */
	private Integer steelWorkerNum;
	/** 钢铁建筑加成 */
	private Integer steelBuildingAdd;
	/** 钢铁科技加成 */
	private Integer steelTechAdd;
	/** 钢铁野地加成 */
	private Integer steelFieldAdd;
	/** 钢铁执政官加成 */
	private Integer steelOfficerAdd;
	/** 钢铁军团加成 */
	private Integer steelGuildAdd;
	/** 钢铁宝物加成 */
	private Integer steelTreasureAdd;
	/** 石油数量 */
	private Long oilNum;
	/** 石油产量 */
	private Long oilOutput;
	/** 石油工人数量 */
	private Integer oilWorkerNum;
	/** 石油建筑加成 */
	private Integer oilBuildingAdd;
	/** 石油科技加成 */
	private Integer oilTechAdd;
	/** 石油野地加成 */
	private Integer oilFieldAdd;
	/** 石油执政官加成 */
	private Integer oilOfficerAdd;
	/** 石油军团加成 */
	private Integer oilGuildAdd;
	/** 石油宝物加成 */
	private Integer oilTreasureAdd;
	/** 石油消耗 */
	private Long oilConsume;
	/** 食物数量 */
	private Long foodNum;
	/** 食物产量 */
	private Long foodOutput;
	/** 食物工人数量 */
	private Integer foodWorkerNum;
	/** 食物建筑加成 */
	private Integer foodBuildingAdd;
	/** 食物科技加成 */
	private Integer foodTechAdd;
	/** 食物野地加成 */
	private Integer foodFieldAdd;
	/** 食物执政官加成 */
	private Integer foodOfficerAdd;
	/** 食物军团加成 */
	private Integer foodGuildAdd;
	/** 食物宝物加成 */
	private Integer foodTreasureAdd;
	/** 食物消耗 */
	private Long foodConsume;
	/** 金钱数量 */
	private Long moneyNum;
	/** 金钱产量 */
	private Long moneyOutput;
	/** 资源科技加成 */
	private Integer moneyTechAdd;
	/** 资源野地加成 */
	private Integer moneyFieldAdd;
	/** 金钱执政官加成 */
	private Integer moneyOfficerAdd;
	/** 金钱军团加成 */
	private Integer moneyGuildAdd;
	/** 金钱宝物加成 */
	private Integer moneyTreasureAdd;
	/** 金钱消耗 */
	private Long moneyConsume;


	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public Long getResourceNumMax() {
		return resourceNumMax;
	}

	public void setResourceNumMax(Long resourceNumMax) {
		this.resourceNumMax = resourceNumMax;
	}

	public Long getWoodNum() {
		return woodNum;
	}

	public void setWoodNum(Long woodNum) {
		this.woodNum = woodNum;
	}

	public Long getWoodOutput() {
		return woodOutput;
	}

	public void setWoodOutput(Long woodOutput) {
		this.woodOutput = woodOutput;
	}

	public Integer getWoodWorkerNum() {
		return woodWorkerNum;
	}

	public void setWoodWorkerNum(Integer woodWorkerNum) {
		this.woodWorkerNum = woodWorkerNum;
	}

	public Integer getWoodBuildingAdd() {
		return woodBuildingAdd;
	}

	public void setWoodBuildingAdd(Integer woodBuildingAdd) {
		this.woodBuildingAdd = woodBuildingAdd;
	}

	public Integer getWoodTechAdd() {
		return woodTechAdd;
	}

	public void setWoodTechAdd(Integer woodTechAdd) {
		this.woodTechAdd = woodTechAdd;
	}

	public Integer getWoodFieldAdd() {
		return woodFieldAdd;
	}

	public void setWoodFieldAdd(Integer woodFieldAdd) {
		this.woodFieldAdd = woodFieldAdd;
	}

	public Integer getWoodOfficerAdd() {
		return woodOfficerAdd;
	}

	public void setWoodOfficerAdd(Integer woodOfficerAdd) {
		this.woodOfficerAdd = woodOfficerAdd;
	}

	public Integer getWoodGuildAdd() {
		return woodGuildAdd;
	}

	public void setWoodGuildAdd(Integer woodGuildAdd) {
		this.woodGuildAdd = woodGuildAdd;
	}

	public Integer getWoodTreasureAdd() {
		return woodTreasureAdd;
	}

	public void setWoodTreasureAdd(Integer woodTreasureAdd) {
		this.woodTreasureAdd = woodTreasureAdd;
	}

	public Long getSteelNum() {
		return steelNum;
	}

	public void setSteelNum(Long steelNum) {
		this.steelNum = steelNum;
	}

	public Long getSteelOutput() {
		return steelOutput;
	}

	public void setSteelOutput(Long steelOutput) {
		this.steelOutput = steelOutput;
	}

	public Integer getSteelWorkerNum() {
		return steelWorkerNum;
	}

	public void setSteelWorkerNum(Integer steelWorkerNum) {
		this.steelWorkerNum = steelWorkerNum;
	}

	public Integer getSteelBuildingAdd() {
		return steelBuildingAdd;
	}

	public void setSteelBuildingAdd(Integer steelBuildingAdd) {
		this.steelBuildingAdd = steelBuildingAdd;
	}

	public Integer getSteelTechAdd() {
		return steelTechAdd;
	}

	public void setSteelTechAdd(Integer steelTechAdd) {
		this.steelTechAdd = steelTechAdd;
	}

	public Integer getSteelFieldAdd() {
		return steelFieldAdd;
	}

	public void setSteelFieldAdd(Integer steelFieldAdd) {
		this.steelFieldAdd = steelFieldAdd;
	}

	public Integer getSteelOfficerAdd() {
		return steelOfficerAdd;
	}

	public void setSteelOfficerAdd(Integer steelOfficerAdd) {
		this.steelOfficerAdd = steelOfficerAdd;
	}

	public Integer getSteelGuildAdd() {
		return steelGuildAdd;
	}

	public void setSteelGuildAdd(Integer steelGuildAdd) {
		this.steelGuildAdd = steelGuildAdd;
	}

	public Integer getSteelTreasureAdd() {
		return steelTreasureAdd;
	}

	public void setSteelTreasureAdd(Integer steelTreasureAdd) {
		this.steelTreasureAdd = steelTreasureAdd;
	}

	public Long getOilNum() {
		return oilNum;
	}

	public void setOilNum(Long oilNum) {
		this.oilNum = oilNum;
	}

	public Long getOilOutput() {
		return oilOutput;
	}

	public void setOilOutput(Long oilOutput) {
		this.oilOutput = oilOutput;
	}

	public Integer getOilWorkerNum() {
		return oilWorkerNum;
	}

	public void setOilWorkerNum(Integer oilWorkerNum) {
		this.oilWorkerNum = oilWorkerNum;
	}

	public Integer getOilBuildingAdd() {
		return oilBuildingAdd;
	}

	public void setOilBuildingAdd(Integer oilBuildingAdd) {
		this.oilBuildingAdd = oilBuildingAdd;
	}

	public Integer getOilTechAdd() {
		return oilTechAdd;
	}

	public void setOilTechAdd(Integer oilTechAdd) {
		this.oilTechAdd = oilTechAdd;
	}

	public Integer getOilFieldAdd() {
		return oilFieldAdd;
	}

	public void setOilFieldAdd(Integer oilFieldAdd) {
		this.oilFieldAdd = oilFieldAdd;
	}

	public Integer getOilOfficerAdd() {
		return oilOfficerAdd;
	}

	public void setOilOfficerAdd(Integer oilOfficerAdd) {
		this.oilOfficerAdd = oilOfficerAdd;
	}

	public Integer getOilGuildAdd() {
		return oilGuildAdd;
	}

	public void setOilGuildAdd(Integer oilGuildAdd) {
		this.oilGuildAdd = oilGuildAdd;
	}

	public Integer getOilTreasureAdd() {
		return oilTreasureAdd;
	}

	public void setOilTreasureAdd(Integer oilTreasureAdd) {
		this.oilTreasureAdd = oilTreasureAdd;
	}

	public Long getOilConsume() {
		return oilConsume;
	}

	public void setOilConsume(Long oilConsume) {
		this.oilConsume = oilConsume;
	}

	public Long getFoodNum() {
		return foodNum;
	}

	public void setFoodNum(Long foodNum) {
		this.foodNum = foodNum;
	}

	public Long getFoodOutput() {
		return foodOutput;
	}

	public void setFoodOutput(Long foodOutput) {
		this.foodOutput = foodOutput;
	}

	public Integer getFoodWorkerNum() {
		return foodWorkerNum;
	}

	public void setFoodWorkerNum(Integer foodWorkerNum) {
		this.foodWorkerNum = foodWorkerNum;
	}

	public Integer getFoodBuildingAdd() {
		return foodBuildingAdd;
	}

	public void setFoodBuildingAdd(Integer foodBuildingAdd) {
		this.foodBuildingAdd = foodBuildingAdd;
	}

	public Integer getFoodTechAdd() {
		return foodTechAdd;
	}

	public void setFoodTechAdd(Integer foodTechAdd) {
		this.foodTechAdd = foodTechAdd;
	}

	public Integer getFoodFieldAdd() {
		return foodFieldAdd;
	}

	public void setFoodFieldAdd(Integer foodFieldAdd) {
		this.foodFieldAdd = foodFieldAdd;
	}

	public Integer getFoodOfficerAdd() {
		return foodOfficerAdd;
	}

	public void setFoodOfficerAdd(Integer foodOfficerAdd) {
		this.foodOfficerAdd = foodOfficerAdd;
	}

	public Integer getFoodGuildAdd() {
		return foodGuildAdd;
	}

	public void setFoodGuildAdd(Integer foodGuildAdd) {
		this.foodGuildAdd = foodGuildAdd;
	}

	public Integer getFoodTreasureAdd() {
		return foodTreasureAdd;
	}

	public void setFoodTreasureAdd(Integer foodTreasureAdd) {
		this.foodTreasureAdd = foodTreasureAdd;
	}

	public Long getFoodConsume() {
		return foodConsume;
	}

	public void setFoodConsume(Long foodConsume) {
		this.foodConsume = foodConsume;
	}

	public Long getMoneyNum() {
		return moneyNum;
	}

	public void setMoneyNum(Long moneyNum) {
		this.moneyNum = moneyNum;
	}

	public Long getMoneyOutput() {
		return moneyOutput;
	}

	public void setMoneyOutput(Long moneyOutput) {
		this.moneyOutput = moneyOutput;
	}

	public Integer getMoneyTechAdd() {
		return moneyTechAdd;
	}

	public void setMoneyTechAdd(Integer moneyTechAdd) {
		this.moneyTechAdd = moneyTechAdd;
	}

	public Integer getMoneyFieldAdd() {
		return moneyFieldAdd;
	}

	public void setMoneyFieldAdd(Integer moneyFieldAdd) {
		this.moneyFieldAdd = moneyFieldAdd;
	}

	public Integer getMoneyOfficerAdd() {
		return moneyOfficerAdd;
	}

	public void setMoneyOfficerAdd(Integer moneyOfficerAdd) {
		this.moneyOfficerAdd = moneyOfficerAdd;
	}

	public Integer getMoneyGuildAdd() {
		return moneyGuildAdd;
	}

	public void setMoneyGuildAdd(Integer moneyGuildAdd) {
		this.moneyGuildAdd = moneyGuildAdd;
	}

	public Integer getMoneyTreasureAdd() {
		return moneyTreasureAdd;
	}

	public void setMoneyTreasureAdd(Integer moneyTreasureAdd) {
		this.moneyTreasureAdd = moneyTreasureAdd;
	}

	public Long getMoneyConsume() {
		return moneyConsume;
	}

	public void setMoneyConsume(Long moneyConsume) {
		this.moneyConsume = moneyConsume;
	}

}
