/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.market
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ResTransportationVO;

	public final class TransportResouceByCityPosEvent extends CairngormEvent
	{

		public var resTransportation:ResTransportationVO;

		public var cityID:int;

		public var targetPosX:int;
		
		public var targetPosY:int;

		public static const TRANSPORTRESOUCEBYCITYPOS_EVENT:String = "com.hifong.war.events.TransportResouceByCityPosEvent";

		public function TransportResouceByCityPosEvent(resTransportation:ResTransportationVO,cityID:int,targetPosX:int,targetPosY:int) 
		{
			super( TRANSPORTRESOUCEBYCITYPOS_EVENT );
			this.resTransportation = resTransportation;
			this.cityID = cityID;
			this.targetPosX = targetPosX;
			this.targetPosY = targetPosY;
		}
	}
}
