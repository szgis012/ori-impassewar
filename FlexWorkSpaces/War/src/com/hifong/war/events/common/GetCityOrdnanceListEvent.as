/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得城市军械信息列表事件
     *
     */
	public final class GetCityOrdnanceListEvent extends CairngormEvent
	{
		public static const GETCITYORDNANCELIST_EVENT:String = "com.hifong.war.events.GetCityOrdnanceListEvent";
		
		public function GetCityOrdnanceListEvent() 
		{
			super( GETCITYORDNANCELIST_EVENT );
		}
	}
}
