/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.treasure
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.TreasureQueueDelegate;
	import com.hifong.war.constant.TreasureCategoryConstant;
	import com.hifong.war.constant.TreasureTypeConstant;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.events.common.GetCityResourcesMaxEvent;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.events.treasure.CancelTreasureQueueEvent;
	import com.hifong.war.events.treasure.GetTreasureQueueListEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.TreasureQueueVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理取消宝物的效果
     *
     */
	public final class CancelTreasureQueueCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		/** 要取消的宝物效果编号*/
		private var treasureQueue:TreasureQueueVO;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:CancelTreasureQueueEvent = event as CancelTreasureQueueEvent;
			this.treasureQueue = evt.treasureQueue;
			var delegate:TreasureQueueDelegate = new TreasureQueueDelegate( this );
			delegate.deleteTreasureQueue(evt.treasureQueue.treasureQueueID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			switch(treasureQueue.category){
				//普通宝物	
				case TreasureCategoryConstant.NORMAL:
					switch(treasureQueue.type){
						//仓库容量加成
						case TreasureTypeConstant.STORAGE_ADD:
							dispatcher.dispatchEvent(new GetCityResourcesMaxEvent());
							break;
						//增加人口上限		
						case TreasureTypeConstant.POPULATION_MAX_ADD:
							dispatcher.dispatchEvent(new GetCityPopulationEvent(model.cityInfo.cityID));
							break;	
					}
					break;
				//生产类宝物
				case TreasureCategoryConstant.RESOURCE_PRODUCTION:
					dispatcher.dispatchEvent(new GetCityResourcesOutputEvent(model.cityInfo.cityID));
					dispatcher.dispatchEvent(new GetCityResourcesEvent(model.cityInfo.cityID));
					break;
			}
			
			//重新加载宝物效果列表
			dispatcher.dispatchEvent(new GetTreasureQueueListEvent());
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
