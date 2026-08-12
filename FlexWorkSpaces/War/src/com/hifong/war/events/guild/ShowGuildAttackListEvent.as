/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildAttackListEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var start:int;
		
		public var offset:int;

		public static const SHOWGUILDATTACKLIST_EVENT:String = "com.hifong.war.events.ShowGuildAttackListEvent";

		public function ShowGuildAttackListEvent(guildID:int,start:int,offset:int) 
		{
			super( SHOWGUILDATTACKLIST_EVENT );
			this.guildID = guildID;
			this.start = start;
			this.offset = offset;
		}
	}
}
