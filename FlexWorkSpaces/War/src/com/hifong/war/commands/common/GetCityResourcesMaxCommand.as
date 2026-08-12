/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityResourcesMaxEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityVO;
	
	import mx.rpc.IResponder;
	
    /**
     * 处理获得城市资源上限的事件
     */
	public final class GetCityResourcesMaxCommand implements ICommand, IResponder
	{
		private var cityInfo:CityVO = ModelLocator.getInstance().cityInfo;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityResourcesMaxEvent = event as GetCityResourcesMaxEvent;
			var delegate:CityDelegate = new CityDelegate( this );
			delegate.getCityResourcesNumMax(cityInfo.cityID);
		}
	     
		public function result(data:Object) : void
		{
			cityInfo.cityResource.foodNumMax = data.result as Number;
			cityInfo.cityResource.woodNumMax = data.result as Number;
			cityInfo.cityResource.steelNumMax = data.result as Number;
			cityInfo.cityResource.oilNumMax =data.result as Number;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
