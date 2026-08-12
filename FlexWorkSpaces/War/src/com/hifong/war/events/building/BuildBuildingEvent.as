/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 建筑建造事件
     *
     */
	public final class BuildBuildingEvent extends CairngormEvent
	{
		/** 城市编号 */
		public var cityID:int;
		/** 建筑编号 */
		public var buildingID:int;
		/** 位置 */
		public var position:int;
		
		public static const BUILDBUILDING_EVENT:String = "com.hifong.war.events.BuildBuildingEvent";
		
		public function BuildBuildingEvent(cityID:int,buildingID:int,position:int) 
		{
			super( BUILDBUILDING_EVENT );
			
			this.cityID = cityID;
			this.buildingID = buildingID;
			this.position = position;
		}
	}
}
