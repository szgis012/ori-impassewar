/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityPopulationEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYPOPULATION_EVENT:String = "com.hifong.war.events.GetCityPopulationEvent";

		public function GetCityPopulationEvent(cityID:int) 
		{
			super( GETCITYPOPULATION_EVENT );
			this.cityID = cityID;
		}
	}
}
