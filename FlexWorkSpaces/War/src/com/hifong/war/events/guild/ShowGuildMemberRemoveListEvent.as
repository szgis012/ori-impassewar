/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildMemberRemoveListEvent extends CairngormEvent
	{
		
		public var guildID:int;
		
		public static const SHOWGUILDMEMBERREMOVELIST_EVENT:String = "com.hifong.war.events.ShowGuildMemberRemoveListEvent";

		public function ShowGuildMemberRemoveListEvent(guildID:int) 
		{
			super( SHOWGUILDMEMBERREMOVELIST_EVENT );
			this.guildID = guildID;
		}
	}
}
