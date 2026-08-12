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
	import com.hifong.war.events.building.armory.FinishAllProduceProcessEvent;
	import com.hifong.war.events.common.GetCityOrdnanceListEvent;
	import com.hifong.war.events.common.GetOrdnanceProcessListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	
    /**
     * 处理立即完成所有生产军械的进程(需要一定的道具)
     *
     */
	public final class FinishAllProduceProcessCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:FinishAllProduceProcessEvent = event as FinishAllProduceProcessEvent;
			var delegate:ArmoryDelegate = new ArmoryDelegate( this );
			delegate.finishAllProduceProcess(model.cityInfo.cityID);
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
