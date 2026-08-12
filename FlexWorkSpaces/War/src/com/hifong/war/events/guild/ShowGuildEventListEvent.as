/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildEventListEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var start:int;
		
		public var offset:int;

		public static const SHOWGUILDEVENTLIST_EVENT:String = "com.hifong.war.events.ShowGuildEventListEvent";

		public function ShowGuildEventListEvent(guildID:int,start:int,offset:int) 
		{
			super( SHOWGUILDEVENTLIST_EVENT );
			this.guildID = guildID;
			this.start = start;
			this.offset = offset;
		}
	}
}
