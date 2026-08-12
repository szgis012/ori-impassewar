/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.MessageOutboxVO;

	public final class SendMessageEvent extends CairngormEvent
	{
		
		public var message:MessageOutboxVO;
		
		public static const SENDMESSAGE_EVENT:String = "com.hifong.war.events.SendMessageEvent";
		
		public function SendMessageEvent(msg:MessageOutboxVO) 
		{
			super( SENDMESSAGE_EVENT );
			this.message = msg;
		}
	}
}
