package com.war.domain;

import java.io.Serializable;

public class PlayerRank implements Serializable {

	private static final long serialVersionUID = 5633211144265334626L;
	
	/** 排名 */
	private Integer rank;
	/** 玩家编号 */
	private Integer playerID;
	/** 名称 */
	private String name;
	/** 城市名称 */
	private String cityName;
	/** 头衔 */
	private String honor;
	/** 声望 */
	private Long renown;
	/** 工会编号 */
	private Integer guildID;
	/** 工会名称 */
	private String guildName;
	/** 国家 */
	private Integer country;
	
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public Long getRenown() {
		return renown;
	}

	public void setRenown(Long renown) {
		this.renown = renown;
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

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

}
