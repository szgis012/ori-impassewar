/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 取消城市的执政官设置
	 * 
	 */ 
	public final class CancelCityOfficerEvent extends CairngormEvent
	{

		public static const CANCELCITYOFFICER_EVENT:String = "com.hifong.war.events.CancelCityOfficerEvent";

		/** 城市指挥官编号 */
		public var cityHeroID:int;
		
		
		public function CancelCityOfficerEvent(cityHeroID:int) 
		{
			super( CANCELCITYOFFICER_EVENT );
			this.cityHeroID = cityHeroID;
		}
	}
}
