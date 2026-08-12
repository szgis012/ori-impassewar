/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获取可建造建筑列表事件
     *
     */
	public final class GetBuildableBuildingListEvent extends CairngormEvent
	{
		/** 城市编号 */
		public var cityID:int;
		
		
		public static const GETBUILDABLEBUILDINGLIST_EVENT:String = "com.hifong.war.events.GetBuildableBuildingListEvent";
		
		public function GetBuildableBuildingListEvent(cityID:int) 
		{
			super( GETBUILDABLEBUILDINGLIST_EVENT );
			
			this.cityID = cityID;
		}
	}
}
