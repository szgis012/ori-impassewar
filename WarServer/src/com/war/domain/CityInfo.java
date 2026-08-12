package com.war.domain;

import java.io.Serializable;

public class CityInfo implements Serializable {

	private static final long serialVersionUID = -3138682403585322953L;

	/** 城市编号 */
	private Integer cityID;
	/** 玩家编号 */
	private Integer playerID;
	/** 玩家名称 */
	private String playerName;
	/** 国家 */
	private Integer country;
	/** 城市名称 */
	private String name;
	/** X坐标 */
	private Integer posX;
	/** Y坐标 */
	private Integer posY;
	/** 建筑点数 */
	private Long constructionPoint;
	/** 科技点数 */
	private Long technologyPoint;
	/** 人口 */
	private Long population;
	/** 执政官 */
	private Integer officer;
	/** 留守军队 */
	private Integer defensiveMilitary;

	
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
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

}
