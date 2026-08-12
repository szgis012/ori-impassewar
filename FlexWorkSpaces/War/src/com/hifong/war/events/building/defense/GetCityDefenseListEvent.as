/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.defense
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     *  获得城市所有防御的信息事件
     *
     */
	public final class GetCityDefenseListEvent extends CairngormEvent
	{
		public static const GETCITYDEFENSELIST_EVENT:String = "com.hifong.war.events.GetCityDefenseListEvent";
		
		public function GetCityDefenseListEvent() 
		{
			super( GETCITYDEFENSELIST_EVENT );
		}
	}
}
