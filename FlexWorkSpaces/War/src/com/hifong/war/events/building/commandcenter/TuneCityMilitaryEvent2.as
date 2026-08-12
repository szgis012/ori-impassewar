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
	public final class TuneCityMilitaryEvent2 extends CairngormEvent
	{
		/** 城市军队编号 */
		public var cityMilitaryID:int;
		/**军队信息 ，共八对 。格式如：armyID1:num1,armyID2:num2...*/
		public var armyInfo:String;
		
		public static const TUNEMILITARY_EVENT:String = "com.hifong.war.events.TuneMilitaryEvent";
		
		public function TuneCityMilitaryEvent2(cityMilitaryID:int,armyInfo:String) 
		{
			super( TUNEMILITARY_EVENT );
			
			this.cityMilitaryID = cityMilitaryID;
			this.armyInfo = armyInfo;
		}
	}
}
