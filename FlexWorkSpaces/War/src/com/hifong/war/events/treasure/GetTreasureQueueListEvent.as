/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetTreasureQueueListEvent extends CairngormEvent
	{

		public static const GETTREASUREQUEUELIST_EVENT:String = "com.hifong.war.events.GetTreasureQueueListEvent";

		public function GetTreasureQueueListEvent() 
		{
			super( GETTREASUREQUEUELIST_EVENT );
		}
	}
}
