package com.war.domain;

import java.io.Serializable;

public class GuildRank implements Serializable {

	private static final long serialVersionUID = 4325210739054909355L;
	
	/** 排名 */
	private Integer rank;
	/** 工会编号 */
	private Integer guildID;
	/** 工会名称 */
	private String name;
	/** 创建玩家编号 */
	private Integer chairmanID;
	/** 创建玩家名称 */
	private String chairmanName;
	/** 人数 */
	private Integer population;
	/** 人数上限 */
	private Integer populationMax;
	/** 声望 */
	private Integer renown;
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getGuildID() {
		return guildID;
	}
	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getChairmanID() {
		return chairmanID;
	}
	public void setChairmanID(Integer chairmanID) {
		this.chairmanID = chairmanID;
	}
	public String getChairmanName() {
		return chairmanName;
	}
	public void setChairmanName(String chairmanName) {
		this.chairmanName = chairmanName;
	}
	public Integer getPopulation() {
		return population;
	}
	public void setPopulation(Integer population) {
		this.population = population;
	}
	public Integer getPopulationMax() {
		return populationMax;
	}
	public void setPopulationMax(Integer populationMax) {
		this.populationMax = populationMax;
	}
	public Integer getRenown() {
		return renown;
	}
	public void setRenown(Integer renown) {
		this.renown = renown;
	}
	
}
