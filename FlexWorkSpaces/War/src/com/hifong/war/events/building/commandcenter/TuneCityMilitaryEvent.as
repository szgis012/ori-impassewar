/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 编制一只军队事件
     *
     */
	public final class TuneCityMilitaryEvent extends CairngormEvent
	{
		public static const TUNEMILITARY_EVENT:String = "com.hifong.war.events.TuneMilitaryEvent";
		
		public var cityMilitaryID:int;
		
		public var militaryArmyStr:String;
		
		public function TuneCityMilitaryEvent(cityMilitaryID:int, militaryArmyStr:String) 
		{
			super( TUNEMILITARY_EVENT );
			
			this.cityMilitaryID = cityMilitaryID;
			this.militaryArmyStr = militaryArmyStr;
		}
	}
}
