/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildEventPageEvent extends CairngormEvent
	{

		public var guildID:int;

		public static const SHOWGUILDEVENTPAGE_EVENT:String = "com.hifong.war.events.ShowGuildEventPageEvent";

		public function ShowGuildEventPageEvent(guildID:int) 
		{
			super( SHOWGUILDEVENTPAGE_EVENT );
			this.guildID = guildID;
		}
	}
}
