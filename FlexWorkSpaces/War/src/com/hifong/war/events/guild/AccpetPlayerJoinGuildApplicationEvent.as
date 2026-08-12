/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class AccpetPlayerJoinGuildApplicationEvent extends CairngormEvent
	{

		public static const ACCPETPLAYERAPPLICATION_EVENT:String = "com.hifong.war.events.AccpetPlayerApplicationEvent";

		public var playerID:int;
		
		public var guildID:int;

		public function AccpetPlayerJoinGuildApplicationEvent(playerID:int,guildID:int) 
		{
			super( ACCPETPLAYERAPPLICATION_EVENT );
			this.playerID = playerID;
			this.guildID = guildID;
		}
	}
}
