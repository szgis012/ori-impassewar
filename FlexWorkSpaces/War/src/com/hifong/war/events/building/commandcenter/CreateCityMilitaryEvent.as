/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class CreateCityMilitaryEvent extends CairngormEvent
	{

		public var cityID:int;
		
		public var name:String;
		
		public var cityHeroID:int;

		public static const CREATECITYMILITARY_EVENT:String = "com.hifong.war.events.CreateCityMilitaryEvent";

		public function CreateCityMilitaryEvent(cityID:int,name:String,cityHeroID:int) 
		{
			super( CREATECITYMILITARY_EVENT );
			this.cityID = cityID;
			this.name = name;
			this.cityHeroID = cityHeroID;
		}
	}
}
