/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class CancelInvitePlayerEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var playerID:int;

		public static const CANCELINVITEPLAYER_EVENT:String = "com.hifong.war.events.CancelInvitePlayerEvent";

		public function CancelInvitePlayerEvent(guildID:int,playerID:int) 
		{
			super( CANCELINVITEPLAYER_EVENT );
			this.guildID = guildID;
			this.playerID = playerID;
		}
	}
}
