package com.war.domain;

import java.io.Serializable;

public class ResTrade implements Serializable {

	private static final long serialVersionUID = -4263754332583168334L;
	
	/** 资源交易编号 */
	private Integer resTradeID;
	/** 卖家城市编号 */
	private Integer cityID;
	/** 卖家城市X坐标 */
	private Integer cityPosX;
	/** 卖家城市Y坐标 */
	private Integer cityPosY;
	/** 资源类型(1.木材 2.钢铁 3.石油 4.食物) */
	private Integer resourceType;
	/** 数量 */
	private Integer amount;
	/** 价格 */
	private Integer price;
	/** 最长交易时间 */
	private Long maxTime;
	/** 是否只允许盟友交易 */
	private Integer isAllyOnly;
	/** 状态(1.正常 2.交易中) */
	private Integer state;
	/** 卖家城市信息 */
	private CityInfo cityInfo;

	public Integer getResTradeID() {
		return resTradeID;
	}

	public void setResTradeID(Integer resTradeID) {
		this.resTradeID = resTradeID;
	}
	
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getCityPosX() {
		return cityPosX;
	}

	public void setCityPosX(Integer cityPosX) {
		this.cityPosX = cityPosX;
	}

	public Integer getCityPosY() {
		return cityPosY;
	}

	public void setCityPosY(Integer cityPosY) {
		this.cityPosY = cityPosY;
	}
	
	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Long maxTime) {
		this.maxTime = maxTime;
	}
	
	public Integer getIsAllyOnly() {
		return isAllyOnly;
	}

	public void setIsAllyOnly(Integer isAllyOnly) {
		this.isAllyOnly = isAllyOnly;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public CityInfo getCityInfo() {
		return cityInfo;
	}

	public void setCityInfo(CityInfo cityInfo) {
		this.cityInfo = cityInfo;
	}

}