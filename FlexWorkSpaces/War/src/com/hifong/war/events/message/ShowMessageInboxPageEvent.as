/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowMessageInboxPageEvent extends CairngormEvent
	{

		public var playerID:int;

		public static const SHOWMESSAGEINBOXPAGE_EVENT:String = "com.hifong.war.events.ShowMessageInboxPageEvent";
		
		public function ShowMessageInboxPageEvent(playerID:int) 
		{
			super( SHOWMESSAGEINBOXPAGE_EVENT );
			this.playerID = playerID;
		}
		
	}
}
