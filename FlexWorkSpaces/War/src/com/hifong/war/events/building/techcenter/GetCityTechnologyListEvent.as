/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.techcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityTechnologyListEvent extends CairngormEvent
	{

		public var cityID:int;
		
		public var technologyType:int;

		public static const GETTECHNOLOGYLIST_EVENT:String = "com.hifong.war.events.GetTechnologyListEvent";

		public function GetCityTechnologyListEvent(cityID:int,technologyType:int) 
		{
			super( GETTECHNOLOGYLIST_EVENT );
			this.cityID = cityID;
			this.technologyType = technologyType;
		}
	}
}
