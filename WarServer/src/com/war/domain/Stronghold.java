package com.war.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 要塞
 *
 * @author ghleed
 * @version 1.0
 */
public class Stronghold implements Serializable {
  
	private static final long serialVersionUID = 7462671782911923072L;
	
	/** 要塞编号 */
	private Integer strongholdID;
	/** 要塞所属玩家编号 */
	private Integer playerID;
	/** X坐标 */
	private Integer posX;
	/** Y坐标 */
	private Integer posY;
	/** 要塞名称 */
	private String name;
	/** 创建时间 */
	private Date createtime;

	public Integer getStrongholdID() {
		return strongholdID;
	}

	public void setStrongholdID(Integer strongholdID) {
		this.strongholdID = strongholdID;
	}
	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}
	public Integer getPosX() {
		return posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}
	public Integer getPosY() {
		return posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}