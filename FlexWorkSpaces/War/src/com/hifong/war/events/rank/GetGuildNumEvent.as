/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetGuildNumEvent extends CairngormEvent
	{

		public static const GETGUILDNUM_EVENT:String = "com.hifong.war.events.GetGuildNumEvent";

		public function GetGuildNumEvent() 
		{
			super( GETGUILDNUM_EVENT );
		}
	}
}
