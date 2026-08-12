/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityPopulationRankListByCityRankEvent extends CairngormEvent
	{

		public var rank:int;

		public static const SHOWCITYPOPULATIONRANKLISTBYCITYRANK_EVENT:String = "com.hifong.war.events.ShowCityPopulationRankListByCityRankEvent";

		public function ShowCityPopulationRankListByCityRankEvent(rank:int) 
		{
			super( SHOWCITYPOPULATIONRANKLISTBYCITYRANK_EVENT );
			this.rank = rank;
		}
	}
}
