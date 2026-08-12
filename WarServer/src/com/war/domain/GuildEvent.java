package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class GuildEvent implements Serializable {

	private static final long serialVersionUID = 7704246650976723082L;

	/** 工会事件编号 */
	private Integer guildEventID;
	/** 工会编号 */
	private Integer guildID;
	/** 事件描述 */
	private String description;
	/** 创建时间 */
	private Date createTime;

	public Integer getGuildEventID() {
		return guildEventID;
	}

	public void setGuildEventID(Integer guildEventID) {
		this.guildEventID = guildEventID;
	}
	
	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}