package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class TradeQueue implements Serializable {

	private static final long serialVersionUID = -7770726606939537755L;

	/** 交易队列编号 */
	private Integer tradeQueueID;
	/** 出售方城市编号 */
	private Integer sellerID;
	/** 购买方城市编号 */
	private Integer buyerID;
	/** 城市编号 */
	private Integer cityID;
	/** 目标城市编号 */
	private Integer targetCityID;
	/** 目标编号 */
	private Integer targetID;
	/** 交易类型(1.资源 2.军械 3.宝物 4.运输) */
	private Integer type;
	/** 商人数量 */
	private Integer businessmanNum;
	/** 到达时间 */
	private Date arriveTime;
	/** 出售方城市信息 */
	private CityInfo sellerCityInfo;
	/** 购买方城市信息 */
	private CityInfo buyerCityInfo;
	/** 目标对象(资源交易 军械交易 宝物交易 运输) */
	private Object targetObject;

	
	public Integer getTradeQueueID() {
		return tradeQueueID;
	}

	public void setTradeQueueID(Integer tradeQueueID) {
		this.tradeQueueID = tradeQueueID;
	}
	
	public Integer getSellerID() {
		return sellerID;
	}

	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}
	
	public Integer getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(Integer buyerID) {
		this.buyerID = buyerID;
	}

	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	
	public Integer getTargetCityID() {
		return targetCityID;
	}

	public void setTargetCityID(Integer targetCityID) {
		this.targetCityID = targetCityID;
	}
	
	public Integer getTargetID() {
		return targetID;
	}

	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getBusinessmanNum() {
		return businessmanNum;
	}

	public void setBusinessmanNum(Integer businessmanNum) {
		this.businessmanNum = businessmanNum;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public CityInfo getSellerCityInfo() {
		return sellerCityInfo;
	}

	public void setSellerCityInfo(CityInfo sellerCityInfo) {
		this.sellerCityInfo = sellerCityInfo;
	}

	public CityInfo getBuyerCityInfo() {
		return buyerCityInfo;
	}

	public void setBuyerCityInfo(CityInfo buyerCityInfo) {
		this.buyerCityInfo = buyerCityInfo;
	}

	public Object getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}

}