/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得城市已有的建筑列表事件
     *
     */
	public final class GetCompletedBuildingListEvent extends CairngormEvent
	{
		/** 城市编号 */
		public var cityID:int;
		
		public static const GETCOMPLETEDBUILDINGLIST_EVENT:String = "com.hifong.war.events.GetCompletedBuildingListEvent";
		
		public function GetCompletedBuildingListEvent(cityID:int) 
		{
			super( GETCOMPLETEDBUILDINGLIST_EVENT );
			
			this.cityID = cityID;
		}
	}
}
