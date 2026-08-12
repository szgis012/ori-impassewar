/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class InitGuildManageEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var playerID:int;

		public static const INITGUILDMANAGE_EVENT:String = "com.hifong.war.events.InitGuildManageEvent";

		public function InitGuildManageEvent(guildID:int,playerID:int) 
		{
			super( INITGUILDMANAGE_EVENT );
			this.guildID = guildID;
			this.playerID = playerID;
		}
	}
}
