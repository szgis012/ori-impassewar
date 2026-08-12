/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetGuildListByGuildNameEvent extends CairngormEvent
	{

		public static const GETGUILDLISTBYGUILDNAME_EVENT:String = "com.hifong.war.events.GetGuildListByGuildNameEvent";

		public var guildName:String;

		public function GetGuildListByGuildNameEvent(guildName:String) 
		{
			super( GETGUILDLISTBYGUILDNAME_EVENT );
			this.guildName = guildName;
		}
	}
}
