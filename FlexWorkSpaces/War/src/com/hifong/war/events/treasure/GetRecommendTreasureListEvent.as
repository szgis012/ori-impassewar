/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.treasure
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得推荐宝物列表
	 * 
	 */ 
	public final class GetRecommendTreasureListEvent extends CairngormEvent
	{

		public static const GETRECOMMENDTREASURELIST_EVENT:String = "com.hifong.war.events.GetRecommendTreasureListEvent";

		public function GetRecommendTreasureListEvent() 
		{
			super( GETRECOMMENDTREASURELIST_EVENT );
		}
	}
}
