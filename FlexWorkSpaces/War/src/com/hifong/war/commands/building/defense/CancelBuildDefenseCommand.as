/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.defense
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityDefenseDelegate;
	import com.hifong.war.common.CityDefenseInfo;
	import com.hifong.war.events.building.defense.CancelBuildDefenseEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetDefenseProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 取消城防的建造
     *
     */
	public final class CancelBuildDefenseCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var cityDefenseInfo:CityDefenseInfo = model.cityDefenseInfo;
		
		/**  城防建造的进程信息*/
		private var productionProcess:ProductionQueueVO;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:CancelBuildDefenseEvent = event as CancelBuildDefenseEvent;
			this.productionProcess = evt.productionProcess;
			var delegate:CityDefenseDelegate = new CityDefenseDelegate( this );
			delegate.cancelBuildDefense(this.productionProcess.productionQueueID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();		
			//更新资源	
			dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));	
			//更新城防进程信息
			dispatcher.dispatchEvent(new GetDefenseProcessListEvent());
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
