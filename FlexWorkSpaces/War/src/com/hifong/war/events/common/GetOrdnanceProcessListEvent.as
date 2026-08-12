/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得军械生产进程列表事件
     *
     */
	public final class GetOrdnanceProcessListEvent extends CairngormEvent
	{
		public static const GETORDNANCEPROCESSLIST_EVENT:String = "com.hifong.war.events.GetOrdnanceProcessListEvent";
		
		public function GetOrdnanceProcessListEvent() 
		{
			super( GETORDNANCEPROCESSLIST_EVENT );
		}
	}
}
