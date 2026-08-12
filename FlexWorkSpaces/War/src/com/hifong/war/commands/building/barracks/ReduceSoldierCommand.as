/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.barracks
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.BarracksDelegate;
	import com.hifong.war.events.building.barracks.ReduceSoldierEvent;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理裁减新兵事件
     *
     */
	public final class ReduceSoldierCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		private var reduceNum:int ;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:ReduceSoldierEvent = event as ReduceSoldierEvent;
			reduceNum = evt.reduceNum;
			var delegate:BarracksDelegate = new BarracksDelegate( this );
			delegate.reduceSoldier(model.cityInfo.cityID,reduceNum);
		}
		
		public function result(data:Object) : void
		{
			var result:ResultEvent = data as ResultEvent;
			model.cityInfo.recruitNum -= reduceNum;
			model.cityInfo.populationFree += reduceNum;
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			//更新资源产量信息
			dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
			//更新资源消耗信息
			dispatcher.dispatchEvent(new GetCityResourceConsumeEvent());
			
			MsgBox.showMessage("裁减新兵成功！");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
