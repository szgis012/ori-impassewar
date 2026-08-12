package com.war.domain;


import java.io.Serializable;


/**
 * 军械信息
 *
 * @author ghleed
 * @version 1.0
 */
public class Ordnance implements Serializable {

	private static final long serialVersionUID = -1139121506420122779L;
	
	/** 军械编号 */
	private Integer ordnanceID;
	/** 约束依赖编号 */
	private Integer constraintDependID;
	/** 军械名称 */
	private String name;
	/** 军械图片 */
	private String image;
	/** 军械描述信息 */
	private String description;
	/** 军械类型 OrdnanceTypeConstant中定义*/
	private Integer type;
	/** 对应的约束依赖对象*/
	private ConstraintDepend constraintDepend;

	public Integer getOrdnanceID() {
		return ordnanceID;
	}

	public void setOrdnanceID(Integer ordnanceID) {
		this.ordnanceID = ordnanceID;
	}
	public Integer getConstraintDependID() {
		return constraintDependID;
	}

	public void setConstraintDependID(Integer constraintDependID) {
		this.constraintDependID = constraintDependID;
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