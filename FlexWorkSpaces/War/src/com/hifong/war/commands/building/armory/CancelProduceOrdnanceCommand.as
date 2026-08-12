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
	import com.hifong.war.events.building.armory.CancelProduceOrdnanceEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetOrdnanceProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理取消军械生产事件
     *
     */
	public final class CancelProduceOrdnanceCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		private var ordnanceInfo:OrdnanceInfo = ModelLocator.getInstance().ordnanceInfo;
		
		//生产进程
		private var productionProcess:ProductionQueueVO;
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:CancelProduceOrdnanceEvent = event as CancelProduceOrdnanceEvent;
			this.productionProcess = evt.productionProcess
			var delegate:ArmoryDelegate = new ArmoryDelegate( this );
			delegate.cancelProduceOrdnance(this.productionProcess.productionQueueID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();		
			//更新资源	
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));	
			//更新军械生产进程信息
			dispatcher.dispatchEvent(new GetOrdnanceProcessListEvent());
			
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
