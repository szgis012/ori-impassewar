/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.GuildPlayerVO;
	/**
     * 军团成员授权
     * @param guildPlayer(guildID,playerID,dutyName,permission)
     */
	public final class GuildMemberGrantEvent extends CairngormEvent
	{

		public var guildPlayer:GuildPlayerVO;

		public static const GUILDMEMBERGRANT_EVENT:String = "com.hifong.war.events.GuildMemberGrantEvent";

		public function GuildMemberGrantEvent(guildPlayer:GuildPlayerVO) 
		{
			super( GUILDMEMBERGRANT_EVENT );
			this.guildPlayer = guildPlayer;
		}
	}
}
