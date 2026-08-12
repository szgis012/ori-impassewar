/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class IsPlayerNameExistedEvent extends CairngormEvent
	{

		public static const ISPLAYERNAMEEXISTED_EVENT:String = "com.hifong.war.events.IsPlayerNameExistedEvent";

		public var playerName:String;

		public function IsPlayerNameExistedEvent(playerName:String) 
		{
			super( ISPLAYERNAMEEXISTED_EVENT );
			this.playerName = playerName;
		}
		
	}
}
