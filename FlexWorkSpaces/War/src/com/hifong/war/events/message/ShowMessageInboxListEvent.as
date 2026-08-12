/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowMessageInboxListEvent extends CairngormEvent
	{
		
		public var playerID:int; 
		
		public var start:int;
		
		public var offset:int;

		public static const GETMESSAGEINBOXLIST_EVENT:String = "com.hifong.war.events.GetMessageInboxListEvent";

		public function ShowMessageInboxListEvent(playerID:int,start:int,offset:int) 
		{
			super( GETMESSAGEINBOXLIST_EVENT );
			this.playerID = playerID;
			this.start = start;
			this.offset = offset;
		}
	}
}
