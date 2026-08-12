/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.techcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class TechnologyResearchFinishedEvent extends CairngormEvent
	{

		public var cityTechnologyID:int;

		public static const TECHNOLOGYRESEARCHFINISHED_EVENT:String = "com.hifong.war.events.TechnologyResearchFinishedEvent";

		public function TechnologyResearchFinishedEvent(cityTechnologyID:int) 
		{
			super( TECHNOLOGYRESEARCHFINISHED_EVENT );
			this.cityTechnologyID = cityTechnologyID;
		}
	}
}
