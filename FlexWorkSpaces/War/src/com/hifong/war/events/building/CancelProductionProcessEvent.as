/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ProductionQueueVO;

    /**
     * 取消生产类制造过程的事件
     *
     */
	public final class CancelProductionProcessEvent extends CairngormEvent
	{
		public var productionProcess:ProductionQueueVO;
		
		public static const CANCELPRODUCTIONPROCESS_EVENT:String = "com.hifong.war.events.CancelProductionProcessEvent";
		
		public function CancelProductionProcessEvent(productionProcess:ProductionQueueVO) 
		{
			super( CANCELPRODUCTIONPROCESS_EVENT );
			
			this.productionProcess = productionProcess;
		}
	}
}
