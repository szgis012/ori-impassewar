/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class LevelUpSkillEvent extends CairngormEvent
	{

		public var cityHeroID:int;
		
		public var heroSkillID:int;

		public static const SKILLLEVELUP_EVENT:String = "com.hifong.war.events.SkillLevelUpEvent";

		public function LevelUpSkillEvent(cityHeroID:int,heroSkillID:int) 
		{
			super( SKILLLEVELUP_EVENT );
			this.cityHeroID = cityHeroID;
			this.heroSkillID = heroSkillID;
		}
	}
}
