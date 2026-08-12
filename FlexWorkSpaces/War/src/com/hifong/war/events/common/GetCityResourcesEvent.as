/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityResourcesEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYRESOURCES_EVENT:String = "com.hifong.war.events.GetCityResourcesEvent";

		public function GetCityResourcesEvent(cityID:int) 
		{
			super( GETCITYRESOURCES_EVENT );
			this.cityID = cityID;
		}
	}
}
