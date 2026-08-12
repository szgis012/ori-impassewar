package com.war.domain;

import java.io.Serializable;

public class ResTransportation implements Serializable {

	private static final long serialVersionUID = 8773141120254095866L;
	
	/** 资源运输编号 */
	private Integer resTransportationID;
	/** 木材数量 */
	private Long woodAmount;
	/** 钢铁数量 */
	private Long steelAmount;
	/** 石油数量 */
	private Long oilAmount;
	/** 食物数量 */
	private Long foodAmount;
	/** 金钱数量 */
	private Long moneyAmount;

	
	public Integer getResTransportationID() {
		return resTransportationID;
	}

	public void setResTransportationID(Integer resTransportationID) {
		this.resTransportationID = resTransportationID;
	}
	
	public Long getWoodAmount() {
		return woodAmount;
	}

	public void setWoodAmount(Long woodAmount) {
		this.woodAmount = woodAmount;
	}
	
	public Long getSteelAmount() {
		return steelAmount;
	}

	public void setSteelAmount(Long steelAmount) {
		this.steelAmount = steelAmount;
	}
	
	public Long getOilAmount() {
		return oilAmount;
	}

	public void setOilAmount(Long oilAmount) {
		this.oilAmount = oilAmount;
	}
	
	public Long getFoodAmount() {
		return foodAmount;
	}

	public void setFoodAmount(Long foodAmount) {
		this.foodAmount = foodAmount;
	}
	
	public Long getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(Long moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
	
}