/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.military
{
	import com.adobe.cairngorm.control.CairngormEvent;

	/**
	 * 获得出兵队列的详细信息
	 */ 
	public final class GetAttackDetailEvent extends CairngormEvent
	{

		public static const GETATTACKDETAIL_EVENT:String = "com.hifong.war.events.GetAttackDetailEvent";
		/** 出兵队列编号*/
		public var depoyQueueID:int;
		
		public function GetAttackDetailEvent(depoyQueueID:int) 
		{
			super( GETATTACKDETAIL_EVENT );
			this.depoyQueueID = depoyQueueID;
		}
	}
}
