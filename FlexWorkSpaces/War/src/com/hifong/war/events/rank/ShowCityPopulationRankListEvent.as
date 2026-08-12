/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityPopulationRankListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const SHOWCITYPOPULATIONRANKLIST_EVENT:String = "com.hifong.war.events.ShowCityPopulationRankListEvent";

		public function ShowCityPopulationRankListEvent(cityID:int) 
		{
			super( SHOWCITYPOPULATIONRANKLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
