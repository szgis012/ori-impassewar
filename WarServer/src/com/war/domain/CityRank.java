package com.war.domain;

import java.io.Serializable;

public class CityRank implements Serializable {

	private static final long serialVersionUID = 5153271234232800613L;
	
	/** 排名 */
	private Integer rank;
	/** 城市编号 */
	private Integer cityID;
	/** 名称 */
	private String name;
	/** 玩家编号 */
	private Integer playerID;
	/** 玩家名称 */
	private String playerName;
	/** 工会编号 */
	private Integer guildID;
	/** 工会名称 */
	private String guildName;
	/** 建筑点数 */
	private Long constructionPoint;
	/** 科技点数 */
	private Long technologyPoint;
	/** 当前人口 */
	private Long populationTotal;
	
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
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

	public Long getPopulationTotal() {
		return populationTotal;
	}

	public void setPopulationTotal(Long populationTotal) {
		this.populationTotal = populationTotal;
	}

}
