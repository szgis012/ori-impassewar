/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class MilitaryAttackArrivedEvent extends CairngormEvent
	{

		public var depoyQueueID:int;

		public static const MILITARYATTACKARRIVED_EVENT:String = "com.hifong.war.events.MilitaryAttackArrivedEvent";

		public function MilitaryAttackArrivedEvent(depoyQueueID:int) 
		{
			super( MILITARYATTACKARRIVED_EVENT );
			this.depoyQueueID = depoyQueueID;
		}
	}
}
