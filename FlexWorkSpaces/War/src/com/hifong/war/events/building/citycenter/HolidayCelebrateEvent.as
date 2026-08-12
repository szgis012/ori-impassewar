/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 节日庆典事件
     *
     */
	public final class HolidayCelebrateEvent extends CairngormEvent
	{
		public static const HOLIDAYCELEBRATE_EVENT:String = "com.hifong.war.events.HolidayCelebrateEvent";
		
		public function HolidayCelebrateEvent() 
		{
			super( HOLIDAYCELEBRATE_EVENT );
		}
	}
}
