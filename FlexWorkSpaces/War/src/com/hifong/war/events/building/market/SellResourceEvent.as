/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ResTradeVO;

	public final class SellResourceEvent extends CairngormEvent
	{

		public var cityID:int;

		public var resTrade:ResTradeVO;

		public static const SELLRESOURCE_EVENT:String = "com.hifong.war.events.SellResourceEvent";

		public function SellResourceEvent(cityID:int,resTrade:ResTradeVO) 
		{
			super( SELLRESOURCE_EVENT );
			this.cityID = cityID;
			this.resTrade = resTrade;
		}
	}
}
