/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityResourcesOutputEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYRESOURCESOUTPUT_EVENT:String = "com.hifong.war.events.GetCityResourcesOutputEvent";

		public function GetCityResourcesOutputEvent(cityID:int) 
		{
			super( GETCITYRESOURCESOUTPUT_EVENT );
			this.cityID = cityID;
		}
	}
}
