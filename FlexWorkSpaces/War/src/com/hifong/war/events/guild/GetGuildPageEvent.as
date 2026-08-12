/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetGuildPageEvent extends CairngormEvent
	{

		public static const GETGUILDPAGE_EVENT:String = "com.hifong.war.events.GetGuildPageEvent";

		public function GetGuildPageEvent() 
		{
			super( GETGUILDPAGE_EVENT );
		}
	}
}
