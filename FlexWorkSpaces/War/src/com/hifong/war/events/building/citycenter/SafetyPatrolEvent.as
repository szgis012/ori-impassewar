/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 安全巡查事件
     * 
     */
	public final class SafetyPatrolEvent extends CairngormEvent
	{
		public static const SAFETYPATROL_EVENT:String = "com.hifong.war.events.SafetyPatrolEvent";
		
		public function SafetyPatrolEvent() 
		{
			super( SAFETYPATROL_EVENT );
		}
	}
}
