/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class StudySkillEvent extends CairngormEvent
	{

		public var cityHeroID:int;
		
		public var skillID:int;
		
		public static const STUDYSKILL_EVENT:String = "com.hifong.war.events.StudySkillEvent";

		public function StudySkillEvent(cityHeroID:int,skillID:int) 
		{
			super( STUDYSKILL_EVENT );
			this.cityHeroID = cityHeroID;
			this.skillID = skillID;
		}
	}
}
