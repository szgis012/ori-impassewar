package com.war.domain;

import java.io.Serializable;

/**
 * 游戏卡(如新手卡等)
 * 
 * @author TopTong
 * @version 1.0
 */
public class GameCard implements Serializable {

	private static final long serialVersionUID = 6459956836625147973L;
	
	/** 游戏卡号 */
	private String gameCardNO;
	/** 类型(1.新手卡 2.推广序列号) */
	private Integer type;
	/** 状态(1.正常 2.已使用) */
	private Integer state;

	public String getGameCardNO() {
		return gameCardNO;
	}

	public void setGameCardNO(String gameCardNO) {
		this.gameCardNO = gameCardNO;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}