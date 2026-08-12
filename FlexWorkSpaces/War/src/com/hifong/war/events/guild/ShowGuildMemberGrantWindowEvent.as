/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import flash.display.DisplayObject;

	public final class ShowGuildMemberGrantWindowEvent extends CairngormEvent
	{

		public var guildID:int;
		
		public var playerID:int;
		
		public var parentDisplayObject:DisplayObject;

		public static const SHOWGUILDMEMBERGRANTWINDOW_EVENT:String = "com.hifong.war.events.ShowGuildMemberGrantWindowEvent";

		public function ShowGuildMemberGrantWindowEvent(guildID:int,playerID:int,parentDisplayObject:DisplayObject) 
		{
			super( SHOWGUILDMEMBERGRANTWINDOW_EVENT );
			this.guildID = guildID;
			this.playerID = playerID;
			this.parentDisplayObject = parentDisplayObject;
		}
	}
}
