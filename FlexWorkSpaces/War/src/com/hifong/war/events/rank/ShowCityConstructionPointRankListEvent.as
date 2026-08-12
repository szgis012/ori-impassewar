/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityConstructionPointRankListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const SHOWCITYCONSTRUCTIONPOINTRANKLIST_EVENT:String = "com.hifong.war.events.ShowCityConstructionPointRankListEvent";

		public function ShowCityConstructionPointRankListEvent(cityID:int) 
		{
			super( SHOWCITYCONSTRUCTIONPOINTRANKLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
