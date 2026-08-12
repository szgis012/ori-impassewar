/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class AccelerateMilitaryRetruningEvent extends CairngormEvent
	{

		public static const ACCELERATEMILITARYRETRUNING_EVENT:String = "com.hifong.war.events.AccelerateMilitaryRetruningEvent";

		public var depoyQueueID:int;

		public function AccelerateMilitaryRetruningEvent(depoyQueueID:int) 
		{
			super( ACCELERATEMILITARYRETRUNING_EVENT );
			this.depoyQueueID = depoyQueueID;
		}
	}
}
