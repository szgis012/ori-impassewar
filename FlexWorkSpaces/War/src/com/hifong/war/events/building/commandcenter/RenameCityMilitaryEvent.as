/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RenameCityMilitaryEvent extends CairngormEvent
	{

		public var cityID:int;

		public var cityMilitaryID:int;
		
		public var name:String;

		public static const RENAMECITYMILITARY_EVENT:String = "com.hifong.war.events.RenameCityMilitaryEvent";

		public function RenameCityMilitaryEvent(cityID:int,cityMilitaryID:int,name:String) 
		{
			super( RENAMECITYMILITARY_EVENT );
			this.cityID = cityID;
			this.cityMilitaryID = cityMilitaryID;
			this.name = name;
		}
	}
}
