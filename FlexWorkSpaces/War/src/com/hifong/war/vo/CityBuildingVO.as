/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.CityBuilding")]
    [Bindable]
	public class CityBuildingVO implements IValueObject {
		/** 城市建筑编号 */
	    public var cityBuildingID:int;
		/** 城市编号 */
	    public var cityID:int;
	    /** 建筑编号 */
	    public var buildingID:int;
	    /** 地图位置 */
	    public var position:int;
	    /** 建筑等级 */
	    public var level:int;
	    /** 状态(1.正常 2.建造中 3.拆除中) */
	    public var state:int;
	    /** 建筑*/
	    public var building:BuildingVO;
	    /** 建筑排程(建造, 拆除中有效)*/
	    public var processQueue:ProcessQueueVO;
	}
}