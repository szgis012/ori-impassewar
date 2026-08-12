package com.war.domain;

import java.io.Serializable;

public class GuildTechnologyGuild implements Serializable {

	 /**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8022800611601093797L;
	/** 军团科技编号 */
	private Integer guildTechnologyguildID;
	/** 军团编号 */
	private Integer guildID;
	/** 科技编号 */
	private Integer guildtechnologyID;
	/** 等级 */
	private Integer level;
	/** 状态(1.正常 2.升级中) */
	private Integer state;


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

	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}

	public Integer getGuildTechnologyguildID() {
		return guildTechnologyguildID;
	}

	public void setGuildTechnologyguildID(Integer guildTechnologyguildID) {
		this.guildTechnologyguildID = guildTechnologyguildID;
	}

	public Integer getGuildtechnologyID() {
		return guildtechnologyID;
	}

	public void setGuildtechnologyID(Integer guildtechnologyID) {
		this.guildtechnologyID = guildtechnologyID;
	}


}