/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.GuildVO;

	public final class CreateGuildEvent extends CairngormEvent
	{

		public var guild:GuildVO;

		public static const CREATEGUILD_EVENT:String = "com.hifong.war.events.CreateGuildEvent";

		public function CreateGuildEvent(guild:GuildVO) 
		{
			super( CREATEGUILD_EVENT );
			this.guild = guild;
		}
	}
}
