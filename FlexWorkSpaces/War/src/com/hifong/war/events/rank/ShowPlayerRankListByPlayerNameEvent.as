/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowPlayerRankListByPlayerNameEvent extends CairngormEvent
	{

		public var playerName:String;

		public static const SHOWPLAYERRANKLISTBYPLAYERNAME_STEP1_EVENT:String = "com.hifong.war.events.ShowPlayerRankListByPlayerName_Step1Event";

		public function ShowPlayerRankListByPlayerNameEvent(playerName:String) 
		{
			super( SHOWPLAYERRANKLISTBYPLAYERNAME_STEP1_EVENT );
			this.playerName = playerName;
		}
	}
}
