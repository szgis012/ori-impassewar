/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetServerTimeEvent extends CairngormEvent
	{

		public static const GETSERVERTIME_EVENT:String = "com.hifong.war.events.GetServerTimeEvent";

		public function GetServerTimeEvent() 
		{
			super( GETSERVERTIME_EVENT );
		}
	}
}
