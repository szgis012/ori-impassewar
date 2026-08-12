/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 领取军团补贴
	 * @param guildID
	 * @param playerID
	 */
	public final class ReceiveSubsidyEvent extends CairngormEvent
	{

		public static const RECEIVESUBSIDY_EVENT:String = "com.hifong.war.events.ReceiveSubsidyEvent";

		public var guildID:int;
		public var playerID:int;
		public function ReceiveSubsidyEvent(guildID:int,playerID:int) 
		{
			super( RECEIVESUBSIDY_EVENT );
			this.guildID=guildID;
			this.playerID=playerID;
		}
	}
}
