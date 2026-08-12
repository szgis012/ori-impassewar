package com.war.domain;

import java.io.Serializable;

public class CityTechnology implements Serializable {

	private static final long serialVersionUID = 3949032091680801918L;
	
	/** 城市科技编号 */
	private Integer cityTechnologyID;
	/** 城市编号 */
	private Integer cityID;
	/** 科技编号 */
	private Integer technologyID;
	/** 科技等级 */
	private Integer level;
	/** 状态(1.正常 2.升级中) */
	private Integer state;
	/** 科技信息 */
	private Technology technology;

	public Integer getCityTechnologyID() {
		return cityTechnologyID;
	}

	public void setCityTechnologyID(Integer cityTechnologyID) {
		this.cityTechnologyID = cityTechnologyID;
	}
	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getTechnologyID() {
		return technologyID;
	}

	public void setTechnologyID(Integer technologyID) {
		this.technologyID = technologyID;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}
	

}