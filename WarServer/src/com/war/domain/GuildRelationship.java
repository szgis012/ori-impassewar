package com.war.domain;

import java.io.Serializable;

public class GuildRelationship implements Serializable {

	private static final long serialVersionUID = 1349892326220704583L;

	/** 工会关系编号 */
	private Integer guildRelationshipID;
	/** 工会编号 */
	private Integer guildID;
	/** 目标工会编号 */
	private Integer targetGuildID;
	/** 类型(1.友好 2.中立 3.敌对) */
	private Integer type;
	/** 目标工会信息 */
	private Guild targetGuild;

	public Integer getGuildRelationshipID() {
		return guildRelationshipID;
	}

	public void setGuildRelationshipID(Integer guildRelationshipID) {
		this.guildRelationshipID = guildRelationshipID;
	}

	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}

	public Integer getTargetGuildID() {
		return targetGuildID;
	}

	public void setTargetGuildID(Integer targetGuildID) {
		this.targetGuildID = targetGuildID;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Guild getTargetGuild() {
		return targetGuild;
	}

	public void setTargetGuild(Guild targetGuild) {
		this.targetGuild = targetGuild;
	}

}