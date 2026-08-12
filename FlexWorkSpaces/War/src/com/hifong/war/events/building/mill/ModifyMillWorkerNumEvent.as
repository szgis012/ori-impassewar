/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.mill
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 磨坊工作人数调整事件
     *
     */
	public final class ModifyMillWorkerNumEvent extends CairngormEvent
	{
		/** 工作人数 */
		public var workerNum:int;
		
		public static const MODIFYMILLWORKERNUM_EVENT:String = "com.hifong.war.events.ModifyMillWorkerNumEvent";
		
		public function ModifyMillWorkerNumEvent(num:int) 
		{
			super( MODIFYMILLWORKERNUM_EVENT );
			
			this.workerNum = num;
		}
	}
}
