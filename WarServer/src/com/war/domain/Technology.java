package com.war.domain;

import java.io.Serializable;

public class Technology implements Serializable {

	private static final long serialVersionUID = -8954220136400095605L;
	
	/** 科技编号 */
	private Integer technologyID;
	/** 科技名称 */
	private String name;
	/** 图片 */
	private String image;
	/** 最高等级 */
	private Integer maxLevel;
	/** 科技描述 */
	private String description;
	/** 类型(1.生产 2.士兵 3.车辆 4.飞机 5.其他) */
	private Integer type;
	/** 约束依赖 */
	private ConstraintDepend constraintDepend;

	public Integer getTechnologyID() {
		return technologyID;
	}

	public void setTechnologyID(Integer technologyID) {
		this.technologyID = technologyID;
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
	
	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ConstraintDepend getConstraintDepend() {
		return constraintDepend;
	}

	public void setConstraintDepend(ConstraintDepend constraintDepend) {
		this.constraintDepend = constraintDepend;
	}

}