/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RefuseGuildInvitationEvent extends CairngormEvent
	{
		
		public var playerID:int;
		
		public var guildID:int;

		public static const REFUSEGUILDINVITATION_EVENT:String = "com.hifong.war.events.RefuseGuildInvitationEvent";

		public function RefuseGuildInvitationEvent(playerID:int,guildID:int) 
		{
			super( REFUSEGUILDINVITATION_EVENT );
			this.playerID = playerID;
			this.guildID = guildID;
		}
	}
}
