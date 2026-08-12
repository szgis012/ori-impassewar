/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ChangeCityHeroEquipmentEvent extends CairngormEvent
	{

		public var cityHeroID:int;
		
		public var playerEquipmentID:int;
		
		public var equipmentCategory:int;
		
		public static const CHANGECITYHEROEQUIPMENT_EVENT:String = "com.hifong.war.events.ChangeCityHeroEquipmentEvent";

		public function ChangeCityHeroEquipmentEvent(cityHeroID:int,playerEquipmentID:int,equipmentCategory:int) 
		{
			super( CHANGECITYHEROEQUIPMENT_EVENT );
			this.cityHeroID = cityHeroID;
			this.playerEquipmentID = playerEquipmentID;
			this.equipmentCategory = equipmentCategory;
		}
	}
}
