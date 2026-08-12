/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.defense
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.CityDefenseDelegate;
	import com.hifong.war.events.building.defense.FinishAllBuildDefenseEvent;
	import com.hifong.war.events.building.defense.GetCityDefenseListEvent;
	import com.hifong.war.events.common.GetDefenseProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理完成所有的城防建造过程(需要道具)
     *
     */
	public final class FinishAllBuildDefenseCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:FinishAllBuildDefenseEvent = event as FinishAllBuildDefenseEvent;
			var delegate:CityDefenseDelegate = new CityDefenseDelegate( this );
			delegate.finishAllBuildProcess(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			//获得城防数量信息
			dispatcher.dispatchEvent(new GetCityDefenseListEvent());
			//重新加载进程列表
			dispatcher.dispatchEvent(new GetDefenseProcessListEvent());
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
