/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityResourcesEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;

	public final class GetCityResourcesCommand implements ICommand, IResponder
	{

		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityResourcesEvent = event as GetCityResourcesEvent;
			var delegate:CityDelegate = new CityDelegate(this);
			delegate.getCityResourcesNum(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			model.cityInfo.cityResource.woodNum = data.result.woodNum;
			
			model.cityInfo.cityResource.steelNum = data.result.steelNum;
			
			model.cityInfo.cityResource.oilNum = data.result.oilNum;
			
			model.cityInfo.cityResource.foodNum = data.result.foodNum;
			
			model.cityInfo.cityResource.moneyNum = data.result.moneyNum;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
		
	}
}