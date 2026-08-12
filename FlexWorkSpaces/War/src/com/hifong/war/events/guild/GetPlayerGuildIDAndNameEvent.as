/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetPlayerGuildIDAndNameEvent extends CairngormEvent
	{

		public static const GETPLAYERGUILDIDANDNAME_EVENT:String = "com.hifong.war.events.getPlayerGuildIDAndNameEvent";

		public var playerID:int;

		public function GetPlayerGuildIDAndNameEvent(playerID:int) 
		{
			super( GETPLAYERGUILDIDANDNAME_EVENT );
			this.playerID = playerID;
		}
	}
}
