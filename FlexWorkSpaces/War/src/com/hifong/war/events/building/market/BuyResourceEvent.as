/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class BuyResourceEvent extends CairngormEvent
	{

		public var resTradeID:int;
		
		public var cityID:int;
		
		public var resourceType:int;
		
		public var start:int;
		
		public var offset:int;

		public static const BUYRESOURCE_EVENT:String = "com.hifong.war.events.BuyResourceEvent";

		public function BuyResourceEvent(resTradeID:int,cityID:int,resourceType:int,start:int,offset:int) 
		{
			super( BUYRESOURCE_EVENT );
			this.resTradeID = resTradeID;
			this.cityID = cityID;
			this.resourceType = resourceType;
			this.start = start;
			this.offset = offset;
		}
	}
}
