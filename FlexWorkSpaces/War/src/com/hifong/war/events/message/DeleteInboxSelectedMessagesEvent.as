/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class DeleteInboxSelectedMessagesEvent extends CairngormEvent
	{

		public static const DELETEINBOXSELECTEDMESSAGES_EVENT:String = "com.hifong.war.events.DeleteInboxSelectedMessagesEvent";
		
		public var messageInboxIDs:Array;
		
		public function DeleteInboxSelectedMessagesEvent(messageInboxIDs:Array) 
		{
			super( DELETEINBOXSELECTEDMESSAGES_EVENT );
			this.messageInboxIDs = messageInboxIDs;
		}
	}
}
