/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class CureCityWoundedArmyEvent extends CairngormEvent
	{

		public static const CURECITYWOUNDEDARMY_EVENT:String = "com.hifong.war.events.CureCityWoundedArmyEvent";

		public var cityWoundedArmyID:int;
		public var num:int;
		public function CureCityWoundedArmyEvent(cityWoundedArmyID:int,num:int) 
		{
			super( CURECITYWOUNDEDARMY_EVENT );
			this.cityWoundedArmyID=cityWoundedArmyID;
			this.num=num;
		}
	}
}
