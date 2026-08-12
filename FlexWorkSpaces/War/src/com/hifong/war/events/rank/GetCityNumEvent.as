/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.rank
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityNumEvent extends CairngormEvent
	{

		public static const GETCITYNUM_EVENT:String = "com.hifong.war.events.GetCityNumEvent";

		public function GetCityNumEvent() 
		{
			super( GETCITYNUM_EVENT );
		}
	}
}
