/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.defense
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 建造城市的防御事件
     *
     */
	public final class BuildCityDefenseEvent extends CairngormEvent
	{
		public static const BUILDCITYDEFENSE_EVENT:String = "com.hifong.war.events.BuildCityDefenseEvent";
		
		/** 城防类型 CityDefenseTypeConstant中定义*/
		public var defenseType:int;
		/** 建造数量*/
		public var num:int;
		
		public function BuildCityDefenseEvent(defenseType:int,num:int) 
		{
			super( BUILDCITYDEFENSE_EVENT );
			
			this.defenseType = defenseType;
			this.num = num;
		}
	}
}
