/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.armory
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ProductionQueueVO;

    /**
     * 取消军械生产事件
     *
     */
	public final class CancelProduceOrdnanceEvent extends CairngormEvent
	{
		/**
		 * 生产进程
		 */ 
		public var productionProcess:ProductionQueueVO;
		
		public static const CANCELPRODUCEORDNANCE_EVENT:String = "com.hifong.war.events.CancelProduceOrdnanceEvent";
		
		public function CancelProduceOrdnanceEvent(productionProcess:ProductionQueueVO) 
		{
			super( CANCELPRODUCEORDNANCE_EVENT );
			
			this.productionProcess = productionProcess;
		}
	}
}
