/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.player
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ViewTargetPlayerInfoEvent extends CairngormEvent
	{

		public static const VIEWTARGETPLAYERINFO_EVENT:String = "com.hifong.war.events.ViewTargetPlayerInfoEvent";

		public var playerID:int;

		public function ViewTargetPlayerInfoEvent(playerID:int) 
		{
			super( VIEWTARGETPLAYERINFO_EVENT );
			this.playerID = playerID;
		}
	}
}
