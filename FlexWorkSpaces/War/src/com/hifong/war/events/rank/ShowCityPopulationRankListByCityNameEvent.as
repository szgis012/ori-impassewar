/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityPopulationRankListByCityNameEvent extends CairngormEvent
	{

		public var cityName:String;

		public static const SHOWCITYPOPULATIONRANKLISTBYCITYNAME_EVENT:String = "com.hifong.war.events.ShowCityPopulationRankListByCityNameEvent";

		public function ShowCityPopulationRankListByCityNameEvent(cityName:String) 
		{
			super( SHOWCITYPOPULATIONRANKLISTBYCITYNAME_EVENT );
			this.cityName = cityName;
		}
	}
}
