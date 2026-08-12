package com.war.domain;

import java.io.Serializable;

public class GuildTechnology implements Serializable {

	 /**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1243418929539416875L;
	/** 军团科技编号 */
	private Integer guildtechnologyID;
	/** 名称 */
	private String name;
	/** 科技描述 */
	private String description;
	/** 最高级别 */
	private Integer maxLevel;
	/** 类型(1.资源类) */
	private Integer type;
	/** 军团科技对象 */
	private GuildTechnologyGuild guildTechnologyGuild;
	/** 军团花费对象 */
	private GuildtechnologyCost guildtechnologyCost;
	
	/**
	 * @return the guildtechnologyCost
	 */
	public GuildtechnologyCost getGuildtechnologyCost() {
		return guildtechnologyCost;
	}

	/**
	 * @param guildtechnologyCost the guildtechnologyCost to set
	 */
	public void setGuildtechnologyCost(GuildtechnologyCost guildtechnologyCost) {
		this.guildtechnologyCost = guildtechnologyCost;
	}

	public Integer getGuildtechnologyID() {
		return guildtechnologyID;
	}

	public void setGuildtechnologyID(Integer guildtechnologyID) {
		this.guildtechnologyID = guildtechnologyID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public GuildTechnologyGuild getGuildTechnologyGuild() {
		return guildTechnologyGuild;
	}

	public void setGuildTechnologyGuild(GuildTechnologyGuild guildTechnologyGuild) {
		this.guildTechnologyGuild = guildTechnologyGuild;
	}


}