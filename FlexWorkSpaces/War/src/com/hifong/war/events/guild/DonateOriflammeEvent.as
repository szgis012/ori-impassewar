/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 军团成员捐献军旗到军团
	 * @param guildID 	军团ID
	 * @param playerID	玩家ID
	 * @param oriflammeType		军旗类型
	 * @param oriflammeNum	军旗数量
	 */
	public final class DonateOriflammeEvent extends CairngormEvent
	{

		public static const DONATEORIFLAMME_EVENT:String = "com.hifong.war.events.DonateOriflammeEvent";
	
		public var guildID:int;
		public var playerID:int;
		public var oriflammeType:String
		public var oriflammeNum:int;
		public function DonateOriflammeEvent(guildID:int, playerID:int, oriflammeType:String, oriflammeNum:int) 
		{
			super( DONATEORIFLAMME_EVENT );
			this.guildID=guildID;
			this.playerID=playerID;
			this.oriflammeType=oriflammeType;
			this.oriflammeNum=oriflammeNum;
		}
	}
}
