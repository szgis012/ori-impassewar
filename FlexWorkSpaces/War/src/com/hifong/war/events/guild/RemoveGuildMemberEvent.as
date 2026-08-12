/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RemoveGuildMemberEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var playerID:int;

		public static const REMOVEGUILDMEMBER_EVENT:String = "com.hifong.war.events.RemoveGuildMemberEvent";

		public function RemoveGuildMemberEvent(guildID:int,playerID:int) 
		{
			super( REMOVEGUILDMEMBER_EVENT );
			this.guildID = guildID;
			this.playerID = playerID;
		}
	}
}
