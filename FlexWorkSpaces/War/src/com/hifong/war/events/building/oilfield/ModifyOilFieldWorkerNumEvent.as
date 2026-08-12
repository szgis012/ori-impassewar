/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.oilfield
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 油田工人人数调整事件
     *
     */
	public final class ModifyOilFieldWorkerNumEvent extends CairngormEvent
	{
		/** 工作人数 */
		public var workerNum:int;
		
		
		public static const MODIFYOILFIELDWORKERNUM_EVENT:String = "com.hifong.war.events.ModifyOilFieldWorkerNumEvent";
		
		public function ModifyOilFieldWorkerNumEvent(num:int) 
		{
			super( MODIFYOILFIELDWORKERNUM_EVENT );
			
			this.workerNum = num;
		}
	}
}
