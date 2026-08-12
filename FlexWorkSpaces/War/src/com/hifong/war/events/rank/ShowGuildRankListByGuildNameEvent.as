/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildRankListByGuildNameEvent extends CairngormEvent
	{

		public var guildName:String;

		public static const SHOWGUILDRANKLISTBYGUILDNAME_EVENT:String = "com.hifong.war.events.ShowGuildRankListByGuildNameEvent";

		public function ShowGuildRankListByGuildNameEvent(guildName:String) 
		{
			super( SHOWGUILDRANKLISTBYGUILDNAME_EVENT );
			this.guildName = guildName;
		}
	}
}
