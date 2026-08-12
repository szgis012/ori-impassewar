/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RemoveGuildMemberByPlayerNameEvent extends CairngormEvent
	{

		public var guildID:int;

		public var playerName:String;

		public static const REMOVEGUILDMEMBERBYPLAYERNAME_EVENT:String = "com.hifong.war.events.RemoveGuildMemberByPlayerNameEvent";

		public function RemoveGuildMemberByPlayerNameEvent(guildID:int,playerName:String) 
		{
			super( REMOVEGUILDMEMBERBYPLAYERNAME_EVENT );
			this.guildID = guildID;
			this.playerName = playerName;
		}
	}
}
