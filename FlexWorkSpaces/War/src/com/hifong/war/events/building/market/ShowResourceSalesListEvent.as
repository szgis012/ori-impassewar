/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowResourceSalesListEvent extends CairngormEvent
	{

		public var cityID:int;
		
		public var resourceType:int;
		
		public var start:int;
		
		public var offset:int;

		public static const SHOWRESOURCESALESLIST_EVENT:String = "com.hifong.war.events.ShowResourceSalesListEvent";

		public function ShowResourceSalesListEvent(cityID:int,resourceType:int,start:int,offset:int) 
		{
			super( SHOWRESOURCESALESLIST_EVENT );
			this.cityID = cityID;
			this.resourceType = resourceType;
			this.start = start;
			this.offset = offset;
		}
	}
}
