/*
 Copyright (c) 2008 XI'AN HIFONG Co. All Rights Reserved.
 	
 */

package com.hifong.war.commands.common
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.hifong.war.business.CityDelegate;
	import com.hifong.war.events.common.GetCityByIDEvent;
	import com.hifong.war.model.ModelLocator;
	import com.hifong.war.util.MsgBox;
	import com.hifong.war.vo.CityVO;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.ResultEvent;
	
    /**
     * 处理获得城市信息的事件
     *
     */
	public final class GetCityByIDCommand implements ICommand, IResponder
	{
		private var model:ModelLocator = ModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent) : void
		{
			var evt:GetCityByIDEvent = event as GetCityByIDEvent;
			var delegate:CityDelegate = new CityDelegate( this );
			delegate.getCityByID(evt.cityID);
		}
		
		public function result(data:Object) : void
		{
			var rs:ResultEvent = data as ResultEvent;
			
			model.cityInfo = rs.result as CityVO;
		}
		
		public function fault(info:Object) : void
		{
			MsgBox.showDefaultError(info);
		}
	}
}
