package com.war.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Guild implements Serializable {

	private static final long serialVersionUID = 7441036911453026287L;
	
	/** 工会编号 */
	private Integer guildID;
	/** 工会名称 */
	private String name;
	/** 等级 */
	private Integer level;
	/** 工会图片 */
	private String image;
	/** 创建玩家编号 */
	private Integer chairmanID;
	/** 声望 */
	private Long renown;
	/** 员官数量 */
	private Integer officialNum;
	/** 人数 */
	private Integer population;
	/** 人数上限 */
	private Integer populationMax;
	/** 金钱 */
	private Long money;
	/** 士官军旗数量 */
	private Integer oriflammeLowerNum;
	/** 校官军旗数量 */
	private Integer oriflammeIntermediateNum;
	/** 元帅军旗数量 */
	private Integer oriflammeAdvancedNum;
	/** 排名 */
	private Integer rank;
	/** 工会介绍 */
	private String introduction;
	/** 工会公告 */
	private String notice;
	/** 友好工会列表 */
	private List<GuildRelationship> friendlyGuildList;
	/** 中立工会列表 */
	private List<GuildRelationship> neutralGuildList;
	/** 敌对工会列表 */
	private List<GuildRelationship> hostileGuildList;
	/** 创建时间 */
	private Date createTime;
	/** 创建玩家信息 */
	private Player chairman;

	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Integer getChairmanID() {
		return chairmanID;
	}

	public void setChairmanID(Integer chairmanID) {
		this.chairmanID = chairmanID;
	}
	
	public Long getRenown() {
		return renown;
	}

	public void setRenown(Long renown) {
		this.renown = renown;
	}
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}
	
	public Integer getPopulationMax() {
		return populationMax;
	}

	public void setPopulationMax(Integer populationMax) {
		this.populationMax = populationMax;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	public List<GuildRelationship> getFriendlyGuildList() {
		return friendlyGuildList;
	}

	public void setFriendlyGuildList(List<GuildRelationship> friendlyGuildList) {
		this.friendlyGuildList = friendlyGuildList;
	}

	public List<GuildRelationship> getNeutralGuildList() {
		return neutralGuildList;
	}

	public void setNeutralGuildList(List<GuildRelationship> neutralGuildList) {
		this.neutralGuildList = neutralGuildList;
	}

	public List<GuildRelationship> getHostileGuildList() {
		return hostileGuildList;
	}

	public void setHostileGuildList(List<GuildRelationship> hostileGuildList) {
		this.hostileGuildList = hostileGuildList;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Player getChairman() {
		return chairman;
	}

	public void setChairman(Player chairman) {
		this.chairman = chairman;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getOfficialNum() {
		return officialNum;
	}

	public void setOfficialNum(Integer officialNum) {
		this.officialNum = officialNum;
	}
	
}