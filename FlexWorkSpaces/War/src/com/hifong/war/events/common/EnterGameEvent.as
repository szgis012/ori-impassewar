/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 用户通过登陆验证进入游戏的事件
	 * 
	 */ 
	public final class EnterGameEvent extends CairngormEvent
	{

		public static const ENTERGAME_EVENT:String = "com.hifong.war.events.EnterGameEvent";

		/** 登陆用户的编号*/
		public var userID:int;
		
		public function EnterGameEvent(userID:int) 
		{
			super( ENTERGAME_EVENT );
			this.userID = userID;
		}
	}
}
