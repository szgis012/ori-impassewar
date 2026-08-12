/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class HasMilitaryInBattleOrGoingToMapEvent extends CairngormEvent
	{

		public static const HASMILITARYGOINGTOMAP_EVENT:String = "com.hifong.war.events.HasMilitaryGoingToMapEvent";

		public var cityMilitaryID:int;

		public var posX:int;
		
		public var posY:int;

		public function HasMilitaryInBattleOrGoingToMapEvent(cityMilitaryID:int,posX:int,posY:int) 
		{
			super( HASMILITARYGOINGTOMAP_EVENT );
			this.cityMilitaryID = cityMilitaryID;
			this.posX = posX;
			this.posY = posY;
		}
	}
}
