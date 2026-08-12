/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildMemberListEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var start:int;
		
		public var offset:int;

		public static const SHOWGUILDMEMBERLIST_EVENT:String = "com.hifong.war.events.ShowGuildMemberListEvent";

		public function ShowGuildMemberListEvent(guildID:int,start:int,offset:int) 
		{
			super( SHOWGUILDMEMBERLIST_EVENT );
			this.guildID = guildID;
			this.start = start;
			this.offset = offset;
		}
	}
}
