/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityDepoyQueueListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYDEPOYQUEUELIST_EVENT:String = "com.hifong.war.events.GetCityDepoyQueueListEvent";

		public function GetCityDepoyQueueListEvent(cityID:int) 
		{
			super( GETCITYDEPOYQUEUELIST_EVENT );
			this.cityID = cityID;
		}
	}
}
