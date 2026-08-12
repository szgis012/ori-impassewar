/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityResourcesOutputEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetCityResourcesOutputCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityResourcesOutputEvent = event as GetCityResourcesOutputEvent;
			var delegate:CityDelegate = new CityDelegate(this);
			delegate.getCityResourcesOutput(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			model.cityInfo.cityResource.woodOutput = data.result.woodOutput;
			model.cityInfo.cityResource.steelOutput = data.result.steelOutput;
			model.cityInfo.cityResource.oilOutput = data.result.oilOutput;
			model.cityInfo.cityResource.foodOutput = data.result.foodOutput;
			model.cityInfo.cityResource.moneyOutput = data.result.moneyOutput;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}