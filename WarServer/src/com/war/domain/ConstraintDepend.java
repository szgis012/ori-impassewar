package com.war.domain;

import java.io.Serializable;
import java.util.List;

public class ConstraintDepend implements Serializable {

	private static final long serialVersionUID = -2622283738442337774L;
	
	/** 约束依赖编号 */
	private Integer constraintDependID;
	/** 等级 */
	private Integer level;
	/** 建造目标(目标对象主键) */
	private Integer targetID;
	/** 条件类型(1.建筑 2. 科技 3.士兵) */
	private Integer type;
	/** 描述 */
	private String description;
	/** 前置建筑(建筑编号,建筑等级;) */
	private String preBuildings;
	/** 花费人口 */
	private Long costPopulation;
	/** 花费木材 */
	private Long costWood;
	/** 花费钢铁 */
	private Long costSteel;
	/** 花费石油 */
	private Long costOil;
	/** 花费食物 */
	private Long costFood;
	/** 花费金钱 */
	private Long costMoney;
	/** 花费时间 */
	private Long costTime;
	/** 前置建筑列表 */
	private List<PreBuilding> preBuildingList;

	public Integer getConstraintDependID() {
		return constraintDependID;
	}

	public void setConstraintDependID(Integer constraintDependID) {
		this.constraintDependID = constraintDependID;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getTargetID() {
		return targetID;
	}

	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPreBuildings() {
		return preBuildings;
	}

	public void setPreBuildings(String preBuildings) {
		this.preBuildings = preBuildings;
	}
	
	public Long getCostPopulation() {
		return costPopulation;
	}

	public void setCostPopulation(Long costPopulation) {
		this.costPopulation = costPopulation;
	}
	
	public Long getCostWood() {
		return costWood;
	}

	public void setCostWood(Long costWood) {
		this.costWood = costWood;
	}
	
	public Long getCostSteel() {
		return costSteel;
	}

	public void setCostSteel(Long costSteel) {
		this.costSteel = costSteel;
	}
	
	public Long getCostOil() {
		return costOil;
	}

	public void setCostOil(Long costOil) {
		this.costOil = costOil;
	}
	
	public Long getCostFood() {
		return costFood;
	}

	public void setCostFood(Long costFood) {
		this.costFood = costFood;
	}
	
	public Long getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(Long costMoney) {
		this.costMoney = costMoney;
	}
	
	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	public List<PreBuilding> getPreBuildingList() {
		return preBuildingList;
	}

	public void setPreBuildingList(List<PreBuilding> preBuildingList) {
		this.preBuildingList = preBuildingList;
	}
}