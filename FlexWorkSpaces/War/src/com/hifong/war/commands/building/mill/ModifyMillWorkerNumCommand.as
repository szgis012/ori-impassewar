/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.mill
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.ResourcesTuneDelegate;
	import com.hifong.war.events.building.mill.ModifyMillWorkerNumEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
    /**
     * 处理食物工人人数调整事件
     *
     */
	public final class ModifyMillWorkerNumCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		private var workerNum:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:ModifyMillWorkerNumEvent = event as ModifyMillWorkerNumEvent;
			this.workerNum = evt.workerNum;
			var delegate:ResourcesTuneDelegate = new ResourcesTuneDelegate( this );
			delegate.modifyFoodWorkerNum(ModelLocator.getInstance().cityInfo.cityID,evt.workerNum);
		}
		
		public function result(data:Object) : void
		{
			//更新食物工人人数
			model.cityInfo.cityResource.foodWorkerNum = workerNum;
			
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新城市资源产量
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			//更新城市人口信息
			dispatcher.dispatchEvent(new GetCityPopulationEvent(model.cityInfo.cityID));
			
			
			MsgBox.showMessage("调整人数成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
