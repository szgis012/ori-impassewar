/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.stat
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ExchangedCityResourcesEvent extends CairngormEvent
	{

		public static const EXCHANGEDCITYRESOURCES_EVENT:String = "com.hifong.war.events.ExchangedCityResourcesEvent";

		public var cityID:int;
		
		public var exchangedWoodNum:Number;
		
		public var exchangedSteelNum:Number;
		
		public var exchangedOilNum:Number;
		
		public var exchangedFoodNum:Number;

		public function ExchangedCityResourcesEvent(cityID:int, exchangedWoodNum:Number, exchangedSteelNum:Number, exchangedOilNum:Number, exchangedFoodNum:Number) 
		{
			super( EXCHANGEDCITYRESOURCES_EVENT );
			this.cityID = cityID;
			this.exchangedWoodNum = exchangedWoodNum;
			this.exchangedSteelNum = exchangedSteelNum;
			this.exchangedOilNum = exchangedOilNum;
			this.exchangedFoodNum = exchangedFoodNum;
		}
	}
}
