/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityResourceSalesListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const SHOWCITYRESOURCESALESLIST_EVENT:String = "com.hifong.war.events.ShowCityResourceSalesListEvent";

		public function ShowCityResourceSalesListEvent(cityID:int) 
		{
			super( SHOWCITYRESOURCESALESLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
