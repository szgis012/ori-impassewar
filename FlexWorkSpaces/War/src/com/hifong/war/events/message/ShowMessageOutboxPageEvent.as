/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowMessageOutboxPageEvent extends CairngormEvent
	{
		
		public var playerID:int;

		public static const SHOWMESSAGEOUTBOXPAGE_EVENT:String = "com.hifong.war.events.ShowMessageOutboxPageEvent";
		
		public function ShowMessageOutboxPageEvent(playerID:int) 
		{
			super( SHOWMESSAGEOUTBOXPAGE_EVENT );
			this.playerID = playerID;
		}
	}
}
