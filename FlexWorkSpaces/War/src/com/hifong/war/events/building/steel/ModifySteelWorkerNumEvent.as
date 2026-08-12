/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.steel
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 钢铁厂工人数量调整事件
     *
     */
	public final class ModifySteelWorkerNumEvent extends CairngormEvent
	{
		/** 工作人数 */
		public var workerNum:int;
		
		
		public static const MODIFYSTEELWORKERNUM_EVENT:String = "com.hifong.war.events.ModifySteelWorkerNumEvent";
		
		public function ModifySteelWorkerNumEvent(num:int) 
		{
			super( MODIFYSTEELWORKERNUM_EVENT );
			
			this.workerNum = num;
		}
	}
}
