/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得城市资源消耗的事件
	 * 
	 */ 
	public final class GetCityResourceConsumeEvent extends CairngormEvent
	{

		public static const GETCITYRESOURCECONSUME_EVENT:String = "com.hifong.war.events.GetCityResourceConsumeEvent";

		public function GetCityResourceConsumeEvent() 
		{
			super( GETCITYRESOURCECONSUME_EVENT );
		}
	}
}
