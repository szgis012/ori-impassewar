/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowMessageOutboxListEvent extends CairngormEvent
	{
		
		public var playerID:int; 
		
		public var start:int;
		
		public var offset:int;
		
		public static const GETMESSAGEOUTBOXLIST_EVENT:String = "com.hifong.war.events.GetMessageOutboxListEvent";
		
		public function ShowMessageOutboxListEvent(playerID:int,start:int,offset:int) 
		{
			super( GETMESSAGEOUTBOXLIST_EVENT );
			this.playerID = playerID;
			this.start = start;
			this.offset = offset;
		}
	}
}
