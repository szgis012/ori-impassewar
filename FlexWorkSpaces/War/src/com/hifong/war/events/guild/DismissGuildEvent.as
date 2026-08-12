/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class DismissGuildEvent extends CairngormEvent
	{

		public static const DISMISSGUILD_EVENT:String = "com.hifong.war.events.DismissGuildEvent";

		public var guildID:int;

		public function DismissGuildEvent(guildID:int) 
		{
			super( DISMISSGUILD_EVENT );
			this.guildID = guildID;
		}
	}
}
