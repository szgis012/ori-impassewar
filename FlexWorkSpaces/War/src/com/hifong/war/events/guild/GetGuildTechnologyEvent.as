/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package  com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	/**
	 * 取得军团可研究科技列表
	 * @param guildID
	 * @return list
	 */
	public final class GetGuildTechnologyEvent extends CairngormEvent
	{

		public static const GETGUILDTECHNOLOGY_EVENT:String = "com.hifong.war.events.GetGuildTechnologyEvent";
		public var guildID:int;
		public function GetGuildTechnologyEvent(guildID:int) 
		{
			super( GETGUILDTECHNOLOGY_EVENT );
			this.guildID=guildID;
		}
	}
}
