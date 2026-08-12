/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得城市资源上限的事件
	 * 
	 */ 
	public final class GetCityResourcesMaxEvent extends CairngormEvent
	{

		public static const GETCITYRESOURCESMAX_EVENT:String = "com.hifong.war.events.GetCityResourcesMaxEvent";

		public function GetCityResourcesMaxEvent() 
		{
			super( GETCITYRESOURCESMAX_EVENT );
		}
	}
}
