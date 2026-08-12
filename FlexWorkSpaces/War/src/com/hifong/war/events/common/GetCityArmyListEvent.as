/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得城市拥有的军队列表
     *
     */
	public final class GetCityArmyListEvent extends CairngormEvent
	{
		public static const GETCITYARMYLIST_EVENT:String = "com.hifong.war.events.GetCityArmyListEvent";
		
		public function GetCityArmyListEvent() 
		{
			super( GETCITYARMYLIST_EVENT );
		}
	}
}
