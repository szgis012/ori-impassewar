/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class DismissCityWoundedArmyEvent extends CairngormEvent
	{

		public static const DISMISSCITYWOUNDEDARMY_EVENT:String = "com.hifong.war.events.DismissCityWoundedArmyEvent";
		public var cityWoundedArmyID:int;
		public var num:int;
		public function DismissCityWoundedArmyEvent(cityWoundedArmyID:int,num:int) 
		{
			super( DISMISSCITYWOUNDEDARMY_EVENT );
			this.cityWoundedArmyID=cityWoundedArmyID;
			this.num=num;
		}
	}
}
