/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class CancelApplyJoinGuildEvent extends CairngormEvent
	{

		public static const CANCELAPPLYJOINGUILD_EVENT:String = "com.hifong.war.events.CancelApplyJoinGuildEvent";

		public var guildID:int;
		
		public var playerID:int;

		public function CancelApplyJoinGuildEvent(guildID:int,playerID:int) 
		{
			super( CANCELAPPLYJOINGUILD_EVENT );
			this.guildID = guildID;
			this.playerID = playerID;
		}
	}
}
