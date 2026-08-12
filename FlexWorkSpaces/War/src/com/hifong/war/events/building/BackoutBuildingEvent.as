/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 建筑拆除事件
     *
     */
	public final class BackoutBuildingEvent extends CairngormEvent
	{
		/** 城市建筑编号 */
		public var cityBuildingID:int;
		
		public static const BACKOUTBUILDING_EVENT:String = "com.hifong.war.events.BackoutBuildingEvent";
		
		public function BackoutBuildingEvent(cityBuildingID:int) 
		{
			super( BACKOUTBUILDING_EVENT );
			
			this.cityBuildingID = cityBuildingID;
		}
	}
}
