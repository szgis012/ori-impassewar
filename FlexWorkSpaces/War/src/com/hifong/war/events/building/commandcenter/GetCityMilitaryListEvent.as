/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityMilitaryListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYMILITARYLIST_EVENT:String = "com.hifong.war.events.GetCityMilitaryListEvent";

		public function GetCityMilitaryListEvent(cityID:int) 
		{
			super( GETCITYMILITARYLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
