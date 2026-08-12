/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.common
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得城市的税率和治安信息 
	 * 
	 */ 
	public final class GetCityTaxAndSecurityEvent extends CairngormEvent
	{

		public static const GETCITYTAXANDSECURITY_EVENT:String = "com.hifong.war.events.GetCityTaxAndSecurityEvent";

		public function GetCityTaxAndSecurityEvent() 
		{
			super( GETCITYTAXANDSECURITY_EVENT );
		}
	}
}
