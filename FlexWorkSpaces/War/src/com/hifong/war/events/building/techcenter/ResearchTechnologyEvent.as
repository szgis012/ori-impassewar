/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.techcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ResearchTechnologyEvent extends CairngormEvent
	{

		public var cityID:int;
		
		public var technologyID:int;

		public static const RESEARCHTECHNOLOGY_EVENT:String = "com.hifong.war.events.ResearchTechnologyEvent";

		public function ResearchTechnologyEvent(cityID:int,technologyID:int) 
		{
			super( RESEARCHTECHNOLOGY_EVENT );
			this.cityID = cityID;
			this.technologyID = technologyID;
		}
	}
}
