/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class IsCityNameExistedEvent extends CairngormEvent
	{

		public static const ISCITYNAMEEXISTED_EVENT:String = "com.hifong.war.events.IsCityNameExistedEvent";

		public var cityName:String;

		public function IsCityNameExistedEvent(cityName:String) 
		{
			super( ISCITYNAMEEXISTED_EVENT );
			this.cityName = cityName;
		}
	}
}
