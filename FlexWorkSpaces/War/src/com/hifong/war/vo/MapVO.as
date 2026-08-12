/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.vo
{
	import com.adobe.cairngorm.vo.IValueObject;
	
	[RemoteClass(alias="com.war.domain.Map")]
	[Bindable]
	public class MapVO implements IValueObject {
		/** 地图编号 */
	    public var mapID:int;
	    /** 地图X坐标 */
	    public var posX:int;
	    /** 地图Y坐标 */
	    public var posY:int;
	    /** 区域 */
	    public var area:int;
	    /** 类别(1.玩家城市 2.要塞 3.野地) */
	    public var category:int;
	    /** 地图类型(1.玩家城市 2.要塞 11.空地 12.空地1 13.空地2 14.空地3,15.空地4 16.空地5 17.空地6 18.空地7 19.空地8 21.林地1 22.林地2 23.林地3 31.山地1  32.山地2  33.山地3 41.湖泊1 42.湖泊2 43.湖泊3 51.平原1 52.平原2 53.平原3) */
	    public var type:int;
	    /** 地图状态(1.正常 2.战斗中) */
	    public var state:int;
	    /** 目标编号 */
	    public var targetID:int;
		/** 地图上的野怪 */
	    public var mapMonster:Object;
	    /** 拥有城市的玩家信息*/
   		public var player:PlayerVO;
   		/**该点的名称（玩家主城或营寨的名称）*/
   		public var name:String;
	}
}