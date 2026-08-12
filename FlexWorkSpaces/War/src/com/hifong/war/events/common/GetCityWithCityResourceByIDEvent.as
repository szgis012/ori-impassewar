/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得城市信息的事件
	 * 
	 */ 
	public final class GetCityWithCityResourceByIDEvent extends CairngormEvent
	{
		public static const GETCITY_WITH_CITYRESOURCE_BYID_EVENT:String = "com.hifong.war.events.GetCityWithCityResourceByIDEvent";
		
		/** 城市编号*/
		public var cityID:int;
		
		
		public function GetCityWithCityResourceByIDEvent(cityID:int) 
		{
			super( GETCITY_WITH_CITYRESOURCE_BYID_EVENT );
			this.cityID = cityID;
		}
	}
}
