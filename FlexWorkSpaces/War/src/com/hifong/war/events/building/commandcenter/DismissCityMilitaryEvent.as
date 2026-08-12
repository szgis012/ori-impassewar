/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class DismissCityMilitaryEvent extends CairngormEvent
	{
		
		public var cityID:int;
		
		public var cityMilitaryID:int;

		public static const DISMISSCITYMILITARY_EVENT:String = "com.hifong.war.events.DismissCityMilitaryEvent";

		public function DismissCityMilitaryEvent(cityID:int,cityMilitaryID:int) 
		{
			super( DISMISSCITYMILITARY_EVENT );
			this.cityID = cityID;
			this.cityMilitaryID = cityMilitaryID;
		}
	}
}
