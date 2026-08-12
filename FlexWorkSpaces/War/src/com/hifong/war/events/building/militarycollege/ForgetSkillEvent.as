/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class ForgetSkillEvent extends CairngormEvent
	{

		public var cityHeroID:int;
		
		public var heroSkillID:int;
		
		public static const FORGETSKILL_EVENT:String = "com.hifong.war.events.ForgetSkillEvent";

		public function ForgetSkillEvent(cityHeroID:int,heroSkillID:int) 
		{
			super( FORGETSKILL_EVENT );
			this.cityHeroID = cityHeroID;
			this.heroSkillID = heroSkillID;
		}
	}
}
