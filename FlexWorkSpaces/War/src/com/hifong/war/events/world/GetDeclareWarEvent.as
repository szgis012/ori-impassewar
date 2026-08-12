/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetDeclareWarEvent extends CairngormEvent
	{

		public var playerID:int;
		
		public var targetPlayerID:int;

		public static const GETDECLAREWAR_EVENT:String = "com.hifong.war.events.GetDeclareWarEvent";

		public function GetDeclareWarEvent(playerID:int,targetPlayerID:int) 
		{
			super( GETDECLAREWAR_EVENT );
			this.playerID = playerID;
			this.targetPlayerID = targetPlayerID;
		}
	}
}
