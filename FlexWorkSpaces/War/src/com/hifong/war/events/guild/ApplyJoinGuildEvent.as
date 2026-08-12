/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ApplyJoinGuildEvent extends CairngormEvent
	{

		public static const APPLYJOINGUILD_EVENT:String = "com.hifong.war.events.ApplyJoinGuildEvent";

		public var guildID:int;
		
		public var playerID:int;

		public function ApplyJoinGuildEvent(guildID:int,playerID:int) 
		{
			super( APPLYJOINGUILD_EVENT );
			this.guildID = guildID;
			this.playerID = playerID;
		}
	}
}
