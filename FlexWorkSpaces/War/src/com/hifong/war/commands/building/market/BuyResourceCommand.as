/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.market
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.MarketDelegate;
	import com.hifong.war.events.building.market.BuyResourceEvent;
	import com.hifong.war.events.building.market.ShowResourceSalesListEvent;
	import com.hifong.war.events.common.GetCityBusinessFreeEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class BuyResourceCommand implements ICommand, IResponder
	{

		private var model:ModelLocator = ModelLocator.getInstance();
		
		private var cityID:int;

		private var resourceType:int;
		
		private var start:int;
		
		private var offset:int;

		public function execute(event:CairngormEvent) : void
		{
			var evt:BuyResourceEvent = event as BuyResourceEvent;
			cityID = evt.cityID;
			resourceType = evt.resourceType;
			start = evt.start;
			offset = evt.offset;
			
			var delegate:MarketDelegate = new MarketDelegate(this);
			delegate.buyResource(evt.resTradeID,evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();
			
			//更新城市资源信息
			dispatcher.dispatchEvent(new GetCityResourcesEvent(cityID));
			//更新资源列表
			dispatcher.dispatchEvent(new ShowResourceSalesListEvent(cityID,resourceType,start,offset));
			//更新空闲商人数量
			dispatcher.dispatchEvent(new GetCityBusinessFreeEvent());
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}