/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.commandcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetPlayerGuildAppInvListEvent extends CairngormEvent
	{

		public var playerID:int;

		public static const GETGUILDAPPINVLIST_EVENT:String = "com.hifong.war.events.GetGuildAppInvListEvent";

		public function GetPlayerGuildAppInvListEvent(playerID:int) 
		{
			super( GETGUILDAPPINVLIST_EVENT );
			this.playerID = playerID;
		}
	}
}
