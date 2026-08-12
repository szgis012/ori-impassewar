/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 刷新CityBuilding信息的事件
     *
     */
	public final class RefreshCityBuildingEvent extends CairngormEvent
	{
		/** 城市建筑编号*/
		public var cityBuildingID:int;
		
		public static const REFRESHCITYBUILDING_EVENT:String = "com.hifong.war.events.RefreshCityBuildingEvent";
		
		public function RefreshCityBuildingEvent(cbid:int) 
		{
			super( REFRESHCITYBUILDING_EVENT );
			
			this.cityBuildingID = cbid;
		}
	}
}
