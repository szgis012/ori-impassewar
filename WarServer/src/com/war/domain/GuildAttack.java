package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class GuildAttack implements Serializable {

	private static final long serialVersionUID = 5041419131070057942L;

	/** 工会攻击编号 */
	private Integer guildAttackID;
	/** 工会编号 */
	private Integer guildID;
	/** 类型(1.进攻 2.防守) */
	private Integer type;
	/** 事件描述 */
	private String description;
	/** 对方工会编号 */
	private Integer targetGuildID;
	/** 创建时间 */
	private Date createTime;
	/** 对方工会信息 */
	private Guild targetGuild;
	
	public Integer getGuildAttackID() {
		return guildAttackID;
	}

	public void setGuildAttackID(Integer guildAttackID) {
		this.guildAttackID = guildAttackID;
	}
	
	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getTargetGuildID() {
		return targetGuildID;
	}

	public void setTargetGuildID(Integer targetGuildID) {
		this.targetGuildID = targetGuildID;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Guild getTargetGuild() {
		return targetGuild;
	}

	public void setTargetGuild(Guild targetGuild) {
		this.targetGuild = targetGuild;
	}

}