package com.war.domain;


import java.io.Serializable;
import java.util.List;


/**
 * 兵种
 *
 * @author ghleed
 * @version 1.0
 */
public class Army implements Serializable {
	
	private static final long serialVersionUID = 1650992921832799244L;
	
	/** 兵种编号 */
	private Integer armyID;
	/** 兵种名称 */
	private String name;
	/** 图片 */
	private String image;
	/** 兵种的描述 */
	private String description;
	/** 生命值 */
	private Integer life;
	/** 攻击值 */
	private Integer attack;
	/** 防御值 */
	private Integer defense;
	/** 攻击范围 */
	private Integer range;
	/** 移动速度 */
	private Integer speed;
	/** 负重 */
	private Integer carry;
	/** 消耗食物 */
	private Integer costFood;
	/** 消耗军费 */
	private Integer costMoney;
	/** 消耗石油 */
	private Integer costOil;
	/** 所占人口 */
	private Integer population;
	/** 攻击类型 */
	private Integer attackType;
	/** 防御类型 */
	private Integer defenseType;
	/** 兵种ArmyTypeConstant中定义 */
	private Integer type;
	/** 兵种招募所需军械条件列表 */
	private List<ArmyDepend> armyDependList;
	/** 兵种招募所需的前提建筑，科技条件*/
	private ConstraintDepend constraintDepend;

	public Integer getArmyID() {
		return armyID;
	}

	public void setArmyID(Integer armyID) {
		this.armyID = armyID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}
	
	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}
	
	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	public Integer getCarry() {
		return carry;
	}

	public void setCarry(Integer carry) {
		this.carry = carry;
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
	
	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}
	
	public Integer getAttackType() {
		return attackType;
	}

	public void setAttackType(Integer attackType) {
		this.attackType = attackType;
	}
	
	public Integer getDefenseType() {
		return defenseType;
	}

	public void setDefenseType(Integer defenseType) {
		this.defenseType = defenseType;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<ArmyDepend> getArmyDependList() {
		return armyDependList;
	}

	public void setArmyDependList(List<ArmyDepend> armyDependList) {
		this.armyDependList = armyDependList;
	}

	public Integer getCostOil() {
		return costOil;
	}

	public void setCostOil(Integer costOil) {
		this.costOil = costOil;
	}

	public ConstraintDepend getConstraintDepend() {
		return constraintDepend;
	}

	public void setConstraintDepend(ConstraintDepend constraintDepend) {
		this.constraintDepend = constraintDepend;
	}

}