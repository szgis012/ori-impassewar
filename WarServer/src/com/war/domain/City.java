package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class City implements Serializable {

	private static final long serialVersionUID = 8608352904459732090L;
	
	/** 城市编号 */
	private Integer cityID;
	/** 玩家编号 */
	private Integer playerID;
	/** 地图编号 */
	private Integer mapID;
	/** X坐标 */
	private Integer posX;
	/** Y坐标 */
	private Integer posY;
	/** 城市名称 */
	private String name;
	/** 城市状态(0.新手 1.正常 2.免战 3.封停) */
	private Integer state;
	/** 建筑点数 */
	private Long constructionPoint;
	/** 科技点数 */
	private Long technologyPoint;
	/** 空闲人口 */
	private Long populationFree;
	/** 当前总人口 */
	private Long populationTotal;
	/** 人口上限 */
	private Long populationMax;
	/** 新兵数量 */
	private Integer recruitNum;
	/** 税收 */
	private Integer tax;
	/** 治安 */
	private Integer security;
	/** 执政官 */
	private Integer officer;
	/** 留守军队 */
	private Integer defensiveMilitary;
	/** 空闲商人数量 */
	private Integer businessmanFree;
	/** 创建时间 */
	private Date createTime;
	/** 城市资源 */
	private CityResource cityResource;

	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}
	
	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Long getConstructionPoint() {
		return constructionPoint;
	}

	public void setConstructionPoint(Long constructionPoint) {
		this.constructionPoint = constructionPoint;
	}
	
	public Long getTechnologyPoint() {
		return technologyPoint;
	}

	public void setTechnologyPoint(Long technologyPoint) {
		this.technologyPoint = technologyPoint;
	}
	
	public Long getPopulationFree() {
		return populationFree;
	}

	public void setPopulationFree(Long populationFree) {
		this.populationFree = populationFree;
	}
	
	public Long getPopulationTotal() {
		return populationTotal;
	}

	public void setPopulationTotal(Long populationTotal) {
		this.populationTotal = populationTotal;
	}

	public Long getPopulationMax() {
		return populationMax;
	}

	public void setPopulationMax(Long populationMax) {
		this.populationMax = populationMax;
	}
	
	public Integer getRecruitNum() {
		return recruitNum;
	}

	public void setRecruitNum(Integer recruitNum) {
		this.recruitNum = recruitNum;
	}
	
	public Integer getTax() {
		return tax;
	}

	public void setTax(Integer tax) {
		this.tax = tax;
	}
	
	public Integer getSecurity() {
		return security;
	}

	public void setSecurity(Integer security) {
		this.security = security;
	}
	
	public Integer getOfficer() {
		return officer;
	}

	public void setOfficer(Integer officer) {
		this.officer = officer;
	}
	
	public Integer getDefensiveMilitary() {
		return defensiveMilitary;
	}

	public void setDefensiveMilitary(Integer defensiveMilitary) {
		this.defensiveMilitary = defensiveMilitary;
	}
	
	public Integer getBusinessmanFree() {
		return businessmanFree;
	}

	public void setBusinessmanFree(Integer businessmanFree) {
		this.businessmanFree = businessmanFree;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public CityResource getCityResource() {
		return cityResource;
	}

	public void setCityResource(CityResource cityResource) {
		this.cityResource = cityResource;
	}
	
}