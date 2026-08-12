/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityTaxAndSecurityEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得城市的税率和治安信息的事件
     *
     */
	public final class GetCityTaxAndSecurityCommand implements ICommand, IResponder
	{
		private var cityInfo:CityVO = ModelLocator.getInstance().cityInfo;
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityTaxAndSecurityEvent = event as GetCityTaxAndSecurityEvent;
			var delegate:CityDelegate = new CityDelegate( this );
			delegate.getCityTaxAndSecurity(cityInfo.cityID);
		}

		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			
			cityInfo.tax = rs.result.tax;
			cityInfo.security = rs.result.security;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
