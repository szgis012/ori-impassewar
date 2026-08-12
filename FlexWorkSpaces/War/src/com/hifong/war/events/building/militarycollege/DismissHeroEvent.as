/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class DismissHeroEvent extends CairngormEvent
	{
		
		public var cityHeroID:int;

		public static const DISMISSHERO_EVENT:String = "com.hifong.war.events.DismissHeroEvent";

		public function DismissHeroEvent(cityHeroID:int) 
		{
			super( DISMISSHERO_EVENT );
			this.cityHeroID = cityHeroID;
		}
	}
}
