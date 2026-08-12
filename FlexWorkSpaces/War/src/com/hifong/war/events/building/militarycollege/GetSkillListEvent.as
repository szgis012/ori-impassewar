/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.militarycollege
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetSkillListEvent extends CairngormEvent
	{

		public static const GETSKILLLIST_EVENT:String = "com.hifong.war.events.GetSkillListEvent";

		public function GetSkillListEvent() 
		{
			super( GETSKILLLIST_EVENT );
		}
	}
}
