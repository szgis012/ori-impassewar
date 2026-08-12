package com.war.domain;

import java.io.Serializable;

/**
 * 宝物信息
 * 
 * @author ghleed
 * @version 1.0
 */
public class Treasure implements Serializable {
	
	private static final long serialVersionUID = -735045895742506275L;
	
	/** 宝物编号 */
	private Integer treasureID;
	/** 宝物名称 */
	private String name;
	/** 宝物描述信息 */
	private String description;
	/** 宝物类别 TreasureCategoryConstant中定义 */
	private Integer category;
	/** 宝物类型，命名方法为宝物所属的分类数字+两位代表宝物类型的数字组成。TreasureTypeConstant中定义 */
	private Integer type;
	/** 宝物价格 */
	private Integer cost;
	/** 宝物图片地址 */
	private String imgSrc;
	/** 使用宝物时执行的脚本地址 */
	private String codeSrc;
	/** 是否可以购买(0.不可购买 1.可购买) */
	private Integer canBuy;
	/** 是否可以礼金购买(0.不可购买 1.可购买) */
	private Integer canGiftCertificateBuy;
	/** 直接使用提示(若宝物可直接使用则为空，否则该字段为直接使用时提示信息) */
	private String directUseTooltip;
	/** 宝物状态。在TreasureStateConstant中定义 */
	private Integer state;

	public Integer getTreasureID() {
		return treasureID;
	}

	public void setTreasureID(Integer treasureID) {
		this.treasureID = treasureID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public String getCodeSrc() {
		return codeSrc;
	}

	public void setCodeSrc(String codeSrc) {
		this.codeSrc = codeSrc;
	}

	public Integer getCanBuy() {
		return canBuy;
	}

	public void setCanBuy(Integer canBuy) {
		this.canBuy = canBuy;
	}

	public Integer getCanGiftCertificateBuy() {
		return canGiftCertificateBuy;
	}

	public void setCanGiftCertificateBuy(Integer canGiftCertificateBuy) {
		this.canGiftCertificateBuy = canGiftCertificateBuy;
	}

	public String getDirectUseTooltip() {
		return directUseTooltip;
	}

	public void setDirectUseTooltip(String directUseTooltip) {
		this.directUseTooltip = directUseTooltip;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	

}
