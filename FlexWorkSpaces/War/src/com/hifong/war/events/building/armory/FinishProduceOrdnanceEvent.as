/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.armory
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ProductionQueueVO;

    /**
     * 客户端完成军械生产时的事件
     *
     */
	public final class FinishProduceOrdnanceEvent extends CairngormEvent
	{
		/**  军械生产的进程信息*/
		public var productionProcess:ProductionQueueVO;
		
		public static const FINISHPRODUCEORDNANCE_EVENT:String = "com.hifong.war.events.FinishProduceOrdnanceEvent";
		
		public function FinishProduceOrdnanceEvent( productionProcess:ProductionQueueVO ) 
		{
			super( FINISHPRODUCEORDNANCE_EVENT );
			
			this.productionProcess = productionProcess;
		}
	}
}
