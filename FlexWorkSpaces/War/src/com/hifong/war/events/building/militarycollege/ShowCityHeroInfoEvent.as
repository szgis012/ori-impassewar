/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityHeroInfoEvent extends CairngormEvent
	{
		
		public var cityHeroID:int;

		public static const SHOWCITYHEROINFO_EVENT:String = "com.hifong.war.events.ShowCityHeroInfoEvent";

		public function ShowCityHeroInfoEvent(cityHeroID:int) 
		{
			super( SHOWCITYHEROINFO_EVENT );
			this.cityHeroID = cityHeroID;
		}
	}
}
