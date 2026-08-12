/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class AcceptGuildInvitationEvent extends CairngormEvent
	{
		
		public var playerID:int;
		
		public var guildID:int;

		public static const ACCEPTGUILDINVITATION_EVENT:String = "com.hifong.war.events.AcceptGuildInvitationEvent";

		public function AcceptGuildInvitationEvent(playerID:int,guildID:int) 
		{
			super( ACCEPTGUILDINVITATION_EVENT );
			this.playerID = playerID;
			this.guildID = guildID;
		}
	}
}
