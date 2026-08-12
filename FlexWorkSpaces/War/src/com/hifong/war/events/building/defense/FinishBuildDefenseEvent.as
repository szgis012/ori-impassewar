/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.events.building.defense
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.vo.ProductionQueueVO;

    /**
     * 完成城防建造事件
     *
     */
	public final class FinishBuildDefenseEvent extends CairngormEvent
	{
		public static const FINISHBUILDDEFENSE_EVENT:String = "com.hifong.war.events.FinishBuildDefenseEvent";
		
		/**  城防建造的进程信息*/
		public var productionProcess:ProductionQueueVO;
		
		public function FinishBuildDefenseEvent( productionProcess:ProductionQueueVO) 
		{
			super( FINISHBUILDDEFENSE_EVENT );
			
			this.productionProcess = productionProcess;
		}
	}
}
