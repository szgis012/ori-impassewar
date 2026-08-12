/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildAttackPageEvent extends CairngormEvent
	{

		public var guildID:int;

		public static const SHOWGUILDATTACKPAGE_EVENT:String = "com.hifong.war.events.ShowGuildAttackPageEvent";

		public function ShowGuildAttackPageEvent(guildID:int) 
		{
			super( SHOWGUILDATTACKPAGE_EVENT );
			this.guildID = guildID;
		}
	}
}
