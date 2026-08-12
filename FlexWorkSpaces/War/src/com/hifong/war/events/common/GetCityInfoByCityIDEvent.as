/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得城市部分信息事件
	 */ 
	public final class GetCityInfoByCityIDEvent extends CairngormEvent
	{

		public static const GETCITYINFOBYCITYID_EVENT:String = "com.hifong.war.events.GetCityInfoByCityIDEvent";

		public function GetCityInfoByCityIDEvent() 
		{
			super( GETCITYINFOBYCITYID_EVENT );
		}
	}
}
