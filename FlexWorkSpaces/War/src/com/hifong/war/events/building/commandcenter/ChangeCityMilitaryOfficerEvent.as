/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ChangeCityMilitaryOfficerEvent extends CairngormEvent
	{

		public var cityID:int;

		public var cityMilitaryID:int;
		
		public var cityHeroID:int;

		public static const CHANGECITYMILITARYOFFICER_EVENT:String = "com.hifong.war.events.ChangeCityMilitaryOfficerEvent";

		public function ChangeCityMilitaryOfficerEvent(cityID:int,cityMilitaryID:int,cityHeroID:int) 
		{
			super( CHANGECITYMILITARYOFFICER_EVENT );
			this.cityID = cityID;
			this.cityMilitaryID = cityMilitaryID;
			this.cityHeroID = cityHeroID;
		}
	}
}
