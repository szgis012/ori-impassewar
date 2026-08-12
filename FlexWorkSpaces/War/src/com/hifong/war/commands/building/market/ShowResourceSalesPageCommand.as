/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.market
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.building.MarketDelegate;
	import com.hifong.war.events.building.market.ShowResourceSalesPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class ShowResourceSalesPageCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:ShowResourceSalesPageEvent = event as ShowResourceSalesPageEvent;
			var delegate:MarketDelegate = new MarketDelegate(this);
			delegate.getResourceSalesAmount(evt.cityID,evt.resourceType);
		}
		
		public function result(data:Object) : void
		{
			var salesAmount:int = data.result as int;
			var pageSize:int = ModelLocator.getInstance().marketPageSize;
			var pageNum:int;
	
			if(salesAmount%pageSize!=0){
				pageNum = salesAmount/pageSize + 1;
			}else{
				pageNum = salesAmount/pageSize;
			}
	
			ModelLocator.getInstance().resourceSalesPage = pageNum;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}