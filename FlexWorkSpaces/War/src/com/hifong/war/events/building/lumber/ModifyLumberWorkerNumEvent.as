/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.lumber
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 木材厂工人数量调整事件
     *
     */
	public final class ModifyLumberWorkerNumEvent extends CairngormEvent
	{
		/** 工人数量 */
		public var workerNum:int;
		
		
		public static const MODIFYLUMBERWORKERNUM_EVENT:String = "com.hifong.war.events.ModifyLumberWorkerNumEvent";
		
		public function ModifyLumberWorkerNumEvent(num:int) 
		{
			super( MODIFYLUMBERWORKERNUM_EVENT );
			
			this.workerNum = num;
		}
	}
}
