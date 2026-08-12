/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityTradeQueueListEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCITYTRADEQUEUELIST_EVENT:String = "com.hifong.war.events.GetCityTradeQueueListEvent";

		public function GetCityTradeQueueListEvent(cityID:int) 
		{
			super( GETCITYTRADEQUEUELIST_EVENT );
			this.cityID = cityID;
		}
	}
}
