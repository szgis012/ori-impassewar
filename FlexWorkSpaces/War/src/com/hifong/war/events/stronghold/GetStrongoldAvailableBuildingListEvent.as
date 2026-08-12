/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.stronghold
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得要塞可建筑的建筑列表
	 * 
	 */ 
	public final class GetStrongoldAvailableBuildingListEvent extends CairngormEvent
	{

		public static const GETSTRONGOLDAVAILABLEBUILDINGLIST_EVENT:String = "com.hifong.war.events.GetStrongoldAvailableBuildingListEvent";
		
		/** 要塞编号*/
		public var strongholdID:int;
		
		public function GetStrongoldAvailableBuildingListEvent( strongholdID:int ) 
		{
			super( GETSTRONGOLDAVAILABLEBUILDINGLIST_EVENT );
			this.strongholdID =  strongholdID;
		}
	}
}
