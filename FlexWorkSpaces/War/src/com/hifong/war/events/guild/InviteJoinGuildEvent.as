/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class InviteJoinGuildEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var playerName:String;

		public static const INVITEJOINGUILD_EVENT:String = "com.hifong.war.events.InviteJoinGuildEvent";

		public function InviteJoinGuildEvent(guildID:int,playerName:String) 
		{
			super( INVITEJOINGUILD_EVENT );
			this.guildID = guildID;
			this.playerName = playerName;
		}
	}
}
