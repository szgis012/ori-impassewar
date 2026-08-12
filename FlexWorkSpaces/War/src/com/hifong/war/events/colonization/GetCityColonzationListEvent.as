/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.colonization
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityColonzationListEvent extends CairngormEvent
	{

		public static const GETCITYCOLONZATIONLIST_EVENT:String = "com.hifong.war.events.GetCityColonzationListEvent";

		public var cityID:int;

		public function GetCityColonzationListEvent(cityID:int) 
		{
			super( GETCITYCOLONZATIONLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
