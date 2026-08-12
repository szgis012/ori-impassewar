package com.war.domain;

import java.io.Serializable;

public class GuildtechnologyCost implements Serializable {

	 /**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -251653627916828999L;
	/** 军团科技花费编号 */
	private Integer guildtechnologyCostID;
	/** 军团科技编号 */
	private Integer guildtechnologyID;
	/** 等级 */
	private Integer level;
	/** 描述 */
	private String description;
	/** 需求军团等级 */
	private Integer requiredGuildLevel;
	/** 花费金钱 */
	private Long money;
	/** 花费低级军旗数量 */
	private Integer oriflammeLowerNum;
	/** 花费中级军旗数量 */
	private Integer oriflammeIntermediateNum;
	/** 花费高级军旗数量 */
	private Integer oriflammeAdvancedNum;
	/** 花费时间(小时) */
	private Integer time;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getRequiredGuildLevel() {
		return requiredGuildLevel;
	}
	
	public void setRequiredGuildLevel(Integer requiredGuildLevel) {
		this.requiredGuildLevel = requiredGuildLevel;
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

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getGuildtechnologyCostID() {
		return guildtechnologyCostID;
	}

	public void setGuildtechnologyCostID(Integer guildtechnologyCostID) {
		this.guildtechnologyCostID = guildtechnologyCostID;
	}

	public Integer getGuildtechnologyID() {
		return guildtechnologyID;
	}

	public void setGuildtechnologyID(Integer guildtechnologyID) {
		this.guildtechnologyID = guildtechnologyID;
	}


}