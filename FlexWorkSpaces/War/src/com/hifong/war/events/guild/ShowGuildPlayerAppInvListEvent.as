/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildPlayerAppInvListEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public static const SHOWGUILDPLAYERAPPINVLIST_EVENT:String = "com.hifong.war.events.ShowGuildPlayerAppInvListEvent";

		public function ShowGuildPlayerAppInvListEvent(guildID:int) 
		{
			super( SHOWGUILDPLAYERAPPINVLIST_EVENT );
			this.guildID = guildID;
		}
	}
}
