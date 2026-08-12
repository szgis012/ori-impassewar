/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class SaveHeroPointEvent extends CairngormEvent
	{

		public var cityHeroID:int;
		
		public var commandAdded:int;
		
		public var defenseAdded:int;
		
		public var mindAdded:int;
		
		public var executivepowerAdded:int;

		public static const ADDHEROPOINT_EVENT:String = "com.hifong.war.events.AddHeroPointEvent";

		public function SaveHeroPointEvent(cityHeroID:int,commandAdded:int,defenseAdded:int,mindAdded:int,executivepowerAdded:int) 
		{
			super( ADDHEROPOINT_EVENT );
			this.cityHeroID = cityHeroID;
			this.commandAdded = commandAdded;
			this.defenseAdded = defenseAdded;
			this.mindAdded = mindAdded;
			this.executivepowerAdded = executivepowerAdded;
		}
	}
}
