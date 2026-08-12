package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class GuildPlayer implements Serializable {

	private static final long serialVersionUID = -3751514506303155259L;
	
	/** 军团玩家编号 **/
	private Integer guildPlayerID;
	/** 军团编号 */
	private Integer guildID;
	/** 玩家编号 */
	private Integer playerID;
	/** 贡献度 */
	private Long contribution;
	/** 捐献金钱数量 */
	private Integer subscribeMoney;
	/** 捐献士官军旗数量 */
	private Integer subscribeOriflammeLowerNum;
	/** 捐献校官军旗数量 */
	private Integer subscribeOriflammeIntermediateNum;
	/** 捐献量元帅军旗数量 */
	private Integer subscribeOriflammeAdvancedNum;
	/** 职务名称 */
	private String dutyName;
	/** 成员权限 */
	private String permission;
	/** 允许驻军(0.不允许 1.允许) */
	private Integer allowGarrison;
	/** 创建时间 */
	private Date createTime;
	/** 玩家信息 */
	private Player player;
	
	private GuildIncExpHistory guildIncExpHistory;

	public GuildIncExpHistory getGuildIncExpHistory() {
		return guildIncExpHistory;
	}
	
	public void setGuildIncExpHistory(GuildIncExpHistory guildIncExpHistory) {
		this.guildIncExpHistory = guildIncExpHistory;
	}

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
	
	public Long getContribution() {
		return contribution;
	}

	public void setContribution(Long contribution) {
		this.contribution = contribution;
	}
	
	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getSubscribeMoney() {
		return subscribeMoney;
	}

	public void setSubscribeMoney(Integer subscribeMoney) {
		this.subscribeMoney = subscribeMoney;
	}

	public Integer getSubscribeOriflammeLowerNum() {
		return subscribeOriflammeLowerNum;
	}

	public void setSubscribeOriflammeLowerNum(Integer subscribeOriflammeLowerNum) {
		this.subscribeOriflammeLowerNum = subscribeOriflammeLowerNum;
	}

	public Integer getSubscribeOriflammeIntermediateNum() {
		return subscribeOriflammeIntermediateNum;
	}

	public void setSubscribeOriflammeIntermediateNum(
			Integer subscribeOriflammeIntermediateNum) {
		this.subscribeOriflammeIntermediateNum = subscribeOriflammeIntermediateNum;
	}

	public Integer getSubscribeOriflammeAdvancedNum() {
		return subscribeOriflammeAdvancedNum;
	}

	public void setSubscribeOriflammeAdvancedNum(
			Integer subscribeOriflammeAdvancedNum) {
		this.subscribeOriflammeAdvancedNum = subscribeOriflammeAdvancedNum;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getAllowGarrison() {
		return allowGarrison;
	}

	public void setAllowGarrison(Integer allowGarrison) {
		this.allowGarrison = allowGarrison;
	}

	public Integer getGuildPlayerID() {
		return guildPlayerID;
	}

	public void setGuildPlayerID(Integer guildPlayerID) {
		this.guildPlayerID = guildPlayerID;
	}
}