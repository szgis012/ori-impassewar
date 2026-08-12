package com.hifong.war.events.building.commandcenter
{
	/**
	 * 获取伤兵事件
	 */
	import com.adobe.cairngorm.control.CairngormEvent;

	public class GetWoundArmyEvent extends CairngormEvent
	{
		public static const GET_WOUND_ARMY_EVENT:String="com.hifong.war.events.GetWoundArmyEvent";
		public var cityID:int;
		public function GetWoundArmyEvent(cityID:int)
		{
			super(GET_WOUND_ARMY_EVENT);
			this.cityID=cityID;
		}
		
	}
}