/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RefreshCityCandidacyHeroEvent extends CairngormEvent
	{

		public static const REFRESHCITYCANDIDACYHERO_EVENT:String = "com.hifong.war.events.RefreshCityCandidacyHeroEvent";

		public function RefreshCityCandidacyHeroEvent() 
		{
			super( REFRESHCITYCANDIDACYHERO_EVENT );
		}
	}
}
