package com.war.domain;

import java.io.Serializable;
import java.util.Date;

public class MapFavourite implements Serializable {

	private static final long serialVersionUID = -899448192171757593L;

	/** 地图收藏编号 */
	private Integer mapFavouriteID;
	/** 玩家编号 */
	private Integer playerID;
	/** 地图编号 */
	private Integer mapID;
	/** 创建时间 */
	private Date createTime;
	/** 地图 */
	private Map map;

	public Integer getMapFavouriteID() {
		return mapFavouriteID;
	}

	public void setMapFavouriteID(Integer mapFavouriteID) {
		this.mapFavouriteID = mapFavouriteID;
	}

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	
}
