/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.techcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCurrentResearchingTechnologyEvent extends CairngormEvent
	{

		public var cityID:int;

		public static const GETCURRENTRESEARCHINGTECHNOLOGY_EVENT:String = "com.hifong.war.events.GetCurrentResearchingTechnologyEvent";

		public function GetCurrentResearchingTechnologyEvent(cityID:int) 
		{
			super( GETCURRENTRESEARCHINGTECHNOLOGY_EVENT );
			this.cityID = cityID;
		}
	}
}
