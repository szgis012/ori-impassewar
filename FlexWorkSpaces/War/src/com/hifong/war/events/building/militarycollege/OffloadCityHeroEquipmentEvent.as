/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class OffloadCityHeroEquipmentEvent extends CairngormEvent
	{

		public var cityHeroID:int;
		
		public var category:int;

		public var currentCategory:int;

		public static const OFFLOADCITYHEROEQUIPMENT_EVENT:String = "com.hifong.war.events.OffloadCityHeroEquipmentEvent";

		public function OffloadCityHeroEquipmentEvent(cityHeroID:int,category:int,currentCategory:int) 
		{
			super( OFFLOADCITYHEROEQUIPMENT_EVENT );
			this.cityHeroID = cityHeroID;
			this.category = category;
			this.currentCategory = currentCategory;
		}
	}
}
