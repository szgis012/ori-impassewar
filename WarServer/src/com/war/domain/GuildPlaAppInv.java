package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class GuildPlaAppInv implements Serializable {

	private static final long serialVersionUID = -5578432243435455637L;
	
	/** 工会编号 */
	private Integer guildID;
	/** 玩家编号 */
	private Integer playerID;
	/** 类型(1.申请加入 2.邀请加入) */
	private Integer type;
	/** 创建时间 */
	private Date createTime;
	/** 工会信息 */
	private Guild guild;
	/** 玩家信息 */
	private Player player;

	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Guild getGuild() {
		return guild;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}