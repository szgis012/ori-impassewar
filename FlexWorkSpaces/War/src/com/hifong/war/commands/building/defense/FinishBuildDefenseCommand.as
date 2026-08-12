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
	import com.hifong.war.events.building.defense.FinishBuildDefenseEvent;
	import com.hifong.war.events.building.defense.GetCityDefenseListEvent;
	import com.hifong.war.events.common.GetDefenseProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.ProductionQueueVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理客户端城防建造完成事件
     *
     */
	public final class FinishBuildDefenseCommand implements ICommand, IResponder
	{
		private var cityDefenseInfo:CityDefenseInfo = ModelLocator.getInstance().cityDefenseInfo;
		
		/**  城防建造的进程信息*/
		private var productionProcess:ProductionQueueVO;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:FinishBuildDefenseEvent = event as FinishBuildDefenseEvent;
			this.productionProcess = evt.productionProcess;
			var delegate:CityDefenseDelegate = new CityDefenseDelegate( this );
			delegate.clientProcessFinished(productionProcess.productionQueueID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			//更新城防信息
			dispatcher.dispatchEvent(new GetCityDefenseListEvent());
			//更新进程信息
			dispatcher.dispatchEvent(new GetDefenseProcessListEvent());
		}
		
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
