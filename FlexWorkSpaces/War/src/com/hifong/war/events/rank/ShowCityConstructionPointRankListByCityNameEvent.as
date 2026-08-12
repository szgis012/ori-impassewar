/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityConstructionPointRankListByCityNameEvent extends CairngormEvent
	{

		public var cityName:String;

		public static const SHOWCITYCONSTRUCTIONPOINTRANKLISTBYCITYNAME_EVENT:String = "com.hifong.war.events.ShowCityConstructionPointRankListByCityNameEvent";

		public function ShowCityConstructionPointRankListByCityNameEvent(cityName:String) 
		{
			super( SHOWCITYCONSTRUCTIONPOINTRANKLISTBYCITYNAME_EVENT );
			this.cityName = cityName;
		}
	}
}
