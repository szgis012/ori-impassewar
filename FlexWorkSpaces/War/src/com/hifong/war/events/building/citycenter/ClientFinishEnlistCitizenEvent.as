/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.citycenter
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ProductionQueueVO;

    /**
     * 客户端完成征召市民进程时事件
     *
     */
	public final class ClientFinishEnlistCitizenEvent extends CairngormEvent
	{
		public static const CLIENTFINISHENLISTCITIZEN_EVENT:String = "com.hifong.war.events.ClientFinishEnlistCitizenEvent";
		
		/** 征召市民的进程信息*/
		public var productionProcess:ProductionQueueVO;
		
		public function ClientFinishEnlistCitizenEvent(productionProcess:ProductionQueueVO) 
		{
			super( CLIENTFINISHENLISTCITIZEN_EVENT );
			
			this.productionProcess = productionProcess;
		}
	}
}
