/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得兵种信息列表事件
     *
     */
	public final class GetArmyListEvent extends CairngormEvent
	{
		public static const GETARMYLIST_EVENT:String = "com.hifong.war.events.GetArmyListEvent";
		
		public function GetArmyListEvent() 
		{
			super( GETARMYLIST_EVENT );
		}
	}
}
