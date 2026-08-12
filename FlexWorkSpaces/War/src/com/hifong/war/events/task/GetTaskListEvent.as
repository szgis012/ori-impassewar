/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.task
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 获得任务类别的事件类
     *
     */
	public final class GetTaskListEvent extends CairngormEvent
	{
		/** 任务类型 */
		public var taskType:int ;
		
		public static const GETTASKLIST_EVENT:String = "com.hifong.war.events.GetTaskListEvent";
		
		public function GetTaskListEvent(type:int) 
		{
			super( GETTASKLIST_EVENT );
			this.taskType = type;
		}
	}
}
