/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 城市改名事件
     *
     */
	public final class ChangeCityNameEvent extends CairngormEvent
	{
		/** 城市的新名字 */
		public var newCityName:String;
		
		public static const CHANGECITYNAME_EVENT:String = "com.hifong.war.events.ChangeCityNameEvent";
		
		public function ChangeCityNameEvent(newCityName:String) 
		{
			super( CHANGECITYNAME_EVENT );
			
			this.newCityName = newCityName;
		}
	}
}
