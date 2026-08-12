/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class CancelResourceSaleEvent extends CairngormEvent
	{
		
		public var cityID:int;
		
		public var resTradeID:int;

		public static const CANCELRESOURCESALE_EVENT:String = "com.hifong.war.events.CancelResourceSaleEvent";

		public function CancelResourceSaleEvent(cityID:int,resTradeID:int) 
		{
			super( CANCELRESOURCESALE_EVENT );
			this.cityID = cityID;
			this.resTradeID = resTradeID;
		}
	}
}
