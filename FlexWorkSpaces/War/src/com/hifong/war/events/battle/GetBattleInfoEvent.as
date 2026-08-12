/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.battle
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetBattleInfoEvent extends CairngormEvent
	{

		public var battleID:int;

		public static const GETBATTLEINFO_EVENT:String = "com.hifong.war.events.GetBattleInfoEvent";

		public function GetBattleInfoEvent(battleID:int) 
		{
			super( GETBATTLEINFO_EVENT );
			this.battleID = battleID;
		}
	}
}
