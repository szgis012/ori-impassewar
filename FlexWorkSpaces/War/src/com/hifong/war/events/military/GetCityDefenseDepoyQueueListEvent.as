/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityDefenseDepoyQueueListEvent extends CairngormEvent
	{

		public var cityID:int;
		
		public static const GETCITYDEFENSEDEPOYQUEUELIST_EVENT:String = "com.hifong.war.events.GetCityDefenseDepoyQueueListEvent";

		public function GetCityDefenseDepoyQueueListEvent(cityID:int) 
		{
			super( GETCITYDEFENSEDEPOYQUEUELIST_EVENT );
			this.cityID = cityID;
		}
	}
}
