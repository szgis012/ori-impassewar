/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得城防建造进程列表事件
     *
     */
	public final class GetDefenseProcessListEvent extends CairngormEvent
	{
		public static const GETDEFENSEPROCESSLIST_EVENT:String = "com.hifong.war.events.GetDefenseProcessListEvent";
		
		public function  GetDefenseProcessListEvent() 
		{
			super( GETDEFENSEPROCESSLIST_EVENT );
		}
	}
}
