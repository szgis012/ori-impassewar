/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.defense
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ProductionQueueVO;

    /**
     * 取消城防建造事件
     *
     */
	public final class CancelBuildDefenseEvent extends CairngormEvent
	{
		public static const CANCELBUILDDEFENSE_EVENT:String = "com.hifong.war.events.CancelBuildDefenseEvent";
		
		/**  城防建造的进程信息*/
		public var productionProcess:ProductionQueueVO;
		
		public function CancelBuildDefenseEvent(productionProcess:ProductionQueueVO) 
		{
			super( CANCELBUILDDEFENSE_EVENT );
			
			this.productionProcess = productionProcess;
		}
	}
}
