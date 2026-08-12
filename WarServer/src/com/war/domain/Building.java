package com.war.domain;

import java.io.Serializable;

public class Building implements Serializable {

	private static final long serialVersionUID = 8831796739311158013L;
	
	/** 建筑编号 */
	private Integer buildingID;
	/** 建筑名称 */
	private String name;
	/** 图片 */
	private String image;
	/** 最高等级 */
	private Integer maxLevel;
	/** 描述 */
	private String description;
	/** 是否唯一(1.是 2.否) */
	private Integer isOnlyone;
	/** 当前级别对应的约束依赖 */
	private ConstraintDepend constraintDepend;
	/** 下一等级对应的约束依赖 */
	private ConstraintDepend nextConstraintDepend;

	public Integer getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(Integer buildingID) {
		this.buildingID = buildingID;
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
	
	public Integer getIsOnlyone() {
		return isOnlyone;
	}

	public void setIsOnlyone(Integer isOnlyone) {
		this.isOnlyone = isOnlyone;
	}

	public ConstraintDepend getConstraintDepend() {
		return constraintDepend;
	}

	public void setConstraintDepend(ConstraintDepend constraintDepend) {
		this.constraintDepend = constraintDepend;
	}

	public ConstraintDepend getNextConstraintDepend() {
		return nextConstraintDepend;
	}

	public void setNextConstraintDepend(ConstraintDepend nextConstraintDepend) {
		this.nextConstraintDepend = nextConstraintDepend;
	}


}