/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class RecallMilitaryEvent extends CairngormEvent
	{

		public static const RECALLMILITARY_EVENT:String = "com.hifong.war.events.RecallMilitaryEvent";

		public var depoyQueueID:int;

		public function RecallMilitaryEvent(depoyQueueID:int) 
		{
			super( RECALLMILITARY_EVENT );
			this.depoyQueueID = depoyQueueID;
		}
	}
}
