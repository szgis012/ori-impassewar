/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public final class GetDepoyQueueInfoEvent extends CairngormEvent
	{

		public var depoyQueueID:int;

		public static const GETDEPOYQUEUEINFO_EVENT:String = "com.hifong.war.events.GetDepoyQueueInfoEvent";

		public function GetDepoyQueueInfoEvent(depoyQueueID:int) 
		{
			super( GETDEPOYQUEUEINFO_EVENT );
			this.depoyQueueID = depoyQueueID;
		}
	}
}
