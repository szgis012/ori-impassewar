package com.war.domain;


import java.io.Serializable;


/**
 * 城市的军械信息
 *
 * @author ghleed
 * @version 1.0
 */
public class CityOrdnance implements Serializable {

	private static final long serialVersionUID = 2427341501771615332L;
	
	/** 编号 */
	private Integer cityOrdnanceID;
	/** 军械编号 */
	private Integer ordnanceID;
	/** 城市编号 */
	private Integer cityID;
	/** 军械数量 */
	private Integer num;

	public Integer getCityOrdnanceID() {
		return cityOrdnanceID;
	}

	public void setCityOrdnanceID(Integer cityOrdnanceID) {
		this.cityOrdnanceID = cityOrdnanceID;
	}
	public Integer getOrdnanceID() {
		return ordnanceID;
	}

	public void setOrdnanceID(Integer ordnanceID) {
		this.ordnanceID = ordnanceID;
	}
	public Integer getCityID() {
		return cityID;
	}

	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
