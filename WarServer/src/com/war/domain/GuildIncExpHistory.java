package com.war.domain;
import java.io.Serializable;
import java.util.Date;

public class GuildIncExpHistory implements Serializable {

	private static final long serialVersionUID = -8874463012330335104L;
	/** 军团收入支出历史编号 */
	private Integer guildIncExpHistoryID;
	/** 军团编号 */
	private Integer guildID;
	/** 军团玩家编号 */
	private Integer playerID;
	/** 金钱 */
	private Long money;
	/** 低级军旗数量 */
	private Integer oriflammeLowerNum;
	/** 中级军旗数量 */
	private Integer oriflammeIntermediateNum;
	/** 高级军旗数量 */
	private Integer oriflammeAdvancedNum;
	/** 类型(1.捐献 2.支出) */
	private Integer type;
	/** 创建时间 */
	private Date createTime;

	public Integer getGuildIncExpHistoryID() {
		return guildIncExpHistoryID;
	}

	public void setGuildIncExpHistoryID(Integer guildIncExpHistoryID) {
		this.guildIncExpHistoryID = guildIncExpHistoryID;
	}

	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Integer getOriflammeLowerNum() {
		return oriflammeLowerNum;
	}

	public void setOriflammeLowerNum(Integer oriflammeLowerNum) {
		this.oriflammeLowerNum = oriflammeLowerNum;
	}

	public Integer getOriflammeIntermediateNum() {
		return oriflammeIntermediateNum;
	}

	public void setOriflammeIntermediateNum(Integer oriflammeIntermediateNum) {
		this.oriflammeIntermediateNum = oriflammeIntermediateNum;
	}

	public Integer getOriflammeAdvancedNum() {
		return oriflammeAdvancedNum;
	}

	public void setOriflammeAdvancedNum(Integer oriflammeAdvancedNum) {
		this.oriflammeAdvancedNum = oriflammeAdvancedNum;
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

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}


}