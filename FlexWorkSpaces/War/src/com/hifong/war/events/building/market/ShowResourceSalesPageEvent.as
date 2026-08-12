/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ShowResourceSalesPageEvent extends CairngormEvent
	{

		public var cityID:int;

		public var resourceType:int;

		public static const SHOWRESOURCESALESPAGE_EVENT:String = "com.hifong.war.events.ShowResourceSalesPageEvent";

		public function ShowResourceSalesPageEvent(cityID:int,resourceType:int) 
		{
			super( SHOWRESOURCESALESPAGE_EVENT );
			this.cityID = cityID;
			this.resourceType = resourceType;
		}
	}
}
