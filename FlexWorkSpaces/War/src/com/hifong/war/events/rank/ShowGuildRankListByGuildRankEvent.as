/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildRankListByGuildRankEvent extends CairngormEvent
	{

		public var rank:int;

		public static const SHOWGUILDRANKLISTBYGUILDRANK_EVENT:String = "com.hifong.war.events.ShowGuildRankListByGuildRankEvent";

		public function ShowGuildRankListByGuildRankEvent(rank:int) 
		{
			super( SHOWGUILDRANKLISTBYGUILDRANK_EVENT );
			this.rank = rank;
		}
	}
}
