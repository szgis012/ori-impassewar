/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetCityCandidacyHeroListEvent extends CairngormEvent
	{
		
		public var cityID:int;

		public static const GETCITYCANDIDACYHEROLIST_EVENT:String = "com.hifong.war.events.GetCityCandidacyHeroListEvent";

		public function GetCityCandidacyHeroListEvent(cityID:int) 
		{
			super( GETCITYCANDIDACYHEROLIST_EVENT );
			this.cityID = cityID;
		}
	}
}
