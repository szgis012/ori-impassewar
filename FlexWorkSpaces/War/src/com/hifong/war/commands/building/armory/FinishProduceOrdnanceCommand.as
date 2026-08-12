/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.armory
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.ArmoryDelegate;
	import com.hifong.war.common.OrdnanceInfo;
	import com.hifong.war.events.building.armory.FinishProduceOrdnanceEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetOrdnanceProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityOrdnanceVO;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
    /**
     * 处理客户端完成军械生产时的事件
     *
     */
	public final class FinishProduceOrdnanceCommand implements ICommand, IResponder
	{
		private var ordnanceInfo:OrdnanceInfo = ModelLocator.getInstance().ordnanceInfo;
		
		/**  军械生产的进程信息*/
		private var productionProcess:ProductionQueueVO;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:FinishProduceOrdnanceEvent = event as FinishProduceOrdnanceEvent;
			this.productionProcess = evt.productionProcess;
			var delegate:ArmoryDelegate = new ArmoryDelegate( this );
			delegate.clientProcessFinished(this.productionProcess.productionQueueID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			//刷新军械信息
			dispatcher.dispatchEvent(new GetCityOrdnanceListEvent());
			dispatcher.dispatchEvent(new GetOrdnanceProcessListEvent());
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
