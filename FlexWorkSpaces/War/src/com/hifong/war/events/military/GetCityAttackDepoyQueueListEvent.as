/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityAttackDepoyQueueListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYATTACKDEPOYQUEUELIST_EVENT:String = "com.hifong.war.events.GetCityAttackDepoyQueueListEvent";

		public function GetCityAttackDepoyQueueListEvent(cityID:int) 
		{
			super( GETCITYATTACKDEPOYQUEUELIST_EVENT );
			this.cityID = cityID;
		}
	}
}
