package com.war.domain;

import java.io.Serializable;

/**
 * 地图信息
 *
 * @author ghleed
 * @version 1.0
 */
public class Map implements Serializable {

	private static final long serialVersionUID = -2082546567896010828L;
	
	/** 地图编号 */
	private Integer mapID;
	/** 地图X坐标 */
	private Integer posX;
	/** 地图Y坐标 */
	private Integer posY;
	/** 区域(11.左上区域 12.左下区域 21.右上区域 22.右下区域 31.上岛屿 32.中岛屿 33.下岛屿 41.海洋 42.海岸) */
	private Integer area;
	/** 地图类型 */
	private Integer type;
	/** 地图状态(1.正常 2.战斗中) */
	private Integer state;
	/** 目标编号 */
	private Integer targetID;
	/** 类别 */
	private Integer category;
	/** 玩家信息*/
	private Player player;
	/** 野怪信息*/
	private MapMonster mapMonster;
	/** 地图名称*/
	private String name;
	
	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
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
	
	public Integer getArea() {
		return area;
	}
	
	public void setArea(Integer area) {
		this.area = area;
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
	
	public Integer getTargetID() {
		return targetID;
	}

	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}
	
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public MapMonster getMapMonster() {
		return mapMonster;
	}

	public void setMapMonster(MapMonster mapMonster) {
		this.mapMonster = mapMonster;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}