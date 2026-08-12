/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class AddCityHeroLoyaltyEvent extends CairngormEvent
	{

		public static const ADDCITYHEROLOYALTY_EVENT:String = "com.hifong.war.events.AddCityHeroLoyaltyEvent";

		public var cityHeroID:int;
		
		public var addLoyalty:int;

		public function AddCityHeroLoyaltyEvent(cityHeroID:int, addLoyalty:int) 
		{
			super( ADDCITYHEROLOYALTY_EVENT );
			this.cityHeroID = cityHeroID;
			this.addLoyalty = addLoyalty;
		}
	}
}
