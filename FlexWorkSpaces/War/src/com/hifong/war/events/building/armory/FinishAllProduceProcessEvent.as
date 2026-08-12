/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.armory
{
	import com.adobe.cairngorm.control.CairngormEvent;

    /**
     * 立即完成所有生产军械的进程(需要一定的道具)
     *
     */
	public final class FinishAllProduceProcessEvent extends CairngormEvent
	{
		public static const FINISHALLPRODUCEPROCESS_EVENT:String = "com.hifong.war.events.FinishAllProduceProcessEvent";
		
		public function FinishAllProduceProcessEvent() 
		{
			super( FINISHALLPRODUCEPROCESS_EVENT );
		}
	}
}
