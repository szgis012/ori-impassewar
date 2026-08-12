/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class MilitaryDefenseArrivedEvent extends CairngormEvent
	{

		public var depoyQueueID:int

		public static const MILITARYDEFENSEARRIVED_EVENT:String = "com.hifong.war.events.MilitaryDefenseArrivedEvent";

		public function MilitaryDefenseArrivedEvent(depoyQueueID:int) 
		{
			super( MILITARYDEFENSEARRIVED_EVENT );
			this.depoyQueueID = depoyQueueID;
		}
	}
}
