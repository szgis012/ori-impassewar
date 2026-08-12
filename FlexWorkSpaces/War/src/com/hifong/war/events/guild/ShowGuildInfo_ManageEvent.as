/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.guild
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowGuildInfo_ManageEvent extends CairngormEvent
	{

		public static const SHOWGUILDINFO_MANAGE_EVENT:String = "com.hifong.war.events.ShowGuildInfo_ManageEvent";

		public function ShowGuildInfo_ManageEvent() 
		{
			super( SHOWGUILDINFO_MANAGE_EVENT );
		}
	}
}
