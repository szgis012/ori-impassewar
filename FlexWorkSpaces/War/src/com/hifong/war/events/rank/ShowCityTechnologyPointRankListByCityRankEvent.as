/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityTechnologyPointRankListByCityRankEvent extends CairngormEvent
	{

		public var rank:int;

		public static const SHOWCITYTECHNOLOGYPOINTRANKLISTBYCITYRANK_EVENT:String = "com.hifong.war.events.ShowCityTechnologyPointRankListByCityRankEvent";

		public function ShowCityTechnologyPointRankListByCityRankEvent(rank:int) 
		{
			super( SHOWCITYTECHNOLOGYPOINTRANKLISTBYCITYRANK_EVENT );
			this.rank = rank;
		}
	}
}
