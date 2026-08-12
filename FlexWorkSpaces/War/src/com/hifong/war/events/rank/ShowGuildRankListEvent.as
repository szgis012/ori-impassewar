/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildRankListEvent extends CairngormEvent
	{
		
		public var guildID:int;

		public static const SHOWGUILDRANKLIST_EVENT:String = "com.hifong.war.events.ShowGuildRankListEvent";

		public function ShowGuildRankListEvent(guildID:int) 
		{
			super( SHOWGUILDRANKLIST_EVENT );
			this.guildID = guildID;
		}
	}
}
