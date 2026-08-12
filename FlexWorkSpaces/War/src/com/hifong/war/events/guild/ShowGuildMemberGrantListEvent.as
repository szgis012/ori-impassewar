/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildMemberGrantListEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var start:int;
		
		public var offset:int;
		
		public static const SHOWGUILDMEMBERGRANTLIST_EVENT:String = "com.hifong.war.events.ShowGuildMemberGrantListEvent";

		public function ShowGuildMemberGrantListEvent(guildID:int,start:int,offset:int) 
		{
			super( SHOWGUILDMEMBERGRANTLIST_EVENT );
			this.guildID = guildID;
			this.start = start;
			this.offset = offset;
		}
	}
}
