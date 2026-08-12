/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ExitGuildEvent extends CairngormEvent
	{

		public var playerID:int;

		public static const EXITGUILD_EVENT:String = "com.hifong.war.events.ExitGuildEvent";

		public function ExitGuildEvent(playerID:int) 
		{
			super( EXITGUILD_EVENT );
			this.playerID = playerID;
		}
	}
}
