/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityBattleListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYBATTLELIST_EVENT:String = "com.hifong.war.events.GetCityBattleListEvent";

		public function GetCityBattleListEvent(cityID:int) 
		{
			super( GETCITYBATTLELIST_EVENT );
			this.cityID = cityID;
		}
	}
}
