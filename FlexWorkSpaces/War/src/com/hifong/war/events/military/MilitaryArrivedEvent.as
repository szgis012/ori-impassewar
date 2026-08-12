/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class MilitaryArrivedEvent extends CairngormEvent
	{

		public var depoyQueueID:int;

		public static const MILITARYARRIVED_EVENT:String = "com.hifong.war.events.MilitaryArrivedEvent";

		public function MilitaryArrivedEvent(depoyQueueID:int) 
		{
			super( MILITARYARRIVED_EVENT );
			this.depoyQueueID = depoyQueueID;
		}
	}
}
