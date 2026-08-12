/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 设置城市执政官事件
	 */ 
	public final class SetCityOfficerEvent extends CairngormEvent
	{

		public static const SETCITYOFFICER_EVENT:String = "com.hifong.war.events.SetCityOfficerEvent";

		/** 要设置为执政官的指挥官编号*/
		public var cityHeroID:int;
		
		
		public function SetCityOfficerEvent(cityHeroID:int) 
		{
			super( SETCITYOFFICER_EVENT );
			this.cityHeroID = cityHeroID;
		}
	}
}
