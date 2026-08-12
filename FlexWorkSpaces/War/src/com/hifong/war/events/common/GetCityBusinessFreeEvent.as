/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得城市空闲商人数量
	 */ 
	public final class GetCityBusinessFreeEvent extends CairngormEvent
	{

		public static const GETCITYBUSINESSFREE_EVENT:String = "com.hifong.war.events.GetCityBusinessFreeEvent";

		public function GetCityBusinessFreeEvent() 
		{
			super( GETCITYBUSINESSFREE_EVENT );
		}
	}
}
