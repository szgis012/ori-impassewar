/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得侦察行动详情
	 * 
	 */ 
	public final class GetSpyDetailEvent extends CairngormEvent
	{

		public static const GETSPYDETAIL_EVENT:String = "com.hifong.war.events.GetSpyDetailEvent";

		/** 侦察进程编号*/
		public var spyQueueID:int;
		
		public function GetSpyDetailEvent(spyQueueID:int) 
		{
			super( GETSPYDETAIL_EVENT );
			this.spyQueueID = spyQueueID;
		}
	}
}
