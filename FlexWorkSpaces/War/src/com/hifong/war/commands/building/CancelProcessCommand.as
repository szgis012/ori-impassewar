/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.BuildingDelegate;
	import com.hifong.war.events.building.CancelProcessEvent;
	import com.hifong.war.events.building.RefreshCityBuildingEvent;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetCityResourcesMaxEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理建造，升级，拆除取消事件
     */
	public final class CancelProcessCommand extends SequenceCommand implements ICommand, IResponder
	{
		public override function execute(event:CairngormEvent) : void
		{
			var evt:CancelProcessEvent = event as CancelProcessEvent;
			var delegate:BuildingDelegate = new BuildingDelegate( this );
			delegate.cancelProcess(evt.processQueueID);
			this.nextEvent = new RefreshCityBuildingEvent(evt.cityBuildingID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新城市资源信息 
			dispatcher.dispatchEvent(new GetCityResourcesEvent(ModelLocator.getInstance().cityInfo.cityID));
			//更新城市人口信息
			dispatcher.dispatchEvent(new GetCityPopulationEvent(ModelLocator.getInstance().cityInfo.cityID));
			//更新城市资源上限
			dispatcher.dispatchEvent(new GetCityResourcesMaxEvent());
			
			this.executeNextCommand();
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
