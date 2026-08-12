/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 军团成员捐献物资到军团
	 * @param guildID 	军团ID
	 * @param playerID	玩家ID
	 * @param money		金币数量
	 */
	public final class DonateMoneyEvent extends CairngormEvent
	{

		public static const DONATEMONEY_EVENT:String = "com.hifong.war.events.DonateMoneyEvent";

		public var guildID:int;
		public var playerID:int;
		public var money:Number;
		public function DonateMoneyEvent(guildID:int,playerID:int,money:Number) 
		{
			super( DONATEMONEY_EVENT );
			this.guildID=guildID;
			this.playerID=playerID;
			this.money=money;
		}
	}
}
