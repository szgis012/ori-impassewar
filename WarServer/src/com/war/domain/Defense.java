package com.war.domain;

import java.io.Serializable;

public class Defense implements Serializable {

	private static final long serialVersionUID = 4983595750664366836L;

	/** 城防编号 */
	private Integer defenseID;
	/** 建筑名称 */
	private String name;
	/** 图片 */
	private String image;
	/** 描述 */
	private String description;
	/** 生命 */
	private Integer life;
	/** 攻击 */
	private Integer attack;
	/** 防御 */
	private Integer defense;
	/** 攻击范围 */
	private Integer range;

	/** 当前级别对应的约束依赖 */
	private ConstraintDepend constraintDepend;

	public Integer getDefenseID() {
		return defenseID;
	}

	public void setDefenseID(Integer defenseID) {
		this.defenseID = defenseID;
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

	public ConstraintDepend getConstraintDepend() {
		return constraintDepend;
	}

	public void setConstraintDepend(ConstraintDepend constraintDepend) {
		this.constraintDepend = constraintDepend;
	}

}
