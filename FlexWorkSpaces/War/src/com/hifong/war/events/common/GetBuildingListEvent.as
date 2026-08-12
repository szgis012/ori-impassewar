/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得所有建筑列表事件
     *
     */
	public final class GetBuildingListEvent extends CairngormEvent
	{
		public static const GETBUILDINGLIST_EVENT:String = "com.hifong.war.events.GetBuildingListEvent";
		
		public function GetBuildingListEvent() 
		{
			super( GETBUILDINGLIST_EVENT );
		}
	}
}
