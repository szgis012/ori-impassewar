/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RefusePlayerJoinGuildApplicationEvent extends CairngormEvent
	{

		public static const REFUSEGUILDAPPLICATION_EVENT:String = "com.hifong.war.events.RefuseGuildApplicationEvent";

		public var playerID:int;
		
		public var guildID:int;
		
		public function RefusePlayerJoinGuildApplicationEvent(playerID:int,guildID:int) 
		{
			super( REFUSEGUILDAPPLICATION_EVENT );
			this.playerID = playerID;
			this.guildID = guildID;
		}
	}
}
