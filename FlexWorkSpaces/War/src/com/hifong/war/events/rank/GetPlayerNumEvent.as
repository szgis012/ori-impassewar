/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetPlayerNumEvent extends CairngormEvent
	{

		public static const GETPLAYERNUM_EVENT:String = "com.hifong.war.events.GetPlayerNumEvent";

		public function GetPlayerNumEvent() 
		{
			super( GETPLAYERNUM_EVENT );
		}
	}
}
