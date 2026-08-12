/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class DeleteMessageEvent extends CairngormEvent
	{

		public static const DELETEMESSAGE_EVENT:String = "com.hifong.war.events.DeleteMessageEvent";
		
		public var messageID:int;
		
		public var operator:int;
		
		public function DeleteMessageEvent(messageID:int,operator:int) 
		{
			super( DELETEMESSAGE_EVENT );
			this.messageID = messageID;
			this.operator = operator;
		}
	}
}
