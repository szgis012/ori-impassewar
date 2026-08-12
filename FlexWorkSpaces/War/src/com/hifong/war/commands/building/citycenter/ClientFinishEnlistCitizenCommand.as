/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.citycenter
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityCenterDelegate;
	import com.hifong.war.events.building.citycenter.ClientFinishEnlistCitizenEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理客户端完成征召市民进程时事件
     *
     */
	public final class ClientFinishEnlistCitizenCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		/** 征召市民的进程信息*/
		private var productionProcess:ProductionQueueVO;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:ClientFinishEnlistCitizenEvent = event as ClientFinishEnlistCitizenEvent;
			this.productionProcess = evt.productionProcess;
			var delegate:CityCenterDelegate = new CityCenterDelegate( this );
			delegate.clientEnlistCitizenFinished(evt.productionProcess.productionQueueID);
		}
		
		public function result(data:Object) : void
		{
			//取消进程
			model.enlistCitizenProcess = null;
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新人口信息
			dispatcher.dispatchEvent(new GetCityPopulationEvent(model.cityInfo.cityID));
			//更新产量信息
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			//更新资源消耗信息
			dispatcher.dispatchEvent(new GetCityResourceConsumeEvent());
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
