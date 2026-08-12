/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;
	
	/**
	 * 每日奖励
	 */
	public final class ReceiveDailyRewardEvent extends CairngormEvent
	{

		public static const RECEIVEDAILYREWARD_EVENT:String = "com.hifong.war.events.ReceiveDailyRewardEvent";
		public var playerID:int;
		public function ReceiveDailyRewardEvent(playerID:int) 
		{
			super( RECEIVEDAILYREWARD_EVENT );
			this.playerID=playerID;
		}
	}
}
