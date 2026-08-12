/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityResourceConsumeEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得城市资源消耗的事件
     *
     */
	public final class GetCityResourceConsumeCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityResourceConsumeEvent = event as GetCityResourceConsumeEvent;
			var delegate:CityDelegate = new CityDelegate( this );
			delegate.getCityResourcesConsume(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			model.cityInfo.cityResource.moneyConsume = rs.result.moneyConsume;
			model.cityInfo.cityResource.foodConsume = rs.result.foodConsume;
			model.cityInfo.cityResource.oilConsume = rs.result.oilConsume;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
