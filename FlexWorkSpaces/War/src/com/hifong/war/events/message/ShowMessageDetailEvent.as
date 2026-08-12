/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.message
{
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import flash.display.DisplayObject;

    /**
     *
     * Defines the "ShowMessageDetail" use-case which represent a specific
     * user based event or system event.
     *
     * @see com.adobe.cairngorm.control.CairngormEvent
     *
     */
	public final class ShowMessageDetailEvent extends CairngormEvent
	{
		
		public var messageID:int;
		
		public var parentDisplayObject:DisplayObject;
		
		public static const SHOWMESSAGEDETAIL_EVENT:String = "com.hifong.war.events.ShowMessageDetailEvent";
		
		public function ShowMessageDetailEvent(messageID:int,parentDisplayObject:DisplayObject) 
		{
			super( SHOWMESSAGEDETAIL_EVENT );
			this.messageID = messageID;
			this.parentDisplayObject = parentDisplayObject;
		}
	}
}
