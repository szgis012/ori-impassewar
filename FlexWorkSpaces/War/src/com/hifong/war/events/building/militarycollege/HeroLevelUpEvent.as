/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class HeroLevelUpEvent extends CairngormEvent
	{

		public var cityHeroID:int;

		public static const HEROLEVELUP_EVENT:String = "com.hifong.war.events.HeroLevelUpEvent";

		public function HeroLevelUpEvent(cityHeroID:int) 
		{
			super( HEROLEVELUP_EVENT );
			this.cityHeroID = cityHeroID;
		}
	}
}
