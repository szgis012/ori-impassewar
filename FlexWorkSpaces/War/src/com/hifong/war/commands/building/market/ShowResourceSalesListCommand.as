/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.building.market
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.commands.SequenceCommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.building.MarketDelegate;
	import com.hifong.war.events.building.market.ShowResourceSalesListEvent;
	import com.hifong.war.events.building.market.ShowResourceSalesPageEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.utils.ObjectProxy;

	public final class ShowResourceSalesListCommand extends SequenceCommand implements ICommand, IResponder
	{

		public override function execute(event:CairngormEvent) : void
		{
			var evt:ShowResourceSalesListEvent = event as ShowResourceSalesListEvent;
			this.nextEvent = new ShowResourceSalesPageEvent(evt.cityID,evt.resourceType);
			var delegate:MarketDelegate = new MarketDelegate(this);
			delegate.getResourceSalesList(evt.cityID,evt.resourceType,evt.start,evt.offset);
			this.executeNextCommand();
		}
		
		public function result(data:Object) : void
		{
			var arrayCollection:ArrayCollection = data.result as ArrayCollection;
			var array:Array = arrayCollection.toArray();
			for(var i:int=0;i<array.length;i++){
				array[i] = new ObjectProxy(array[i]);
			}
			ModelLocator.getInstance().resourceSalesList = new ArrayCollection(array);
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}