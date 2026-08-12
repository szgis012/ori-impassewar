/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.task
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 领取任务奖励的事件
     *
     */
	public final class ReceiveRewardEvent extends CairngormEvent
	{
		/** 任务id */
		public var playerTaskID:int ;
		/** 任务类型 */
		public var taskType:int;
		
		public static const RECEIVEREWARD_EVENT:String = "com.hifong.war.events.ReceiveRewardEvent";
		
		public function ReceiveRewardEvent(ptID:int,type:int) 
		{
			super( RECEIVEREWARD_EVENT );
			this.playerTaskID = ptID;
			this.taskType = type;
		}
	}
}
