/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowCityTechnologyPointRankListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const SHOWCITYTECHNOLOGYPOINTRANKLIST_EVENT:String = "com.hifong.war.events.ShowCityTechnologyPointRankListEvent";

		public function ShowCityTechnologyPointRankListEvent(cityID:int) 
		{
			super( SHOWCITYTECHNOLOGYPOINTRANKLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
