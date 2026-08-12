/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 取消城守军队
	 * 
	 */ 
	public final class CancelDefensiveMilitaryEvent extends CairngormEvent
	{

		public static const CANCELDEFENSIVEMILITARY_EVENT:String = "com.hifong.war.events.CancelDefensiveMilitaryEvent";

		/** 城市军队编号*/
		public var cityMilitaryID:int;
		
		
		public function CancelDefensiveMilitaryEvent(cityMilitaryID:int) 
		{
			super( CANCELDEFENSIVEMILITARY_EVENT );
			this.cityMilitaryID = cityMilitaryID;
		}
	}
}
