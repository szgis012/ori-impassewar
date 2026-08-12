/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityConstructionPointRankListByCityRankEvent extends CairngormEvent
	{

		public var rank:int;

		public static const SHOWCITYCONSTRUCTIONPOINTRANKLISTBYCITYRANK_EVENT:String = "com.hifong.war.events.ShowCityConstructionPointRankListByCityRankEvent";

		public function ShowCityConstructionPointRankListByCityRankEvent(rank:int) 
		{
			super( SHOWCITYCONSTRUCTIONPOINTRANKLISTBYCITYRANK_EVENT );
			this.rank = rank;
		}
	}
}
