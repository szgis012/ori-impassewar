/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.stat
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.ResourcesTuneDelegate;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.events.stat.UpdateResourcesWorkerEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理同时更新四种资源工作人数的事件
     *
     */
	public final class UpdateResourcesWorkerCommand implements ICommand, IResponder
	{
		private var cityInfo:CityVO = ModelLocator.getInstance().cityInfo;
		
		/** 木材厂工作人数*/
		private var woodWorkerNum:int;
		
		/** 炼钢厂工作人数*/
		private var steelWorkerNum:int;
		
		/** 油田工作人数*/
		private var oilWorkerNum:int;
		
		/** 食物工作人数*/
		private var foodWorkerNum:int;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:UpdateResourcesWorkerEvent = event as UpdateResourcesWorkerEvent;
			var delegate:ResourcesTuneDelegate = new ResourcesTuneDelegate( this );
			this.woodWorkerNum = evt.woodWorkerNum;
			this.steelWorkerNum = evt.steelWorkerNum;
			this.oilWorkerNum = evt.oilWorkerNum;
			this.foodWorkerNum = evt.foodWorkerNum;
			delegate.updateResourcesWorkerEvent(cityInfo.cityID,woodWorkerNum,steelWorkerNum,oilWorkerNum,foodWorkerNum);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(cityInfo.cityID));
			dispatcher.dispatchEvent(new GetCityResourceConsumeEvent());
			dispatcher.dispatchEvent(new GetCityPopulationEvent(cityInfo.cityID));
			
			cityInfo.cityResource.woodWorkerNum = this.woodWorkerNum;
			cityInfo.cityResource.steelWorkerNum = this.steelWorkerNum;
			cityInfo.cityResource.oilWorkerNum = this.oilWorkerNum;
			cityInfo.cityResource.foodWorkerNum = this.foodWorkerNum;
			
			MsgBox.showMessage("修改成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
