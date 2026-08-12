package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable {

	private static final long serialVersionUID = 5516150665286201358L;
	
	/** 玩家编号 */
	private Integer playerID;
	/** 用户编号 */
	private String userName;
	/** 玩家名称 */
	private String name;
	/** 玩家头像*/
	private String headImg;
	/** 军衔编号 */
	private Integer honorID;
	/** 军衔名称 */
	private String honorName;
	/** 工会编号 */
	private Integer guildID;
	/** 国家 ContryTypeConstant中定义*/
	private Integer country;
	/** 声望 */
	private Long renown;
	/** 进攻点数 */
	private double attackPoint;
	/** 防御点数 */
	private double defensePoint;
	/** 排名 */
	private Integer rank;
	/** 金钱 */
	private Integer money;
	/** 礼金数量 */
	private Integer giftCertificate;
	/** 是否已领取每日登录奖励(0.否 1.是) */
	private Integer haveReceiveDailyreward;
	/** 玩家状态：在PlayerStateConstant中定义*/
	private Integer state;
	/** 登录次数*/
	private Integer loginNum;
	/** 在线时间(单位:分钟) */
	private Integer onlineTime;
	/** 最后登陆时间*/
	private Date lastLoginTime;
	/** 城市信息 */
	private City city;
	/** 工会名称 */
	private String guildName;
	/** 创建时间 */
	private Date createTime;
	
	
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getHonorID() {
		return honorID;
	}

	public void setHonorID(Integer honorID) {
		this.honorID = honorID;
	}
	
	public String getHonorName() {
		return honorName;
	}

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}
	
	public Integer getGuildID() {
		return guildID;
	}

	public void setGuildID(Integer guildID) {
		this.guildID = guildID;
	}
	
	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}
	
	public Long getRenown() {
		return renown;
	}

	public void setRenown(Long renown) {
		this.renown = renown;
	}
	
	public double getAttackPoint() {
		return attackPoint;
	}

	public void setAttackPoint(double attackPoint) {
		this.attackPoint = attackPoint;
	}
	
	public double getDefensePoint() {
		return defensePoint;
	}

	public void setDefensePoint(double defensePoint) {
		this.defensePoint = defensePoint;
	}
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getGiftCertificate() {
		return giftCertificate;
	}

	public void setGiftCertificate(Integer giftCertificate) {
		this.giftCertificate = giftCertificate;
	}

	public Integer getHaveReceiveDailyreward() {
		return haveReceiveDailyreward;
	}

	public void setHaveReceiveDailyreward(Integer haveReceiveDailyreward) {
		this.haveReceiveDailyreward = haveReceiveDailyreward;
	}
	
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Integer getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Integer onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}