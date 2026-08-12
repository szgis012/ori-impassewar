/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 取得军团收入总和或支出总和或玩家捐献总和
	 * 1：若playerID为null表示取得军团收入总和或支出总和（type不为null）
	 * 2：若playerID不为Null表示取得军团玩家捐献总和（type应为null）
	 * @param guildID
	 * @param guildPlayerID
	 * @param type
	 */
	public final class GetTotalAlmsOfGuildMemberInGuildEvent extends CairngormEvent
	{

		public static const GETTOTALALMSOFGUILDMEMBERINGUILD_EVENT:String = "com.hifong.war.events.GetTotalAlmsOfGuildMemberInGuildEvent";
		
		public var guildID:int;
		public var guildPlayerID:int;
		public var types:int;
		public function GetTotalAlmsOfGuildMemberInGuildEvent(guildID:int, guildPlayerID:int,type:int) 
		{
			super( GETTOTALALMSOFGUILDMEMBERINGUILD_EVENT );
			this.guildID=guildID;
			this.guildPlayerID=guildPlayerID;
			this.types=type;
		}
	}
}
