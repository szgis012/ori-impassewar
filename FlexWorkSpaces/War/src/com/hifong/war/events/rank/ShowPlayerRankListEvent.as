/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowPlayerRankListEvent extends CairngormEvent
	{

		public var playerID:int;

		public static const SHOWPLAYERRANKLIST_STEP1_EVENT:String = "com.hifong.war.events.ShowPlayerRankList_Step1Event";

		public function ShowPlayerRankListEvent(playerID:int) 
		{
			super( SHOWPLAYERRANKLIST_STEP1_EVENT );
			this.playerID = playerID;
		}
	}
}
