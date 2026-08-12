/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class DeleteOutboxSelectedMessagesEvent extends CairngormEvent
	{

		public static const DELETEOUTBOXSELECTEDMESSAGES_EVENT:String = "com.hifong.war.events.DeleteOutboxSelectedMessagesEvent";
		
		public var messageOutboxIDs:Array;
		
		
		public function DeleteOutboxSelectedMessagesEvent(messageOutboxIDs:Array) 
		{
			super( DELETEOUTBOXSELECTEDMESSAGES_EVENT );
			this.messageOutboxIDs = messageOutboxIDs;
		}
	}
}
