/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class LoadGameInfoEvent extends CairngormEvent
	{

		public static const LOADGAMEINFO_EVENT:String = "com.hifong.war.events.LoadGameInfoEvent";

		public var playerID:int;
		
		public var cityID:int;

		public function LoadGameInfoEvent(playerID:int,cityID:int) 
		{
			super( LOADGAMEINFO_EVENT );
			this.playerID = playerID;
			this.cityID = cityID;
		}
	}
}
