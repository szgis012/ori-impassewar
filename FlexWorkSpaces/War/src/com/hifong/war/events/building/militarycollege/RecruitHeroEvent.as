/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RecruitHeroEvent extends CairngormEvent
	{

		public var cityCandidacyHeroID:int;

		public static const RECRUITHERO_EVENT:String = "com.hifong.war.events.RecruitHeroEvent";

		public function RecruitHeroEvent(cityCandidacyHeroID:int) 
		{
			super( RECRUITHERO_EVENT );
			this.cityCandidacyHeroID = cityCandidacyHeroID;
		}
	}
}
