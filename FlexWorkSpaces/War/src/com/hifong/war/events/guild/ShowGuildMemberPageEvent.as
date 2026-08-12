/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildMemberPageEvent extends CairngormEvent
	{

		public var guildID:int;

		public static const SHOWGUILDMEMBERPAGE_EVENT:String = "com.hifong.war.events.ShowGuildMemberPageEvent";

		public function ShowGuildMemberPageEvent(guildID:int) 
		{
			super( SHOWGUILDMEMBERPAGE_EVENT );
			this.guildID = guildID;
		}
	}
}
