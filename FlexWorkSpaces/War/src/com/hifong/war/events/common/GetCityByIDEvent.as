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
	public final class GetCityByIDEvent extends CairngormEvent
	{
		public static const GETCITYBYID_EVENT:String = "com.hifong.war.events.GetCityByIDEvent";
		
		/** 城市编号*/
		public var cityID:int;
		
		
		public function GetCityByIDEvent(cityID:int) 
		{
			super( GETCITYBYID_EVENT );
			this.cityID = cityID;
		}
	}
}
