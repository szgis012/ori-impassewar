/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ResTransportationVO;

	public final class TransportResouceByCityNameEvent extends CairngormEvent
	{

		public var resTransportation:ResTransportationVO;

		public var cityID:int;

		public var targetCityName:String;

		public static const TRANSPORTRESOUCEBYCITYNAME_EVENT:String = "com.hifong.war.events.TransportResouceByCityNameEvent";

		public function TransportResouceByCityNameEvent(resTransportation:ResTransportationVO,cityID:int,targetCityName:String) 
		{
			super( TRANSPORTRESOUCEBYCITYNAME_EVENT );
			this.resTransportation = resTransportation;
			this.cityID = cityID;
			this.targetCityName = targetCityName;
		}
	}
}
