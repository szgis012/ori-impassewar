/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	
	/**
	 * 设置城守军队
	 * 
	 */ 
	public final class SetDefensiveMilitaryEvent extends CairngormEvent
	{

		public static const SETDEFENSIVEMILITARY_EVENT:String = "com.hifong.war.events.SetDefensiveMilitaryEvent";

		/** 城市军队编号*/
		public var cityMilitaryID:int;
		
		
		public function SetDefensiveMilitaryEvent(cityMilitaryID:int) 
		{
			super( SETDEFENSIVEMILITARY_EVENT );
			this.cityMilitaryID = cityMilitaryID;
		}
	}
}
