/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.world
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 宣战事件
     *
     */
	public final class DeclareWarEvent extends CairngormEvent
	{
		
		public var playerID:int;
		
		public var targetPlayerID:int;
		
		public static const DECLAREWAR_EVENT:String = "com.hifong.war.events.DeclareWarEvent";
		
		public function DeclareWarEvent(playerID:int,targetPlayerID:int) 
		{
			super( DECLAREWAR_EVENT );
			this.playerID = playerID;
			this.targetPlayerID = targetPlayerID;
		}
	}
}
