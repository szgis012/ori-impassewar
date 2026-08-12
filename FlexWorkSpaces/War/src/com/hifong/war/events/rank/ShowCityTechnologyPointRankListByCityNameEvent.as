/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityTechnologyPointRankListByCityNameEvent extends CairngormEvent
	{

		public var cityName:String;

		public static const SHOWCITYTECHNOLOGYPOINTRANKLISTBYCITYNAME_EVENT:String = "com.hifong.war.events.ShowCityTechnologyPointRankListByCityNameEvent";

		public function ShowCityTechnologyPointRankListByCityNameEvent(cityName:String) 
		{
			super( SHOWCITYTECHNOLOGYPOINTRANKLISTBYCITYNAME_EVENT );
			this.cityName = cityName;
		}
	}
}
