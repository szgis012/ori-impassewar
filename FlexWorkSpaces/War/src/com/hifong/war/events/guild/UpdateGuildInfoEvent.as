/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.GuildVO;

	public final class UpdateGuildInfoEvent extends CairngormEvent
	{

		public var guild:GuildVO;

		public static const UPDATEGUILDINFO_EVENT:String = "com.hifong.war.events.UpdateGuildInfoEvent";

		public function UpdateGuildInfoEvent(guild:GuildVO) 
		{
			super( UPDATEGUILDINFO_EVENT );
			this.guild = guild;
		}
	}
}
