/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityPopulationEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetCityPopulationCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityPopulationEvent = event as GetCityPopulationEvent;
			var delegate:CityDelegate = new CityDelegate(this);
			delegate.getCityPopulation(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			model.cityInfo.populationFree = data.result.populationFree;
			model.cityInfo.populationTotal = data.result.populationTotal;
			model.cityInfo.populationMax = data.result.populationMax;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showMessage(info.toString());
		}
		
	}
}