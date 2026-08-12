/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class UseHeroItemEvent extends CairngormEvent
	{

		public static const USERHEROITEM_EVENT:String = "com.hifong.war.events.UserHeroItemEvent";

		public var playerID:int;
		
		public var treasureID:int;
		
		public var params:Object;

		public function UseHeroItemEvent(playerID:int,treasureID:int,params:Object) 
		{
			super( USERHEROITEM_EVENT );
			this.playerID = playerID;
			this.treasureID = treasureID;
			this.params = params;
		}
	}
}
