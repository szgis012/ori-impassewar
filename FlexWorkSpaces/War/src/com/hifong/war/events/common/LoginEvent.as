/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 登陆系统事件
     *
     */
	public final class LoginEvent extends CairngormEvent
	{
		/** 用户名*/
		public var name:String;
		/** 密码*/
		//public var password:String;
		
		
		public static const LOGIN_EVENT:String = "com.hifong.war.events.LoginEvent";
		
		public function LoginEvent(name:String)
		{
			super( LOGIN_EVENT );
			
			this.name = name;
			//this.password = password;
		}
	}
}
