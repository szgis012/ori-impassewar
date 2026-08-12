/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityHeroListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYHEROLIST_EVENT:String = "com.hifong.war.events.GetCityHeroListEvent";

		public function GetCityHeroListEvent(cityID:int) 
		{
			super( GETCITYHEROLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
