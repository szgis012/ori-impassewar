/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 建造，升级，拆除取消事件
     *
     */
	public final class CancelProcessEvent extends CairngormEvent
	{
		/** 进程编号 */
		public var processQueueID:int;
		/** 城市建筑编号 */
		public var cityBuildingID:int;
		
		public static const CANCELPROCESS_EVENT:String = "com.hifong.war.events.CancelProcessEvent";
		
		public function CancelProcessEvent(processQueueID:int,cityBuildingID:int) 
		{
			super( CANCELPROCESS_EVENT );
			
			this.processQueueID = processQueueID;
			this.cityBuildingID = cityBuildingID;
		}
	}
}
