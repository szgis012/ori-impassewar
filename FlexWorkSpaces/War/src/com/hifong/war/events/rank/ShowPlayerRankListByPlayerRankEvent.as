/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowPlayerRankListByPlayerRankEvent extends CairngormEvent
	{

		public var rank:int;

		public static const SHOWPLAYERRANKLISTBYPLAYERRANK_EVENT:String = "com.hifong.war.events.ShowPlayerRankListByPlayerRankEvent";

		public function ShowPlayerRankListByPlayerRankEvent(rank:int) 
		{
			super( SHOWPLAYERRANKLISTBYPLAYERRANK_EVENT );
			this.rank = rank;
		}
	}
}
