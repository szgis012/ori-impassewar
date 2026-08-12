/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.techcenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class CancelResearchTechnologyEvent extends CairngormEvent
	{

		public var cityID:int;
		
		public static const CANCELRESEARCHTECHNOLOGY_EVENT:String = "com.hifong.war.events.CancelResearchTechnologyEvent";

		public function CancelResearchTechnologyEvent(cityID:int) 
		{
			super( CANCELRESEARCHTECHNOLOGY_EVENT );
			this.cityID = cityID;
		}
	}
}
