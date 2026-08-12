/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class HeroRenameEvent extends CairngormEvent
	{

		public var cityHeroID:int;
		
		public var newHeroName:String;

		public static const HERORENAME_EVENT:String = "com.hifong.war.events.HeroRenameEvent";

		public function HeroRenameEvent(cityHeroID:int,newHeroName:String) 
		{
			super( HERORENAME_EVENT );
			this.cityHeroID = cityHeroID;
			this.newHeroName = newHeroName;
		}
	}
}
