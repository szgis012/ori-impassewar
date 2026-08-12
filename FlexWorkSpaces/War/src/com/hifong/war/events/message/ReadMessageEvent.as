/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ReadMessageEvent extends CairngormEvent
	{

		public static const READMESSAGE_EVENT:String = "com.hifong.war.events.ReadMessageEvent";

		public var messageID:int;
		
		public function ReadMessageEvent(messageID:int) 
		{
			super( READMESSAGE_EVENT );
			this.messageID = messageID;
		}
	}
}
