/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityBusinessFreeEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	
	import mx.rpc.IResponder;
	
	
    /**
	 * 获得城市空闲商人数量
	 */ 
	public final class GetCityBusinessFreeCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityBusinessFreeEvent = event as GetCityBusinessFreeEvent;
			var delegate:CityDelegate = new CityDelegate( this );
			delegate.getCityBusinessFree(model.cityInfo.cityID);
		}
		
		public function result(data:Object) : void
		{
			model.cityInfo.businessmanFree = data.result;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
