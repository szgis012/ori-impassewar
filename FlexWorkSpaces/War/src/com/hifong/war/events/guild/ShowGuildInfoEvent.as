/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildInfoEvent extends CairngormEvent
	{

		public var guildID:int;

		public static const SHOWGUILDINFO_EVENT:String = "com.hifong.war.events.ShowGuildInfoEvent";
		
		public function ShowGuildInfoEvent(guildID:int) 
		{
			super( SHOWGUILDINFO_EVENT );
			this.guildID = guildID;
		}
	}
}
