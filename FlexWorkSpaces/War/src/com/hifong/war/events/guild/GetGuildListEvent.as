/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetGuildListEvent extends CairngormEvent
	{

		public static const GETGUILDLIST_EVENT:String = "com.hifong.war.events.GetGuildListEvent";

		public var start:int;
		
		public var offset:int;

		public function GetGuildListEvent(start:int,offset:int) 
		{
			super( GETGUILDLIST_EVENT );
			this.start = start;
			this.offset = offset;
		}
	}
}
