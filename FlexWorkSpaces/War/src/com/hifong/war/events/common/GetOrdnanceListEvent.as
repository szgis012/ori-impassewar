/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得军械信息列表事件
     *
     */
	public final class GetOrdnanceListEvent extends CairngormEvent
	{
		public static const GETORDNANCELIST_EVENT:String = "com.hifong.war.events.GetOrdnanceListEvent";
		
		public function GetOrdnanceListEvent() 
		{
			super( GETORDNANCELIST_EVENT );
		}
	}
}
