package com.war.domain;

/**
 * 玩家宝物信息
 *
 * @author ghleed
 * @version 1.0
 */
public class PlayerTreasure implements java.io.Serializable{
	
	private static final long serialVersionUID = 530761499887535312L;
	
	/** 玩家编号 */
	private Integer playerID;
	/** 宝物编号 */
	private Integer treasureID;
	/** 宝物数量 */
	private int num;
	/** 对应的宝物引用 */
	private Treasure treasure;
	
	public int getNum() {
		return num;
	}

	public Treasure getTreasure() {
		return treasure;
	}

	public void setTreasure(Treasure treasure) {
		this.treasure = treasure;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public Integer getTreasureID() {
		return treasureID;
	}

	public void setTreasureID(Integer treasureID) {
		this.treasureID = treasureID;
	}
}
