/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得玩家的信息
	 * 
	 */ 
	public final class GetPlayerInfoEvent extends CairngormEvent
	{

		public static const GETPLAYERINFO_EVENT:String = "com.hifong.war.events.GetPlayerInfoEvent";
		/** 玩家编号*/
		public var playerID:int;
		
		public function GetPlayerInfoEvent(playerID:int) 
		{
			super( GETPLAYERINFO_EVENT );
			this.playerID = playerID;
		}
	}
}
