/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 登陆时加载用户基础数据的事件
	 * 
	 */ 
	public final class LoadPlayerGlobalDataEvent extends CairngormEvent
	{
		public static const LOADPLAYERGLOBALDATA_EVENT:String = "com.hifong.war.events.LoadPlayerGlobalDataEvent";

		/** 用户编号*/
		public var userID:int;
		
		
		public function LoadPlayerGlobalDataEvent(userID:int) 
		{
			super( LOADPLAYERGLOBALDATA_EVENT );
			this.userID = userID;
		}
	}
}
