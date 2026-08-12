/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ResTransportationVO;

	public final class TransportResourceEvent extends CairngormEvent
	{

		public var resTransportation:ResTransportationVO;

		public var cityID:int;
		
		public var targetCityID:int;

		public static const TRANSPORTRESOURCE_EVENT:String = "com.hifong.war.events.TransportResourceEvent";

		public function TransportResourceEvent(resTransportation:ResTransportationVO,cityID:int,targetCityID:int) 
		{
			super( TRANSPORTRESOURCE_EVENT );
			this.resTransportation = resTransportation;
			this.cityID = cityID;
			this.targetCityID = targetCityID;
		}
	}
}
