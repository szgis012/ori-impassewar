/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetFreeCityHeroListCommandEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETFREECITYHEROLISTCOMMAND_EVENT:String = "com.hifong.war.events.GetFreeCityHeroListCommandEvent";

		public function GetFreeCityHeroListCommandEvent(cityID:int) 
		{
			super( GETFREECITYHEROLISTCOMMAND_EVENT );
			this.cityID = cityID;
		}
	}
}
