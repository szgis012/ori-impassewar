/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得城市军事行动信息列表
	 * 
	 */ 
	public final class GetMilitaryActionListEvent extends CairngormEvent
	{

		public static const GETMILITARYACTIONLIST_EVENT:String = "com.hifong.war.events.GetMilitaryActionListEvent";

		public function GetMilitaryActionListEvent() 
		{
			super( GETMILITARYACTIONLIST_EVENT );
		}
	}
}
