/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.market
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.hifong.war.business.building.MarketDelegate;
	import com.hifong.war.events.building.market.TransportResourceEvent;
	import com.hifong.war.events.common.GetCityBusinessFreeEvent;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class TransportResourceCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:TransportResourceEvent = event as TransportResourceEvent;
			var delegate:MarketDelegate = new MarketDelegate(this);
			delegate.transportResouce(evt.resTransportation,evt.cityID,evt.targetCityID);
		}
		
		public function result(data:Object) : void
		{
			var dispatcher:CairngormEventDispatcher = CairngormEventDispatcher.getInstance();

			//更新城市资源信息
			dispatcher.dispatchEvent(new GetCityResourcesEvent(ModelLocator.getInstance().cityInfo.cityID));
			//更新空闲商人数量
			dispatcher.dispatchEvent(new GetCityBusinessFreeEvent());
			
			MsgBox.showMessage("运送资源成功");
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}