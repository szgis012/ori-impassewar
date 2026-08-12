/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ResetHeroPointEvent extends CairngormEvent
	{
		
		public var playerID:int;
		
		public var cityHeroID:int;
		
		public var command:int;
		
		public var defense:int;
		
		public var mind:int;
		
		public var executivepower:int;

		public static const RESETHEROPOINT_EVENT:String = "com.hifong.war.events.ResetHeroPointEvent";

		public function ResetHeroPointEvent(playerID:int, cityHeroID:int, command:int, defense:int, mind:int, executivepower:int) 
		{
			super( RESETHEROPOINT_EVENT );
			this.playerID = playerID;
			this.cityHeroID = cityHeroID;
			this.command = command;
			this.defense = defense;
			this.mind = mind;
			this.executivepower = executivepower;
		}
	}
}
