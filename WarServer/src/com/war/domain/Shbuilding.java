package com.war.domain;


import java.io.Serializable;

/**
 * 要塞建筑
 *
 * @author ghleed
 * @version 1.0
 */
public class Shbuilding implements Serializable {

	private static final long serialVersionUID = 7380737377406967678L;
	
	/** 要塞建筑编号 */
	private Integer shbuildingID;
	/** 建筑名称 */
	private String name;
	/** 图片 */
	private String image;
	/** 最高等级 */
	private Integer maxLevel;
	/** 描述 */
	private String description;
	/** 是否唯一(1.是 2.否) */
	private Integer isonlyone;

	public Integer getShbuildingID() {
		return shbuildingID;
	}

	public void setShbuildingID(Integer shbuildingID) {
		this.shbuildingID = shbuildingID;
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
	public Integer getIsonlyone() {
		return isonlyone;
	}

	public void setIsonlyone(Integer isonlyone) {
		this.isonlyone = isonlyone;
	}

}
