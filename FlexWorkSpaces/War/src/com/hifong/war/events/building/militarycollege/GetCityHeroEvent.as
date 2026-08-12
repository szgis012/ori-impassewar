/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityHeroEvent extends CairngormEvent
	{

		public var cityHeroID:int;

		public static const GETCITYHEROEQUIPMENT_EVENT:String = "com.hifong.war.events.GetCityHeroEquipmentEvent";

		public function GetCityHeroEvent(cityHeroID:int) 
		{
			super( GETCITYHEROEQUIPMENT_EVENT );
			this.cityHeroID = cityHeroID;
		}
	}
}
