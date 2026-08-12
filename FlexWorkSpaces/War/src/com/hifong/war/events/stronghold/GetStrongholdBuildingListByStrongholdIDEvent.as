/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.stronghold
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得要塞已建的建筑信息 
	 * 
	 */ 
	public final class GetStrongholdBuildingListByStrongholdIDEvent extends CairngormEvent
	{

		public static const GETSTRONGHOLDBUILDINGLISTBYSTRONGHOLDID_EVENT:String = "com.hifong.war.events.GetStrongholdBuildingListByStrongholdIDEvent";
		
		/** 要塞编号*/
		public var strongholdID:int;
		
		public function GetStrongholdBuildingListByStrongholdIDEvent(strongholdID:int) 
		{
			super( GETSTRONGHOLDBUILDINGLISTBYSTRONGHOLDID_EVENT );
			this.strongholdID = strongholdID;
		}
	}
}
